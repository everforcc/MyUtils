package cc.core.file.use.m3u8helper;

import cc.constant.ConstantFile;
import cc.core.file.utils.FileUtils;
import cc.core.file.utils.IFileUtils;
import cc.core.io.base.PrintWriterUtils;
import cc.java0.ffmpeg.utils.FFmpegUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author c.c.
 * @date 2020/12/10
 */
public class M3u8ToVideo implements IFileUtils {

    /**
     * 1. 替换文件夹的路径,将修改后的m3u8统一输出到 /m3u8 目录下
     * 2. 执行命令
     */

    public static void main(String[] args) {
        // 文件所在路径
        String filePath = ConstantFile.L1_javaFilePath + "/整理/m3u8";
        // m3u8 旧路径
        String oldPath = ConstantFile.L1_javaFilePath + "/整理/test";
        // m3u8 新路径
        String newPath = ConstantFile.L1_javaFilePath + "/整理/test";

        //String[] strings = {oldPath,newPath};
        FileUtils.recursion(filePath,new M3u8ToVideo());
        //parsePath(txt,fileName,ConstantFile.javaFilePath + "/整理/test");
    }

    // 待完善，需要文件测试，原来的文件都删了,
    @Override
    public boolean accept(File[] fileList, int i, String... strings) {
        File file = fileList[i];
        String fileName = file.getName();
        try {
            if(fileName.endsWith(".m3u8")) {

                String path = file.getParent();

                // , 目前没有统一规则所以先手动替换文件
                //String content = PrintWriterUtils.fileReader(file);
                // 还要调整分隔符
                //content = content.replaceAll(strings[0],strings[1]);
                //content = content.replace("/", "\\");;
                // 修改后的m3u8的路径，在原文件同级的m3u8的文件夹里面
                //PrintWriterUtils.fileWriter(file.getParent() + File.separator + "m3u8",file.getName(),content);

                // ffmpeg所需参数
                String m3u8Path = file.getAbsolutePath();
                // 生成文件名同级的 mp4  // File.separator + "mp4" +
                String newVideo = file.getParent() + File.separator + "new" + file.getName() + ".mp4";

                // 调用FFmpeg合并文件
                //FFmpegUtils.m3u8ToMp4(m3u8Path, newVideo);


                BufferedReader br = null;
                // 默认字符编码GBK
                br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String readLine;
                StringBuilder builder = new StringBuilder();
                String newFileName = "new" + fileName;
                while ((readLine = br.readLine()) != null) {
                    // 一次读一行，所以我也要换行
                    builder.append(readLine + "\r\n");
                    if(!readLine.startsWith("#")){

                        System.out.println("readLine:" + readLine);

                        parsePath(readLine + "\r\n",newFileName,path);
                    }else {

                        System.out.println("path:" + path);
                        System.out.println("fileName:" + fileName);
                        System.out.println("readLine:" + readLine);

                        PrintWriterUtils.printWriter(path,newFileName,readLine + "\r\n");
                    }
                }

                // 上面流程走完，下面用新路径生成，不用进入下级文件夹

                String ffmpegFilePath = file.getParent() + File.separator + newFileName;
                FFmpegUtils.m3u8ToMp4(ffmpegFilePath,ffmpegFilePath + ".mp4");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static String parsePath(String txt,String fileName,String path){

        txt = txt.replaceAll("\\|","_"); // windows字符限制 | 替换为 _

        System.out.println("txt.indexOf(fileName):" + txt.indexOf(fileName));
        System.out.println("txt:" + txt);
        System.out.println("fileName:" + fileName);

        txt = txt.substring(txt.indexOf(fileName),txt.length());

        System.out.println("2txt:" + txt);

        txt = path + File.separator + txt;

        System.out.println("3txt:" + txt);

        PrintWriterUtils.printWriter(path, fileName,txt);

        return txt;
    }

}
