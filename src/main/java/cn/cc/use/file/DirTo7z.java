package cn.cc.use.file;

import cn.cc.core.file.zip.SevenZCompressUtils;
import cn.cc.entity.FileSysMsg;

import java.io.File;
import java.util.List;

/**
 * @author c.c.
 * @date 2021/2/8
 */
public class DirTo7z {

    public static void main(String[] args) {
        getFileList("F:\\待上传\\【zero搬运】【国产动漫】《秦时明月》 【第1-5季+第1季重置版+3D电影+特别篇】【全174集+10集+1部+6集】【第1-3季480P 第4-5季1080P 电影1080P 特别篇720P】【he Legend of Qin】");
    }

    public static void getFileList(String path) {

        File file = new File(path);
        // 获取该文件夹下的所有文件
        File[] fileList = file.listFiles();
        // 便利所有的文件夹
        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {

                //如果是文件夹
                if (fileList[i].isDirectory()) {
                    // 递归寻找第一级目录下的所有文件
                    // 压缩
                    System.out.println("dir:" + fileList[i].getAbsolutePath());
                    //SevenZCompressUtils.compress("c.c.5664",fileList[i].getAbsolutePath() + ".7z",fileList[i].getAbsolutePath() + "/*");
                    getFileList(fileList[i].getAbsolutePath());
                }else {
                    String newPath = fileList[i].getAbsolutePath();
                    newPath = newPath.replace("F:","D:");
                    System.out.println("file:" + newPath);
                    SevenZCompressUtils.compress("c.c.5664",newPath + ".7z",fileList[i].getAbsolutePath());
                }
            }
        }

    }

}
