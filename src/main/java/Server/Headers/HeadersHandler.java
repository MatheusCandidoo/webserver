package Server.Headers;

import java.io.IOException;
import java.time.LocalDateTime;

import Server.Files.FileHandler;


public class HeadersHandler {

    public String getHeaders(FileHandler fileHandler) throws IOException {
        String status = "HTTP/1.1 200 OK\n";
        if(!fileHandler.fileExists())
            status =  "HTTP/1.1 404 Not Found\r\nContent-Length: 0\r\n\r\n";

        if(fileHandler.fileIsDirectory()){
           status = "HTTP/1.1 301 Moved Permanently\r\nLocation: /index.html\r\n\r\n";
        }

        String mimeType = fileHandler.getMimeType();
        // Ensure HTML files are served with the correct content type
        if (fileHandler.getFilePath().toLowerCase().endsWith(".html")) {
            mimeType = "text/html";
        }

        return status+
                "Content-Type: " + mimeType + "\r\n" +
                "date: "+ LocalDateTime.now()+"\r\n"+
                "Content-Length: " +fileHandler.getFileSize() + "\r\n" +
                "Connection: close\r\n"+
                "\r\n";
    }
}
