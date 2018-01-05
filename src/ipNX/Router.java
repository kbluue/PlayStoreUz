package ipNX;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by _kbluue_ on 1/4/2018.
 */
public class Router {

    ArrayList<Interface> interfaces;
    private String location, address;
    private AutomatedTelnetClient client = new AutomatedTelnetClient(false);

    Router (ArrayList<Interface> interfaces,String location) {
        this.interfaces = interfaces;
        this.location = location;
    }

    Router (String address){
        this.address = address;
        interfaces = getSubInterfaces(address, false);
    }

    @Override
    public String toString() {
        String out = "";
        out += ((location == null ? "Location not Set" : location) + "\n");
        out += String.format("%-15s%-10s%-10s%-60s\n", "Interface", "Status", "Protocol", "Description");
        for (Interface anInterface : interfaces) out += (anInterface.toString() + "\n");
        return out;
    }

    public void printMap(){
        System.out.println(toString());
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //// TODO: 1/5/2018 refit this method
//    public static void setMapLocation(ArrayList<Interface> map, String location){
//        for (Interface anInterface : map) anInterface.setLocation(location);
//    }

    public String generateRunCommand(){
        String out = "";
        for (Interface anInterface : interfaces) out += ("sh run int " + anInterface.name + "\n");
        return out;
    }

    public void generateRunCommand(String filename){
        printToFile(generateRunCommand(), HB.localPath + "temp\\" + filename);
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

    public ArrayList<Interface> readSource(Scanner src){ //// TODO: 1/4/2018 Annotate lines to determine code function
        boolean interfaceNext = false; //tracker to determine when and what data line is a sub interface
        ArrayList<Interface> interfaces = new ArrayList<>(); //output array of sub-interfaces

        //while loop: reads all the content of the source
        while (src.hasNextLine()){
            String next = src.nextLine(); //gets line for processing
            if (next.equals("")) continue; //empty line: continue
            String[] line = HB.lineTrim(next).split(" ", 4); //removes unnecessary spaces so slpit by single space can function properly

            if (interfaceNext){
                //add interface to arraylist
                interfaces.add(new Interface(line));
            } else if (Objects.equals(line[0], "Interface")) interfaceNext = true; // indication to process next line as sub interface


            // indication that all interfaces have been checked (Assumption been that no interface name will have the char # in it)
            if (line[0].contains("#")) break;
        }

        setLocation(client.location); //set the common location for all the sub interfaces just processed
        return interfaces;
    }

    public ArrayList<Interface> getSubInterfaces(String routerAddress, boolean log){
        //log into router
        if (!client.login(routerAddress)) {
            System.err.printf("Printing of interfaces on %s failed.%n", routerAddress);
            return null;
        }
        String content = client.sendCommand("sh int desc");
        if (log) printToFile("store\\interfaces\\" + routerAddress, content);
        System.out.printf("All interfaces in %s printed%n", client.location);
        client.logout();
        return readSource(new Scanner(content));
    }

}
