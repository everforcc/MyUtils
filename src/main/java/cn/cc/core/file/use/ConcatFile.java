package cn.cc.core.file.use;

import cn.cc.core.file.utils.FileUtils;
import cn.cc.core.file.utils.IFileUtils;
import cn.cc.core.io.utils.PrintWriterUtils;

import java.io.File;

/**
 * @author c.c.
 * @date 2020/12/10
 */
public class ConcatFile implements IFileUtils {

    // 合并多个文件，例如按照章节下载的小说
    public static void main(String[] args) {
       String filePath = "E:\\java\\novel\\www.qinxiaoshuo.com\\叛逆的鲁鲁修menu";
       FileUtils.recursion(filePath,new ConcatFile(),"E:\\java\\novel\\www.qinxiaoshuo.com\\叛逆的鲁鲁修.txt");
    }


    @Override
    public boolean accept(File[] fileList, int i, String... strings) {
        try {
            System.out.println(fileList[i].getAbsolutePath());
            String content = PrintWriterUtils.fileReader(fileList[i].getAbsolutePath());
            System.out.println(content);
            PrintWriterUtils.fileWriter(strings[0],content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
