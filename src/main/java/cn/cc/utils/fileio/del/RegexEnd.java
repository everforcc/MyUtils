package cn.cc.utils.fileio.del;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexEnd {
    static String regex = "[0-9]?";
    static Pattern pattern = Pattern.compile(regex);
    static String path = "F:\\resources\\test";

    public static void main(String[] args) {
        System.out.println("filename:1");
        getFileList(path);


    }


    public static void getFileList(String path) {


        File file = new File(path);

        File[] fileList = file.listFiles();

        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {

                if (fileList[i].isFile()) {

                    rename(fileList[i], fileList[i].getPath());
                }


                if (fileList[i].isDirectory()) {

                    getFileList(path + "\\" + fileList[i].getName());
                }
            }
        }
    }

    static void rename(File f, String path) {
        path = path.substring(0, path.lastIndexOf("\\"));
        String fileName = f.getName();
        //System.out.println("filename:"+fileName);
        if (fileName.contains(".")) {
            String end = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
            //System.out.println("end:" + end);

            Matcher matcher = pattern.matcher(end);

            if (matcher.find()) {
                System.out.println("path:" + path);

                String start = fileName.substring(0, fileName.indexOf("."));
                System.out.println("start:" + start);
                f.renameTo(new File(path + "\\" + start + ".jpg"));
            }
        }
    }

}
