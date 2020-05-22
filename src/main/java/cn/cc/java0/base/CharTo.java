package cn.cc.java0.base;

/**
 * Yukino
 * 2020/4/23
 */
public class CharTo {


    /**
     * 方法一：将char 强制转换为byte
     * @param ch
     * @return
     */
    public static byte charToByteAscii(char ch){
        byte byteAscii = (byte)ch;

        return byteAscii;
    }
    /**
     * 方法二：将char直接转化为int，其值就是字符的ascii
     * @param ch
     * @return
     */
    public static byte charToByteAscii2(char ch){
        byte byteAscii = (byte)ch;

        return byteAscii;
    }
}
