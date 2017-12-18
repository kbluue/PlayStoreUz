package ipNX;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by _kbluue_ on 12/16/2017.
 */
public class RouterRun {

    public static void main(String[] args){
        try {
            new RouterRun().readSource(new Scanner(new File("C:\\Users\\_kbluue_\\OneDrive\\Documents\\Uzor\\src\\putty.log")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String lineTrim(String line){
        String[] words = line.split(" ");
        String out = "";
        for (String word : words) {
            if (!Objects.equals(word, "")) out += (word + " ");
        }
        return out;
    }

    public void readSource(Scanner src){
        boolean interfaceNext = false;
        ArrayList<Interface> interfaces = new ArrayList<>();

        while (src.hasNextLine()){
            String next = src.nextLine();
            if (next.equals("")) continue;
            String[] line = lineTrim(next).split(" ", 4);

            if (interfaceNext){
                //add interface to arraylist
                interfaces.add(new Interface(line));
            }

            if (Objects.equals(line[0], "Interface")) interfaceNext = true;

            if (line[0].contains("#") && !line[0].contains("##")){
                String location = line[0].split("#")[0];

                if (interfaceNext) //add location to interfaces at once
                interfaceNext = false;

                if (line.length == 1) return;
            }

            // read for "sh run int command"
            if (line[1].contains("run") || line[2].contains("run")) ;
        }

        for (Interface i : interfaces) {
            System.out.println(i.toString());
        }
    }
}
