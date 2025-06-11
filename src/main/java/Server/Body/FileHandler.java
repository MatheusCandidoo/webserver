package Server.Body;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileHandler {
    File file;

    public FileHandler(String path) {
        this.file = new File(path);
    }


    public boolean  fileExists(){
        return file.exists() && !file.isDirectory();
    }

    public byte[] getFileBytes() throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    public int getFileSize() throws IOException {
        return getFileBytes().length;
    }

    public String getMimeType() throws IOException {
        String mimeType = Files.probeContentType(file.toPath());
        if (mimeType == null) mimeType = "application/octet-stream";
        return mimeType;
    }
}
