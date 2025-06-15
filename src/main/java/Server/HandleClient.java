package Server;

import Server.Files.FileHandler;
import Server.Headers.HeadersHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HandleClient implements Runnable {
    Socket client;
    String standartPath = "C:\\Users\\mathe\\OneDrive\\Documentos\\projetos\\WebServer\\www\\";
    public HandleClient(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println("Cliente conectado: " + client.getInetAddress());
            OutputStream out = client.getOutputStream();
            String url = getURL();
            String filePath = standartPath + url.replace("/", "\\");
            // Ensure directory paths end with a separator
            if (url.endsWith("/")) {
                filePath = filePath.substring(0, filePath.length() - 1);
            }
            System.out.println("Procurando arquivo: " + filePath);
            FileHandler fileHandler = new FileHandler(filePath);
            String response = new HeadersHandler().getHeaders(fileHandler);
            out.write(response.getBytes(StandardCharsets.UTF_8));
            out.write(fileHandler.getFileBytes());
            out.flush();

            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getURL() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String requestLine = reader.readLine();
        if (requestLine != null && !requestLine.isEmpty()) {
            String[] parts = requestLine.split(" ");
            if (parts.length > 1) {
                return parts[1];
            }
        }
        return "";
    }
}
