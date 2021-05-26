package cn.cc.use.url.http.website;

import cn.cc.core.web.http.webmagic.base.Constant;
import org.apache.commons.io.FileUtils;
import org.bytedeco.javacpp.presets.opencv_core;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.xsoup.Xsoup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author c.c.
 * @date 2021/3/14
 */
public class Picture_ababbb {

    // https://www.ababbb.com/5914.html
    private static String rootUrl = "https://www.ababbb.com/";
    private static String rootDir = "www.ababbb.com/";
    private static String hiddenUrl = "https://www.ababbb.com/wp-json/b2/v1/getHiddenContent";

    private static String cookie = "Hm_lvt_b2c596bcff164c850089cb341dbe4138=1615703351; wordpress_logged_in_2dfee5ed51029be34cfc0559ae9827fc=15738573601%7C1616308842%7CSPqv9kk04VajmEmcbtZ7sICIHXgiGdLtmpzGM0VrHkD%7Cc1d60b6361b92271de37ea57201ad3efa7780968b70b1beaf85974c85ecffa63; b2_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvd3d3LmFiYWJiYi5jb20iLCJpYXQiOjE2MTU3MDQwNDIsIm5iZiI6MTYxNTcwNDA0MiwiZXhwIjoxNjE2OTEzNjQyLCJkYXRhIjp7InVzZXIiOnsiaWQiOiIxMTE5MCJ9fX0.K55TlH3fCTkHKdWYdOxAo7yUf28h9zaQKSK4n1oweuE; Hm_lpvt_b2c596bcff164c850089cb341dbe4138=1615704453";

    private static String searchResultUrlPattern = "//ul[@class='b2_gap ']//a[@class='thumb-link']/@href"; //|//div[@class='item-link-text']/text()

    private static String pageImgDirPattern = "//div[@class='content-excerpt']//text()";
    private static String pageImgUrlPattern = "//div[@class='entry-content']//p//img/@src";


    private static List<String> errList = new ArrayList<>();

    /**
     * 1.搜索链接拿出所有地址  数字.html
     * 21.下载地址下的所有图片，不重要
     * 22.拿出地址下的所有链接，post
     * 3.校验链接是否有效，进行标注
     *
     */

    public static void main(String[] args) {
        //String typeUrl = "https://www.ababbb.com/5228.html";
        /*String searchUrl = "https://www.ababbb.com/page/%s?s=森萝财团&type=post";

        for(int i=1;i<2;i++) {
            String url = String.format(searchUrl,i);
            System.out.println(url);
            searchResultHtml(url);
        }*/

        //pageMsg("https://www.ababbb.com/5914.html");

        matchBD("5914");
    }

    public static void searchResultHtml(String searchUrl){
        // 自动翻页
        Document document = null;
        try {
            document = Jsoup.connect(searchUrl).userAgent(Constant.userAgent).cookie("cookie",cookie).get();
            List<String> urlList = Xsoup.compile(searchResultUrlPattern).evaluate(document).list();

            int size = urlList.size();
            System.out.println("size:" + size);
            for(int i=0;i < size;i++){
                System.out.println(urlList.get(i));
                pageMsg(urlList.get(i));
            }
            // 拿到数据后两个操作

        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(document);
    }

    // 每页的图片信息
    public static void pageMsg(String pageUrl){
        Document document = null;
        try {
            document = Jsoup.connect(pageUrl).userAgent(Constant.userAgent).cookie("cookie",cookie).get();
            List<String> urlList = Xsoup.compile(pageImgUrlPattern).evaluate(document).list();
            String dir = Xsoup.compile(pageImgDirPattern).evaluate(document).get();

            //urlList.forEach(System.out::println);
            // 拿到数据后两个操作
            int size = urlList.size();
            for(int i=0;i<size;i++){
                down(urlList.get(i),dir + File.separator,i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(document);
    }

    public static void matchBD(String id){
        try {
            Document document = Jsoup.connect(hiddenUrl).userAgent(Constant.userAgent)
                    .ignoreContentType(true)
                    .cookie("cookie",cookie)
                    //.data("accept","application/json, text/plain, */*")
                    .data("authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvd3d3LmFiYWJiYi5jb20iLCJpYXQiOjE2MTU3MDQwNDIsIm5iZiI6MTYxNTcwNDA0MiwiZXhwIjoxNjE2OTEzNjQyLCJkYXRhIjp7InVzZXIiOnsiaWQiOiIxMTE5MCJ9fX0.K55TlH3fCTkHKdWYdOxAo7yUf28h9zaQKSK4n1oweuE")
                    .data("id",id)
                    .data("order_id","")
                    .post();
            System.out.println(document.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static int retry = 0;
    public static void down(String fileUrl,String dir,int i){
        dir = cn.cc.core.file.utils.FileUtils.checkFileName(dir);
        //fileName = cn.cc.core.file.utils.FileUtils.checkFileName(fileName);
        Connection connection = Jsoup.connect(fileUrl).userAgent(Constant.userAgent).cookie("cookie",cookie);
        Connection.Response response = null;
        try {
            response = connection.method(Connection.Method.GET).ignoreContentType(true).timeout(60*1000).execute();
            InputStream inputStream = response.bodyStream();
            System.out.println(response.contentType());
            String[] suffix = response.contentType().split("/");

            File file = new File("E:\\craw\\" + rootDir + File.separator + dir + File.separator + subSuffix(fileUrl) + i + "." + suffix[1]);

            if(file.exists()){
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
                down(fileUrl,dir,i);
            }else {
                retry = 0;
                errList.add(fileUrl + "\n");
            }
        }

    }

    public static String subSuffix(String url){
        try {
            System.out.println("url:" + url);
            URL u = new URL(url);
            url = u.getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //url.lastIndexOf()
        url = url.substring(url.lastIndexOf("/"),url.length());
        System.out.println(url);
        return url;
    }

}
