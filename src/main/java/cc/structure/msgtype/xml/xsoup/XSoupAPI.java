package cc.structure.msgtype.xml.xsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import us.codecraft.xsoup.Xsoup;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author c.c.
 * @date 2021/1/19
 */
public class XSoupAPI {

    public static void main(String[] args) {

        try {
            //menu();
            testSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void testSelect(){
        String html = "<html><div><a href='https://github.com'>github.com</a></div>" +
                "<table><tr><td>a</td><td>b</td></tr></table></html>";

        Document document = Jsoup.parse(html);

        System.out.println(document.body());

        // jsoup1.11.3 高版本解析会有问题
        String result = Xsoup.compile("//a/@href").evaluate(document).get();

        System.out.println(result);

        // 测试方法判断两者是否一致 1. 如果两者一致, 程序继续往下运行. 2. 如果两者不一致, 中断测试方法, 抛出异常信息
        Assert.assertEquals("https://github.com", result);

        List<String> list = Xsoup.compile("//tr/td/text()").evaluate(document).list();
        Assert.assertEquals("a", list.get(0));
        Assert.assertEquals("b", list.get(1));
    }

    // 示例
    static void menu() throws IOException {

        String novelUrl = "https://www.biqugg.com/xs/15293/";
        Document document = Jsoup.parse(new URL(novelUrl).openStream(),"utf-8",novelUrl);// 转换为Dom树

        String result = Xsoup.compile("//div[@id=list]").evaluate(document).get();
        List<String> list = Xsoup.compile("//div[@id=list]//dd//a/@href").evaluate(document).list(); // //dd 可以省去， // 表示后代的所有元素
        //System.out.println(result);

        list.forEach(System.out::println);

        /*Element element_list = document.getElementById("list");
        Elements elements_a = element_list.getElementsByTag("a");

        for(Element element:elements_a){

            System.out.println(element.attr("href"));
            System.out.println(element.text());

            //content("https://www.biqugg.com/xs/15293/" + elelment.attr("href"),element.text());
        }*/
    }

}
