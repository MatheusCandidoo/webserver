package Server.Files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    private File file;
    private static final String BASE_DIRECTORY = "C:\\Users\\mathe\\OneDrive\\Documentos\\projetos\\WebServer\\www\\";

    public FileHandler(String path) throws IOException {
        normalizePath(path);
    }

    public boolean fileExists() {
        return file.exists();
    }

    public boolean fileIsDirectory() {
        if (file.isDirectory()) {
            File indexFile = new File(file.getPath() + File.separator + "index.html");
            System.out.println("Looking for index.html at: " + indexFile.getPath());
            if (indexFile.exists()) {
                file = indexFile;
                return false;
            }
            return true;
        }
        return false;
    }

    public byte[] getFileBytes() throws IOException {
        if (fileIsDirectory()) {
            return new byte[0];
        }

        return Files.readAllBytes(file.toPath());
    }

    public int getFileSize() throws IOException {
        return getFileBytes().length;
    }

    public String getMimeType() throws IOException {
        String mimeType = Files.probeContentType(file.toPath());
        if (mimeType == null)
            mimeType = "application/octet-stream";
            return mimeType;
    }

    public String getFilePath() {
        return file.getPath();
    }

    private void normalizePath(String path) throws IOException {
        String filePath = BASE_DIRECTORY + path.replace("/", "\\");
        if (filePath.endsWith("/")) {
            filePath = filePath.substring(0, filePath.length() - 1);
        }

        Path normalizedPath = Paths.get(filePath).normalize();
        Path basePath = Paths.get(BASE_DIRECTORY).toAbsolutePath();
        if (!normalizedPath.startsWith(basePath)) {
            throw new IOException("Access denied: Path traversal attempt detected");
        }

        this.file = normalizedPath.toFile();
    }
}
