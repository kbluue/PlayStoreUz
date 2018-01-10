package ipNX;

import org.jetbrains.annotations.Contract;

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

    final static String[] ROUTER = new String[]{"Abuja_P_01#", "Amazon_BTS#", "Hotoro_BTS#", "IHS_ABJ_003#", "kenazplaza_BTS#",
            "PE_MillPark#", "PE_OldCBN#", "Udo-udoma_BTS#", "Utako_BTS#", "Dugbe_Core_router#", "ODOONA_ELEWE#",
            "PE_AGODI#", "PE_KANO#", "SABONGARI_BTS#", "Sharada", "Hotoro_BTS#", "ABULE-EGBA_BST#", "AGEGE_BST#",
            "Ajah_BST#", "APAPA_II_BST#", "BRAS-03#", "BROADBASE-P-01#", "DC-Access-Router#", "Dolphin_BST#",
            "ERICMOORE-PE#", "HQ_ISG_2#", "HQ_P-ROUTER#", "HQ-BRAS-01#", "HQ-PE_02#", "HQ-PE-01#", "IKORODU_BST#",
            "ILUPEJU_BST#", "IPNX_RACKCENTRE#", "KEFFI_BST#", "Lekki2_BST#", "MARINA-PE-01#", "MARINA-PE-02",
            "MEDALLION-P-01#", "NEW_FESTAC_BST#", "NEW_LEKKI_ROUTER#", "OBA_AKRAN_BST#", "OBALENDE_BST#", "OJODU_BST#",
            "ONIRU_BST#", "Oregun_BST#", "PE_GBAGADA#", "PE_IKEJA_02#", "PE_IKEJA#", "PE_ILASA#", "PE_MEDDALLION#",
            "PE_ORILE#", "PE_VI#", "Shangisha_BST#", "SURULERE_BST#", "YAB-PE-01#", "ELEKAHIA_BTS#", "PE_ELITOR#",
            "P_PHC#", "PE_PHC-01#", "PH_NTA#", "PH_PresidentialBST#", "WOJI_BTS#", "WONODI_BST#", "FIDELITY_AGGR_ROUTER#"},

    ADDRESS = new String[]{"41.184.56.117", "41.184.95.134", "172.29.67.5", "41.184.95.135", "41.184.95.129",
            "62.173.34.136", "41.184.95.130", "41.184.95.133", "41.184.95.132", "62.173.35.250", "41.184.127.133",
            "41.184.127.130", "41.184.111.129", "41.184.110.10", "41.184.110.2", "172.29.67.5", "41.184.62.8",
            "41.184.62.2", "41.184.62.4", "62.173.35.229", "41.184.62.50", "62.173.34.141", "62.173.34.147",
            "41.184.62.7", "41.184.62.24", "62.173.35.244", "62.173.35.101", "62.173.35.246", "62.173.35.251",
            "62.173.34.142", "62.173.35.22", "41.184.62.6", "41.184.62.26", "41.184.62.18", "41.184.62.3",
            "62.173.34.132", "41.184.62.9", "62.173.34.230", "41.184.62.17", "62.173.35.248", "41.184.62.10",
            "41.184.62.21", "41.184.62.1", "62.173.35.252", "41.184.62.14", "62.173.35.249", "41.184.62.19",
            "62.173.34.131", "62.173.34.146", "62.173.34.145", "62.173.35.228", "62.173.34.129", "41.184.62.11",
            "62.173.34.94", "62.173.34.130", "172.18.24.33", "172.18.24.57", "41.184.78.9", "41.184.62.23",
            "172.29.3.5", "41.184.79.129", "41.184.127.131", "172.29.4.129", "10.61.24.249"};

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

    @Contract(pure = true)
    static String coat(String s){
        return "\"" + s + "\"";
    }

}
