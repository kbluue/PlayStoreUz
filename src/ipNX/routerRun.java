package ipNX;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by _kbluue_ on 12/16/2017.
 */
public class RouterRun {


    public static void main(String[] args){
//        try {
//            new RouterRun().readSource(new Scanner(new File("C:\\Users\\_kbluue_\\OneDrive\\Documents\\Uzor\\src\\putty.log")));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        Router dolphin = new Router("41.184.62.7");
        dolphin.generateRunCommand("dolphin test");
        System.out.println(dolphin.toString());

    }

}
