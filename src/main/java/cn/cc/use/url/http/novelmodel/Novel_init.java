package cn.cc.use.url.http.novelmodel;

import cn.cc.core.io.utils.PrintWriterUtils;
import cn.cc.core.web.http.header.Header;
import cn.cc.use.url.http.novelmodel.model.BookModel;
import cn.cc.use.url.http.novelmodel.model.ContentModel;
import cn.cc.use.url.http.novelmodel.model.RootModel;
import cn.cc.use.url.http.novelmodel.model.TypeModel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.util.*;

/**
 * @author c.c.
 * @date 2021/2/1
 */
public class Novel_init {

    public static void main(String[] args) {
        RootModel rootModel = init_RootModel();
        rootModel.setBookModel(init_BookModel());
        rootModel.setContentModel(init_ContentModel());
        rootModel.setTypeModel(init_TypeModel());
        System.out.println(rootModel.toString());

    }

    static {
        // 加载系统json路径
    }
    // 语法 https://github.com/code4craft/xsoup

    // 初始化一些json 放入爬取列表

    public static List<RootModel> init(){
        List<RootModel> rootModelList = new ArrayList<RootModel>();
        File file = new File("src/main/resources/json");
        File[] files = file.listFiles();
        if(files!=null&&files.length>0){
            for(File f:files){
                String json = getJSON(f.getAbsolutePath());
                JSONObject jsonObject = JSON.parseObject(json);
                // 增加类型信息
                jsonObject.put("@type","cn.cc.core.web.http.use.novelmodel.model.RootModel");
                json = jsonObject.toString();
                RootModel rootModel = (RootModel)JSON.parse(json);
                rootModelList.add(rootModel);
            }
        }else {
            System.err.println("请配置网站json");
        }
        return rootModelList;
    }

    // 网站基本信息
    public static RootModel init_RootModel(){
        RootModel rootModel = new RootModel();
        rootModel.setHeaders(Header.lanwlMap());
        rootModel.setCharset("gbk");
        rootModel.setRootUrl("https://www.lanwl.com");
        rootModel.setSearchUrl("");
        rootModel.setNovelTypeUrlPattern("//div[@id='subnav']//a[@href!=/]/@href");
        return rootModel;
    }

    // 小说类型
    public static TypeModel init_TypeModel(){
        TypeModel typeModel = new TypeModel();
        typeModel.setNovelUrlPattern("//ul[@class='item-con']//a/@href");
        typeModel.setNextPagePattern("//a[@class='next']/@href");
        typeModel.setTotalPagePattern("//a[@class='last']/text()");
        return typeModel;
    }

    // www.yulinzhanye.la/6/6007/
    // 小说信息
    public static BookModel init_BookModel(){
        BookModel bookModel = new BookModel();
        bookModel.setMenuUrlPattern("//div[@class='book-link']//a/@href/regex('/txt/\\d{1,5}/\\d{1,5}/')");// 正则表达式也能用，
        bookModel.setChapterListPattern("//dl[@class='chapterlist']//a/@href");
        return bookModel;
    }

    /*public static SearchModel init_SearchModel(){

    }*/

    // 内容
    public static ContentModel init_ContentModel(){
        ContentModel contentModel = new ContentModel();
        contentModel.setTitle("//div[@id='BookCon']//h1/text()");
        contentModel.setContent("//div[@id='BookText']/text()");
        return contentModel;
    }

    public static String getJSON(String resource){

        try {
            // 静态方法和非晶态方法不同
            return PrintWriterUtils.fileReader(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /*
    *
    * // 网站基本信息
    public static RootModel init_RootModel(){
        RootModel rootModel = new RootModel();
        rootModel.setHeaders(Header.lanwlMap());
        rootModel.setCharset("gbk");
        rootModel.setRootUrl("https://www.lanwl.com");
        rootModel.setSearchUrl("");
        rootModel.setNovelTypeUrlPattern("//div[@id='subnav']//a[@href!=/]/@href");
        return rootModel;
    }

    // 小说类型
    public static TypeModel init_TypeModel(){
        TypeModel typeModel = new TypeModel();
        typeModel.setNovelUrlPattern("//ul[@class='item-con']//a/@href");
        typeModel.setNextPagePattern("//a[@class='next']/@href");
        typeModel.setTotalPagePattern("//a[@class='last']/text()");
        return typeModel;
    }

    // 小说信息
    public static BookModel init_BookModel(){
        BookModel bookModel = new BookModel();
        bookModel.setMenuUrlPattern("//div[@class='book-link']//a/@href/regex('/txt/\\d{1,5}/\\d{1,5}/')");// 正则表达式也能用，
        bookModel.setChapterListPattern("//dl[@class='chapterlist']//a/@href");
        return bookModel;
    }

    // 内容
    public static ContentModel init_ContentModel(){
        ContentModel contentModel = new ContentModel();
        contentModel.setTitle("//div[@id='BookCon']//h1/text()");
        contentModel.setContent("//div[@id='BookText']/text()");
        return contentModel;
    }

    * */
}
