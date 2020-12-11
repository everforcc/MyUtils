package cn.cc.core.http.urlrequest.webmagic;

import net.sf.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * Yukino
 * 2020/5/12
 */
public class PageProcessor {
    /**
     * PageProcessor负责解析页面，抽取有用信息，以及发现新的链接。WebMagic使用Jsoup作为HTML解析工具，并基于其开发了解析XPath的工具Xsoup。

     在这四个组件中，PageProcessor对于每个站点每个页面都不一样，是需要使用者定制的部分。
     */

    /**
     * 先不使用数据库
     * 随后用boot搭建环境
     */

    /**
     * 用来存下一章节的链接
     */
    private String NextUrl;

    private final String LINGYU="http://www.paoshu8.com/0_157/?hwvkbw=x3ggc&qetwxw=gr6ma2&tcjqri=xtzlk2&jabafu=nb6la2&hyrwja=zos8z&tmlelm=zayzk2&dgzybg=zos8m1";

    /**
     * 1.获取书籍链接
     * 2.下载
     */

    /**
     * 1.获取第一章
     * 2.获取内容
     * 3.判断是否为最后一章
     * 4.结束当前线程
     */

    public String novelIndexByTitle(String url){
        // 获取首页链接
        return "首页url";
    }

    public String nextUrl(){
        // 获取下一章的url
        // 判断是否为最后一章
        return "下一章url";
    }

    public String novelContent(String pageUrl ){
        // 根据页面地址获取页面内容
        return "content";
    }

    public void novelDown(){
        // 下载到文件中
    }

    // 每个元素的地址都在固定位置

    /**
     * 1.首页地址
     * 2.获取链接
     */
    @Test
    public void crawFlow(){
        try {
            getHTMLByURL(LINGYU);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getHTMLByURL(String urlPath)throws Exception{
        Connection connection1 = Jsoup.connect(urlPath);// 获取连接

        Map<String,String> mapHeards = (Map) JSONObject.fromObject(heardsJSON);

        for(Map.Entry<String, String> entry : mapHeards.entrySet()){
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            //System.out.println(mapKey+":"+mapValue);
            connection1.header(mapKey, mapValue);// 配置模拟浏览器
        }



        Connection.Response response = connection1.execute();// 获取响应
        String resHTLM = response.body();
        //System.out.println(response.body());
        //System.out.println(resHTLM);
        Document document = Jsoup.parse(resHTLM);
        Element element = document.getElementById("list");
        System.out.println(element.hasText());
        Elements elements = element.getElementsByTag("dl");
        Elements elements1 = elements.get(0).getElementsByTag("dd");
        System.out.println(elements1.get(10));

    }

    String heardsJSON = "{\n" +
            "\t\"Accept\": \"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\",\n" +
            "\t\"Accept-Encoding\": \"gzip, deflate\",\n" +
            "\t\"Accept-Language\": \"zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7\",\n" +
            "\t\"Cache-Control\": \"max-age=0\",\n" +
            "\t\"Connection\": \"keep-alive\",\n" +
            "\t\"Cookie\": \"width=85%25; Hm_lvt_9352f2494d8aed671d970e0551ae3758=1589191858,1589265866\",\n" +
            "\t\"Host\": \"www.paoshu8.com\",\n" +
            "\t\"Referer\": \"http://www.paoshu8.com/0_157/?hwvkbw=x3ggc&qetwxw=gr6ma2&tcjqri=xtzlk2&jabafu=nb6la2\",\n" +
            "\t\"Upgrade-Insecure-Requests\": \"1\",\n" +
            "\t\"User-Agent\": \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/82.0.4083.0 Safari/537.36\"\n" +
            "}" ;


    public void setHeardsJSON(){

        Map<String,String> mapHeards = (Map) JSONObject.fromObject(heardsJSON);

        for(Map.Entry<String, String> entry : mapHeards.entrySet()){
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            System.out.println(mapKey+":"+mapValue);
        }
    }

}
