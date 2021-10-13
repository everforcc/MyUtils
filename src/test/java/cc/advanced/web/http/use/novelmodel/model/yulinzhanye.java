package cc.advanced.web.http.use.novelmodel.model;

import cc.resource.PropertiesHeader;
import cc.use.url.http.novelmodel.flow.CrawFlow2;
import cc.use.url.http.novelmodel.model.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author c.c.
 * @date 2021/2/4
 */
public class yulinzhanye {

    static RootModel rootModel = new RootModel();
    static{

        // 根目录
        rootModel.setHeaders(PropertiesHeader.yulinzhanyeMap());
        rootModel.setCharset("gbk");
        rootModel.setRootUrl("http://www.yulinzhanye.la");
        rootModel.setSearchUrl("http://www.yulinzhanye.la/s.php");
        // 小说类型


        // 匹配小说的类型地址
        rootModel.setNovelTypeUrlPattern("//div[@class='channel']/a/@href/regex('/fenlei/.{1,10}')");
        TypeModel typeModel = new TypeModel();
        typeModel.setNovelUrlPattern("//div[@class='mod block rank-switch']//div[@class='bd']//ul[1]//a/@href");
        rootModel.setTypeModel(typeModel);

        SearchModel searchModel = new SearchModel();
        searchModel.setSearchType("POST");
        Map<String,String> map = new HashMap<>();
        map.put("objectType","2");
        map.put("type","articlename");
        searchModel.setKeyMap(map);
        searchModel.setSearchKey("s");
        searchModel.setEncode("gbk");
        searchModel.setNovelUrlPattern("//div@class='bd']//li[@class='column-2 ']//a[@class='name']/@href");
        searchModel.setNovelNamePattern("//div@class='bd']//li[@class='column-2 ']//a[@class='name']/text()");
        rootModel.setSearchModel(searchModel);

        // 章节
        BookModel bookModel = new BookModel();
        bookModel.setMenuUrlPattern("");
        // 待办语法
        //bookModel.setChapterListPattern("//div[@class='mod block update chapter-list']/tag[2]//ul[@class='list']/a/@href");
        bookModel.setChapterListPattern("//div[@class='container']//div[7]//div[@class='bd']//ul[@class='list']//a/@href"); // /ul[@class='list']/a/@href
        //bookModel.setNextChapterListPattern("//a[@class='nextPage']/@href");
        rootModel.setBookModel(bookModel);

        // 内容
        ContentModel contentModel = new ContentModel();
        contentModel.setTitle("//h1[@class='page-title']"); //
        contentModel.setContent("//div[@class='page-content font-large']/p");
            /*contentModel.setTitle("//div[class='container']");
            contentModel.setContent("//div[class='container']");*/
        contentModel.setNextPage("//center[@class='chapterPages']//a/@href");
        contentModel.setCharset("utf-8");
        rootModel.setContentModel(contentModel);



    }

    // 1.首页
    @Test
    void root(){
        try {
            List<String> typeList = CrawFlow2.root(rootModel);
            typeList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void type(){
        try {
            List<String> stringList = CrawFlow2.type("http://www.yulinzhanye.la/fenlei/7_1.html",rootModel);
            stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void search(){
        try {
            List<String> stringList = null;
            stringList = CrawFlow2.search("斗破",rootModel);
            stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void book(){
        try {
            List<String> stringList = CrawFlow2.book("http://www.yulinzhanye.la/6/6774/",rootModel);
            stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void content(){
        try {

            CrawFlow2.content("http://www.yulinzhanye.la/20/20304/508768_3.html",rootModel);
            //stringList.forEach(System.out::println);
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println("异常");
        }
    }

    @Test
    void json(){
        System.out.println(rootModel);
    }

    @Test
    void t2(){
        // %C5%AE%B6%F9
        try {
            System.out.println(URLDecoder.decode("%C5%AE%B6%F9","GBK"));
            /*System.out.println(URLEncoder.encode("%C5%AE%B6%F9","GBK"));
            System.out.println(URLDecoder.decode("女儿"));
            System.out.println(URLEncoder.encode("女儿"));*/
            System.out.println(URLEncoder.encode("女儿","GBK"));
            //System.out.println(URLEncoder.encode("女儿","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void t3(){
        try {
            // objectType=2&type=articlename&s=%C5%AE%B6%F9
            Connection connection = Jsoup.connect("http://www.yulinzhanye.la/s.php").headers(PropertiesHeader.yulinzhanye_searchMap())
                    .cookie("Cookie"," __cfduid=dee02bfc3e30dffbdfe45b3c75cf1d02b1612186554; UM_distinctid=1775dceab98b70-0cb532ca859bf8-6c5a742c-1fa400-1775dceab99b57; Hm_lvt_ff727c5985a6b2da2c623aef1ab0d8db=1612186562,1612234189; PHPSESSID=1somln1mvo3t2uq7ln69fk4ma5; CNZZDATA1279262821=2126683797-1612182810-%7C1612237269; Hm_lpvt_ff727c5985a6b2da2c623aef1ab0d8db=1612241568")
                    .method(Connection.Method.POST)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4356.6 Safari/537.36")
                    .data("objectType","2")
                    .data("type","articlename")
                    .data("page","2")
                    .data("s","%C5%AE%B6%F9");
            String response = connection.execute().body();
            System.out.println( response );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
