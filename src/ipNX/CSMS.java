package ipNX;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by _kbluue_ on 12/16/2017.
 */
public class CSMS{

    public static void main(String[] args){
        System.out.println(HB.readFromWWW("62.173.34.213/csms/view_customers"));
//        try {
//            HB.printToFile("C:\\Users\\_kbluue_\\OneDrive\\Documents\\Uzor\\src\\ipNX\\links.csms",
//                    gather(new Scanner(new String(Files.readAllBytes(Paths.get("" +
//                            "C:\\Users\\_kbluue_\\OneDrive\\Documents\\Uzor\\src\\ipNX\\source.csms" +
//                            ""))).split("tbody")[1])));
//        } catch (IOException  ignored){}
    }

    public static String gather(Scanner in){
        String format = "%-10s%-35s%-120s%-20s%-20s%-20s%-20s\n";
        String out = String.format(format, "SN ID","Circuit ID", "Customer Name", "Territory", "Service Type", "Network Platform",
                "Link State");
        in.nextLine();
        while (in.hasNextLine()){
            String link[] = extract(in);
            if (link[0] != null) out += String.format(format, link);
        }
        return out;
    }

    private static String[] extract(Scanner in){
        String out[] = new String[7], line = in.nextLine();
        try {
            out[0] = line.split("\'")[1].substring(22);
            out[1] = line.split("\'")[2].split("<")[0].substring(1);
            for (int i = 2; i < 7; i++) out[i] = in.nextLine().split(">")[(i > 2 ? 1 : 2)].split("<")[0];
        } catch (ArrayIndexOutOfBoundsException ignored){}
        return out;
    }

}