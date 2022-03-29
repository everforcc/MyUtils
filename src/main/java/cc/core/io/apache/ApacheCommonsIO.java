package cc.core.io.apache;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Yukino
 * 2020/3/12
 */
public class ApacheCommonsIO {

    //Apache Commons IO

    // 适用于最普通的操作
    // 另一个高可用的IO操作库是Apache Commons IO。我们从其Javadoc看到FileUtils实用类，用于一般的文件操作任务。从URL下载文件，仅需一行代码：
    public void demol(String fileName,String fileUrl)throws Exception{

        // 普通的还行，但是很多都需要设置请求头
        int CONNECT_TIMEOUT=0;
        int READ_TIMEOUT=0;
        FileUtils.copyURLToFile(
                new URL(fileUrl),
                new File(fileName),
                CONNECT_TIMEOUT,
                READ_TIMEOUT);

        //从性能角度看，与前面JAVA IO示例相同。底层代码使用相同的概念，从InputStream读取一些字节并将它们写入OutputStream。不同之处在于，URLConnection类在这里用于控制连接超时，这样下载就不会阻塞很长时间:
        URLConnection connection = new URL("").openConnection();
        connection.setConnectTimeout(CONNECT_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);

    }


    // 这个异常重新下载是需要的,简而言之就是多了个请求头Range

    //恢复下载
    //
    //考虑到internet连接的不确定性，失败时我们可以重新下载文件，但不是再次从字节0位置下载文件。
    // 让我们重写前面的第一个示例，以添加这个功能。
    //
    //我们首先要知道的是，我们可以使用HTTP HEAD方法从给定URL读取文件的大小，而无需实际下载它:
    public void repaly()throws Exception{
        String fileUrl="";
        URL url = new URL(fileUrl);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        // 设置请求方法
        httpConnection.setRequestMethod("HEAD");

        long removeFileSize = httpConnection.getContentLengthLong();

        //现在我们有了文件内容的总大小，可以检查文件是否已下载了部分内容。如果是，我们将继续从磁盘上记录的最后一个字节开始下载:
        long existingFileSize = 1;//outputFile.length();
        int fileLength=1;

        if (existingFileSize < fileLength) {
            httpConnection.setRequestProperty(
                    //设置请求数据的 坐标
                    "Range", "bytes=" + existingFileSize + "-" + fileLength
            );
        }

        //上述代码我们配置了URLConnection以在一定范围内请求文件内容。范围将从最后下载的字节位置开始，并以远程文件大小的字节长度为结束。
        //
        //使用范围HEAD标识的另一种常见方法是通过设置不同的字节范围以块形式下载文件。例如，要下载2KB文件，我们可以使用范围0 - 1024和1024 - 2048。

        //与前节代码稍微不同的是设置FileOutputStream 方法append参数为true：
        String FILE_NAME="";
        // 从末尾开始下载
        OutputStream os = new FileOutputStream(FILE_NAME, true);


        //在我们做了这个更改之后，其余的代码与我们前面看到的代码一样。
        //总结
        //
        //在本文中，我们已经看到Java中从URL下载文件的几种实现方式。
        //最常见的实现是在执行读/写操作时使用缓冲区。这个实现即使对于大文件也是安全的，因为我们没有将整个文件加载到内存中。
        //我们还了解了如何使用Java NIO通道实现零拷贝下载。这很有用，因为它最小化了在读取和写入字节时执行的上下文切换的次数，并且通过使用直接缓冲区字节不会加载到应用程序内存中。另外，由于下载文件通常是通过HTTP完成的，我们也说明如何使用AsyncHttpClient库实现这一点。

    }

}
