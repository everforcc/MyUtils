package cn.cc.utils.fileio.io;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * Yukino
 * 2020/3/12
 */
public class NIO {

    /**
     * 使用NIO
     * @param url
     * @throws Exception
     */
    public void WriteStringToFile5(URL url, String FILE_NAME)throws Exception{
        //java NIO包提供了无缓冲情况下在两个通道之间直接传输字节的可能。
        //
        //为了读来自URL的文件，需从URL流创建ReadableByteChannel
        ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
        //从ReadableByteChannel 读取字节将被传输至FileChannel:
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
        FileChannel fileChannel = fileOutputStream.getChannel();
        //然后使用transferFrom方法，从ReadableByteChannel 类下载来自URL的字节传输到FileChannel：
        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        //transferTo() 和 transferFrom() 方法比简单使用缓存从流中读更有效。依据不同的底层操作系统，数据可以直接从文件系统缓存传输到我们的文件，而不必将任何字节复制到应用程序内存中。
        //在Linux和UNIX系统上，这些方法使用零拷贝技术，减少了内核模式和用户模式之间的上下文切换次数。
    }

}
