package cn.cc.core.basemethod;

/**
 * Yukino
 * 2020/4/23
 */
public class PrimaryDataType {

    //针对基本数据的操作

    /**
     * ASCII ((American Standard Code for Information Interchange): 美国信息交换标准代码）是基于拉丁字母的一套电脑编码系统，
     * 主要用于显示现代英语和其他西欧语言。它是最通用的信息交换标准，并等同于国际标准ISO/IEC 646。ASCII第一次以规范标准的类型发表是在1967年，
     * 最后一次更新则是在1986年，到目前为止共定义了128个字符
     */
    public static void main(String[] args) {
        allASCII();
    }

    public static void allASCII(){
        //一共128个 依次输出
        // 二进制 八进制 十进制 十六进制 缩写 jjie解释
        System.out.println("二进制 八进制 十进制 十六进制 对应字符");
        for(int i=0;i<128;i++){
          Integer.toBinaryString(i);
          Integer.toOctalString(i);
          Integer.toHexString(i);
          System.out.println(Integer.toBinaryString(i)+"---"+Integer.toOctalString(i)+"---"+i+"---0x"+Integer.toHexString(i)+"---"+(char)i);
        }
    }

}
