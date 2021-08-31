package cc.core.file.apache;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author c.c.
 * @date 2021/1/21
 */
public class ApacheAPI {

    // 看下 apache 的源码
    public static void main(String[] args) {
        t1();
    }

    static void t1(){
        // 计算文件大小的
        System.out.println(FileUtils.byteCountToDisplaySize(2047L));
    }

    static void t2(){
        try {
            // 判断是否是 文件以及目标文件是否存在的
            FileUtils.copyFile(new File(""),new File(""));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
