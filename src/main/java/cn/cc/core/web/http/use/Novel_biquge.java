package cn.cc.core.web.http.use;

import cn.cc.core.web.http.header.Header;
import cn.cc.core.web.http.utils.HttpClientUtils;
import cn.cc.core.io.utils.PrintWriterUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author c.c.
 * @date 2020/12/8
 */
public class Novel_biquge {

    // JDK 永远滴神
    // HttpStatus 已经定义好了 所涉及的状态码

    // 笔趣阁需要多次重定向，附带 Referer= 来获取数据
    // Referer= http://www.biquge.info/10_10229/5008000.html
    public static String path = "http://www.biquge.info";
    public static String novelUrl = "http://www.biquge.info/10_10229";
    public static String novelName = "斗破苍穹";
    public static String novelUrlCatalogue = "http://www.biquge.info/10_10229/5008001.html";
    public static Set<Map.Entry<Object,Object>> set = new HashSet<>();
    public static void main(String[] args) {
        novelCatalogue(novelUrl,"",novelName);
        // 转发
        //novelName(novelUrl,"斗破苍穹,第三十章 辱人者，人恒辱之","");
        //novelName(novelUrl + "?zunwlm=nic7z1");
    }


    // 拿到 书网址
    // 取出书目录
    // 取出书内容
    // 保存


    //取出所有章节的目录
    public static void  novelCatalogue(String novelUrl,String referer,String title){
        try {
            Map<String, String> map = Header.biqugeMap();
            if (referer != null) {
                map.put("Referer", referer);
            }
            //String result = HttpURLConnectionUtil.sendToUrlRequest(novelUrlCatalogue,"GET",ConstantCharSet.UTF_8, map);
            String result = HttpClientUtils.getWebContent(novelUrl, map);
            //System.out.println(result);

            if (regex(result, title)) {
                // 如果匹配到了就取出章节信息
                //novelCatalogue(result,referer, title);
                novelCatalogueHtml(result);
            }
        }catch (Exception e){
            System.err.println("重试中novelCatalogue()---");
            novelCatalogue(novelUrlCatalogue,title,referer);
        }
    }

    //  处理html，取出章节信息
    public static void novelCatalogueHtml(String html){
        Document document = Jsoup.parse(html);
        Element element = document.getElementById("list");
        Elements elements = element.getElementsByTag("a");
        for(Element e:elements){
            System.out.println(e.attr("href")+" "+e.text());
            novelName(novelUrl+"/"+e.attr("href"),e.text(),"");
        }
    }

    // 根据小说章节地址取出html
    public static void novelName(String novelUrlCatalogue,String title,String referer){
        try {
            Map<String, String> map = Header.biqugeMap();
            if (referer != null) {
                map.put("Referer", referer);
            }
            //String result = HttpURLConnectionUtil.sendToUrlRequest(novelUrlCatalogue,"GET",ConstantCharSet.UTF_8, map);
            String result = HttpClientUtils.getWebContent(novelUrlCatalogue, map);
            //System.out.println(result);

            if (regex(result, title)) {
                novelNameHtml(result, title);
            }else {
                //  如果返回false 也要重试
                // novelName(novelUrlCatalogue,title,referer);
            }
        }catch (Exception e){
            System.err.println("重试中novelName()---"); // 设置代理ip
            novelName(novelUrlCatalogue,title,referer);
        }
    }

    // 匹配出小说下一次重定向的地址
    public static boolean regex(String result,String title) {
        Pattern pattern = Pattern.compile("window.location=\".{0,100}\"");
        Matcher matcher = pattern.matcher(result);
        //是否匹配到了
        if (matcher.find()) {// 进入后可以全匹配
            String param = matcher.group(0);
            System.out.println(param);
            param = param.replaceAll("window.location=\"","").replace("\"","");
            novelUrlCatalogue = path + param;
            System.out.println(novelUrlCatalogue);
            novelName(path + param,title,novelUrlCatalogue);
        } else if(result.contains(title)){
            System.out.println(title);
            return true;
        }else{
            System.err.println("error，未匹配到");
        }
        return false;
    }

    // 取出content 中的小说内容，并处理数据
    public static void novelNameHtml(String novelHtml,String title){
        Document document = Jsoup.parse(novelHtml);
        Element element = document.getElementById("content");
        String content = element.toString();
        System.out.println(content);
        // 去除头尾的元素
        content = content.replaceAll("<div id=\"content\">","")
                         .replaceAll("<!--go-->","")
                         .replaceAll("<!--over-->","")
                         .replaceAll("</div>","");
        // 空格转换
        content = content.replaceAll("&nbsp;"," ");
//        content = content.replaceAll("<br/><br/>","\r"); //
        content = content.replaceAll("<br>\r","\r"); // 因为有连续两个 br 单独的<br> 后面追加了\r表示回车符号
        content = content.replaceAll("<br>",""); //
        // System.out.println(content);
        save(title);
        save(content);
    }

    // 保存内容
    public static void save(String content){
        PrintWriterUtils.fileWriter("E:\\novel\\biquge","斗破苍穹.txt",content);
    }

}
