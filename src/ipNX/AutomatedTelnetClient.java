package ipNX;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by _kbluue_ on 12/19/2017.
 */
public class AutomatedTelnetClient {

    private TelnetClient telnet = new TelnetClient();
    private InputStream in;
    private PrintStream out;
    private String user, password, location = "";

    public AutomatedTelnetClient(String server, String user, String password) {

        this.user = user;
        this.password = password;

        login(server, true, true);

        System.out.println(sendCommand("terminal length 0"));
    }

    public void login(String address,boolean log,boolean start){
        if (start) {
            try {
                telnet.connect(address, 23);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
        } else write(address);

        write(user);
        write(password);

        String readOut = readUntil("#");

        if (readOut.contains(".")) System.out.println("Error connecting to " + address); //// FIXME: 1/3/2018 Ampersand change (keyboard error)
        else {
            String lines[] = readOut.split("\n");
            location = lines[lines.length - 1];
        }

        if (log) System.out.println(readOut);
    }

    public void login(String address,boolean log){
        login(address, log, false);
    }

    public void login(String address){
        login(address, false, false);
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
            System.out.println(value);
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

    public void printAllInterface(String routerAddress){
        //log into router
        login(routerAddress);
        String content = sendCommand("sh int desc");
        Interface.printToFile("C:\\Users\\_kbluue_\\OneDrive\\Documents\\Uzor\\src\\ipNX\\" + routerAddress, content);
        write("exit");
    }

    public static void main(String[] args) {
//        try {
//            AutomatedTelnetClient telnet = new AutomatedTelnetClient(
//                    "10.163.4.2", "west", "corenetwork");
//            telnet.write("sh int");
//            telnet.readTest();
////            String src = telnet.sendCommand("sh int desc");
////            new RouterRun().readSource(new Scanner(src));
//            telnet.disconnect();
//            System.out.println("DONE");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        System.out.println(Arrays.toString("erfev\neocnv".split("\n")));
//        for (int i = 0; i < 100; i++) {
//            System.out.println(i + " ==> " + (char) i);
//        }
    }

}
