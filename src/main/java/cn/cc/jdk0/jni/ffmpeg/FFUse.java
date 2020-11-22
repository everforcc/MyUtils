package cn.cc.jdk0.jni.ffmpeg;

import java.io.*;

public class FFUse {
    public static void main(String[] args) {
        a1();
    }

    static void a1(){
        final String FFMPEG_PATH = "D:\\environment\\ffmpeg\\bin\\ffmpeg.exe"; // ffmpeg 程序路径
        final String COMMAND = "%s -i %s -i %s -map 1 -map 0 -c copy -disposition:0 attached_pic -y %s"; // ffmpeg 替换封面的命令
        // 1. 生成文件封面，然后合并
        String FILE_PATH = "E:\\test\\github\\视频\\鹿鸣";
        File[] files = new File(FILE_PATH).listFiles();
        VideoUtil videoUtil = new VideoUtil();
        for (File a : files) {
            videoUtil.getScreenshot(a.getPath());
        }
        for (File a : files) {
            System.out.println(a.getAbsolutePath());
            if (a.getAbsolutePath().endsWith(".flv")) {
                String newPath = a.getParent() + "/" + a.getName().substring(0, a.getName().lastIndexOf(".")) + "_.flv"; // 新生成的文件名后面添加_ 下划线
                String cmd = String.format(COMMAND, FFMPEG_PATH, a.getAbsolutePath(), a.getPath(), newPath);
                ChangeVedioCover.execCmd(cmd);
                // a.delete();// 删除源文件

            }
            if (a.isDirectory()) {
                //printPath(a);
            }
        }
    }

}
