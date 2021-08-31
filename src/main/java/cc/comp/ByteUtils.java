package cc.comp;

/**
 * @author c.c.
 * @date 2020/12/14
 */
public class ByteUtils {
    public static void main(String[] args) {
        cut();
        baseMsg();
    }

    /**
     * 基本信息
     */
    private static void baseMsg(){
        System.out.println("Byte.MAX_VALUE:" + Byte.MAX_VALUE);
        System.out.println("Byte.MIN_VALUE:" + Byte.MIN_VALUE);
        // java中的基本数据类型 存的是补码
        System.out.println(Integer.toBinaryString(Byte.MAX_VALUE));
        System.out.println(Integer.toBinaryString(Byte.MIN_VALUE));
    }

    /**
     * byte数组转换十六进制
     * @param bytes
     * @return
     */
    public static String bytesToHexString(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer("");
        if(bytes==null||bytes.length<0){
           return null;
        }

        for(int i = 0; i < bytes.length; i++){
            int b = bytes[i] & 0xFF;
            String s = Integer.toHexString(b);
            if(s.length() < 2){
               stringBuffer.append(0);
            }
            stringBuffer.append(s);
        }
        return stringBuffer.toString();
    }

    /**
     * 十六进制转换byte
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString){
        if(hexString == null||"".equals(hexString)){
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] bytes = new byte[length];
        for(int i = 0; i < length; i++){
            int pos = i * 2;
            bytes[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return null;
    }

    /**
     *  提供给上面方法
     * @param c
     * @return
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 同方法 bytesToHexString
     * 将指定byte数组以16进制的形式打印到控制台
     * @param b
     */
    public static void printHexString(byte[] b) {
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase());
        }
    }

    /**
     * 强转导致的截取问题
     */
    public static void cut(){
        byte b = (byte) 128;
        System.out.println(b);
/* byte因为无法容纳128(byte范围是-128-127),因此需要进行截断，用二进制表示过程就是这样的：
int 00000000 00000000 00000000 10000000
byte 10000000 //最高位符号位1代表此数为负数，因此是-128。*/
        byte b1 = (byte) -129;
        System.out.println(b1);
/*int 11111111 11111111 11111111 01111111
byte 011111111 //即127。*/
        double d = 200.123;
        System.out.println((int)d);
/*double转换为int时会去掉小数位，因此输出200。*/
        char c ='a';
        byte s = (byte) c;
        System.out.println(s);
/*char转换为byte时，由于byte无法容纳char的16位，因此需要进行强制转换并截断一部分，
但是由于’a’字符对应的十进制97并未超过byte的范围因此此处直接输出’a’的AscⅡ码97。*/
    }


}
