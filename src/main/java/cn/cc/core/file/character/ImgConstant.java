package cn.cc.core.file.character;

import cn.cc.core.io.utils.PrintWriterUtils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author c.c.
 * @date 2020/12/11
 */
public class ImgConstant {

    // 删除

    // 字符文件编码 cpdetector EF BB BF

    // 根据前缀 浏览器收藏和桌面保存
    // 图像类型识别模块

    public static void main(String[] args) {
        File file = new File("E:\\java\\test\\rn.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Set<Byte> set = new HashSet<>();
            int length = 0;
            byte[] buf = new byte[3];
            while ((length = fileInputStream.read(buf, 0, buf.length)) != -1) {
                // 天天用，还不是很理解，read看看源码
                for(byte b:buf){
                    //System.out.println(b);
                    /*set.add(b);
                    PrintWriterUtils.fileWriter("E:\\java\\byte","head.txt",b + "");*/
                    System.out.printf("%x",b);
                    System.out.printf(" >> ");
                    System.out.printf(Integer.toHexString(b));
                    System.out.printf(" >> ");
                    // 基础太差了，原来没学过，要补习
                    // https://www.cnblogs.com/freeliver54/archive/2012/07/30/2615149.html
                    System.out.printf(Integer.toHexString(b & 0xFF));
                    System.out.println(" >> " + b);
                }
                //System.out.printf("%x",buf);
                //System.out.printf("%x",buf);
                break;
            }
            System.out.println(set.size());

            System.out.println(JPG_INT[0]);
            System.out.println(JPG_INT[1]);
            /*System.out.println(JPG_BYTE[0]);
            System.out.println(JPG_BYTE[1]);*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 总结常见的类型，和别人总结好的类型，然后对比 识别文件类型
    private static int[] JPG_INT = {0xff,0xd8};
    //private static byte[] JPG_BYTE = {-1,-40};


}
