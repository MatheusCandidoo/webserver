import Server.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Server.run();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
