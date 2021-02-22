package cn.cc.use.url.http.novelmodel.flow;

import cn.cc.core.web.http.header.Header;
import cn.cc.use.url.http.novelmodel.Novel_init;
import cn.cc.use.url.http.novelmodel.model.BookModel;
import cn.cc.use.url.http.novelmodel.model.ContentModel;
import cn.cc.use.url.http.novelmodel.model.RootModel;
import cn.cc.use.url.http.novelmodel.model.TypeModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.xsoup.Xsoup;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author c.c.
 * @date 2021/2/1
 */
public class CrawFlow {

    private static Map map;
    private static String charset;
    private static String rootUrl;

    private static String typeUrl;
    private static boolean typeNextPageFlag = true;
    private static String typeTotalPage;

    // 网站跟
    private static RootModel rootModel;
    private static Map<String,RootModel> rootModelMap = new HashMap<>();
    private static List<String> urlList = new ArrayList<>();

    // 目录
    private static boolean chapterNextPageFlag = true;
    private static String chapterUrl;

    public static void main(String[] args) {
        try {
            // 初始化
            // init();//flow();
            // 搜索，返回可用url， 根据root去map里面取出，然后执行 book 详情
            RootModel rootModel = new RootModel();

            BookModel bookModel = new BookModel();
            bookModel.setMenuUrlPattern("");
            // 待办语法
            //bookModel.setChapterListPattern("//div[@class='mod block update chapter-list']/tag[2]//ul[@class='list']/a/@href");
            bookModel.setChapterListPattern("//div[@class='container']/div[7]//a/@href"); // /ul[@class='list']/a/@href
            bookModel.setNextChapterListPattern("//a[@class='nextPage']/@href");

            ContentModel contentModel = new ContentModel();
            contentModel.setTitle("//h1[@class='page-title']//text()"); //
            contentModel.setContent("//div[@class='page-content font-large']/p//text()");
            /*contentModel.setTitle("//div[class='container']");
            contentModel.setContent("//div[class='container']");*/

            rootModel.setBookModel(bookModel);
            rootModel.setContentModel(contentModel);

            rootModel.setRootUrl("rootModel");
            rootModel.setHeaders(Header.yulinzhanyeMap());
            rootModel.setCharset("utf-8");
            rootModel.setRootUrl("http://www.yulinzhanye.la");

            rootModelMap.put("http://www.yulinzhanye.la",rootModel);
            String bookUrl = "http://www.yulinzhanye.la/6/6007/";
            /*List<String> charList = book("http://www.yulinzhanye.la/6/6007/"); // http://www.yulinzhanye.la/6/6007/
            System.out.println(charList.size());
            System.out.println(" >>> ");
            charList.forEach(System.out::println);
            System.out.println(" >>> ");*/

            chapterUrl = bookUrl;
            while (chapterNextPageFlag){ // 是否有下一页目录
                List<String> stringList = book(chapterUrl);
                if (stringList != null && stringList.size() != 0) { // 循环当前目录
                    for (String str : stringList) {
                        System.out.println(str);
                        // 内容
                        content(rootModel.getRootUrl() + str);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 初始化配置列表
    public static void init(){
        List<RootModel> rootModelList = Novel_init.init();
        System.out.println("加载配置列表:共 " + rootModelList.size() + " 个");
        String url ;
        for(RootModel rootModel:rootModelList){
            url = rootModel.getRootUrl();
            System.out.println(" >>> " + url);
            rootModelMap.put(url,rootModel);
            urlList.add(url);
        }
    }

    // 爬取的流程，全站爬取，基本流程
    public static void flow(){
        try {
            List<String> typeList = root();
            for(String type:typeList) {
                typeUrl = rootUrl + type;
                // 类型
                while (typeNextPageFlag) { // 下一页类型
                    List<String> typePageList = type(typeUrl);
                    System.out.println("while >>> ");
                    typePageList.forEach(System.out::println);
                    for(String bookUrl:typePageList) { // 循环当前类型
                        // 章节
                        chapterUrl = bookUrl;
                        while (chapterNextPageFlag){ // 是否有下一页目录
                            List<String> stringList = book(bookUrl);
                            if (stringList != null && stringList.size() != 0) { // 循环当前目录
                                for (String str : stringList) {
                                    // 内容
                                    content(rootUrl + str);
                                }
                            }
                        }
                    }
                    System.out.println("typeNextPageFlag:" + typeNextPageFlag);
                    System.out.println("typeUrl:" + typeUrl);
                    System.out.println("总页数:" + typeTotalPage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //1.访问根目录
    public static List<String> root() throws Exception {

        Document document = requestForHtml(rootModel.getRootUrl());
        if(document != null){

            map = rootModel.getHeaders();
            charset = rootModel.getCharset();
            rootUrl = rootModel.getRootUrl();

            List<String> result = Xsoup.compile(rootModel.getNovelTypeUrlPattern()).evaluate(document).list();
            return result;
        }
        throw new Exception(rootModel.getRootUrl() + "异常");
    }

    //2.小说类型或者搜索
    public static List<String> type(String url) throws Exception {
        Document document = requestForHtml(url);
        if(document != null){
            TypeModel typeModel = rootModelMap.get(rootUrl(url)).getTypeModel();
            //System.out.println(document.body());
            List<String> result = Xsoup.compile(typeModel.getNovelUrlPattern()).evaluate(document).list();

            String flag = Xsoup.compile(typeModel.getNextPagePattern()).evaluate(document).get();

            typeTotalPage = Xsoup.compile(typeModel.getTotalPagePattern()).evaluate(document).get();

            if(flag==null){
                typeNextPageFlag = false;
            }else {
                typeNextPageFlag = true;
                typeUrl = flag;
            }

            return result;
        }
        throw new Exception(url + "异常");
    }


    //3.某个书籍
    public static List<String> book(String url) throws Exception {
        System.out.println("入参:" + url);
        List<String> result = null;
        Document document = requestForHtml(url);
        if(document != null){
            BookModel bookModel = rootModelMap.get(rootUrl(url)).getBookModel();

            if(""==bookModel.getMenuUrlPattern()){
                System.out.println("bookModel.getChapterListPattern():" + bookModel.getChapterListPattern());
                result = Xsoup.compile(bookModel.getChapterListPattern()).evaluate(document).list();
            }else {
                System.out.println("bookModel.getMenuUrlPattern():" + bookModel.getMenuUrlPattern());
                List<String> chapter = Xsoup.compile(bookModel.getMenuUrlPattern()).evaluate(document).list();
                System.out.println(chapter.size());
                if(chapter.size()==1) {
                    document = requestForHtml(rootUrl + chapter.get(0));
                    result = Xsoup.compile(bookModel.getChapterListPattern()).evaluate(document).list();
                }
            }

            // 是否有下一页
            System.out.println("bookModel.getNextChapterListPattern():" + bookModel.getNextChapterListPattern());
            if(""!=bookModel.getNextChapterListPattern()){
                String nextPage = Xsoup.compile(bookModel.getNextChapterListPattern()).evaluate(document).get();
                System.out.println("nextPage:" + nextPage);
                if(""==nextPage){
                    chapterNextPageFlag = false;
                }else {
                    chapterNextPageFlag = true;
                    chapterUrl = rootModelMap.get(rootUrl(url)).getRootUrl() + nextPage;
                    // 有的网站最后一页到了不消失，手动判断下
                    if(url.equals(chapterUrl)){
                        chapterNextPageFlag = false;
                        chapterUrl = "";
                    }
                }

            }else {
                chapterNextPageFlag = false;
            }

            System.out.println("chapterNextPageFlag:" + chapterNextPageFlag);
            System.out.println("chapterUrl:" + chapterUrl);

            return result;
        }
        throw new Exception(url + "异常");
     }

    //4.小说内容
    public static void content(String url) throws Exception {
        System.out.println("content:" + url);
        Document document = requestForHtml(url);
        if(document != null){
            ContentModel contentModel = rootModelMap.get(rootUrl(url)).getContentModel();

            System.out.println("contentModel.getContent():" + contentModel.getContent());
            System.out.println("contentModel.getTitle():" + contentModel.getTitle());

            String title = Xsoup.compile(contentModel.getTitle()).evaluate(document).get();
            String content = Xsoup.compile(contentModel.getContent()).evaluate(document).get();

            System.out.print(" 标题 >>> ");
            System.out.println(title);
            System.out.print(" : 内容 --- ");
            System.out.println(content);

        }else{
            throw new Exception(url + "异常");
        }
    }

    private static int i = 0;

    // 公共请求方法 , 重试还是有问题
    public static Document requestForHtml(String url){ // 容错处理和重试次数，超时等等
        try {
            i++;
            String html = Jsoup.connect(url).headers(rootModelMap.get(rootUrl(url)).getHeaders()).timeout(3000).execute().charset(rootModelMap.get(rootUrl(url)).getCharset()).body();
            Document document = Jsoup.parse(html);
            i = 0;
            return document;
        } catch (Exception e) {
            if(i<10){
                requestForHtml(url);
            }
            System.err.println(url + " >>> 请求异常: " + i + " >>> "  + e);
            e.printStackTrace();
        }
        return null;
    }

    public static String rootUrl(String pathUrl) throws Exception {
        try {
            URL url = new URL(pathUrl);
            return url.getProtocol() + "://" + url.getHost();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        throw new Exception("解析rootUrl异常");

    }

}
