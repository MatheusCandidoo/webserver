package Server.Headers;

import Server.Body.FileHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class HeadersHandler {

    public String getHeaders(FileHandler fileHandler) throws IOException {
        String status = "HTTP/1.1 200 OK\n";
        if(!fileHandler.fileExists())
            status =  "HTTP/1.1 404 Not Found\r\nContent-Length: 0\r\n\r\n";

        return status+
                "Content-Type: "+fileHandler.getMimeType()+"\r\n" +
                "date: "+ LocalDateTime.now()+"\r\n"+
                "Content-Length: " +fileHandler.getFileSize() + "\r\n" +
                "Connection: close\r\n"+
                "\r\n";
    }
}
