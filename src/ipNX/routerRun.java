package ipNX;

/**
 * Created by _kbluue_ on 12/16/2017.
 */
public class RouterRun{





    public static void main(String[] args){
//        try {
//            new RouterRun().readSource(new Scanner(new File("C:\\Users\\_kbluue_\\OneDrive\\Documents\\Uzor\\src\\putty.log")));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        Router dolphin = new Router("41.184.62.7");
//        dolphin.generateRunCommand("dolphin test");
//        System.out.println(dolphin.toString());

        for (String s : HB.ADDRESS) {
            Router router = new Router(s);
            String location = HB.localPath + "\\routerStore\\" + router.getLocation() + ".rtr";
            HB.printToFile(location, router.toString());
        }
    }


}
