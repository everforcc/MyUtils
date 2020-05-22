package cn.cc.utils.fileio.io;

import org.asynchttpclient.*;

import java.io.FileOutputStream;

/**
 * Yukino
 * 2020/3/12
 */
public class AsyncHTTPClient {

    //上面我们已经使用java 核心功能实现从URL下载文件。当无需调整性能是，我们也可以利用第三方库轻松实现。举例，在实际场景中，需要实现异步下载，我们可以封装逻辑至Callable，下面看已有库实现。
    //
    //
    //
    //Async HTTP Client
    //
    //Async HTTP Client是使用Netty框架执行异步HTTP请求的流行库。我们使用它对URL文件执行GET请求并获取其内容。首先需要创建HTTP client：
    public void model()throws Exception {

        String FILE_NAME = "";
        String FILE_URL = "";
        AsyncHttpClient client = Dsl.asyncHttpClient();
        //下面内容放到FileOutputStream：
        final FileOutputStream stream = new FileOutputStream(FILE_NAME);

        client.prepareGet(FILE_URL).execute(new AsyncCompletionHandler<FileOutputStream>() {

            @Override
            public State onBodyPartReceived(HttpResponseBodyPart bodyPart)
                    throws Exception {
                //是从内部类访问的，需要声明为final吗
                stream.getChannel().write(bodyPart.getBodyByteBuffer());
                return State.CONTINUE;
            }

            @Override
            public FileOutputStream onCompleted(Response response)
                    throws Exception {
                return stream;
            }
        });

    }


    //上面我们覆盖了onBodyPartReceived() 方法。缺省实现是累加HTTP 接收块至ArrayList，这可能导致耗费高内存或下载大文件时OutOfMemory 异常。
    //代替累加每个HttpResponseBodyPart 至内存，我们使用FileChannel写字节至本地文件。getBodyByteBuffer()方法通过ByteBuffer访问bodyPart内容。ByteBuffers的优势是把内存分配到JVM堆之外，所以不会影响应用程序的内存。

}
