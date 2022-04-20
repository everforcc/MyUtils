package cc.core.file.use.markdown;

import cc.sysconstant.ConstantFile;
import cc.core.io.base.PrintWriterUtils;

import java.io.File;

/**
 * @author c.c.
 * @date 2021/3/22
 */
public class SystemPic {

    private static String imgPath = "![](%s)  ";
    private static String filePath = "";

    public static void main(String[] args) {
        //parseMsg("");

        //
        File file = new File(ConstantFile.L1_javaFilePath + "");
        /*System.out.println(file.getParent());
        System.out.println(file.getParentFile());
        System.out.println(file.getName());*/

        File[] fileList = file.listFiles();

        saveMD("","[TOC]");

        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                //如果是文件夹
                if (fileList[i].isDirectory()) {
                    filePath = fileList[i].getName();

                    System.out.println("[filePath] : " + filePath);

                    saveMD(filePath + ".md","# " + filePath);

                    parseMsg(fileList[i].getAbsolutePath());
                }
            }
        }

    }

    public static void parseMsg(String path){

        File file = new File(path);
        File[] fileList = file.listFiles();
        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                // 如果是文件
                if (fileList[i].isFile()) {
                    if(fileList[i].getName().endsWith(".jpeg")||fileList[i].getName().endsWith(".jpg")){
                        String imgMD = String.format(imgPath,fileList[i].getAbsolutePath());
                        saveMD(filePath + ".md",imgMD);
                    }
                }
                //如果是文件夹
                if (fileList[i].isDirectory()) {
                    saveMD(filePath + ".md","## " + fileList[i].getName());
                    parseMsg(fileList[i].getAbsolutePath());
                }
            }
        }

    }

    public static void saveMD(String fileName,String content){
        PrintWriterUtils.printWriter(ConstantFile.L1_javaFilePath + "/图片\\", "图片-1.md",content + "\r\n");
    }

}
