package cc.core.io.base;

import cc.sysconstant.ConstantCharSet;
import cc.sysconstant.ConstantFile;
import cc.core.file.utils.FileUtils;

import java.io.*;

/**
 * @author c.c.
 * @date 2020/12/9
 */
public class PrintWriterUtils {
    /**
     * 写
     * 1.1. printWriter 只有write没有read
     * 1.2. fileWriter
     * 1.3. bufferedWriter
     *
     * 读
     * 2.1 bufferReader
     * 2.2 fileReader
     */
    public static void main(String[] args) {
        bufferedWriter(ConstantFile.L1_javaFilePath + "/1.txt", ConstantCharSet.UTF_8,"123");
    }

    /**
     * 系统文件 文件末尾追加，不用指定编码默认UTF-8
     */
    public static void printWriter(String filePath, String fileName, String content){
        // 文件名校验
        fileName = FileUtils.checkFileName(fileName);
        filePath = FileUtils.checkFilePath(filePath);
        printWriter(new File(filePath + File.separator + fileName),content);
    }
    public static void printWriter(String filePath, String content) {
        printWriter(new File(filePath),content);
    }
    public static void printWriter(File f,String content) {

        // 乱码处理
        // new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), encoding));
        // web乱码 printWriter=new PrintWriter(new OutputStreamWriter(new FileOutputStream(ndfFileName), ConstantCharSet.UTF_8));

        //如果文件存在，则追加内容；如果文件不存在，则创建文件
        // 据说 new BufferedWriter( 包装后能缓冲加速
        try (FileWriter fw = new FileWriter(f, true);
             PrintWriter pw = new PrintWriter(new BufferedWriter(fw));)
        {
            pw.print(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 示例代码建议使用 printWriter
     * 单独使用 FileWriter
     * @param file
     * @param content
     */
    public void fileWriter(File file, String content){

        //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
        try (FileWriter writer = new FileWriter(file, true);){
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定编码
     * char数组
     * new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), encoding));
     * @param filePath
     * @param charSet
     * @param content
     */
    public static void bufferedWriter(String filePath,String charSet,String content) {
        char[] chars = new char[100];
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), charSet));){
            bw.append("追加");
            bw.write(content); // 往已有的文件上添加字符串，手动确定是否换行
            bw.write(chars); // chars数组
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
