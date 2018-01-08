package ipNX;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Created by _kbluue_ on 1/5/2018.
 */
public class HB {

    final static String localPath = Paths.get("").toAbsolutePath().toString();

    public static String lineTrim(String line){
        String[] words = line.split(" ");
        String out = "";
        for (String word : words) {
            if (!Objects.equals(word, "")) out += (word.trim() + " ");
        }
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
