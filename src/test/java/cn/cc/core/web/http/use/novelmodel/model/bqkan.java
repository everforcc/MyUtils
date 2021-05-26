package cn.cc.core.web.http.use.novelmodel.model;

import cn.cc.core.web.http.header.Header;
import cn.cc.use.url.http.novelmodel.flow.CrawFlow2;
import cn.cc.use.url.http.novelmodel.model.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author c.c.
 * @date 2021/2/2
 */
public class bqkan {


    // 根目录
    static RootModel rootModel = new RootModel();
    static {
        // 网站根目录
        rootModel.setHeaders(Header.bqkanMap());
        rootModel.setRootUrl("https://www.bqkan.com/");
        rootModel.setCharset("gbk");
        rootModel.setNovelTypeUrlPattern("//div[@class='nav']//li//a/@href/regex('/[a-z]{1,20}xiaoshuo/')");

        // 类型
        TypeModel typeModel = new TypeModel();
        typeModel.setNovelUrlPattern("//div[@class='l bd']//span[@class='s2']//a/@href");
        rootModel.setTypeModel(typeModel);

        // 搜索
        rootModel.setSearchUrl("https://so.biqusoso.com/s.php");
        SearchModel searchModel = new SearchModel();
        searchModel.setNovelUrlPattern("//div[@class='search-list']//a/@href");
        searchModel.setSearchType("POST");

        Map<String,String> map = new HashMap<>();
        map.put("ie","utf-8");
        map.put("siteid","biqukan.com");
        searchModel.setKeyMap(map);
        searchModel.setSearchKey("q");
        searchModel.setNovelNamePattern("//div[@class='search-list']//a/text()");
        rootModel.setSearchModel(searchModel);

        // 章节
        BookModel bookModel = new BookModel();
        bookModel.setChapterListPattern("//div[@class='listmain']//a/@href"); // dd[position()=1]//
        rootModel.setBookModel(bookModel);

        ContentModel contentModel = new ContentModel();
        contentModel.setContent("//div[@id='content']"); // /allText()
        contentModel.setTitle("//div[@class='content']//h1/allText()");
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
            List<String> stringList = CrawFlow2.type("https://www.bqkan.com/xuanhuanxiaoshuo/",rootModel);
            stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void search(){
        List<String> stringList = null;
        try {
            stringList = CrawFlow2.search("女儿",rootModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stringList.forEach(System.out::println);
    }

    @Test
    void book(){
        try {
            List<String> stringList = CrawFlow2.book("https://www.bqkan.com/79455_79455697/",rootModel);
            stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void content(){
        try {
            CrawFlow2.content("https://www.bqkan.com/79455_79455697/91942992.html",rootModel);
            //stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void json(){
        System.out.println(rootModel);
    }

}
