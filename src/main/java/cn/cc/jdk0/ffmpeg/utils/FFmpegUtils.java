package cn.cc.jdk0.ffmpeg.utils;

import cn.cc.jdk0.cmd.CmdUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author c.c.
 * @date 2020/12/10
 */
public class FFmpegUtils {
    public static void main(String[] args) {
        //
        //replaceCover("E:\\ffmpeg\\1.flv","E:\\ffmpeg\\1.jpg","E:\\ffmpeg\\2.mp4");
        //subVideoForImg("E:\\java\\ffmpeg\\2.mp4","E:\\java\\ffmpeg\\2.png");
        //subVideoForGIF("E:\\java\\ffmpeg\\2.mp4",0,1,"E:\\java\\ffmpeg\\2.gif");
        //concatFileList("%s.txt","%s.mp4");
        subVideoImg("filepath");
    }

    // 合并m3u8
    public static void m3u8ToMp4(String oldFilePath,String newFilePath){
        if(oldFilePath==null||newFilePath==null){
            return;
        }
        String COMMAND = String.format(ConstantFFmpeg.m3u8ToVideoCOMMAND, oldFilePath, newFilePath);
        CmdUtils.execCmd(COMMAND);
        // 命令行返回输出完了，才算结束
        System.out.println("cmd >>> end");
    }

    // 合并文本清单，格式,需要file那边把文件格式给梳理好
    /**
     *  file 'D:/临时/Download/582c92be63903fd67ec45dbacb317226b2d74fc5/Y2hlbmppbmdjb25n0'
     *  file 'D:/临时/Download/582c92be63903fd67ec45dbacb317226b2d74fc5/Y2hlbmppbmdjb25n1'
     */
    // 合并清单里面的文件
    public static void concatFileList(String fileListPath,String newFilePath){
        String COMMAND = String.format(ConstantFFmpeg.txtFileListCOMMAND,fileListPath,newFilePath);
        CmdUtils.execCmd(COMMAND);
    }

    // 合并指定的几段文件，
    public static void concatVideo(String[] oldFilePathList,String newFilePath){
        if(newFilePath==null||oldFilePathList==null||oldFilePathList.length==0){
            return;
        }

        String concatFile = "";
        for(String s:oldFilePathList){
            concatFile += s +"|";
        }
        if("".equals(concatFile)){
            return;
        }else {
            concatFile=concatFile.substring(0,concatFile.length()-1);
        }
        String COMMAND = String.format(ConstantFFmpeg.concatVideoCOMMAND,concatFile,newFilePath);
        CmdUtils.execCmd(COMMAND);
    }

    // 替换封面
    public static void replaceCover(String videoPath,String imgPath,String newFilePath){
        if(videoPath==null||imgPath==null||newFilePath==null){
            return;
        }
        String COMMAND = String.format(ConstantFFmpeg.replaceCoverCOMMAND,ConstantFFmpeg.FFMPEG_PATH,videoPath,imgPath,newFilePath);
        CmdUtils.execCmd(COMMAND);
    }

    // 截取某一帧图片
    public static void subVideoForImg(String file,String imgFile){
        if(file==null||imgFile==null){
            return;
        }
        String COMMAND = String.format(ConstantFFmpeg.screenImgCOMMAND,file,imgFile);
        CmdUtils.execCmd(COMMAND);
    }

    // 截取一段gif
    public static void subVideoForGIF(String file,double startSecond,double endSecond,String gifFile){
        if(file==null||gifFile==null||startSecond==Double.NaN||endSecond==Double.NaN){
            return;
        }
        if(startSecond>endSecond){
            return;
        }
        if(!gifFile.endsWith(".gif")){
            return;
        }

        String COMMAND = String.format(ConstantFFmpeg.screenGIFCOMMAND,file,startSecond,endSecond,gifFile);
        CmdUtils.execCmd(COMMAND);
    }

    // 执行自定义的命令
    public static void exec(String COMMAND){
        if(COMMAND==null){
            return;
        }
        CmdUtils.execCmd(COMMAND);
    }

    // 视频截图
    public static void subVideoImg(String filePath){
        try {
            // 默认截取第一帧
            videoImage(filePath);
        } catch (FrameGrabber.Exception e) {
            // 截图异常
            e.printStackTrace();
        }
    }

    // 命名有点问题 可以设置为文件名加帧数
    /**
     * 截取视频第六帧的图片
     * @param filePath 视频路径
     * @return 图片的相对路径 例：pic/1.png
     * @throws FrameGrabber.Exception
     */
    private static String videoImage(String filePath) throws FrameGrabber.Exception {
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
            //截取第1帧
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
