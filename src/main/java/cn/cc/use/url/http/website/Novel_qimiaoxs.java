package cn.cc.use.url.http.website;

import cn.cc.core.web.http.header.Header;
import cn.cc.core.web.http.utils.HttpURLConnectionUtil;
import cn.cc.core.io.utils.PrintWriterUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author c.c.
 * @date 2020/12/3
 */
public class Novel_qimiaoxs {

    // 没办法分类就按照功能分类
    public static String novelType = "http://www.qimiaoxs.com/fendjb.asp?id=24";

    // 小说名 链接里有所有的信息
    public static String novelName = "http://www.qimiaoxs.com/book.asp?id=13626";

    // novel + 站名 + 分类
    public static String fileRootPath = "E:\\novel\\qimiaoxs\\";

    // 模拟实体类
    private static Map<String, String> novelMap = new HashMap<>();

    public static void main(String[] args) {
        novelMenu();
        //novelName("http://www.qimiaoxs.com/book.asp?id=13438","两性小说");
        //novelName("http://www.qimiaoxs.com/book.asp?id=50418", "穿越家有儿女");
        //save("E:\\临时\\qimiaoxs\\","1.txt","1.txt");
    }

    //

    // 小说分类菜单
    public static void novelMenu() {
        //try {
        String result = null;
        result = HttpURLConnectionUtil.sendToUrlRequest(novelType, "GET", "gb2312", Header.qimiaoxsMap());
        System.out.println(result);
        System.exit(1);
        Document document = Jsoup.parse(result);
        Elements elements_table = document.getElementsByTag("table");
        System.out.println(elements_table.size());
        //System.out.println(elements.get(3));


        Elements elements_table_tbody = elements_table.get(3).getElementsByTag("tbody");
        System.out.println(elements_table_tbody.size());
        //最右边的列表
        //System.out.println(elements_table_tbody.get(2));

        //
        Elements elements_table_tbody_tr = elements_table_tbody.get(2).getElementsByTag("tr");
        System.out.println(elements_table_tbody_tr.size());

        // 月排行
        //System.out.println(elements_table_tbody_tr.get(1));
        // 取出书的链接
        Element element_bookList = elements_table_tbody_tr.get(1);
        Elements elements_bookList = element_bookList.getElementsByTag("a");
        System.out.println(elements_bookList);
        for (Element e : elements_bookList) {
            novelName(e.attr("href"), e.text());
        }


    }

    // 小说名
    public static void novelName(String novelName, String fileName) {
        //try {
        String result = HttpURLConnectionUtil.sendToUrlRequest(novelName, "GET", "gb2312", Header.qimiaoxsMap());
        //System.out.println(result);
        Document document = Jsoup.parse(result);
        Elements elements = document.getElementsByTag("table");
        //System.out.println(elements.size());

        // 文件名等信息
        //System.out.println(elements.get(3));
        Element element_3 = elements.get(3);
        Elements element_3_td = element_3.getElementsByTag("td");
            /*System.out.println(element_3_td.size());
            System.out.println(element_3_td.get(2).text());*/
        novelMap.put("novelType", "热门分类");
        //novelMap.put("novelName",element_3_td.get(2).text());
        novelMap.put("novelName", fileName);
        //System.out.println(elements.get(4));
        // 获取目录等内容
        Element element_4 = elements.get(4);
        Elements elements_4_a = element_4.getElementsByTag("a");
        System.out.println(elements_4_a.size());
        //System.out.println(elements_a);
        Map<String, String> map = new HashMap<String, String>();
        int size = elements_4_a.size();
        String[] strAry = new String[size - 1];
        System.out.println("排序前");
        for (int i = 1; i < size; i++) {
            System.out.println(elements_4_a.get(i).attr("href") + " " + elements_4_a.get(i).text());
            map.put(elements_4_a.get(i).attr("href"), elements_4_a.get(i).text());
            strAry[i - 1] = elements_4_a.get(i).attr("href");
        }
        // https://www.cnblogs.com/starcrm/p/10736749.html
        Arrays.sort(strAry);
        System.out.println("排序后");
        for (String str : strAry) {
            System.out.println(str + "  " + map.get(str));
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            novelCatalogue(str, map.get(str));
        }
        //拿到目录，下载啊，按顺序

    }

    // 小说目录
    public static void novelCatalogue(String url, String name) {
        String result = null;
        int flag = 0;
        Elements elements = null;
        while (flag == 0) {
            result = HttpURLConnectionUtil.sendToUrlRequest(url, "GET", "gb2312", Header.qimiaoxsMap());
            System.out.println("novelCatalogue()result:" + result);
            Document document = Jsoup.parse(result);
            elements = document.getElementsByClass("content");
            System.out.println(url + ">>>" + name);
            System.out.println("novelCatalogue()elements.size():" + elements.size());
            System.out.println("novelCatalogue()elements:" + elements);
            flag = elements.size();
        }
        //System.out.println(elements.get(0).toString());

        String str = elements.get(0).toString();
        /*str = str.replaceAll("<img src='.{0,10}/.{0,10}.jpg'>","")
                .replaceAll("<img src=\".{0,10}/.{0,10}.jpg\">","")
                .replaceAll("<br>","\r\n");*/
        System.out.println(str);
        //recorMsgd("E:\\临时\\",fileName + "原始.txt",str);
        str = str.replace("<td colspan=\"2\" class=\"content\">", "").replace("</td>", "");
        str = str.replaceAll("<img src='axi/", "")
                .replaceAll("<img src=\"image/", "")
                .replaceAll(".jpg\">", "")
                .replaceAll("<br>", "\r\n");
        //.replaceAll("document.write\\(\"","").replaceAll("\"\\);","");
        str = name + "\r\n" + str;
        novelContent(str, "");
    }

    //小说内容
    public static void novelContent(String content, String fileName) {
        // 网址，存放目录，文件名，章节名，内容
        save(fileRootPath + novelMap.get("novelType") + File.separator, novelMap.get("novelName") + ".txt", content);
    }

    // 保存
    public static void save(String path, String fileName, String content) {
        PrintWriterUtils.fileWriter(path, fileName, content);
    }
}
