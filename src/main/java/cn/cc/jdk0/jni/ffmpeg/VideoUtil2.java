package cn.cc.jdk0.jni.ffmpeg;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class VideoUtil2 {
    // 截取视频封面，任意帧

    public static void main(String[] args) {
        try {
            videoImage("E:\\test\\github\\ffmpeg\\鹿鸣\\aid885015708_cid250840872_Trick or Treat ฅ'ω'ฅ_Ep_15_BiliBili.flv");
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 截取视频第六帧的图片
     * @param filePath 视频路径
     * @return 图片的相对路径 例：pic/1.png
     * @throws FrameGrabber.Exception
     */
    public static String videoImage(String filePath) throws FrameGrabber.Exception {
        String pngPath = "";
        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);
        ff.start();
        //
        int ffLength = ff.getLengthInFrames();
        System.out.println("ffLength:"+ffLength);
        Frame f;
        int i = 0;
        while (i < ffLength) {
            f = ff.grabImage();
            //截取第6帧
            if((i>0) &&  (f.image != null)){
            //if(f.image != null){
                pngPath = i + ".png";
                //执行截图并放入指定位置
                doExecuteFrame(f, filePath + File.separator + pngPath);
                // break;
            }
            i++;
        }
        ff.stop();

        return pngPath;
    }

    /**
     * 截取缩略图
     * @param f Frame
     * @param targerFilePath:封面图片存放路径
     */
    private static void doExecuteFrame(Frame f, String targerFilePath) {
        String imagemat = "png";
        if (null == f || null == f.image) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bi = converter.getBufferedImage(f);
        File output = new File(targerFilePath);
        try {
            ImageIO.write(bi, imagemat, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
