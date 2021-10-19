package cc.advanced.web.http.webmagic;

import cc.resource.PropertiesHeader;
import cc.constant.ConstantFile;
import cc.core.io.PrintWriterUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.utils.HttpConstant;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author c.c.
 * @date 2020/12/11
 */
public class QinXiaoShuoPageProcessor implements PageProcessor {

    private static String novelUrlFormat = "http://www.qinxiaoshuo.com/read/0/%s/%s.html";

    private static String cookie = "__cfduid=d929787e41f17d6e78d0d4da22a341c1d1607606102; token=5fa014f29999497cb427972c:1:1607607529:d1e33bd6613f1e1cc0ea041879e6cb7b; Hm_lvt_670dc4f892f02bdafabb0500f3a778a0=1607606105,1607652735,1607666481; Hm_lpvt_670dc4f892f02bdafabb0500f3a778a0=1607673552";
    private static String useragent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31";
    private static String root = "www.qinxiaoshuo.com";

    private static Map<String,String> map = new HashMap<>();
    private static List<String> stringList = new ArrayList<>();
    private static int index = 0;
    private static DecimalFormat decimalFormat = new DecimalFormat("0000");
    @Override
    public void process(Page page) {

        //  自定义几种状态
        /**
         * 1.找书籍id         一次找10本 标记是否收集到尽头, 收集十本书之后记住当前的位置，退出， 记住id不能重复，如果没有书籍了标记结束，如果连接没有了，书籍也没了，就推出，没链接了，自己就推出了
         * 根据连接判断是书籍还是目录，流程还要再进行梳理
         *
         * 2.根据书籍 id 找目录 存下所有目录和顺序。 书名，某个div下的h1，作为文件夹顶级目录，
         * 3.根据目录下载小说 下载小说，按照目录章节下载，下载的时候从集合移除id，如果没有连接了，那么进入寻找书籍
         */

        if(stringList.size()==0) {
            System.out.println(">>> 收集连接");
            // chapter_content
            //System.out.println(page.getHtml().xpath("//div[@id='chapter_content']"));
            //System.out.println(">>>");
            //System.out.println(page);
            // 获得返回json，json包含了所有章节的信息
            Json json = page.getJson(); // 这个json不会用，也没个文档
            //System.out.println(json.toString());
            JSONObject jsonObject = JSON.parseObject(json.toString());
            JSONArray jsonArray_VolumesArray = jsonObject.getJSONArray("Volumes");
            String book_id = ((JSONObject) jsonArray_VolumesArray.get(0)).getString("Book_id");
            //System.out.println(book_id);
            //System.out.println("Volumes:" + jsonArray_VolumesArray.size());
            for (int i = 0; i < jsonArray_VolumesArray.size(); i++) {
                JSONObject jsonArray_Volumes = (JSONObject) jsonArray_VolumesArray.get(i);
                JSONArray jsonArray_Volumes_ChaptersArray = jsonArray_Volumes.getJSONArray("Chapters");
                String volume_name = jsonArray_Volumes.getString("Volume_name");
                //System.out.println("分卷:" + jsonArray_Volumes.getString("Volume_name") + "，章节数:" + jsonArray_Volumes_ChaptersArray.size());
                for (int j = 0; j < jsonArray_Volumes_ChaptersArray.size(); j++) {
                    index++;
                    JSONObject jsonArray_Volumes_Chapters = (JSONObject) jsonArray_Volumes_ChaptersArray.get(j);
                    String html_id = jsonArray_Volumes_Chapters.getString("Chapter_id");
                    String chapter_name = jsonArray_Volumes_Chapters.getString("Chapter_name");
                /*System.out.print(j + " >>> ");
                System.out.print("链接:" + jsonArray_Volumes_Chapters.getString("Chapter_id") + "\t");
                System.out.println("章节名:" + jsonArray_Volumes_Chapters.getString("Chapter_name"));*/
                    String url = String.format(novelUrlFormat, book_id, html_id);
                    //System.out.println(volume_name + ">>>" + url + ">>>" + chapter_name);
                    map.put(url, decimalFormat.format(index) + volume_name + chapter_name);
                    stringList.add(url);
                }
            }
            page.addTargetRequests(stringList);
        }else {
            String url = page.getUrl().get();
            // chapter_content
            //System.out.println(page.getHtml().xpath("//div[@id='chapter_content']").toString());
            String content = page.getHtml().xpath("//div[@id='chapter_content']").toString();
            content = content.replace("<br>","").replace("</div>","").replace("<div id=\"chapter_content\">","");
            saveTXT(ConstantFile.javaFilePath + "/java/wemagic/我的青春恋爱物语果然有问题md3",map.get(url) + ".md",content);

            /*System.out.println("stringList.size():" + stringList.size());
            System.out.println("map.size():" + map.size());
            System.out.println("url: " + url );
            System.out.println("stringList.remove(url):" + stringList.remove(url));*/
            System.out.println(">>> 线程执行顺序map.remove(url):" + map.remove(url));
            //System.out.println(map.get());

        }



        //System.out.println(">>>");
    }

    private Site site = Site
            .me()
            .setDomain(root)
            .setSleepTime(3000)
            .setTimeOut(3000) // 超时和等待3秒
            .addCookie("Cookie",cookie)
            .setUserAgent(useragent);

    @Override
    public Site getSite() {
        // Header.qinxiaoshuoMap()
        Map<String,String> map = PropertiesHeader.qinxiaoshuoMap();

        /*if (map != null && map.size() > 0) {
            for (Map.Entry entry : map.entrySet()) {
                //System.out.println((String) entry.getKey() + "---" + conn.getRequestProperty((String) entry.getKey()));
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                site.addHeader(key, value);
            }
        }*/

        //site.addHeader("","");
        return site;
    }

    public static void main(String[] args) {

        String bookMenu = "http://www.qinxiaoshuo.com/api/user/book/get/1162";
        Request request = new Request(bookMenu);
        request.setMethod(HttpConstant.Method.POST);
        //request.addCookie("Cookie",cookie);
        Map<String,String> map = PropertiesHeader.qinxiaoshuoMap();
        if (map != null && map.size() > 0) {
            for (Map.Entry entry : map.entrySet()) {
                //System.out.println((String) entry.getKey() + "---" + conn.getRequestProperty((String) entry.getKey()));
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                request.addHeader(key,value);
            }
        }
        //request.addHeader("User-Agent",useragent);

        // 自定义 Pipeline
        /*Spider.create(new QinXiaoShuoPageProcessor())
                .addUrl("http://www.qinxiaoshuo.com/read/0/1162/5d77c86d56fec85e5b0fc8ef.html")
                .run();*/
        Spider.create(new QinXiaoShuoPageProcessor()).addRequest(request).thread(10).run();;
    }

    public static void saveTXT(String path,String name,String content){
        PrintWriterUtils.fileWriter(path,name,content);
    }

}
