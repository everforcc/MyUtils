package cc.use.file.zip;

import cc.constant.ConstantFile;
import cc.core.file.zip.SevenZCompressUtils;
import java.io.File;

/**
 * @author c.c.
 * @date 2021/2/8
 */
public class DirTo7z {

    // 两种类型 是否进入文件夹
    /**
     * 1. 当前目录的下级目录 按照文件夹全部压缩 /*
     * 2. 分别压缩每个文件
     */

    // 密码
    private static final String passWord = "自己的密码";
    // 需要压缩的文件夹
    private static final String sourceFilePath = ConstantFile.javaFilePath + "";
    // 是否进入文件夹
    private static final boolean isCD = false;
    // 文件后缀,可以多 "." 但不能少 "."
    private static final String fileType = ".7z";

    public static void main(String[] args) {
        // ""
        /*String path = ConstantFile.javaFilePath + "";
        getFileList(path);*/
        /*String[] dir = {"",""};
        for(String s:dir){
            getFileList(path + s);
        }*/
        //getFileList(sourceFilePath);

        String path = ConstantFile.javaFilePath + "";
        File file = new File(path);

        File[] fileList = file.listFiles();

        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                //如果是文件夹
                if (fileList[i].isDirectory()) {
                    getFileList(fileList[i].getAbsolutePath());
                }
            }
        }

        //getFileList(path);

    }

    public static void getFileList(String path) {
        File file = new File(path);
        // 获取该文件夹下的所有文件
        File[] fileList = file.listFiles();
        // 便利所有的文件夹
        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                dealFile(fileList[i]);
            }
        }
    }

    /**
     *   如果进入文件夹，那么就生成一个顶级文件夹同级的 7z 文件夹
     *   如果不进入， 那么就生成一个子集的文件 .7z
     * @param file
     */
    public static void dealFile(File file){
        //如果是文件夹
        if (file.isDirectory()) {
            // 递归寻找第一级目录下的所有文件
            // 压缩
            System.out.println("dir:" + file.getAbsolutePath());
            if(isCD) {
                getFileList(file.getAbsolutePath());
            }else {
                SevenZCompressUtils.compress(passWord, file.getAbsolutePath() + fileType, file.getAbsolutePath() + "/*");
            }
        }else { // 只有在进入文件夹的情况下，才会出现文件
            String newPath = file.getAbsolutePath();

            // 替换顶级文件夹
            newPath = newPath.replace(sourceFilePath,sourceFilePath + "7z");

            System.out.println("file:" + newPath);
            if(isCD) {
                SevenZCompressUtils.compress(passWord, newPath + fileType, file.getAbsolutePath());
            }
        }
    }

}
