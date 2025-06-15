package Server.Files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileHandler {
    File file;

    public FileHandler(String path) {
        this.file = new File(path);
    }


    public boolean fileExists(){
        return file.exists();
    }

    public boolean fileIsDirectory(){
        System.out.println("Checking if directory: " + file.getPath());
        System.out.println("Is directory: " + file.isDirectory());
        if(file.isDirectory()){
            File indexFile = new File(file.getPath() + File.separator + "index.html");
            System.out.println("Looking for index.html at: " + indexFile.getPath());
            System.out.println("Index.html exists: " + indexFile.exists());
            if(indexFile.exists()){
                file = indexFile;
                return false;
            }
            return true;
        }
        return false;
    }

    public byte[] getFileBytes() throws IOException {
        if(fileIsDirectory()){
            return new byte[0];
        }

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

    public String getFilePath() {
        return file.getPath();
    }
}
