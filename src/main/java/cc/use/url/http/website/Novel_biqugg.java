package cc.use.url.http.website;

import cc.constant.ConstantFile;
import cc.core.io.PrintWriterUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * @author c.c.
 * @date 2021/1/19
 */
public class Novel_biqugg {

    public static void main(String[] args) {

        try {
            menu("https://www.biqugg.com/xs/15293/");
            //content("https://www.biqugg.com/xs/15293/10420215.html","");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void menu(String novelUrl) throws IOException {
        Document document = Jsoup.parse(new URL(novelUrl).openStream(),"utf-8",novelUrl);// 转换为Dom树
        //Elements elementsclass = document.getElementsByClass("content");
        Element element_list = document.getElementById("list");
        Elements elements_a = element_list.getElementsByTag("a");

        for(Element element:elements_a){

            System.out.println(element.attr("href"));
            System.out.println(element.text());

            //content("https://www.biqugg.com/xs/15293/" + element.attr("href"),element.text());
        }
    }

    // https://www.biqugg.com/xs/15293/10420215.html
    static void content(String novelUrl,String txt) throws IOException {
        Document document = Jsoup.parse(new URL(novelUrl).openStream(),"utf-8",novelUrl);// 转换为Dom树
        String content = document.getElementById("content").toString();
        novelNameHtml(content,txt);
    }

    public static void novelNameHtml(String content,String title){

        content = content.replaceAll("<div id=\"content\">","")
                .replaceAll("<!--go-->","")
                .replaceAll("<!--over-->","")
                .replaceAll("</div>","");
        // 空格转换
        content = content.replaceAll("&nbsp;"," ");
//        content = content.replaceAll("<br/><br/>","\r"); //
        content = content.replaceAll("<br>\r","\r"); // 因为有连续两个 br 单独的<br> 后面追加了\r表示回车符号
        content = content.replaceAll("<br>",""); //
        // System.out.println(content);
        save(title);
        save(content);
    }


    public static void save(String content){
        PrintWriterUtils.fileWriter(ConstantFile.javaFilePath + "\\novel\\biquge","谍影风云.txt",content);
    }

}
