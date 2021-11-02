package cc.core.io;

import cc.constant.ConstantFile;
import cc.core.file.utils.FileUtils;
import cc.core.file.zip.ZipUtils;
import cc.utils.Print_Record;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  这个类只做 InputStream 的工具类
 * @author c.c.
 * @date 2020/12/9
 */
public class InputStreamUtils {

    /**
     * 非文本类
     * 处理字节，例如图片视频流
     */
    public static void main(String[] args) {
        // 每个方法这里都加行 方法名
    }

    static Print_Record print_record = Print_Record.getInstanse(ConstantFile.javaFilePath + "/craw/www.wenku8.net/log","log.txt");

    // 入InputStream >> 岀FileOutputStream
    // 下载文件 带文件路径，文件名，文件大小
    public static void downFileByStream(InputStream in, String filePath, String fileName)throws Exception{
        // 不使用计算比例功能
        downFileByStream(in,filePath,fileName,new BigDecimal(0));
    }

    /**
     *  如果写入失败，这部分的异常需要交给谁处理呢?
     * @param inputStream
     * @param filePath D:dir
     * @param fileName a.txt
     * @param fileLength size
     * @throws Exception
     */
    public static void downFileByStream(InputStream inputStream, String filePath, String fileName, BigDecimal fileLength)throws Exception{
        // 总时间
        Date begindate = new Date();

        // windows的命名规则校验, 文件夹暂时不需要
        fileName = FileUtils.checkFileName(fileName);
        filePath = FileUtils.checkFilePath(filePath);

        File file = new File(filePath + File.separator+ fileName);
        //  加参数是否允许重复写入
        if(file.exists()){
            // 如果文件存在那么不在写入?
            print_record.println(filePath+fileName+"文件存在");
            return;
        }

        FileOutputStream fo = new FileOutputStream(file);
        // rate两位小数
        DecimalFormat df = new DecimalFormat("00");
        // 间隔
        BigDecimal rate = new BigDecimal(0.01);
        // 比例
        BigDecimal tempRate = new BigDecimal(0);

        /**
         * 以流的方式进行下载
         */
        byte[] buf = new byte[1024];
        int length = 0;
        // 文件长度 累计计算当前比例
        BigDecimal tempLength = new BigDecimal(length);

        //据说包装了有缓存，不切换上下文，比较快
        //BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        while ((length = inputStream.read(buf, 0, buf.length)) != -1) {
            if(fileLength!=null&&(fileLength.compareTo(new BigDecimal(0))==1)) {
                tempLength = tempLength.add(new BigDecimal(length));
                // 每 1% 跳出一行数据     num * 100 / 总长 向下取整
                tempRate = new BigDecimal(df.format(tempLength.multiply(new BigDecimal(100)).divide(fileLength, 1, BigDecimal.ROUND_DOWN)));
                // 如果当前比例大于之前存储的比例，那么就输出一行,表示下载比例的数据
                if (tempRate.compareTo(rate) == 1) {
                    System.out.println(fileName + ":下载进度 >>>>>> " + tempRate + "%");
                    rate = tempRate;
                }
            }
            // 写入文件
            fo.write(buf, 0, length);
        }

        //bufferedInputStream.close();
        inputStream.close();
        fo.close();

        /**
         * 计算下载所用时间
         */
        Date enddate = new Date();
        double time = enddate.getTime() - begindate.getTime();

        print_record.println(fileName + "下载耗时:" + time/1000 + " s");
    }

    // 给输入流的 这个校验什么的都没做，不用
    /*public static void IO_FileOutputStream(File file,InputStream in)throws Exception{
        FileOutputStream fo = new FileOutputStream(file);
        *//**
         * 以流的方式进行下载
         *//*
        byte[] buf = new byte[1024];
        int length = 0;
        while ((length = in.read(buf, 0, buf.length)) != -1) {
            fo.write(buf, 0, length);
        }
        in.close();
        fo.close();
    }*/

    public static byte[] inputStreamByte(InputStream inputStream,String charSet)throws Exception {
        FileOutputStream fo_3 = new FileOutputStream(new File("C://3.jpg"));
        FileOutputStream fo_4 = new FileOutputStream(new File("C://4.jpg"));
        List<byte[]> byteList = new ArrayList<>();
        int byteListIndex = 0;
        List<Integer> lengthList = new ArrayList<Integer>();
        int length = 0;
        int tempLength = 0;
        int resultLength = 0;
        byte[] buf = new byte[1];
        //byte[] b = new byte[0];
        while ((length = inputStream.read(buf, 0, buf.length)) != -1) {
            byte[] b = copyByteAry(buf);
            byteList.add(b);
            //System.out.println(length);
            tempLength = length;
            resultLength += length;
            lengthList.add(length);
            fo_3.write(b,0,length);

            if(byteList.get(byteListIndex)!=b){
                System.out.println("wrong:" + byteListIndex);
            }
            byteListIndex++;
        }
        System.out.println("tempLength:" + tempLength);
        //System.out.println("b.length:" + b.length);
        System.out.println("buf.length:" + buf.length);

        fo_3.close();

        System.out.println("resultLength : " + resultLength);
//
        byte[] resultByte = new byte[resultLength];
        int i = 0;
        // copy数据
        byte[] by = new byte[0];
        for(int x=0;x<byteList.size();x++){
            by = byteList.get(x);
            for(int y = 0;y<by.length;y++){
                //System.out.println("i:" + i);
                resultByte[i] = by[y];
                i++;
                fo_4.write(by[y]);
                if(i==resultLength){
                    System.out.println("i==resultLength : " + i);
                    break;
                }
            }
            // TODO 问题
            //fo_4.write(byteList.get(x),0,lengthList.get(x));
            /*if(i==resultLength){
                System.out.println("i==resultLength : " + i);
                break;
            }*/
        }
        System.out.println("by.length:" + by.length);
        fo_4.close();

        inputStream.close();

        return resultByte;
    }

    private static byte[] copyByteAry(byte[] bytes){
        int length = bytes.length;
        byte[] copyBytes = new byte[length];
        for(int i = 0; i<length; i++){
            copyBytes[i] = bytes[i];
        }
        return copyBytes;
    }

    public static String inputStreamStr(InputStream inputStream,String charSet,boolean gzip)throws Exception {
        if(gzip){
            return ZipUtils.gzipRestore(inputStream);
        }
        BufferedReader br = null;
        // 默认字符编码GBK
        if(StringUtils.isNotBlank(charSet)){
            br = new BufferedReader(new InputStreamReader(inputStream,charSet));
        }else {
            br = new BufferedReader(new InputStreamReader(inputStream));
        }
        String readLine;
        StringBuilder builder = new StringBuilder();
        while ((readLine = br.readLine()) != null) {
            // 一次读一行，所以我也要换行
            builder.append(readLine + "\r\n");
        }
        String result = builder.toString();
        return result;
    }

    /**
     * 根据inputStream获得输入的字符串,带编码
     * @param inputStream 输入流
     * @param charSet 字符编码
     * @return
     * @throws Exception
     */
    public static String inputStreamStr(InputStream inputStream,String charSet)throws Exception{
        return inputStreamStr(inputStream,charSet,false);
    }

}
