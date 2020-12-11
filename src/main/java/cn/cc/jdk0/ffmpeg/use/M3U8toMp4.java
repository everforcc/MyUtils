package cn.cc.jdk0.ffmpeg.use;
import cn.cc.jdk0.ffmpeg.utils.ConstantFFmpeg;
import java.io.File;
import java.io.IOException;

/**
 * @author c.c.
 * @date 2020/12/6
 */
public class M3U8toMp4 {

    // 替换路径
    // cn.cc.core.fileio.use.m3u8helper.M3u8ToMp4



    public static void main(String[] args) {
        try {
            printPath(new File("F:\\resources\\9.pre-gal\\视频\\2"));
           /* File a = new File("F:\\resources\\9.pre-gal\\视频\\M3U8\\");
            String newPath = a.getParent() + "/" + a.getName().substring(0, a.getName().lastIndexOf(".")) + "_.mp4"; // 新生成的文件名后面添加_ 下划线
            System.out.println(a.getAbsolutePath());
            System.out.println(newPath);
            String cmd = String.format(COMMAND, a.getAbsolutePath(), newPath);
            System.out.println(cmd);
            ChangeVedioCover.execCmd(cmd);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 文件夹路径
    public static void printPath(File file) throws IOException {
        File[] files = file.listFiles();
        for (File a : files) {
            //System.out.println(a.getAbsolutePath());
            if (a.getAbsolutePath().endsWith(".m3u8")) {
                String newPath = a.getParent() + "/" + a.getName().substring(0, a.getName().lastIndexOf(".")) + "_.mp4"; // 新生成的文件名后面添加_ 下划线
                System.out.println(a.getAbsolutePath());
                System.out.println(newPath);
                String cmd = String.format(ConstantFFmpeg.m3u8ToVideoCOMMAND, a.getAbsolutePath(), newPath);
                //ChangeVedioCover.execCmd(cmd);
                // a.delete();// 删除源文件
            }
            if (a.isDirectory()) {
                printPath(a);
            }
        }
    }

}
