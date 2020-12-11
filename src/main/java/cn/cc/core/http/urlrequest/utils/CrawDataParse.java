package cn.cc.core.http.urlrequest.utils;

/**
 * @author c.c.
 * @date 2020/12/9
 */
public class CrawDataParse {

    // 处理业务
    // 处理爬虫获得的字符串
    public static String parse(String content,String id){

        // 这个位置要修改为正则表达式 <.{0,100}> content
        content = content.replaceAll("<div id=\"" + id + "\">","")
                .replaceAll("<!--go-->","")
                .replaceAll("<!--over-->","")
                .replaceAll("</div>","");

        // 空格转换
        content = content.replaceAll("&nbsp;"," ");

        // 换行处理
        content = content.replaceAll("<br>\r","\r"); // 因为有连续两个 br 单独的<br> 后面追加了\r表示回车符号
        content = content.replaceAll("<br>",""); //

        // 图片，拼音处理

        return content;
    }

}
