package ipNX;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by _kbluue_ on 12/16/2017.
 */
public class Interface {

    String name, status, protocol, description, location;
    boolean xconnect;
    int runError;

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

    public static void printMap(ArrayList<Interface> map){
        System.out.println((map.get(0).location == null) ? "Location not Set" : map.get(0).location);
        System.out.printf("%-15s%-10s%-10s%-60s\n", "Interface", "Status", "Protocol", "Description");
        for (Interface anInterface : map) System.out.println(anInterface.toString());
    }

    public static void setMapLocation(ArrayList<Interface> map, String location){
        for (Interface anInterface : map) anInterface.setLocation(location);
    }

    public static String getRunCommand(ArrayList<Interface> map){
        String out = "";
        for (Interface anInterface : map) out += ("sh run int " + anInterface.name + "\n");
        return out;
    }

    public static void generateRunCommand(ArrayList<Interface> map,String outFilePath){
        printToFile(getRunCommand(map), outFilePath);
    }

    public static void printToFile(String filePath,String content){
        File file = new File(filePath);
        StringBuilder builder = new StringBuilder(content);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {
            System.err.println("Error Writing File");
        }
    }

    public static void generateRunCommand(ArrayList<Interface> map){
        generateRunCommand(map, "C:\\Users\\_kbluue_\\OneDrive\\Documents\\Uzor\\src\\ipNX\\batchOut.la2");
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
