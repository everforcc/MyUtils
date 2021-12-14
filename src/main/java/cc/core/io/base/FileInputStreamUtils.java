package cc.core.io.base;

import java.io.*;

public class FileInputStreamUtils {

    private static int k = 1024;

    public static void main(String[] args) {
        //
        try {
            String file_1 = "C:/test/io/1.txt";
            String file_2 = "C:/test/io/2.txt";
            String content = "测试\r\n测试\n测试\r测试";
            // 1.
            printStream(file_1,content,true);
            // 2.
            bufferedInputStream(file_2,new FileInputStream(new File(file_1)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public static void printStream(String filePath,String content) {
        printStream(filePath,content,false);
    }
    public static void printStream(String filePN,String content,boolean flag) {
        try (PrintStream ps = new PrintStream(new FileOutputStream(filePN,flag));)
        {
            //ps.print(true); // 不换行
            ps.print(content); // 全部交给输入自己控制
            //ps.println("println");// 换行
            //ps.append("append");// 在已有的基础上追加
            //ps.printf("[%s]","格式化内容"); //格式化
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * https://blog.csdn.net/neweastsun/article/details/80710184
     * 当从InputStream读取文件时，强烈建议使用BufferedInputStream去包装InputStream，用于提升性能。
     使用缓存可以提升性能。read方法每次读一个字节，每次方法调用意味着系统调用底层的文件系统。当JVM调用read()方法时，程序执行上下文将从用户模式切换到内核模式并返回。
     从性能的角度来看，这种上下文切换非常昂贵。当我们读取大量字节时，由于涉及大量上下文切换，应用程序性能将会很差。
     为了读取URL的字节并写至本地文件，需要使用FileOutputStream 类的write方法
     * @param filePN
     * @param inputStream
     */
    public static void bufferedInputStream(String filePN,InputStream inputStream) {
        // 资源可以自动释放
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(filePN)){
            // 重复操作
            StreamOutputUtils.inToOut(bufferedInputStream,fileOutputStream);
        } catch (IOException e) {
            // handle exception
        }
    }

}
