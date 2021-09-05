package cc.use.url.http.website;

import cc.constant.ConstantFile;
import cc.use.file.WenkuNovelMenu;
import cc.core.file.utils.MDUtils;
import cc.advanced.web.http.header.Header;
import cc.advanced.web.http.utils.CrawDataParse;
import cc.advanced.web.http.utils.HttpURLConnectionUtil;
import cc.core.io.PrintWriterUtils;
import cc.core.regex.utils.RegexUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.net.URL;
import java.util.*;

public class Novel_wenku8_onegroup {

    // 下一部小说

    // 章节 上下 名字问题 add



    private static String novelName = "c.c.";
    private static String splitChapter = "";
    private static String dir = "c.c.1";

    // 上一章，下一章，类似小说的做法
    // 序号 卷 章节名 2.节点没数据要去掉  3.[]和md的目录格式冲突了(或许没冲突看4来说) 4.文件名空格问题 5.去空格出的问题 trim?
    private static String fileName = "%s.%s.%s";
    private static String fileMenu = "- [x] [%s](./" + novelName + "/%s.md)";

    private static String novelIndex = "https://www.wenku8.net/novel/1/%s/";
    private static String novelMsg = "https://www.wenku8.net/book/%s.htm";
    private static String novelType = "未知";

    private static String tempDir = "";

