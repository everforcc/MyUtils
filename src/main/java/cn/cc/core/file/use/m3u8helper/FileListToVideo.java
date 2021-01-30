package cn.cc.core.file.use.m3u8helper;

import cn.cc.core.file.utils.FileUtils;
import cn.cc.core.file.utils.IFileUtils;
import cn.cc.core.io.utils.PrintWriterUtils;
import cn.cc.jdk0.ffmpeg.utils.FFmpegUtils;

import java.io.File;
import java.util.*;

/**
 * @author c.c.
 * @date 2020/12/10
 */
public class FileListToVideo implements IFileUtils {

    /** ffmpeg支持的清单格式 filelist.txt
     *   file 'D:/临时/Download/582c92be63903fd67ec45dbacb317226b2d74fc5/Y2hlbmppbmdjb25n0'
         file 'D:/临时/Download/582c92be63903fd67ec45dbacb317226b2d74fc5/Y2hlbmppbmdjb25n1'
     */

    private static Set<String> set = new HashSet<>();
    private static List<String> list = new ArrayList<>();

    // 现在没文件了，待测试
    public static void main(String[] args) {
        String fileListPath = "F:\\Download\\video\\b12ce162c36cb69b1946c4650335d78a";
        // FileUtils.recursion(fileListPath,new FileListToVideo());
        concatFile(fileListPath,"");
        System.out.println("set.size():" + set.size());
        for(String s:set){
            System.out.println(" >>>>> " + s);
            // 调用ffmpeg
            // 生成和文件夹,txt同名的mp4
            FFmpegUtils.concatFileList(s,s + ".mp4");
        }
    }

    /**
     *  目录结构 所有文件都在同一级 test下面全部都是视频流的文件夹
     *  eg: D:/test/abcabcabc/视频流1,2,3,4
     *  eg: D:/test/cbacbacba/视频流1,2,3,4

    /**
     *
     * @param fileList
     * @param i
     * @param strings
     * @return
     */
    @Override
    public boolean accept(File[] fileList, int i, String... strings) {
        String str =  "file '" + fileList[i].getAbsolutePath() + "' \r\n";;
        list.add(str);
        // 生成与文件夹同名的txt，里面包含目录下的所有文件路径信息
        String fileName = fileList[i].getParent() + ".txt";
        //PrintWriterUtils.fileWriter(fileName,str);
        set.add(fileName);
        return false;
    }

    public static void concatFile(String path,String pre){
        String fileName = path + ".txt";
        for(int i=0 ; i<729 ;i++) {
            String str = "file '" + path + File.separator + i + "' \r\n";
            // 生成与文件夹同名的txt，里面包含目录下的所有文件路径信息
            PrintWriterUtils.fileWriter(fileName,str);
        }
        set.add(fileName);
    }

}
