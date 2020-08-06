package cn.cc.core.http.action.use;

import cn.cc.core.http.action.common.HttpURLConnectionUtil;
import cn.cc.core.http.crawler.webmagic.pageprocessor.PageProcessorCommon;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WenKu8 {
    // private static String url = "https://www.wenku8.net/novel/1/1973/index.htm";
    private static String url = "https://www.wenku8.net/novel/1/1973/74161.htm";

    // 文库的
    public static void main(String[] args) {
        try {

            // 1.返回一个html的串
            // String result = HttpURLConnectionUtil.sendToUrlRequest(url,"GET","","gbk");
            // System.out.println(result);
            // 2.用jsoup来解析
            Document document = PageProcessorCommon.byFile("F:\\aly\\wenku8\\page2.txt");
            Element element = document.getElementById("content");

            String result = parseElse(element.toString());
            System.out.println(result);
            // 这样也可以但是，样式会丢


        } catch (Exception e) {
            System.out.println("反正就是异常了");
            e.printStackTrace();
        }
    }

    private static String parseElse(String element){
        // 首先去掉首位
        element = element.replaceAll("<br>","\r\n");
        element = element.replaceAll("&nbsp;"," ");

        // 图片
        regexTag(element);

        // 最后替换掉所有不需要的tag
        element = element.replaceAll("<.*>","");

        return element;
    }

    private static String regexTag(String string){
        System.out.println(string);
        System.out.println(string.length());
        //String c="《wo》 》";
        // ?一次 *号多次
        //Pattern pattern = Pattern.compile("<.* id=\"content\".*>");
        //        Pattern pattern = Pattern.compile("</div>");
        Pattern pattern = Pattern.compile("<img.*>");
        Matcher matcher = pattern.matcher(string);
        //是否匹配到了
        if (matcher.find()) {// 进入后可以全匹配
            //System.out.println(matcher.group(0)); // 《wo》
            String picTag = matcher.group(0);
            System.out.println(picTag);
            String pic = Pattern.compile("src=\".*\"").matcher(picTag).group(0);
            System.out.println(pic);
        }

        return "";
    }

}
