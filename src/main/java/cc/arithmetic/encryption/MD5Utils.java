package cc.arithmetic.encryption;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author everforcc 2021-11-05
 */
public class MD5Utils {

    public static void main(String[] args) {
        String path = "C:\\test\\7z\\dir\\gj.jpg";
        System.out.println(getMD5One(path));
        System.out.println(getMD5Three(path));
    }

    /**
     * 1. 比较原始的一种实现方法，首先将文件一次性读入内存，然后通过MessageDigest进行MD5加密，最后再手动将其转换为16进制的MD5值。
     */
    private final static String[] strHex = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    public static String getMD5One(String path) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(FileUtils.readFileToByteArray(new File(path)));
            for (int i = 0; i < b.length; i++) {
                int d = b[i];
                if (d < 0) {
                    d += 256;
                }
                int d1 = d / 16;
                int d2 = d % 16;
                sb.append(strHex[d1] + strHex[d2]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 2. 这里借助了Integer类的方法实现16进制的转换，比方法一更简洁一些
     *
     */
    public static String getMD5Two(String path) {
        StringBuffer sb = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(FileUtils.readFileToByteArray(new File(path)));
            byte b[] = md.digest();
            int d;
            for (int i = 0; i < b.length; i++) {
                d = b[i];
                if (d < 0) {
                    d = b[i] & 0xff;
                    // 与上一行效果等同
                    // i += 256;
                }
                if (d < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(d));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 3. 在读入文件信息上有点不同。这里是分多次将一个文件读入，对于大型文件而言，比较推荐这种方式，占用内存比较少。步骤三则是通过BigInteger类提供的方法进行16进制的转换，与方法二类似。
     * @param path
     * @return
     */
    public static String getMD5Three(String path) {
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[8192];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            File f = new File(path);
            System.out.println(f.length());
            FileInputStream fis = new FileInputStream(f);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi.toString(16);
    }

    public static String getMD5Four(String path) throws IOException {
        return DigestUtils.md5Hex(new FileInputStream(path));
    }

}