    public static void main(String[] args) {
        String novelNum = "1765";
        try {
            // 下载一本小说
            // oneNovel(novelNum);

            // ?page=2  140
            novelListNum();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void novelListNum(){
        //https://www.wenku8.net/modules/article/articlelist.php?page=2
        //String modules = "https://www.wenku8.net/modules/article/articlelist.php";

        try {
            for(int i = 2; i < 141; i++) {
                List<String> urlList = new ArrayList<>();
                String modules = "https://www.wenku8.net/modules/article/articlelist.php?page=" + i;
                String result = HttpURLConnectionUtil.sendToUrlRequest(modules, "GET", "gbk", Header.wenku8Map());
                Set<String> stringSet = RegexUtils.matcheList("https://www.wenku8.net/book/\\d{0,6}.htm", result);
                for (String s : stringSet) {
                    System.out.println(s);
                    saveAll(s + "  \n");
                    // System.out.println(s.substring(s.lastIndexOf("/")+1,s.lastIndexOf(".")));
                    oneNovel(s.substring(s.lastIndexOf("/") + 1, s.lastIndexOf(".")));
                    urlList.add(s);
                }
                /*ThreadGroupDownObj threadGroupDown = new ThreadGroupDownObj(urlList);
                threadGroupDown.run();*/
            }
        } catch (Exception e) {
            System.err.println("novelListNum:");
            e.printStackTrace();
        }

    }

    public static void saveAll(String content){
        PrintWriterUtils.fileWriter(ConstantFile.javaFilePath + "\\craw\\www.wenku8.net", "list.md",content);
    }

    // 一本小说的流程
    public static void oneNovel(String novelNum) {

        // 拿到小说号， 0/num
        // 小说名，章节目录


        try {

            // novelMsg
            String[] stringMsg = novelMsg(String.format(novelMsg,novelNum));

            // 定义小说章节顺序
            int i = 1;
            // 小说目录
            Map<String,String> map = new HashMap<>();

            // 保存小说基本信息
            // saveMDMenu

            // dir 目录信息

            // 1.返回一个html的串
            String result = HttpURLConnectionUtil.sendToUrlRequest(novelIndex,"GET","","gbk");
            Document document = Jsoup.parse(result);

            // novelName = document.getElementById("title").text();
            fileMenu = "- [x] [%s](./" + novelName + "/%s.md)";

            // 分卷
            Elements elements_css = document.getElementsByClass("css");
            System.out.println(elements_css.size());
            // 章节
            Elements elements_css_trList = elements_css.get(0).getElementsByTag("tr");
            for(Element elements_css_tr:elements_css_trList ){
                Elements elements_css_tr_vcssList = elements_css_tr.getElementsByClass("vcss");
                if(elements_css_tr_vcssList.size()==1){
                    // 卷
                    splitChapter = elements_css_tr_vcssList.get(0).text();
                }else {
                    // 章节列表
                    Elements elements_css_tr_ccssList = elements_css_tr.getElementsByClass("ccss");
                    for(Element elements_css_tr_ccss:elements_css_tr_ccssList){
                        // 章节有数据
                        String chapter = elements_css_tr_ccss.text();
                        if(!chapter.trim().isEmpty()) {
                            // 章节链接
                            String page = elements_css_tr_ccss.getElementsByTag("a").attr("href");
                            // 章节名格式
                            String menu = String.format(fileName, Constant_use.df.format(i), splitChapter, chapter).replaceAll(" ","");
                            map.put(novelIndex + page, menu);
                            // 同时生成文件目录
                            saveMDMenu(String.format(fileMenu, menu, menu) + "\r\n");
                            i++;
                        }
                    }
                }
            }

            // 小说基本信息
            /*for(String s:stringMsg){
                //System.out.println(s);
                saveMDMenu(s);
            }*/

            // 保存小说内容
            for(String key:map.keySet()){
                // url , 内容
                // 保存 md 文件
                saveMD(String.format(map.get(key)),novelContent(key));
                //return;
            }

            // 调整小说目录
            WenkuNovelMenu.addFileMenu(tempDir ,novelName + "目录" + ".md");
        } catch (Exception e) {
            System.err.println("oneNovel:err");
            e.printStackTrace();
        }
    }

    public static String[] novelMsg(String url){
        // 获取小说封面的基本信息
        String[] strAry = new String[11];
        try {
            String result = HttpURLConnectionUtil.sendToUrlRequest(url,"GET","","gbk");

            novelIndex = RegexUtils.matcheStr("https://www.wenku8.net/novel/\\d{0,6}/\\d{0,6}/index.htm",result).replace("index.htm","");

            Document document = Jsoup.parse(result);
            Elements elements_table = document.getElementsByTag("table");

            Elements elements_table_td = elements_table.get(0).getElementsByTag("td");
            //System.out.println(elements_table.get(0).getElementsByTag("td").size());

            //System.out.println(elements_table_td.get(0));
            novelName = elements_table_td.get(1).getElementsByTag("b").text();
            strAry[0] = MDUtils.mdTitle(novelName);
            //System.out.println(strAry[0] = elements_table_td.get(1).getElementsByTag("b").text());
            //System.out.println(elements_table_td.get(2));
            strAry[2] = MDUtils.mdTitle(elements_table_td.get(3).text());

            // 小说目录层级 取出目录 去除空格
            novelType = strAry[2].substring(strAry[2].indexOf("：") + 1).trim() + File.separator ;
            dir = Constant_use.dirBase + new URL(url).getAuthority() + File.separator ;
            tempDir = dir + novelType + novelName;

            //System.out.println(elements_table_td.get(3).text());
            strAry[3] = MDUtils.mdTitle(elements_table_td.get(4).text());
            //System.out.println(elements_table_td.get(4).text());
            strAry[4] = MDUtils.mdTitle(elements_table_td.get(5).text());
            //System.out.println(elements_table_td.get(5).text());
            strAry[5] = MDUtils.mdTitle(elements_table_td.get(6).text());
            //System.out.println(elements_table_td.get(6).text());
            strAry[6] = MDUtils.mdTitle(elements_table_td.get(7).text());
            //System.out.println(elements_table_td.get(7).text());

            Elements elements_table_td2 = elements_table.get(2).getElementsByTag("td");
            //System.out.println(elements_table.get(2).getElementsByTag("td").size());
            strAry[1] = MDUtils.mdImg(elements_table_td2.get(0).getElementsByTag("img").attr("src"));
            //System.out.println(elements_table_td2.get(0).getElementsByTag("img").attr("src"));

            strAry[7] = MDUtils.mdTitle(elements_table_td2.get(0).getElementsByTag("b").text());
            //System.out.println(elements_table_td2.get(0).getElementsByTag("b").text());
            strAry[8] = MDUtils.mdTitle(elements_table_td2.get(1).getElementsByTag("b").text());
            //System.out.println(elements_table_td2.get(1).getElementsByTag("b").text());

            //System.out.println(elements_table_td2.get(1).getElementsByTag("span").size());
            Elements elements_table_td2_span = elements_table_td2.get(1).getElementsByTag("span");
            //strAry[9] = elements_table_td2_span.get(0).text();
            //System.out.println(elements_table_td2_span.get(0));
            strAry[9] = MDUtils.mdTitle(elements_table_td2_span.get(1).text() + elements_table_td2_span.get(2).text());
            //System.out.println(elements_table_td2_span.get(1));
            //System.out.println(elements_table_td2_span.get(2));
            strAry[10] = MDUtils.font_SimSun(CrawDataParse.parse(elements_table_td2_span.get(3).text() + elements_table_td2_span.get(4)));
            //System.out.println(elements_table_td2_span.get(3));
            //System.out.println(elements_table_td2_span.get(4));
            //System.out.println(elements_table_td2.get(2));

            for(String s:strAry){
                //System.out.println(s);
                saveMDMenu(s);
            }

            // 保存小说基本信息
            //saveMDMenu("");
        } catch (Exception e) {
            System.err.println("novelMsg:err");
            e.printStackTrace();
        }
        return strAry;
    }

    // 获取章节内容
    public static String novelContent(String url){
        try {
            String result = HttpURLConnectionUtil.sendToUrlRequest(url,"GET","","gbk");
            Document document = Jsoup.parse(result);
            Element element = document.getElementById("content");
            String content = element.toString();
            // 移除指定id
            content = CrawDataParse.removeTagById(content,"ul","contentdp");
            // div
            content = CrawDataParse.removeTagByClass(content,"div","divimage");
            // tag
            content = CrawDataParse.removeTagByTag(content,"a");
            content = CrawDataParse.parse(content,new String[]{"content","contentdp"});

            return MDUtils.font_SimSun(content);
        } catch (Exception e) {
            System.err.println("novelContent:err");
            e.printStackTrace();
        }
        return "";
    }

    public static void saveMDMenu(String content){
        PrintWriterUtils.fileWriter(dir + novelType ,novelName + "目录" + ".md",content);
    }

    public static void saveMD(String fileName,String content){
        PrintWriterUtils.fileWriter(tempDir, fileName + ".md",content);
    }

}
