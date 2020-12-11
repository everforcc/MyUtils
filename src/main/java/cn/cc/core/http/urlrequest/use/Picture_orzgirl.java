package cn.cc.core.http.urlrequest.use;

import cn.cc.core.file.utils.ConstantCharSet;
import cn.cc.core.io.utils.InputStreamUtils;
import cn.cc.core.file.utils.FileUtils;
import cn.cc.core.http.urlrequest.header.Header;
import cn.cc.core.http.urlrequest.utils.HttpURLConnectionUtil;
import cn.cc.core.io.utils.PrintWriterUtils;
import cn.cc.utils.Print_Record;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author c.c.
 * @date 2020/12/5
 */
public class Picture_orzgirl {

    // 公共代码提取。 文件名。 \r\n 的意义测试，写入文件测试就行

    private static String picUrl = "https://www.orzgirl.com/赛高酱-私定1万：透明纱-红色丁字裤【37p3v】.html";
    //private static String picUrlName = "透明纱-红色丁字裤";
    private static Print_Record print_record = Print_Record.getInstanse("E:\\picture");
    public static Set<String> urlSet = new HashSet<>();
    public static Set<String> urlSetTemp = new HashSet<>();

    // 先放到文件里，没必要数据库，太麻烦，不是web项目
    public static Set<String> urlSetTotal = new HashSet<>();

    // 原集合遍历完，生成新集合，新集合赋值给原集合，遍历原集合

    public static void main(String[] args) {

        // 初始化，开始爬
        urlSet.add(picUrl);

        saveUrl(picUrl);
        controlFlow(urlSet);
    }

    public static boolean saveUrl(String s){
        if(urlSetTotal.add(s)){
            print_record.println("保存新链接:" + s);
            urlSetTemp.add(s);
            //保存到文件
            PrintWriterUtils.fileWriter("E:\\picture\\url",  "url.txt", s + "\r\n");
            return true;
        }
        print_record.println("链接已存在:" + s);
        return false;
    }

    // 控制流程
    public static void controlFlow(Set<String> urlSet){
        // 进来的话表示新的一轮，置为空重新开始
        urlSetTemp = new HashSet<>();
        for(String s:urlSet) {
            flow(s);
        }
        // 遍历完成
        urlSet = urlSetTemp;
        if(urlSet!=null&&urlSet.size()!=0){
            controlFlow(urlSet);
        }
    }

    public static void flow(String picUrl) {

        // https://imglolita.com/images/2020/11/30/4.jpg
        Set<Map.Entry<Object, Object>> set = new HashSet<>();
        String result = HttpURLConnectionUtil.sendToUrlRequest(picUrl, "GET", ConstantCharSet.UTF_8, Header.orzgirlMap());

        Document document = Jsoup.parse(result);
        Elements elements_1 = document.getElementsByClass("post-thumb-img-content post-thumb");
        print_record.println("elements_1.size():" + elements_1.size());
        Elements elements_1_img = elements_1.get(0).getElementsByTag("img");
        //System.out.println(elements_img);
        Elements elements_2 = document.getElementsByClass("entry-content clear");
        print_record.println("elements_2.size():" + elements_2.size());
        Elements elements_2_img = elements_2.get(0).getElementsByTag("p").get(0).getElementsByTag("img");
        // 下载图片 1
        try {
            downByElements(elements_1_img,picUrl);
            downByElements(elements_2_img,picUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 存链接
        Elements elements_crp_related = document.getElementsByClass("crp_related");
        /*System.out.println(elements_crp_related.size());
        System.out.println(elements_crp_related.get(0));*/
        Element element = elements_crp_related.get(0);
        Elements elements_nextUrl = element.getElementsByTag("a");
        for (Element element_nextUrl : elements_nextUrl) {
            try {
                String nextUrl = new String(element_nextUrl.attr("href").getBytes(), "GB2312");
                // !!!!!!!
                // 过时了，先用着
                nextUrl = URLDecoder.decode(nextUrl);
                saveUrl(nextUrl);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public static void downByElements(Elements elements_img,String picUrl) throws Exception {
        // 下载图片 1
        URL urlPath = new URL(picUrl);
        String dir = urlPath.getPath();
        PrintWriterUtils.fileWriter("E:\\picture\\" + dir + "\\",  "url.txt", picUrl);
        for (Element e : elements_img) {
            String url = e.attr("src");
            print_record.println(url);
            String fileName = url.substring(url.lastIndexOf("/") + 1, url.length());
            try {
                downByUrl(url,dir,fileName);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }


    public static void downByUrl(String url, String dir, String fileName) throws Exception {
        InputStream in = HttpURLConnectionUtil.getStream(url, "GET", "", Header.imglolitaMap());
        System.out.println("in.available():" + in.available());
        dir = FileUtils.checkFileName(dir);
        fileName = FileUtils.checkFileName(fileName);
        if (in.available() > 0) {
            // 下载字节流需要用到的
            InputStreamUtils.downFileByStream(in,"E:\\picture\\" + dir + "\\", fileName, new BigDecimal(1));
        } else {
            PrintWriterUtils.fileWriter("E:\\picture\\" + dir + "\\", fileName + ".txt", url);
        }
    }


}
