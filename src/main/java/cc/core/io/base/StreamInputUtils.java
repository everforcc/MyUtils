package cc.core.io.base;

import cc.sysconstant.ConstantFile;
import cc.core.file.utils.FileUtils;
import cc.core.file.zip.ZipUtils;
import cc.sysutils.Print_Record;
import com.google.common.io.ByteStreams;
import org.apache.poi.util.IOUtils;

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
public class StreamInputUtils {

    /**
     * 分类
     * 1. 字节流
     * 1.1 字节流 > 文件
     * 1.2 字节流 > copy Ary
     * 1.3 字节流 > str
     *
     * 2. Reader系列
     *
     */

    public static void main(String[] args) {

    }

    // 日志记录
    private static Print_Record print_record = Print_Record.getInstanse(ConstantFile.L1_javaFilePath + "/craw/www.wenku8.net/log","log.txt");

    /**
     *  1.1 stream到file
     * @param inputStream
     * @param filePath D:dir
     * @param fileName a.txt
     * @ fileLength size
     * @throws Exception
     */
    public static void streamToFile(InputStream inputStream, String filePath, String fileName)throws Exception{
        // 不使用计算比例功能
        streamToFile(inputStream,filePath,fileName,new BigDecimal(0));
    }
    public static void streamToFile(InputStream inputStream, String filePath, String fileName, BigDecimal fileLength)throws Exception{
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

    /**
     * 给输入流的 这个校验什么的都没做，不用
     * 示例大概流程
     */
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

    /**
     * 1.2 复制 inputstream 的byte数组
     * @param inputStream
     * @param charSet
     * @return
     * @throws Exception
     */
    public static byte[] copyStreamByteAry(InputStream inputStream, String charSet)throws Exception {
        //ByteStreams.toByteArray(inputStream);

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

    /**
     * 1.3 因为要读出字符，所以要全读然后转，否则就要根据编码从底层判断了，比较麻烦
     * 不建议使用这种方法，数据量大,容易出问题
     */
    public static String streamToStr(InputStream inputStream,String charSet)throws Exception {
        byte[] bytes = ByteStreams.toByteArray(inputStream);
        return new String(bytes,charSet);
    }
    public static byte[] streamTobytesGuava(InputStream inputStream)throws Exception {
        return ByteStreams.toByteArray(inputStream);
    }
    public static byte[] streamTobytesApache(InputStream inputStream)throws Exception {
        return IOUtils.toByteArray(inputStream);
    }

    public static String streamToStr(InputStream inputStream,String charSet,boolean gzip)throws Exception {
        if(gzip){
            return ZipUtils.gzipRestore(inputStream);
        }
        byte[] bytes = ByteStreams.toByteArray(inputStream);
        return new String(bytes,charSet);
    }


    /**
     * 2. 使用其他api
     *
     */
    public static String readerUtils(InputStream inputStream,String charSet){
        try {
            return PrintReaderUtils.bufferReaderToStr(inputStream,charSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "获取失败";
    }

}
