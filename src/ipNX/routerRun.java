package ipNX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by _kbluue_ on 12/16/2017.
 */
public class RouterRun {

    public static void main(String[] args){
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
            String[] line = lineTrim(src.nextLine()).split(" ");

            if (interfaceNext){
                //add interface to arraylist
            }

            if (line[0] == "Interface") interfaceNext = true;
        }
    }
}
