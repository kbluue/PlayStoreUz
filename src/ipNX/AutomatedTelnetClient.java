package ipNX;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by _kbluue_ on 12/19/2017.
 */
public class AutomatedTelnetClient {

    private TelnetClient telnet = new TelnetClient();
    private InputStream in;
    private PrintStream out;
    private String prompt = "#", user, password;

    public AutomatedTelnetClient(String server, String user, String password) {

        this.user = user;
        this.password = password;

        try {
            // Connect to the specified server
            telnet.connect(server, 23);

            // Get input and output stream references
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());

            // Log the user on
            readUntil("Username: ");
            write(user);
            readUntil("Password: ");
            write(password);
            sendCommand("terminal length 0");

            // Advance to a prompt
            readUntil(prompt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();
            while (true) {
                System.out.print(ch);
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
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendCommand(String command) {
        try {
            write(command);
            return readUntil(prompt);
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

    public void printAllInterface(String location){
        //log into router
        sendCommand(location);
        readUntil(": ");
        write(user);
        readUntil("Password: ");
        String control = sendCommand(password);
        if (control.contains("% Auth")) return;
        String content = sendCommand("sh int desc");
        Interface.printToFile("C:\\Users\\_kbluue_\\OneDrive\\Documents\\Uzor\\src\\ipNX\\" + location, content);
        sendCommand("exi");
    }

    public static void main(String[] args) {
        try {
            AutomatedTelnetClient telnet = new AutomatedTelnetClient(
                    "10.163.4.2", "west", "corenetwork");
            String[] locations = RouterRun.getArray();
            for (String location : locations) {
                telnet.printAllInterface(location);
            }
//            String src = telnet.sendCommand("sh int desc");
//            new RouterRun().readSource(new Scanner(src));
            telnet.disconnect();
            System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
