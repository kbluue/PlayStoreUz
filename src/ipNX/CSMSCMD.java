import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by _kbluue_ on 2/21/2018.
 */
public class CSMSCMD{

    public static void main(String[] args){
        try {
            printToFile("C:\\Users\\_kbluue_\\OneDrive\\Documents\\Uzor\\src\\ipNX\\links.csms",
                    gather(new Scanner(new String(Files.readAllBytes(Paths.get("" +
                            "C:\\Users\\_kbluue_\\OneDrive\\Documents\\Uzor\\src\\ipNX\\csms.html" +
                            ""))).split("tbody")[1])));
        } catch (IOException  ignored){}
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

    public static void printToFile(String filePath,String content){
        File file = new File(filePath);
        StringBuilder builder = new StringBuilder(content);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error Writing File");
        }
    }

}