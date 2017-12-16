package ipNX;

/**
 * Created by _kbluue_ on 12/16/2017.
 */
public class Interface {

    String name, status, protocol, description, location;
    boolean xconnect;
    int runError;

    final int INVALID_INPUT = 9999;
    final int AUTHORIZATION = 8888;

    public Interface(String name, String status, String protocol, String description) {
        this.name = name;
        this.status = status;
        this.protocol = protocol;
        this.description = description;
        xconnect = false;
    }

    public void isXConfig(){
        xconnect = true;
    }

    public void setXconnect(boolean xconnect){
        this.xconnect = xconnect;
    }

    public boolean getXConnState(){
        return xconnect;
    }

    public void setRunError(int runError) {
        this.runError = runError;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format("");
    }
}
