package cn.cc.core.base;

/**
 * Yukino
 * 2020/4/23
 */
public class CharUtils {

    static byte a=(byte)128;
    static byte b=(byte)129;
    static byte[] c = new byte[]{'d',(byte)0xff,-1,(byte)255,(byte)0x80,(byte) 128,-128};

    byte[] bytes1= new byte[]{'b','b','b','b','b'};
    static byte[] bytes2= new byte[5];

    static {
        bytes2[0]='a';
        bytes2[1]='a';
        bytes2[2]='a';
        bytes2[3]='a';
        bytes2[4]='a';
    }

    public static void main(String[] args) {
        byte a=(byte)127;
        System.out.println("a:"+a);
        //byte b=(byte)129;
        //System.out.println(a);
        //System.out.println(b);
        String stra="abc";
        byte[] bytestr = stra.getBytes();
        System.out.println(bytestr.length);
        System.out.println(new String(bytestr));
        System.out.println(bytestr);
        System.out.println("----------");
        for(byte b:c){
            System.out.println(b);
        }
        System.out.println("----------");
        System.out.println(stra.getBytes());

        System.out.println(c);

        a1();
    }

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
    // 26进制
    public static int byteToInt(String str){
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

    public static void a1(){
        //26进制
        System.out.println(byteToInt("W"));
        System.out.println(byteToInt("Z"));
        System.out.println(byteToInt("AD"));
    }

}
