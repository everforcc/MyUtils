package cc.use.url.http.novel;

import cc.resource.PropertiesHeader;
import cc.advanced.web.craw.utils.CrawDataParse;
import cc.advanced.web.http.utils.HttpClientUtils;
import cc.advanced.web.http.utils.HttpURLConnectionUtil;
import cc.constant.ConstantFile;
import cc.core.io.InputStreamUtils;
import cc.core.io.PrintWriterUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

/**
 * @author c.c.
 * @date 2020/12/10
 */
public class Novel_loveyuedu implements NovelFlow,Runnable {



    /**
     *  文件名格式 name[001][章节名][页码].txt
     */
    private static String fileTXTFormat = "%s[%s][%s][%s].txt";
    // html命名格式
    private static String fileHTMLFormat = "%s[%s][%s][%s].html";
    // md命名格式
    private static String fileMDFormat = "%s[%s][%s].md";
    private static String fileIMGFormat = "%s[%s][%s][%s]";
    private static DecimalFormat df = new DecimalFormat("0000");
    /**
     * 网站根目录
     */
    private static final String novelROOT = "http://www.qinxiaoshuo.com";


    private static String novelName = "我的青春恋爱物语果然有问题";
    private static String filePath = ConstantFile.L1_javaFilePath + "\\java\\novel\\www.qinxiaoshuo.com\\" + novelName;
    private static final String firstNovelContent = "https://www.loveyuedu.com/yuedu/21531/";

    public static void main(String[] args) {

    }

    private static final String novelUrl = "http://www.qinxiaoshuo.com/book/%e5%8f%9b%e9%80%86%e7%9a%84%e9%b2%81%e8%b7%af%e4%bf%ae";
    private static final String novelContent = "http://www.qinxiaoshuo.com/read/0/322/5d77babb56fec85e5b0f61f9.html";
    public static void novelName(){
        String result = HttpClientUtils.getWebContent(novelUrl, PropertiesHeader.qinxiaoshuoMap());
        System.out.println(result);
        Document document = Jsoup.parse(result);
        //System.out.println(document.getElementsByClass("volumes"));
    }
    static String connect(String firstNovelContent){
        String result = HttpClientUtils.getWebContent(firstNovelContent, PropertiesHeader.qinxiaoshuoMap());
        if(result==null){
            connect(firstNovelContent);;
        }
        return result;
    }

    // 章节名
    public static void novelContent(String firstNovelContent,String title,int i,int pn){
        // 或者换一种方式解决编码问题
        // 取出html
        String result = HttpURLConnectionUtil.flow(firstNovelContent,"GET","utf-8", PropertiesHeader.qinxiaoshuoMap());
        Document document = Jsoup.parse(result);

        // 得到最后一个按钮的连接
        //System.out.println("qxs_btn:" + document.getElementsByClass("qxs_btn").size());
        Element element_lastbutton = document.getElementsByClass("qxs_btn").get(document.getElementsByClass("qxs_btn").size()-1);
        String href = element_lastbutton.attr("href");
        String text = element_lastbutton.text();


        if(title.startsWith("下一章")) {
            title = title.replace("下一章", "");
        }
        // 链接和标题
        System.out.println("当前章:" + firstNovelContent + " " + title );

        // 格式化数据 div id=""
        String content = CrawDataParse.parse(document.getElementById("chapter_content").toString(),"chapter_content");
        // 保存html
        saveHTML(firstNovelContent + "\r\n" + result,title, pn,i);
        // 保存当前章节txt
        saveTXT(content,title, pn,i);
        // 保存当前章节 md
        saveMD(content,title,i);
        // 先save再自加了


        // 处理图片文件 content
        if(content.contains("img")){
            Document documentIMG = Jsoup.parse(content);
            //System.out.println(documentIMG);
            Elements elementsIMG = documentIMG.getElementsByTag("img");
            for(Element e: elementsIMG){
                System.out.println(e.attr("src"));
                // 处理图片不存在的情况，给个error
                saveImg(e.attr("src"),title, pn,i);
            }
        }

        if(text.startsWith("下一章")){
            firstNovelContent = novelROOT + href;
            title = text;
            i++;
            pn = 1;
        }else if(text.startsWith("下一页")){
            pn++;
            if(firstNovelContent.indexOf("?") > 0) {
                firstNovelContent = firstNovelContent.substring(0, firstNovelContent.indexOf("?")) + href;
            }else {
                firstNovelContent += href;
            }
        }else {
            // 如果这里表示不是下一章也不是下一页，退出
            System.out.println("小说获取完毕>>>>>>>>");
            return;
        }

        //novelContent(firstNovelContent,title,i,pn);
    }

    public static void novelContentNextPage(String novelContent){
        String result = HttpClientUtils.getWebContent(novelContent, PropertiesHeader.qinxiaoshuoMap());
        System.out.println(result);
        Document document = Jsoup.parse(result);
    }

    // 保存文件

    public static void saveTXT(String content,String suffix,int page,int i){
        String formatNameResult = String.format(fileTXTFormat,novelName,df.format(i),suffix,page);
        PrintWriterUtils.fileWriter(filePath + "txt",formatNameResult,content);
    }

    public static void saveHTML(String content,String suffix,int pn,int i){
        String formatNameResult = String.format(fileHTMLFormat,novelName,df.format(i),suffix,pn);
        PrintWriterUtils.fileWriter(filePath + "html",formatNameResult,content);
    }

    public static void saveMD(String content,String suffix,int i){
        String formatNameResult = String.format(fileMDFormat,novelName,df.format(i),suffix);
        PrintWriterUtils.fileWriter(filePath + "md",formatNameResult,content);
    }

    public static void saveImg(String url,String suffix,int pn,int i){
        InputStream inputStream = null;
        try {
            inputStream = HttpURLConnectionUtil.getStream(url,"GET","", PropertiesHeader.qinxiaoshuoMap());
            DecimalFormat df = new DecimalFormat("000");
            String formatIMGResult = String.format(fileIMGFormat,novelName,df.format(i),suffix,pn);
            InputStreamUtils.downFileByStream(inputStream,filePath + "txt" + File.separator + formatIMGResult ,url.substring(url.lastIndexOf("/") + 1));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void index(String url) {

    }

    @Override
    public void type(String url) {

    }

    @Override
    public void book(String url) {
        String result = HttpURLConnectionUtil.flow(url,"GET","gbk", PropertiesHeader.loveyueduMap());
        Document document = Jsoup.parse(result);
        System.out.println(document.body());
    }

    @Override
    public void menu(String url) {

    }

    @Override
    public void down(String url) {

    }

    private String bookUrl;

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public Novel_loveyuedu(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    @Override
    public void run() {
        book(bookUrl);
    }
}
