package cc.arithmetic.basenum;

import cc.constant.ConstantFile;
import cc.core.io.base.PrintReaderUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * Yukino
 * 2020/4/22
 */
public class Base64 {

    /**
     * 将base64编码字符串转换为图片
     * @param imgStr: base64编码字符串
     * @param path:   图片路径-具体到文件
     * @return
     */
    public static boolean getImage(String imgStr, String path){
        if (imgStr == null){
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @return
     * 需要注意的是，一般插件返回的base64编码的字符串都是有一个前缀的:"data:image/jpeg;base64," , 解码之前这个得去掉。
     */
    public static String getbase64Url(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static void main(String[] args) {
        //System.out.println(getbase64Url(ConstantFile.javaFilePath + "/resources/图片/bilibili/向山进发.gif"));
        //PrintWriterUtils.fileWriter(ConstantFile.javaFilePath + "/resources/图片/bilibili/yellow.txt",getbase64Url(ConstantFile.javaFilePath + "/resources/图片/bilibili/yellow.jpg"));

        try {
            getImage(PrintReaderUtils.bufferReader(ConstantFile.L1_javaFilePath + "/resources/图片/bilibili/yellow.txt"),ConstantFile.L1_javaFilePath + "/resources/图片/bilibili/yellow1.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


