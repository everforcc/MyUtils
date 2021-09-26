package cc.core.file.base64;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author everforcc 2021-09-18
 */
@Slf4j
public class FileToBaseUtils {

    public static void main(String[] args) {
        loadByteEncode("");
    }

    public static String loadByteEncode(String filePath){
        log.info("加载filePath:" + filePath);
        // 打印 日志
        File file = new File("C://1.jpg");
        String str = null;
        try {
            FileInputStream inputFile = null;
            inputFile = new FileInputStream(file);
            //byte[] buffer = new byte[(int) (file.length() * 0.5)]; 试听，只给一半
            byte[] buffer = new byte[(int) (file.length())]; // 这个length就是Range
            inputFile.read(buffer);
            inputFile.close();
            str = new BASE64Encoder().encode(buffer);
        }catch (Exception e){
            log.warn(filePath + e.toString());
            e.printStackTrace();
            // 文件未找到，这时候随机给返回一个? 还是咋处理呢
        }
        log.info("data:image/png;base64," + str);
        return str;
    }

}
