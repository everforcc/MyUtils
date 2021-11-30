package cc.use.file;

import cc.constant.ConstantFile;
import cc.core.file.utils.IFileUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author c.c.
 * @date 2020/12/27
 */
public class FileCopy implements IFileUtils{

    public static void main(String[] args) {
        try {
            //FileUtils.copyFile(new File(ConstantFile.javaFilePath + "\\Cache\\360极速浏览器下载\\pic\\7.7原视频\\IMG_6190.mp4"),new File("7.7原视频IMG_6190.mp4"));
            //String file = new File(ConstantFile.javaFilePath + "\\Cache\\360极速浏览器下载\\狗头\\7.7原视频\\IMG_6190.mp4").getParent();
            //System.out.println(file.substring(file.lastIndexOf("\\")+1));
            cc.core.file.utils.FileUtils.recursion(ConstantFile.L1_javaFilePath + "\\Cache\\360极速浏览器下载\\pic",new FileCopy());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String path = ConstantFile.L1_javaFilePath + "\\Cache\\360极速浏览器下载\\0合集";

    @Override
    public boolean accept(File[] fileList, int i, String... strings) {
        try {
            File file = fileList[i];
            String fileName = file.getName();
            if(fileName.contains("迷路")||fileName.contains("乞讨")
                    ||fileName.contains("docx")||fileName.contains("礼包")
                    ||fileName.contains("礼包")||fileName.contains("打赏")) {
                FileUtils.copyFile(file, new File(path + File.separator + "迷路" + File.separator + fileName));
            }else {
                String dir = file.getParent();
                FileUtils.copyFile(file, new File(path + File.separator + dir.substring(dir.lastIndexOf("\\")+1) + fileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
