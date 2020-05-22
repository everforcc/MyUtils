package cn.cc.utils.math;



import org.junit.jupiter.api.Test;

/**
 * 基本数据类型
 */
public class Base {
    //二进制
    //int binary = 0b11;
    // 八进制
    int octonary = 011;
    // 十进制
    int dec = 11;
    // 十六进制
    int hexadecimal = 0x11;

    // 二进制十进制等 进制转换
    @Test
    public void basicData(){
        System.out.println(octonary);    //9
        System.out.println(dec);          // 11
        System.out.println(hexadecimal); // 17

        int i = 15;
        System.out.println("十进制15对应的二进制为："+Integer.toBinaryString(i));  //1111
        System.out.println("十进制15对应的八进制为："+Integer.toOctalString(i));   //17
        System.out.println(i);  //15
        System.out.println("十进制15对应的十六进制为："+Integer.toHexString(i));    //f
        System.out.println("---------------------------------------------");
        System.out.println("Integer.toString.16:"+Integer.toString(i,16));
        System.out.println("Integer.toString.2:"+Integer.toString(i,2));
        System.out.println("Integer.toString.10:"+Integer.toString(i,10));
        /*
         *   字母开头的是标识符（Identifier），如变量名、函数名等
         *   int o123;
         *   int a = o123;
         *   歧义了
         *   https://www.zhihu.com/question/22477934/answer/21487588
         * */
    }





}
