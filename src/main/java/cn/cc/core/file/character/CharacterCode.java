package cn.cc.core.file.character;

import cn.cc.core.file.utils.ConstantCharSet;
import org.apache.tools.ant.filters.StringInputStream;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author c.c.
 * @date 2020/12/14
 */
public class CharacterCode {

    // 用空写个工具类，相互转换

    // 所有进制，总结一下

    // 春节 ，“春”和“节”的utf-8编码分别是“E6 98 A5”和“E8 8A 82”，
    // 春”和“节”的GB2312编码（我的操作系统“Windows XP”中文版的默认编码）分别是“B4 BA”和“BD DA”
    public static void main(String[] args) {
        try {
            InputStream inputStream = new StringInputStream("严", "Unicode");
            int length = 0;
            byte[] buf = new byte[8];
            while ((length = inputStream.read(buf, 0, buf.length)) != -1) {
                // 天天用，还不是很理解，read看看源码
                for(byte b:buf){
                    // 直接格式化输出
                    // System.out.printf("%x",b);
                    // 转换16进制
                    // System.out.printf(" >> " + Integer.toHexString(b) + " >> ");
                    // 基础太差了，原来没学过，要补习
                    // https://www.cnblogs.com/freeliver54/archive/2012/07/30/2615149.html
                    System.out.printf(Integer.toHexString(b & 0xFF));
                    System.out.println(" >> " + b);
                }
                //System.out.printf("%x",buf);
                //System.out.printf("%x",buf);
                break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
