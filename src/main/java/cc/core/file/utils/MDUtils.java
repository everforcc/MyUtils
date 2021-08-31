package cc.core.file.utils;

/**
 * @author c.c.
 * @date 2020/12/11
 */
public class MDUtils {

    // 有些小说没有格式，用来生成md文件便于阅读

    public static void main(String[] args) {

    }

    public static String mdTitle(String title){
         return "#### " + title + "   \n";
    }

    public static String font_SimSun(String content){
        String[] contentList = content.split("\\n");
        String result = "<font face=\"SimSun\"  size=4 >   \n";
        for(String str:contentList){
            str = str.trim();
            if(!str.isEmpty()){
                // 手动空格不行
                //result += "                " + str + "  \n";
                // html 空格
                result += "&nbsp;&nbsp;" + str + "   \n";
                // \n 换行有↓箭头
                // html 换行，但是有 <br /> 不能隐藏
                //result += str + "<br />" ;
            }
        }
        result += "</font>   \n";
        System.out.println("result:" + result);
        return result;
    }

    public static String mdContent(String content){
        String[] contentList = content.split("\\n");
        String result = "";
        for(String str:contentList){
            if(!str.trim().isEmpty()){
               System.out.println(" >>>:" + str + ";");
               result += "#### " + str + "   \n";
            }
        }
        return result;
    }

    /*public static String mdContent(String content){
        return content;
    }*/

    // md 一般都支持 img 标签
    public static String mdImg(String url){
        // ![Image text](www.baidu.com/img/flexible/logo/pc/result.png)
        // ![](F:\resources\2.图片\heard\head.jpg) 本地路径 ./本级 ../上一级
        return "![Image text](" + url + ")   \n";
    }



}
