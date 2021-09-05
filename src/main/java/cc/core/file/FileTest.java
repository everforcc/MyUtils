package cc.core.file;

import cc.constant.ConstantFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author c.c.
 * @data 2020/12/11
 */
public class FileTest {
    public static void main(String[] args) {
        fileByte();
        //System.out.println(Arrays.toString("/".getBytes()));
        // https://www.cnblogs.com/freeliver54/archive/2012/07/30/2615149.html
        // System.out.printf(Integer.toHexString(b & 0xFF));
    }

    private final static String testFilePath = ConstantFile.javaFilePath + "";
    private final static File file = new File(testFilePath);

    private static void fileByte(){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Set<Byte> set = new HashSet<>();
            int length = 0;
            byte[] buf = new byte[10];
            // 这里得到的是ASCII码
            while ((length = fileInputStream.read(buf, 0, buf.length)) != -1) {
                for(byte b:buf){
                    System.out.printf("%o",b);
                    System.out.printf(" >> ");
                    //System.out.printf(Integer.toHexString(b));
                    System.out.printf("%d",b);
                    System.out.printf(" >> ");
                    System.out.printf("%x",b);
                    System.out.println(" >> " + b);
                }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 总结常见的类型，和别人总结好的类型，然后对比 识别文件类型
    private static int[] JPG_INT = {0xff,0xd8};
    //private static byte[] JPG_BYTE = {-1,-40};

}
