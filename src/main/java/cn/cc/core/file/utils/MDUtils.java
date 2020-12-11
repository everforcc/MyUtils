package cn.cc.core.file.utils;

/**
 * @author c.c.
 * @date 2020/12/11
 */
public class MDUtils {

    // 有些小说没有格式，用来生成md文件便于阅读

    public static void main(String[] args) {

    }

    public static String mdTitle(String title){
         return "#### " + title;
    }

    public static String mdNextLine(String content){
        return content + "  ";
    }

    public static String mdContent(String content){
        return content;
    }

    public static String mdImg(String url){
        // ![Image text](www.baidu.com/img/flexible/logo/pc/result.png)
        // ![](F:\resources\2.图片\heard\head.jpg) 本地路径
        return "![Image text](" + url + ")";
    }



}
