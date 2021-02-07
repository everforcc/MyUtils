package cn.cc.core.file.zip;

// compression/GZIPcompress.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {java GZIPcompress GZIPcompress.java}
// {VisuallyInspectOutput}

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author onjava8
 * @date 2020/12/15
 */
public class GZIPcompress {

    // 最简单的 gzip

    static String path = "E:\\java\\onjava8\\gzip\\Hello.txt";
    static String targetGZFile = "E:\\java\\onjava8\\gzip\\test.gz";

    public static void main(String[] args) {
        write();
        Reading();
    }

    static void write(){
        if (path==null||"".equals(path)) {
            System.out.println("Usage: \nGZIPcompress file\n\tUses GZIP compression to compress  the file to test.gz");
            /**
             1.正常退出
             status为0时为正常退出程序，也就是结束当前正在运行中的java虚拟机。
             2.非正常退出
             status为非0的其他整数（包括负数，一般是1或者-1），表示非正常退出当前程序。
             */
            System.exit(1);
        }

        try ( //  资源自动释放
              InputStream in = new BufferedInputStream(new FileInputStream(path));
              // 和普通流的区别是使用 GZIP包裹
              BufferedOutputStream out = new BufferedOutputStream(
                      new GZIPOutputStream(new FileOutputStream(targetGZFile)));
        ) {
            System.out.println("Writing file");
            int c;
            // 默认多少
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void Reading(){
        System.out.println("Reading file");
        try (
                BufferedReader in2 = new BufferedReader(
                        new InputStreamReader(new GZIPInputStream(new FileInputStream(targetGZFile))));
        ) {
            in2.lines().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


