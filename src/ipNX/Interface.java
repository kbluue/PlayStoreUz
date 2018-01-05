package ipNX;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by _kbluue_ on 12/16/2017.
 */
public class Interface {

    String name, status, protocol, description, location, locationIP;
    boolean xconnect;
    int runError = 0;

    final static int INVALID_INPUT = 9999;
    final static int AUTHORIZATION = 8888;

    public Interface(String... line){
        if (line.length < 4) return;
        this.name = line[0];
        this.status = line[1];
        this.protocol = line[2];
        this.description = line[3];
        xconnect = false;
    }

    public void isXConfig(){
        xconnect = true;
    }

    public void setXconnect(boolean xconnect){
        this.xconnect = xconnect;
    }

    public boolean getXConnState(){
        return xconnect;
    }

    public void setRunError(int runError) {
        this.runError = runError;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format("%-15s%-10s%-10s%-60s", name, status, protocol, description);
    }
}
