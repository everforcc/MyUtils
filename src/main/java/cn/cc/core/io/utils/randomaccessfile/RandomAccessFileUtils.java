package cn.cc.core.io.utils.randomaccessfile;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author c.c.
 * @date 2020/12/9
 */
public class RandomAccessFileUtils {

    // https://blog.csdn.net/yangchanghong1995/article/details/81812486

    public void WriteStringToFile4(String filePath) throws Exception{
        try {
            RandomAccessFile rf = new RandomAccessFile(filePath, "rw");
            rf.writeBytes("op\r\n");
            rf.writeBytes("app\r\n");
            rf.writeBytes("hijklllll");
            rf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /****************************************/
    /**
     * A方法追加文件：使用RandomAccessFile
     */
    public static void appendMethodA(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
