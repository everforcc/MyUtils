package cn.cc.core.http.crawler.jsouputils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

/**
 *  ip没了
 *  先下载下来爬避免被封
 * model
 */
public class MingYanTong {

    /**
     * 这个网站会封ip，注意设置间隔或者使用代理
     *
     */

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            UrlList2();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     * 1.获取地址
     *
     * 2.找到元素节点
     *
     * 3.找到总页码
     *
     * 4.获取每页的数据
     *
     */

    //首页       href="/" 的前缀
    private static final String indexUrl="https://www.mingyantong.com";

    //青春猪头少年不会梦到兔女郎学姐
    private static final String maiUrl="https://www.mingyantong.com/article/813656";

    private static final String localUrl="D:\\a.html";

     public static void UrlList() throws Exception{
         /*Connection connection = Jsoup.connect(localUrl);// 获取连接
         connection.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");// 配置模拟浏览器
         Connection.Response login = connection.execute();// 获取响应*/

         //System.out.println(login.body());   //view-content 167 行
         Document d1 = Jsoup.parse(new File(localUrl),"UTF-8");// 转换为Dom树
         Element et = d1.getElementById("contentin");// 获取div父节点的id block-views-xqarticletermspage-block_1

         System.out.println(et.hasText());
         //class view-content

         Elements elements = et.getElementsByClass("view-content");
         System.out.println(elements.hasText());
         System.out.println(elements.text());
         System.out.println("view-content的尺寸"+elements.size());
         Element element = elements.first();
         System.out.println(element.text());
         Elements elements1 = elements.tagName("div[class]");
         System.out.println(elements1.size());
         String str = elements1.attr(".div a");
         System.out.println(str);
         System.out.println("1");
         Elements elements2 = elements.select(".div a");
         System.out.println(elements2.hasText());
         /*for (Element element : et) {
             //正则匹配url
             Pattern pattern = Pattern.compile(regex);
             Matcher matcher = pattern.matcher(element.attr("content"));
             //是否匹配到了
             if (matcher.matches()) {
                 System.out.println(element.attr("content"));
                 String url=element.attr("content");
                 //如果匹配到了下载
                 downByUrl(element.attr("content"),avnum,url.substring(url.lastIndexOf(".")+1,url.length()));//传图片后坠
             }
         }*/
     }
    public static void UrlList1() throws Exception{
        Document d1 = Jsoup.parse(new File(localUrl),"UTF-8");// 转换为Dom树
        Element et = d1.getElementById("contentin");// 获取div父节点的id block-views-xqarticletermspage-block_1

        //System.out.println(et.hasText());
        //class view-content

        Elements elements = et.getElementsByClass("view-content");
        //System.out.println(elements.hasText());
        //System.out.println(elements.text());
        //System.out.println("view-content的尺寸"+elements.size());
        Element element = elements.first();
        //System.out.println(element.text());
        Elements elements1 = elements.tagName("div[class]");
        //System.out.println(elements1.size());
        String str = elements1.attr(".div a");
        //System.out.println(str);
        //System.out.println("1");
        Elements elements2 = elements.select(".div a");
        //System.out.println(elements2.hasText());
        //views-row views-row-4 views-row-even
        Elements elements3 = et.getElementsByClass("views-row views-row-4 views-row-even");

        //System.out.println(elements3.text());

        //Elements elements4 = elements.select("div");
        //Elements elements4 = elements.select("div > div");
        Elements elements4 = elements.select("[class=xlistju]");//主要的利用选择器
        System.out.println("-------------检验------------");
        System.out.println(elements4.toString());//用这个来检查选择的对不对
        System.out.println("-------------检验------------");
        System.out.println(elements4.size());
        System.out.println("-------------输出start---------");
        for(Element element1:elements4){
            //System.out.println(element1.text());
            //Elements a = element1.select("div > span > div > a");
            System.out.println(element1.text());
            // class xlistju
        }
        System.out.println("-------------输出end---------");
    }

    public static void UrlList2() throws Exception{
        Document d1 = Jsoup.parse(new File(localUrl),"UTF-8");// 转换为Dom树
        Element et = d1.getElementById("contentin");// 获取div父节点的id block-views-xqarticletermspage-block_1
        Elements elements = et.getElementsByClass("view-content");
        Elements elements4 = elements.select("[class=xlistju]");//主要的利用选择器
        System.out.println("-------------检验------------");
        System.out.println(elements4.toString());//用这个来检查选择的对不对
        System.out.println("-------------检验------------");
        System.out.println(elements4.size());
        System.out.println("-------------输出start---------");
        for(Element element1:elements4){
            System.out.println(element1.text());
        }
        System.out.println("-------------输出end---------");
    }

}
