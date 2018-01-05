package ipNX;

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

}
