package ipNX;

import java.io.File;
import java.io.FileNotFoundException;
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
        updateXConnProp();
    }

    Router (File file){
        try {
            Scanner in = new Scanner(file);
            this.location = in.nextLine();
            this.address = in.nextLine();
            in.nextLine();
            this.interfaces = new ArrayList<>();
            while (in.hasNextLine()){
                this.interfaces.add(new Interface(HB.lineTrim(in.nextLine()).split(" ", 4)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String out = "";
        out += ((location == null ? "Location not Set" : location) + "\n");
        out += ((address == null ? "Location not Set" : address) + "\n");
        out += String.format("%-15s%-10s%-10s%-60s\n", "Interface", "Status", "Layer 2 XConnect", "Description");
        for (Interface anInterface : interfaces) out += (anInterface.toString() + "\n");
        return out;
    }

    public void printXcon(){
        System.out.printf("%-15s%-10s%-10s%-60s\n", "Interface", "Status", "Protocol", "Description");
        for (Interface anInterface : interfaces) if (anInterface.xconnect) System.out.println(anInterface.toString());
    }

    public void printMap(){
        System.out.println(toString());
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public String generateRunCommand(){
        String out = "";
        for (Interface anInterface : interfaces) out += ("sh run int " + anInterface.name + "\n");
        return out;
    }

    public void generateRunCommand(String filename){
        HB.printToFile(HB.localPath + "temp\\" + filename, generateRunCommand());
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
        if (log) HB.printToFile("store\\interfaces\\" + routerAddress, content);
        System.out.printf("All interfaces in %s printed%n", client.location);
        client.logout();
        return readSource(new Scanner(content));
    }

    public void updateXConnProp(){
        //log into router
        if (!client.login(address)) {
            System.err.printf("Printing of interfaces on %s failed.%n", address);
            return;
        }
        XConnRunAction();
        client.logout();
    }

    private void XConnRunAction(){
        client.write(generateRunCommand());
        for (Interface anInterface : interfaces)
            if (client.readUntil(location).contains("xconnect")) anInterface.setXconnect(true);
    }

}
