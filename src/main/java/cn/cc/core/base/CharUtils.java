package cn.cc.core.base;

import org.junit.jupiter.api.Test;

/**
 * Yukino
 * 2020/4/23
 */
public class CharUtils {


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


    //把字母转换为数字 A 65可以用来处理excle
    public int byteToInt(String str){
        final int dif = 64;
        final int length = 26 ;
        byte[] bytes = str.getBytes();
        int level = bytes.length;
        int result = 0 ;
        for( int i = 0 ; i < level ; i++ ){
            result += new Double( Math.pow(length,(i)) ).intValue() * ( bytes[ level - i - 1 ] - dif );
        }
        return result;
    }
    @Test
    void a1(){
        //26进制
        System.out.println(this.byteToInt("W"));
        System.out.println(this.byteToInt("Z"));
        System.out.println(this.byteToInt("AD"));
    }

}
