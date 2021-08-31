package cc.comp;

/**
 * Yukino
 * 2020/4/23
 */
public class CharUtils {

    static byte a=(byte)127;
    static byte b=(byte)128;
    static byte c=(byte)129;
    static String str = "abc";

    static byte[] d = new byte[]{'d',(byte)0xff,-1,(byte)255,(byte)0x80,(byte) 128,-128};

    byte[] bytes1= new byte[]{'b','b','b','b','b'};
    static byte[] bytes= new byte[5];

    static {
        bytes[0]='a';
        bytes[1]='a';
        bytes[2]='a';
        bytes[3]='a';
        bytes[4]='a';
    }

    public static void main(String[] args) {

        System.out.println("a:" + a);
        System.out.println("b:" + b);
        System.out.println("c:" + c);
        System.out.println("----------");

        byteString();

        for(byte b:d){
            System.out.println("d --> " + b);
        }
        System.out.println("----------");

        System.out.println("测试26进制和10进制转换");
        System.out.println(byteAZToInt("W"));
        System.out.println(byteAZToInt("Z"));
        System.out.println(byteAZToInt("AD"));
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

    /**
     *  26进制
     *  把字母转换为数字,可以用来处理excle
     *  eg: A 65
     * @param str
     * @return
     */
    public static int byteAZToInt(String str){
        final int dif = 64;
        final int length = 26 ;
        byte[] bytes = str.getBytes();
        int level = bytes.length;
        int result = 0 ;
        for( int i = 0 ; i < level ; i++ ){
            // 算出来的公式，ABC = 1 * 26² + 2 * 26 + 3
            result += new Double( Math.pow(length,(i)) ).intValue() * ( bytes[ level - i - 1 ] - dif );
        }
        return result;
    }

    public static void byteString(){
        System.out.println("String和byte互转");
        byte[] bytestr = str.getBytes();
        System.out.println(bytestr.length);
        System.out.println(new String(bytestr));
        System.out.println(bytestr);
        System.out.println("----------");
    }

}
