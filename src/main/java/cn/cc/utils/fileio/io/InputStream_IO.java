package cn.cc.utils.fileio.io;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Yukino
 * 2020/3/12
 */
public class InputStream_IO {

    public static void main(String[] args) {
        File file= new File("D:aaa.txt");
        IO_PrintWriter(file,"中文");
    }
    /**
     * 总结文件下载的方法
     * \r\n 换行
     */

    File file = new File("D:\\1.txt");

    // 给输入流的
    public static void IO_FileOutputStream(File file)throws Exception{
        InputStream in = null;// 各种输入流
        FileOutputStream fo = new FileOutputStream(file);

        /**
         * 以流的方式进行下载
         */
        byte[] buf = new byte[1024];
        int length = 0;
        while ((length = in.read(buf, 0, buf.length)) != -1) {
            fo.write(buf, 0, length);
        }
        in.close();
        fo.close();
    }

    /**
     * https://blog.csdn.net/neweastsun/article/details/80710184
     * 当从InputStream读取文件时，强烈建议使用BufferedInputStream去包装InputStream，用于提升性能。
     使用缓存可以提升性能。read方法每次读一个字节，每次方法调用意味着系统调用底层的文件系统。当JVM调用read()方法时，程序执行上下文将从用户模式切换到内核模式并返回。

     从性能的角度来看，这种上下文切换非常昂贵。当我们读取大量字节时，由于涉及大量上下文切换，应用程序性能将会很差。

     为了读取URL的字节并写至本地文件，需要使用FileOutputStream 类的write方法
     * @param FILE_NAME
     * @param FILE_URL
     */
    public void IO_BufferedInputStream(String FILE_NAME,String FILE_URL) {
        try (
                BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }

            //使用BufferedInputStream，read方法按照我们设置缓冲器大小读取文件。示例中我们设置一次读取1024字节，所以BufferedInputStream 是必要的。
            //
            //上述示例代码冗长，幸运的是在Java7中Files类包含处理IO操作的助手方法。可以使用File.copy()方法从InputStream中读取所有字节，然后复制至本地文件：
            InputStream in1 = new URL(FILE_URL).openStream();
            Files.copy(in1, Paths.get(FILE_NAME), StandardCopyOption.REPLACE_EXISTING);
            //上述代码可以正常工作，但缺点是字节被缓冲到内存中。Java为我们提供了NIO包，它有方法在两个通道之间直接传输字节，而无需缓冲。下面我们会详细讲解。
        } catch (IOException e) {
            // handle exception
        }
    }

    /**
     * 读取一行
     * @throws Exception
     */
    public void IO_BufferedReader()throws Exception{
        BufferedReader reader  = new BufferedReader(new FileReader(file));
        reader.readLine();
        reader.close();
    }

    /**
     * 根据文件路径读取文件内容并返回数据
     * @param path
     * @return
     * @throws Exception
     */
    public  String IO_BufferReader_Content(String path)throws Exception{
        File file = new File(path);
        BufferedReader reader  = new BufferedReader(new FileReader(file));
        String str="";
        StringBuffer content = new StringBuffer("");
        while((str=reader.readLine())!=null) {
            content.append(str);
        }
        reader.close();
        return content.toString();
    }

    /**
     * 系统文件 文件末尾追加
     */
    public static void IO_PrintWriter(File f,String content) {
        FileWriter fw = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            //File f=new File("D:a.txt");
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        // web乱码 printWriter=new PrintWriter(new OutputStreamWriter(new FileOutputStream(ndfFileName), "UTF-8"));
        pw.println(content);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void IO_FileWriter(String fileUrl, String fileNmae,String content)throws Exception {
        FileWriter writer; // 这个在文件末尾追加
        writer = new FileWriter(fileUrl+"\\"+fileNmae, true); //这个会在后面追加
        //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
        writer.write(content);
        writer.write("\r\n");//换行
        //没有解决关闭流的问题
        //writer.close();
    }
    public void IO_BufferedWriter(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append("我是程序猿");
            bw.write("我是");// 往已有的文件上添加字符串
            bw.write("程序猿\n ");
            bw.close();
            fw.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
/*********************/
//处理字符串的五种方法
// https://blog.csdn.net/yangchanghong1995/article/details/81812486
    public void IO_PrintStream(String filePath) {
        try {
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println("我是程序员");// 往文件里写入字符串
            ps.append("我是程序猿");// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
/*******************************************************************************************************************/
//  分类为 读取，下载，写入， 用的时候整理
/*******************************************************************************************************************/


}
