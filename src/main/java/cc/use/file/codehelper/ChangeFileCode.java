package cc.use.file.codehelper;

import cc.constant.ConstantCharSet;
import cc.constant.ConstantFile;
import cc.core.file.utils.FileUtils;
import cc.core.file.utils.IFileUtils;

import java.io.*;

public class ChangeFileCode implements IFileUtils {

    // 修改文件的编码

    public static void main(String[] args) {
        // 1.以指定的编码读取文件，并以指定的编码写入新的文件
        try {
            String oldCode = ConstantCharSet.GBK;
            String oldPath = ConstantFile.L1_javaFilePath + "\\test\\novel";
            String newCode = ConstantCharSet.UTF_8;
            String newPath = ConstantFile.L1_javaFilePath + "\\test\\ChangeCode";

            //changeCode(oldPath,oldCode, newCode,newPath);

            String[] strings = {oldPath,oldCode,newPath,newCode};
            FileUtils.recursion(oldPath,new ChangeFileCode (),strings);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 参数  旧编码，新编码，旧路径，新路径
    }

    @Override
    public boolean accept(File[] fileList, int i, String... strings) {

        // fileList[] 可以确定当前文件的信息，i确定位置
        File file = fileList[i];
        String oldPath = file.getParent();
        String fileName = file.getName();

        // strings 数组的含义
        String oldCode = strings[1];
        String newPath = file.getParent().replace(strings[0],strings[2]);
        String newCode = strings[3];

        File newFilePath = new File(newPath);
        if(!newFilePath.exists()){
            newFilePath.mkdirs();
        }

        BufferedReader reader  = null;
        BufferedWriter bw = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(oldPath + File.separator + fileName),oldCode));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newPath + File.separator + fileName), newCode));
            String str="";
            while((str=reader.readLine())!=null) {
                //content.append(str);
                System.out.println(str);
                bw.write(str+"\r\n");
            }
            bw.close();
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
