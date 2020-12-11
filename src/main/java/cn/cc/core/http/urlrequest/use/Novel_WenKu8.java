package cn.cc.core.http.urlrequest.use;

import cn.cc.core.http.urlrequest.webmagic.pageprocessor.PageProcessorCommon;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Novel_WenKu8 {
    // private static String url = "https://www.wenku8.net/novel/1/1973/index.htm";
    private static String url = "https://www.wenku8.net/novel/1/1973/74161.htm";

    // 文库的
    public static void main(String[] args) {
        try {

            // 1.返回一个html的串
            // String result = HttpURLConnectionUtil.sendToUrlRequest(url,"GET","","gbk");
            // System.out.println(result);

            // 2.用jsoup来解析
            Document document = PageProcessorCommon.byFile("F:\\aly\\wenku8\\page3.txt");
            Element element = document.getElementById("content");

            /*String result = parseElse(element.toString());
            System.out.println(result);*/
            // 这样也可以但是，样式会丢

            String divString = element.toString();
            String[] divAry = divString.split("\r\n");
            //System.out.println(element.toString());
            for(String s:divAry){
                System.out.println(s);
            }

            // 保存为md文件，既可以带格式，又不是那么麻烦

        } catch (Exception e) {
            System.out.println("反正就是异常了");
            e.printStackTrace();
        }
    }

    private static String replaceToMD(){

        return "";
    }

    private static String parseElse(String element){
        // 首先去掉首位
        element = element.replaceAll("<br>","   ");
        element = element.replaceAll("&nbsp;"," ");

        // 图片
        String pic = regexTag(element);

        // 最后替换掉所有不需要的tag
        element = element.replaceAll("<.*>","");
        element+=pic;
        return element;
    }

    private static String regexTag(String string){
        //String c="《wo》 》";
        // ?一次 *号多次
        //Pattern pattern = Pattern.compile("<.* id=\"content\".*>");
        //        Pattern pattern = Pattern.compile("</div>");
        Pattern pattern = Pattern.compile("<img.*>");
        Matcher matcher = pattern.matcher(string);

        String pic = "";
        //是否匹配到了
        if (matcher.find()) {// 进入后可以全匹配
            String picTag = matcher.group(0);
            //                           src=\".*?\""
            Pattern pattern1 = Pattern.compile("src=\".*?\"");
            Matcher matcher1 = pattern1.matcher(picTag);
            if(matcher1.find()) {
                pic = matcher1.group(0);
                pic=pic.substring(5,pic.length()-1);
            }
        }
        return pic;
    }

}
