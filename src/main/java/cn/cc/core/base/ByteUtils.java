package cn.cc.core.base;

/**
 * @author c.c.
 * @date 2020/12/14
 */
public class ByteUtils {
    public static void main(String[] args) {

    }

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

    // 还不懂
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

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    // 同方法 bytesToHexString
    //将指定byte数组以16进制的形式打印到控制台
    public static void printHexString(byte[] b) {
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase());
        }
    }




}
