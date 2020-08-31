package cn.cc.core.http.action.del;

import cn.cc.core.http.action.common.HttpURLConnectionUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.File;

public class BDwenku {
    public static void main(String[] args) throws Exception {
        /*String url="https://wenku.baidu.com/view/a352115a6d175f0e7cd184254b35eefdc9d3157b.html";
        HttpURLConnectionUtil.sendToUrlRequest(url,"POST","","UTF-8");*/


        Document document = Jsoup.parse(new File("F:\\aly\\百度\\计算机调研报告 - 百度文库.html"),"UTF-8");

        Element element = document.getElementById("reader-container");

        Elements elements = document.getElementsByClass("ie-fix");

        for(int i=0;i<elements.size();i++){
            Elements elements1_p = elements.get(i).getElementsByTag("p");
            System.out.println(elements1_p.text());
        }


        System.out.println(elements.toString());
        Elements elements_p = elements.get(0).getElementsByTag("p");
        System.out.println(elements_p.size());

        /*Elements elements = element.getElementsByTag("p");

        System.out.println(elements.size());*/

        // System.out.println(element.text());

        // System.out.println(document.body());
    }
}
