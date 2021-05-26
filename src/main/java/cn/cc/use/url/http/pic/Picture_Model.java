package cn.cc.use.url.http.pic;

import cn.cc.core.io.utils.PrintWriterUtils;
import cn.cc.core.web.http.webmagic.base.Constant;
import cn.cc.use.design.Print_Record;
import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.xsoup.Xsoup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * @author c.c.
 * @date 2021/3/15
 */
public class Picture_Model {

    // 通用的流程.通用的解析肯定会牺牲性能，不过我选择忽略

    // 网站根目录
    private static String rootUrl = "https://www.senlo.club/category/jdly/fzly";// "https://www.senlo.club/category/jdly/senro";
    private static String rootPath = "www.senlo.club";

    // 搜索链接
    private static String searchUrl = "searchKey=%s";

    // 类型地址，类型名
    private static String rootTypeUrlPattern = "//div[@class='divclass']";
    private static String rootTypeNamePattern = "//div[@class='divclass']";

    // 具体页面的正则
    private static String pageUrlPattern = "//div[@class='post grid']//div[@class='img']//a/@href";
    private static String pageCountPattern = "//div[@class='page']/@text()";
    private static int pageCount = -1;

    // 页面信息
    private static String singleImgUrlPattern = "//figure[@class='wp-block-image']//img/@src";
    private static String singleTitleMsgPattern = "//div[@class='title_style_01']//h2[1]/text()";
    private static String singleElseMsgPattern = "//img[@class='']";
    private static String singleTypeMsgPattern = "//div[@class='logo']//a/text()";

    private static String systemPath = "E:\\craw\\" + rootPath + "\\";
    private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/82.0.4083.0 Safari/537.36" ;
    private static String cookie = "";
    private static int timeout = 6 * 1000;

    private static Print_Record print_record = Print_Record.getInstanse("");

    public static void main(String[] args) {
        /**
         *  1. root拿到类型地址
         *  21. 类型地址拿到页面
         *  22. 搜索地址拿到页面
         *  3. 分页地址链接，翻页
         *  4. 页面详细数据
         */
        // 1
        /*List<String> rootTypeUrlList = root(rootUrl);
        for(String typeUrl:rootTypeUrlList){// 一个类型下

            //第一页分页， 总数或者下一页的位置 匹配到是否有
            List<String> pageList = type(typeUrl);
            // 对当前的链接进行修改开始下次请求
            int size = pageList.size();
            print_record.println("[size] : " + size);
            for(int i=0;i<size;i++){
                // 对象
                page(pageList.get(i));
            }
            // 结束时pageCount
            pageCount = -1;
        }*/

        List<String> stringList = type(rootUrl);
        for(String s:stringList){
            page(s);
        }

    }

    // 首页为了类型链接
    public static List<String> root(String rootUrl){
        print_record.println("[rootUrl] : " + rootUrl);
        Document document = commonGet(rootUrl);
        List<String> rootTypeNameList = xsoupList(rootTypeNamePattern,document);
        List<String> rootTypeUrlList = xsoupList(rootTypeUrlPattern,document);
        return rootTypeUrlList;
    }

    // 一个类型下的所有信息
    public static List<String> type(String typeUrl){
        print_record.println("[typeUrl] : " + typeUrl);
        Document document = commonGet(typeUrl);
        List<String> pageUrlList = xsoupList(pageUrlPattern,document);
        if(pageCount == -1){
            String count = xsoupString(pageCountPattern,document);
            print_record.println("[count] : " + count);
            if(count!=null&&!"".equals(count)) {
                pageCount = Integer.valueOf(count);
            }
        }
        return pageUrlList;
    }

    public static void page(String pageUrl){
        print_record.println("[pageUrl] : " + pageUrl);
        Document document = commonGet(pageUrl);
        List<String> imgUrlList = xsoupList(singleImgUrlPattern,document);
        String title = xsoupString(singleTitleMsgPattern,document);
        //List<String> elseMsgList = xsoupList(singleElseMsgPattern,document);
        String type = xsoupString(singleTypeMsgPattern,document);

        String[] dir = {type,title};

        int size = imgUrlList.size();
        for(int i=0;i<size;i++){
            down(imgUrlList.get(i),i,size,dir);
        }

        //save(elseMsgList,title + ".txt",dir);
    }

    public static Document commonGet(String rootUrl){
        Document document = null;
        try {
            document = Jsoup.connect(rootUrl).userAgent(userAgent)
                            .cookie("cookie",cookie)
                            .timeout(timeout)
                            .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static Document commonPost(String rootUrl, Map<String,String> map){
        Document document = null;
        try {
            document = Jsoup.connect(rootUrl)
                            .userAgent(userAgent)
                            .cookie("cookie",cookie)
                            .timeout(timeout)
                            .data(map).post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static String xsoupString(String xpath,Document document){
        return Xsoup.compile(xpath).evaluate(document).get();
    }

    public static List<String> xsoupList(String xpath, Document document){
        return Xsoup.compile(xpath).evaluate(document).list();
    }


    private static int retry = 0;
    public static void down(String fileUrl,int i,int size,String... dir){

        // 格式化文件名
        StringBuilder patternBuilder = new StringBuilder();
        for(int j=0;j<size;j++){
            patternBuilder.append("0");
        }
        DecimalFormat decimalFormat = new DecimalFormat(patternBuilder.toString());

        // 路径

        //fileName = cn.cc.core.file.utils.FileUtils.checkFileName(fileName);

        Connection.Response response = null;
        try {
            // 获取流和文件后缀
            response = Jsoup.connect(fileUrl).userAgent(Constant.userAgent)
                            .method(Connection.Method.GET)
                            .ignoreContentType(true)
                            .timeout(60*1000)
                            .execute();
            InputStream inputStream = response.bodyStream();
            print_record.println(response.contentType());
            // 后缀名
            String[] suffix = response.contentType().split("/");
            // 根路径，path，文件名
            String fileName = pathFormat(dir) + decimalFormat.format(i) + "." + suffix[1];
            print_record.println("[fileName] : " + fileName);
            File file = new File(fileName);
            if(file.exists()){
                // 校验大小，断点传输

                return;
            }

            FileUtils.copyToFile(inputStream,file);
            retry = 0;
        } catch (IOException e) {
            e.printStackTrace();
            // 重试
            if(retry < 3){
                retry++ ;
                System.out.println("重试第" + retry + "次");
                down(fileUrl,i,size,dir);
            }else {
                retry = 0;
                //errList.add(fileUrl + "\n");
            }
        }

    }

    public static void save(String content,String name,String... path){
        PrintWriterUtils.fileWriter(pathFormat(path),name,content);
    }

    public static void save(List<String> contentList,String name,String... path){
        StringBuilder stringBuilder = new StringBuilder();

        for(String s:contentList){
            stringBuilder.append(s);
        }

        PrintWriterUtils.fileWriter(pathFormat(path),name,stringBuilder.toString());
    }

    private static String pathFormat(String... dir){
        StringBuilder pathBuilder = new StringBuilder(systemPath);
        for(String s:dir) {
            print_record.println("[s] : " + s);
            pathBuilder.append(cn.cc.core.file.utils.FileUtils.checkFileName(s));
            pathBuilder.append(File.separator);
        }
        return pathBuilder.toString();
    }

}
