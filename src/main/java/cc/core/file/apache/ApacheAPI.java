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
        apacheSize();
    }

    /**
     * 计算文件大小，和自己的方法做个对比
     */
    private static void apacheSize(){
        System.out.println(FileUtils.byteCountToDisplaySize(2047L));
    }

    /**
     *  先判断原文件和目标文件是否存在
     */
    private static void apacheCopy(){
        try {
            FileUtils.copyFile(new File(""),new File(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
