package cn.cc.utils.fileio.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;

public class DownFile {

    /**
     * \r\n 换行
     * 没有解决关闭流的问题
     *
     *
     */

    private String fileNmae;
    private String fileUrl;

    private PrintStream printStream;
    //private FileOutputStream fileOutputStream=new FileOutputStream(fileUrl+"\\" +fileNmae);      //这个会重新开始写入,先把文件置空;
    //private String fileNmae=new PrintStream(fileOutputStream);
    public static FileWriter writer; // 这个在文件末尾追加
    private DownFile() {

    }





/*********************/
//处理字符串的五种方法
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
