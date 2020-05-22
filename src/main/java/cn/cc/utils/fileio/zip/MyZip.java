package cn.cc.utils.fileio.zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Yukino
 * 2020/4/26
 */
public class MyZip {

    public static void main(String[] args) {
        MyZip myZip = new MyZip();
        try {
            myZip.zip("D:\\linshi\\a.zip",new File("D:\\linshi\\a.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void zip(String zipFileName, File inputFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName)); // 创建ZipOutputStream类对象
        zip(out, inputFile, "a.txt");//D:\linshi\a.zip 填空   解压后的文件名
        // 调用方法
        System.out.println("压缩中…"); // 输出信息
        out.close(); // 将流关闭
    }

    private void zip(ZipOutputStream out, File inputFile, String path) {

        if(inputFile.isDirectory()){

        }else {
            try {
                out.putNextEntry(new ZipEntry(path));

                FileInputStream fileInputStream = new FileInputStream(inputFile);
                int b; // 定义int型变量
                System.out.println(path);
                while ((b = fileInputStream.read()) != -1) {
                    // 如果没有到达流的尾部
                    out.write(b);
                    // 将字节写入当前ZIP条目
                    }
                    fileInputStream.close(); // 关闭流
                } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
