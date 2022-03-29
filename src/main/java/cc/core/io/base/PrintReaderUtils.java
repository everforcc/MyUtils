package cc.core.io.base;

import cc.core.file.zip.ZipUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class PrintReaderUtils {

    // PrintReaderUtils.bufferReader
    // PrintReaderUtils.bufferReader
    /**
     * 根据inputStream获得输入的字符串,带编码
     * @param inputStream 输入流
     * @param charSet 字符编码
     * @return
     * @throws Exception
     */
    public static String bufferReaderToStr(InputStream inputStream, String charSet)throws Exception{
        return bufferReaderToStr(inputStream,charSet,false);
    }
    public static String bufferReaderToStr(InputStream inputStream, String charSet, boolean gzip)throws Exception {
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
     * 2.1 bufferReader
     *  制定编码
     *  可以加参数指定编码 new BufferedReader(new InputStreamReader(new FileInputStream(filePath), encoding));
     * @param filePath
     * @param charSet
     * @return
     * @throws Exception
     */
    /**
     *  文件路径
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String bufferReader(String filePath)throws Exception{
        File file = new File(filePath);
        BufferedReader reader  = new BufferedReader(new FileReader(file));
        return bufferReader(reader);
    }
    public static String bufferReader(File file)throws Exception{
        BufferedReader reader  = new BufferedReader(new FileReader(file));
        return bufferReader(reader);
    }
    public static String bufferReader(String filePath, String charSet)throws Exception{
        BufferedReader reader  = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charSet));
        return bufferReader(reader);
    }
    private static String bufferReader(BufferedReader reader) throws IOException {
        String str="";
        StringBuffer content = new StringBuffer("");
        while((str=reader.readLine())!=null) {
            content.append(str);
        }
        reader.close();
        return content.toString();
    }

    /**
     * 2.2 fileReader
     */
    public static String fileReader(String filePN){
        File file = new File(filePN);
        return fileReader(file);
    }
    public static String fileReader(File file){
        StringBuilder stringBuilder = new StringBuilder();
        try ( FileReader fileReader = new FileReader(file);){
            char dataBuffer[] = new char[1024];
            int bytesRead;
            while ((bytesRead = fileReader.read(dataBuffer, 0, 1024)) != -1) {
                stringBuilder.append(dataBuffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
