package ipNX;

import org.apache.commons.net.telnet.TelnetClient;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by _kbluue_ on 12/19/2017.
 */
public class AutomatedTelnetClient {

    String host, userid, password, prefix, recentCommand;
    int port;
    Logger logger;
    TelnetClient client = new TelnetClient();

    public void setAuthenticationInfo(String hostname, String username,     String password) {
        this.host = hostname; // $a1
        this.userid = username; // $a1
        this.password = password; // $a1
        this.logger = Logger.getLogger("TelnetConnection");
        this.logger.setLevel(Level.OFF);
        this.prefix = "AutomatedErrorInjector";
        this.recentCommand = ""; // $a1
        this.port = 23; // $a1
        logger.finest("setting authentication info completed for host=" + host + " port=" + port);
    }
    public int runCommand(String command) throws Exception {

        try {

            client.connect(host, logger, userid, password, port); // $a1

        } catch (Throwable e) {

            throw new Exception("Error connecting to:" + host);

        }

        command = command + " ;echo :CommandRC:$?:CommandRC:";

        String response = sndCmd(command);
    }







    }
