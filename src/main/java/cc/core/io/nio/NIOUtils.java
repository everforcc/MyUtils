package cc.core.io.nio;

import cc.sysutils.Print_Record;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Date;

/**
 * @author c.c.
 * @date 2020/12/9
 */
public class NIOUtils {

    private static Print_Record print_record = Print_Record.getInstanse();

    public static void main(String[] args) {
    }

    /**
     *  https://blog.csdn.net/neweastsun/article/details/80710184
     * java NIO包提供了无缓冲情况下在两个通道之间直接传输字节的可能。
     * @param inputStream
     * @param fileName
     * @throws IOException
     */
    public static void nio(InputStream inputStream, String fileName) throws IOException {
        Date begindate = new Date();

        //为了读来自URL的文件，需从URL流创建ReadableByteChannel
        ReadableByteChannel readableByteChannel = Channels.newChannel(inputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        FileChannel fileChannel = fileOutputStream.getChannel();
        //然后使用transferFrom方法，从ReadableByteChannel 类下载来自URL的字节传输到FileChannel：
        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        //transferTo() 和 transferFrom() 方法比简单使用缓存从流中读更有效。依据不同的底层操作系统，数据可以直接从文件系统缓存传输到我们的文件，而不必将任何字节复制到应用程序内存中。
        //在Linux和UNIX系统上，这些方法使用零拷贝技术，减少了内核模式和用户模式之间的上下文切换次数。

        Date enddate = new Date();
        double time = enddate.getTime() - begindate.getTime();
        print_record.println(fileName + "下载耗时:" + time);
    }

}
