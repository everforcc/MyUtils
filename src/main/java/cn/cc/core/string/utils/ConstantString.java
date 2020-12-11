package cn.cc.core.string.utils;

/**
 * @author c.c.
 * @date 2020/12/10
 */
public class ConstantString {
    /**
     * 百度百科里是这么介绍全角和半角的含义的：
     * “全角就是字母和数字等与汉字占等宽位置的字。
     * 半角就是ASCII方式的字符，在没有汉字输入法起作用的时候输入的字母数字和字符都是半角的。
     */

    /**
     1.不间断空格\u00A0,主要用在office中,让一个单词在结尾处不会换行显示,快捷键ctrl+shift+space ;
     2.半角空格(英文符号)\u0020,代码中常用的;
     3.全角空格(中文符号)\u3000,中文文章中使用;
     */
    public static final String space = "\u0020";
    private static final String chineseSpace = "\u3000";

    /**
     \t是补全当前字符串长度到8的整数倍，最少1个最多8个空格
     补多少要看你\t前字符串长度
     比如当前字符串长度10，那么\t后长度是16，也就是补6个空格
     如果当前字符串长度12，此时\t后长度是16，补4个空格
     */
    public static final String autoComplete = "\t";

    public static final String chineseUnicode = "[\u4e00-\u9fa5]";
}
