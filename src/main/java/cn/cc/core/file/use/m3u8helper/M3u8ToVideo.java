package cn.cc.core.file.use.m3u8helper;

import cn.cc.core.file.utils.FileUtils;
import cn.cc.core.file.utils.IFileUtils;
import cn.cc.core.io.utils.PrintWriterUtils;
import cn.cc.jdk0.ffmpeg.utils.FFmpegUtils;

import java.io.File;

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
        String filePath = "";
        // m3u8 旧路径
        String oldPath = "";
        // m3u8 新路径
        String newPath = "";
        String suffix = ".m3u8";
        String[] strings = {oldPath,newPath};
        FileUtils.recursion(filePath,new M3u8ToVideo(),strings);
    }

    // 待完善，需要文件测试，原来的文件都删了,
    @Override
    public boolean accept(File[] fileList, int i, String... strings) {
        File file = fileList[i];
        try {
            String content = PrintWriterUtils.fileReader(file);
            // 还要调整分隔符
            content = content.replaceAll(strings[0],strings[1]);
            content = content.replace("/", "\\");;
            // 修改后的m3u8的路径，在原文件同级的m3u8的文件夹里面
            PrintWriterUtils.fileWriter(file.getParent() + File.separator + "m3u8",file.getName(),content);

            // ffmpeg所需参数
            String m3u8Path = file.getAbsolutePath();
            String newVideo = strings[1] + File.separator + "mp4" + File.separator + file.getName() + ".mp4";

            // 调用FFmpeg合并文件
            FFmpegUtils.m3u8ToMp4(m3u8Path,newVideo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
