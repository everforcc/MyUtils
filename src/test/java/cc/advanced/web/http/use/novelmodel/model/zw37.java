package cc.advanced.web.http.use.novelmodel.model;

import cc.resource.PropertiesHeader;
import cc.use.url.http.novelmodel.flow.CrawFlow2;
import cc.use.url.http.novelmodel.model.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author c.c.
 * @date 2021/2/4
 */
public class zw37 {

    // 根目录
    static RootModel rootModel = new RootModel();
    static {
        // 网站根目录
        rootModel.setHeaders(PropertiesHeader.zw37Map());
        rootModel.setRootUrl("https://www.37zw.net/");
        rootModel.setCharset("gbk");
        rootModel.setNovelTypeUrlPattern("//div[@class='nav']//li//a/@href/regex('/xiaoshuo[0-9]{1}/')");

        // 类型
        TypeModel typeModel = new TypeModel();
        typeModel.setNovelUrlPattern("//div[@class='newlistmulu']//a/@href");
        rootModel.setTypeModel(typeModel);

        // 搜索
        rootModel.setSearchUrl("https://www.37zw.net/s/so.php");
        SearchModel searchModel = new SearchModel();
        searchModel.setNovelUrlPattern("//div[@class='novellist']//a/@href");
        searchModel.setNovelNamePattern("//div[@class='novellist']//a/text()");
        searchModel.setSearchType("GET");

        Map<String,String> map = new HashMap<>();
        map.put("type","articlename");
        searchModel.setKeyMap(map);
        searchModel.setSearchKey("s");

        rootModel.setSearchModel(searchModel);

        // 章节
        BookModel bookModel = new BookModel();
        bookModel.setChapterListPattern("//div[@id='list']//dd//a/@href");
        rootModel.setBookModel(bookModel);

        ContentModel contentModel = new ContentModel();
        contentModel.setContent("//div[@id='content']/text()");
        contentModel.setTitle("//div[@class='bookname']//h1/text()");
        rootModel.setContentModel(contentModel);

    }

    @Test
    void root(){
        try {
            List<String> stringList = CrawFlow2.root(rootModel);
            stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void type(){
        try {
            List<String> stringList = CrawFlow2.type("https://www.37zw.net/xiaoshuo1/",rootModel);
            stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void search(){
        try {
            List<String> stringList = null;
            stringList = CrawFlow2.search("女儿",rootModel);
            stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void book(){
        try {
            List<String> stringList = CrawFlow2.book("https://www.37zw.net/11/11690/",rootModel);
            stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void content(){
        try {
            CrawFlow2.content("https://www.37zw.net/11/11690/13857209.html",rootModel);
            //stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void json(){
        System.out.println(rootModel);
    }

    @Test
    void t1(){
        System.out.println(CrawFlow2.requestForHtml("https://www.37zw.net/s/so.php?type=articlename&s=%C5%AE%B6%F9", rootModel));
    }

    @Test
    void t2() throws IOException {
        String url = "https://www.37zw.net/s/so.php";
        Map<String,String> map = rootModel.getSearchModel().getKeyMap();
        map.put("s","女儿");
        String html = Jsoup.connect(url).headers(rootModel.getHeaders())
                .method(Connection.Method.GET)
                .timeout(3000)
                .data(map)
                .execute().charset(rootModel.getCharset()).body();
        Document document = Jsoup.parse(html);

        System.out.println(document.body());

    }

}
