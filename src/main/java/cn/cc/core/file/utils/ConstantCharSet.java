package cn.cc.core.file.utils;

/**
 * @author c.c.
 * @date 2020/12/9
 */
public class ConstantCharSet {

    // 更具体的笔记里面有

    // 代码里面有很多写死的，以后都要总结到这里改掉，遇到一个改一个吧
    public static final String GBK = "GBK";
    public static final String UTF_8 = "UTF-8";
    public static final String ANSI = "ANSI";

    /* GB2312编码适用于汉字处理、汉字通信等系统之间的信息交换，通行于中国大陆；新加坡等地也采用此编码。中国大陆几乎所有的中文系统和国际化的软件都支持GB 2312。 */
    public static final String gb2312 = "gb2312";
    public static final String GB2312 = "GB2312";

    // 一般不用 Byte Order Mark，就是字节序标记
    /**
     *
     */
    public static final String UTF_8BOM = "UTF-8 BOM";


}
