package cc.core.file.apache;

import cc.sysconstant.ConstantCharSet;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    private static void readLine(File file){

        try {

            // 1.
            List<String> lines = FileUtils.readLines(file, ConstantCharSet.UTF_8);

            // 2.
            final int pagesize = 1000;
            //List<BillWeixin> billList = new ArrayList<>(pagesize);
            try (LineIterator it = FileUtils.lineIterator(file, "UTF-8")) {
                while (it.hasNext()) {
                    String line = it.nextLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
