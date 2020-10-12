package cn.cc.utils.fileio.use.codehelper;

import cn.cc.entity.FileSysMsg;

import java.io.*;

public class ChangeFileCode {
    // 修改文件的编码
    public static void main(String[] args) {
        // 1.以指定的编码读取文件，并以指定的编码写入新的文件
        try {
            changeCode("GBK","F:\\resources\\3.文\\novel","UTF-8","F:\\resources\\3.文\\ChangeCode");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 参数  旧编码，新编码，旧路径，新路径
    }

    public static void changeCode(String oldCode,String oldPath,String newCode,String newPath){
        // 先判断给的是否是文件夹?

        // 根据路径获取文件夹
        File file = new File(oldPath);
        // 获取该文件夹下的所有文件
        File[] fileList = file.listFiles();
        // 便利所有的文件夹
        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                // 如果是文件
                if (fileList[i].isFile()) {
                    /***********************************************/
                    // 具体问题重新定制
                    try {
                        change(oldCode,oldPath,newCode,newPath,fileList[i].getName());
                    } catch (IOException e) {
                        System.out.println("文件转换异常");
                        e.printStackTrace();
                    }
                }
                //如果是文件夹
                if (fileList[i].isDirectory()) {
                    // 递归寻找第一级目录下的所有文件
                    // 只改变旧路径
                    changeCode(oldCode,oldPath + File.separator + fileList[i].getName(),newCode,newPath + File.separator + fileList[i].getName() );
                }
            }
        }
    }

    public static void change(String oldCode,String oldPath,String newCode,String newPath,String fileName) throws IOException {
        File newFilePath = new File(newPath);
        if(!newFilePath.exists()){
            newFilePath.mkdirs();
        }

        BufferedReader reader  = new BufferedReader(new InputStreamReader(
                new FileInputStream(oldPath + File.separator + fileName),oldCode));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newPath + File.separator + fileName), newCode));
        String str="";
        while((str=reader.readLine())!=null) {
            //content.append(str);
            System.out.println(str);
            bw.write(str+"\r\n");
        }
        bw.close();
        reader.close();
    }

}
