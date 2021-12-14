package cc.core.io.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamOutputUtils {

    private static int k = 1024;

    public static void inToOut(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte dataBuffer[] = new byte[k];
        int bytesRead;
        while ((bytesRead = inputStream.read(dataBuffer, 0, k)) != -1) {
            outputStream.write(dataBuffer, 0, bytesRead);
        }
    }

}
