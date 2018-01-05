package ipNX;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ConnectException;
import java.nio.file.Paths;

/**
 * Created by _kbluue_ on 12/19/2017.
 */
public class AutomatedTelnetClient {

    private TelnetClient telnet = new TelnetClient();
    private InputStream in;
    private PrintStream out;
    private String user, password;
    String location;
    final String localPath = Paths.get("").toAbsolutePath().toString();

    public AutomatedTelnetClient(String server, String user, String password) {

        this.user = user;
        this.password = password;

        if (login(server, true, true)) System.out.println(sendCommand("terminal length 0"));
        else System.exit(2);
    }

    public AutomatedTelnetClient(boolean silent){
        user = "west";
        password = "corenetwork";

        if (login("10.163.4.2", !silent, true)) sendCommand("terminal length 0");
        else if (login("10.163.64.2", !silent, true)) sendCommand("terminal length 0");
        else System.exit(2);
    }

    public boolean login(String address,boolean log,boolean start){
        if (start) {
            try {
                telnet.connect(address, 23);
            } catch (ConnectException e){
                System.err.println("Connection timed out");
                System.exit(2);
            }catch (IOException e) {
                e.printStackTrace();
            }
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
        } else {
            write(address);
            readUntil("\n");
            String nLine =  readUntil("\n");

            if (!nLine.contains("Open")){
                System.out.printf("Error connecting to %s%n", address);
                return false;
            }
        }

        write(user);
        write(password);

        String readOut = "";
        boolean introDone = false, introStarted = false;

        while (!introDone){
            String line = readUntil("\n");
            readOut += line;

            if (introStarted && !line.contains("##")) introDone = true;
            else if (line.contains("##")) introStarted = true;
        }

        for (int i = 0; i < (start ? 3 : 6); i++) {
            readOut += readUntil("\n");
        }

        try {
            char control = (char) in.read();
            if (control == '%') {
                System.err.println("Wrong Credentials");
                return false;
            } else readOut += control;
        } catch (IOException e) {
            e.printStackTrace();
        }

        readOut += readUntil("#");
        String lines[] = readOut.split("\n");
        location = lines[lines.length - 1];

        if (log) System.out.println(readOut);

        return true;
    }

    public boolean login(String address,boolean log){
        return login(address, log, false);
    }

    public boolean login(String address){
        return login(address, false);
    }

    public void logout() {
        write("exit");
        String content[] = readUntil("#").split("\n");
        location = content[content.length - 1];
    }

    public String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();
            while (true) {
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String value) {
        try {
            out.println(value);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendCommand(String command) {
        try {
            write(command);
            return readUntil(location);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disconnect() {
        try {
            telnet.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
