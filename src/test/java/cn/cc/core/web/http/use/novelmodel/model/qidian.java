package cn.cc.core.web.http.use.novelmodel.model;

import cn.cc.core.web.http.header.Header;
import cn.cc.use.url.http.novelmodel.flow.CrawFlow2;
import cn.cc.use.url.http.novelmodel.model.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author c.c.
 * @date 2021/2/7
 */
public class qidian {


    // 根目录
    static RootModel rootModel = new RootModel();
    static {

        // 网站个根目录
        rootModel.setRootUrl("https://www.qidian.com/");
        rootModel.setCharset("UTF-8");
        rootModel.setSearchUrl("https://www.qidian.com/search");
        rootModel.setHeaders(Header.qidianMap());

        // 搜索
        SearchModel searchModel = new SearchModel();
        searchModel.setSearchType("GET");
        searchModel.setNovelUrlPattern("//div[@class='book-img-text']//div[@class='book-mid-info']//h4//a/@href");
        searchModel.setNovelNamePattern("//div[@class='book-img-text']//div[@class='book-mid-info']//h4//a/allText()");
        searchModel.setKeyMap(new HashMap<>());
        searchModel.setSearchKey("kw");
        rootModel.setSearchModel(searchModel);

        // 匹配小说的类型地址
        rootModel.setNovelTypeUrlPattern("//div[@class='nav']//a/regex('/[a-z]{0,20}xiaoshuo/')");
        TypeModel typeModel = new TypeModel();
        typeModel.setNovelUrlPattern("//div[@id='newscontent']//span[@class='s2']/a/@href");
        rootModel.setTypeModel(typeModel);

        // 章节
        BookModel bookModel = new BookModel();
        bookModel.setChapterListPattern("//ul[@class='cf']//a/@href");
        rootModel.setBookModel(bookModel);

        // 内容
        ContentModel contentModel = new ContentModel();
        contentModel.setTitle("//div[@class='bookname']//h1/text()");
        contentModel.setContent("//div[@id='content']");
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
            List<String> stringList = CrawFlow2.type("http://www.paoshuzw.com/xuanhuanxiaoshuo/",rootModel);
            stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void search(){
        List<String> stringList = null;
        try {
            stringList = CrawFlow2.search("斗破苍穹",rootModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stringList.forEach(System.out::println);
    }

    @Test
    void book(){
        try {
            List<String> stringList = CrawFlow2.book("https://book.qidian.com/info/1209977#Catalog",rootModel);
            stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void content(){
        try {
            CrawFlow2.content("http://www.paoshuzw.com/45/45430/26919498.html",rootModel);
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
    void t2(){
        try {
            // objectType=2&type=articlename&s=%C5%AE%B6%F9
            Connection connection = Jsoup.connect(rootModel.getSearchUrl()).headers(rootModel.getHeaders())
                    .method(Connection.Method.POST)
                    .data("searchkey","斗破苍穹")
                    .postDataCharset(rootModel.getCharset());

            String response = connection.execute().body();
            System.out.println( response );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
