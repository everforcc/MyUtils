package cc.arithmetic.encryption;

public class DesExplain {
    public static void main(String[] args) {
        String byte16Str="010203ff";

        try {
            DesUtils desUtils=new DesUtils();
            byte[] bytes={1,2,3,-1};

           /* // 测试方法byteArr2HexStr
            System.out.println(DesUtils.byteArr2HexStr(bytes)); // 010203ff
            // 转化为16进制 a/16=b --- c         bc 对应的16进制
            System.out.println(Integer.toString(255,16)); // ff*/

            //
            /*byte[] bytes1 = DesUtils.hexStr2ByteArr(byte16Str) ;
            for (int i=0; i< bytes1.length ; i++ ) {
                System.out.println("bytes1["+i+"]:" + bytes1[i] );
            }*/
            desUtils.getKey("".getBytes());
            byte[] arrBTmp = "abc".getBytes();
            byte[] arrB = new byte[8];

            System.out.println("length:"+arrBTmp.length);
            // 将原始字节数组转换为8位
            for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
                // byte 默认为 0
                arrB[i] = arrBTmp[i];
            }

            for(byte b:arrB){
                System.out.println( "b:" + b );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
