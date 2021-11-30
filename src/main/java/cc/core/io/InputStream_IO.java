package cc.core.io;

import cc.constant.ConstantFile;
import cc.core.file.utils.FileUtils;
import cc.resource.PropertiesHeader;
import cc.advanced.web.http.utils.HttpURLConnectionUtil;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Yukino
 * 2020/3/12
 */
public class InputStream_IO {

    /**
     *  BufferedWriter
     */


    /**
     * 总结文件下载的方法
     * \r 回车
     * \n 下一行的这个位置?
     */

    static void main(String[] args) {
//
        //  9902.0
        //  5819.0
        try {
            String url = new String("https://bkimg.cdn.bcebos.com/pic/d1160924ab18972bd407724166856c899e510eb39caa?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5");

            // 7306
            /*InputStream in2 = HttpURLConnectionUtil.getStream(url, "GET", "", Header.bkimgMap());
            InputStreamUtils.downFileByStream(in2,ConstantFile.javaFilePath + "/test","2.png");*/

            // 8792
            /*InputStream in = HttpURLConnectionUtil.getStream(url, "GET", "", Header.bkimgMap());
            nio(in,ConstantFile.javaFilePath + "/test/1.png");*/
            // 连续两次会把流耗尽？

            // nio好想也没发现多快,这些都是小文件，随后试试大文件

            InputStream in3 = HttpURLConnectionUtil.getStream(url, "GET", "", PropertiesHeader.bkimgMap());
            InputStream_IO.copy(in3, ConstantFile.L1_javaFilePath + "/test","3.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * https://blog.csdn.net/neweastsun/article/details/80710184
     * 当从InputStream读取文件时，强烈建议使用BufferedInputStream去包装InputStream，用于提升性能。
     使用缓存可以提升性能。read方法每次读一个字节，每次方法调用意味着系统调用底层的文件系统。当JVM调用read()方法时，程序执行上下文将从用户模式切换到内核模式并返回。
     从性能的角度来看，这种上下文切换非常昂贵。当我们读取大量字节时，由于涉及大量上下文切换，应用程序性能将会很差。
     为了读取URL的字节并写至本地文件，需要使用FileOutputStream 类的write方法
     * @param filePathName
     * @param url
     */
    void IO_BufferedInputStream(String filePathName,String url) {
        // 资源可以自动释放
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream()); FileOutputStream fileOutputStream = new FileOutputStream(filePathName))
        {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            //使用BufferedInputStream，read方法按照我们设置缓冲器大小读取文件。示例中我们设置一次读取1024字节，所以BufferedInputStream 是必要的。
        } catch (IOException e) {
            // handle exception
        }
    }

    static void copy(InputStream inputStream,String filePath,String fileName) throws IOException {
        //上述示例代码冗长，幸运的是在Java7中Files类包含处理IO操作的助手方法。可以使用File.copy()方法从InputStream中读取所有字节，然后复制至本地文件：
        //以下代码可以正常工作，但缺点是字节被缓冲到内存中。Java为我们提供了NIO包，它有方法在两个通道之间直接传输字节，而无需缓冲。下面我们会详细讲解。
        fileName = FileUtils.checkFileName(fileName);
        filePath = FileUtils.checkFilePath(filePath);
        Files.copy(inputStream, Paths.get(filePath + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
    }

    /************************************************ 写 ******************************************************************/
    // 方法太多了，要总结区别

    // PrintStream
    // https://blog.csdn.net/yangchanghong1995/article/details/81812486
    void IO_PrintStream(String filePath) {
        try {
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println("123");// 往文件里写入字符串
            ps.append("123");// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
