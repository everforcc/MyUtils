package cc.advanced.web.http.use.novelmodel.model;

import cc.resource.PropertiesHeader;
import cc.advanced.web.http.use.novelmodel.flow.CrawFlow2;
import cc.use.url.http.novelmodel.model.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author c.c.
 * @date 2021/2/7
 */
public class biquge5200 {

    // 根目录
    static RootModel rootModel = new RootModel();
    static {
        // 网站根目录
        rootModel.setHeaders(PropertiesHeader.bqkanMap());
        rootModel.setRootUrl("https://www.biquge5200.cc/");
        rootModel.setCharset("gbk");
        rootModel.setNovelTypeUrlPattern("//div[@class='nav']//li//a/@href/regex('//www\\.biquge5200\\.cc/[a-z]{1,10}xiaoshuo/')");// /regex('https://www\.biquge5200\.cc/[a-z]{1,10}xiaoshuo/')

        // 类型
        TypeModel typeModel = new TypeModel();
        typeModel.setNovelUrlPattern("//div[@id='newscontent']//span[@class='s2']//a/@href");
        rootModel.setTypeModel(typeModel);

        // 搜索
        rootModel.setSearchUrl("https://www.biquge5200.cc/modules/article/search.php");
        SearchModel searchModel = new SearchModel();
        searchModel.setNovelUrlPattern("//div[@id='hotcontent']//a/@href");
        searchModel.setSearchType("GET");

        Map<String,String> map = new HashMap<>();
        searchModel.setKeyMap(map);
        searchModel.setSearchKey("searchkey");
        searchModel.setNovelNamePattern("//div[@id='hotcontent']//a/text()");
        rootModel.setSearchModel(searchModel);

        // 章节
        BookModel bookModel = new BookModel();
        bookModel.setChapterListPattern("//div[@id='list']//a/@href");
        rootModel.setBookModel(bookModel);

        ContentModel contentModel = new ContentModel();
        contentModel.setContent("//div[@id='content']"); // /allText()
        contentModel.setTitle("//div[@class='bookname']//h1/allText()");
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
            List<String> stringList = CrawFlow2.type("https://www.biquge5200.cc/xuanhuanxiaoshuo/",rootModel);
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
            List<String> stringList = CrawFlow2.book("https://www.biquge5200.cc/0_7/",rootModel);
            stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void content(){
        try {
            CrawFlow2.content("https://www.biquge5200.cc/0_7/2046.html",rootModel);
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
