package cn.cc.utils.fileio.use;


import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexEnd {
    static String regex="[0-9]?";
    static Pattern pattern = Pattern.compile(regex);
    static String path ="F:\\resources\\test";

    public static void main(String[] args) {

        getFileList(path);


    }


    public static void getFileList(String path) {

        // 先判断给的是否是文件夹?

        // 根据路径获取文件夹
        File file = new File(path);
        // 获取该文件夹下的所有文件
        File[] fileList = file.listFiles();
        // 便利所有的文件夹
        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                // 如果是文件
                if (fileList[i].isFile()) {

                    rename(fileList[i],fileList[i].getPath());
                }

                //如果是文件夹
                if (fileList[i].isDirectory()) {
                    // 递归寻找第一级目录下的所有文件
                    getFileList(path+"\\" + fileList[i].getName());
                }
            }
        }
    }

    static void rename(File f,String path){
        path=path.substring(0,path.lastIndexOf("\\"));
        String fileName = f.getName();
        //System.out.println("filename:"+fileName);
        if(fileName.contains(".")) {
            String end = fileName.substring(fileName.indexOf(".")+1, fileName.length());
            //System.out.println("end:" + end);

            Matcher matcher = pattern.matcher(end);
            //是否匹配到了
            if (matcher.find()) {
                System.out.println("path:"+path);
                System.out.println("匹配到了:"+end);
                String start = fileName.substring(0,fileName.indexOf("."));
                System.out.println("start:"+start);
                f.renameTo(new File(path+"\\"+start+".jpg"));
            }
        }
    }

}
