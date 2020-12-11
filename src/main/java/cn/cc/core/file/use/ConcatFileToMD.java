package cn.cc.core.file.use;

import cn.cc.core.file.utils.FileUtils;
import cn.cc.core.file.utils.IFileUtils;
import cn.cc.core.file.utils.MDUtils;
import cn.cc.core.io.utils.PrintWriterUtils;

import java.io.File;

/**
 * @author c.c.
 * @date 2020/12/10
 */
public class ConcatFileToMD implements IFileUtils {

    // 合并多个文件，例如按照章节下载的小说
    public static void main(String[] args) {
        // 叛逆的鲁鲁修[001][STAGE-0：Interval]--1.txt 7,10 11,length-7
        // 001叛逆的鲁鲁修[STAGE-0：Interval]--1.txt 0,3 9,length-7
        // md文件格式 name[001][STAGE-0：Interval]
       String filePath = "E:\\java\\novel\\www.qinxiaoshuo.com\\欢迎来到实力至上主义教室1";
       String str = "E:\\java\\novel\\www.qinxiaoshuo.com\\欢迎来到实力至上主义教室1md\\";
       String filePre = "欢迎来到实力至上主义教室";
       // 还需要通用点，规定文件名格式
       //String[] strAry = {str,"0","3","9","7"};
       // 欢迎来到实力至上主义教室[001][[STAGE-0：Interval]][1].txt
       String[] strAry = {str,filePre};
       FileUtils.recursion(filePath,new ConcatFileToMD(),strAry);
    }


    @Override
    public boolean accept(File[] fileList, int i, String... strings) {
        try {
            System.out.println(fileList[i].getAbsolutePath());
            String fileName = fileList[i].getName();
            if (fileName.endsWith(".txt")) {

                String content = PrintWriterUtils.fileReader(fileList[i].getAbsolutePath());

                // 序号
                //  章节名
                fileName = fileName.substring(0, fileName.lastIndexOf("[")).replace(strings[1], "");

                //PrintWriterUtils.fileWriter(strings[0], strings[1] + fileName + ".md", MDUtils.mdTitle(fileName));

                PrintWriterUtils.fileWriter(strings[0], strings[1] + fileName + ".md", content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
