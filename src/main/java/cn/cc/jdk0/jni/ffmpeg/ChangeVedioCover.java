package cn.cc.jdk0.jni.ffmpeg;

import java.io.*;

/**
 * 修改视频封面
 * Created by i@merryyou.cn on 2020/3/24
 *
 * @VERSION 1.0
 */
public class ChangeVedioCover {

    // ffmpeg
    // 利用这个工具，可以截取图片并，设置封面, JJdown用的也是这个工具，真的是术业有专攻

    public static final String FFMPEG_PATH = "D:\\environment\\ffmpeg\\bin\\ffmpeg.exe"; // ffmpeg 程序迷路
    public static final String FILE_PATH = "E:\\test\\github\\视频\\鹿鸣"; //需要替换封面的视频目录
    public static final String IMAGE_PATH = "E:\\test\\github\\视频\\鹿鸣\\1.jpg"; // 需要替换的封面照片
    // 以后都要用s%de 形式，看起来比较完整
    public static final String COMMAND = "%s -i %s -i %s -map 1 -map 0 -c copy -disposition:0 attached_pic -y %s"; // ffmpeg 替换封面的命令




    public static void main(String[] args) throws Exception {
         printPath(new File(FILE_PATH));
         // System.out.println(COMMAND);
    }

    public static void printPath(File file) throws IOException {
        File[] files = file.listFiles();
        for (File a : files) {
            System.out.println(a.getAbsolutePath());
            if (a.getAbsolutePath().endsWith(".mp4")) {
                String newPath = a.getParent() + "/" + a.getName().substring(0, a.getName().lastIndexOf(".")) + "_.mp4"; // 新生成的文件名后面添加_ 下划线
                String cmd = String.format(COMMAND, FFMPEG_PATH, a.getAbsolutePath(), IMAGE_PATH, newPath);
                execCmd(cmd);
                // a.delete();// 删除源文件
            }
            if (a.isDirectory()) {
                printPath(a);
            }
        }
    }

    public static void execCmd(String cmd) {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/C", cmd);
        Process process = null;
        try {
            process = builder.redirectErrorStream(true).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in = process.getInputStream();
        outStream(in);
    }

    private static void outStream(InputStream in) {
        // 用一个读输出流类去读
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        // 逐行读取输出到控制台
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

