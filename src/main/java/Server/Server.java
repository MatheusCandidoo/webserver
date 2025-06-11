package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void run() throws IOException {
        ServerSocket server = new ServerSocket(80);
        System.out.println("Servidor rodando na porta "+ server.getLocalPort());

        while (true) {
            System.out.println("Aguardando conexão");
            Socket client = server.accept();
            new Thread(new HandleClient(client)).start();
        }
    }
}
