package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HandleClient implements Runnable {
    Socket client;
    public HandleClient(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println("Cliente conectado: " + client.getInetAddress());

            OutputStream out = client.getOutputStream();
            String response = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nHello World!";
            out.write(response.getBytes(StandardCharsets.UTF_8));
            out.flush();

            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
