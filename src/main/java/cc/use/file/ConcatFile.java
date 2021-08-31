package cc.use.file;

import cc.core.file.utils.FileUtils;
import cc.core.file.utils.IFileUtils;
import cc.core.io.PrintWriterUtils;

import java.io.File;

/**
 * @author c.c.
 * @date 2020/12/10
 */
public class ConcatFile implements IFileUtils {

    // ~~ 两个替换为一个或者加个点
    // 合并多个文件，例如按照章节下载的小说
    public static void main(String[] args) {
       String filePath = "E:\\java\\果然我的青春恋爱喜剧搞错了(我的青春恋爱物语果然有问题)";
       FileUtils.recursion(filePath,new ConcatFile(),"E:\\java\\我的青春恋爱物语果然有问题%s.md");
    }


    @Override
    public boolean accept(File[] fileList, int i, String... strings) {
        try {
            System.out.println(fileList[i].getAbsolutePath());
            String name = fileList[i].getName();
            int first  = name.indexOf(".") + 1;
            name = name.substring(first,name.indexOf(".",first));
            String content = PrintWriterUtils.fileReader(fileList[i].getAbsolutePath());
            System.out.println(content);
            PrintWriterUtils.fileWriter(String.format(strings[0],name),content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
