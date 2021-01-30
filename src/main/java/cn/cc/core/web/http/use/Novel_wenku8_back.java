package cn.cc.core.web.http.use;

import cn.cc.core.file.utils.MDUtils;
import cn.cc.core.web.http.utils.CrawDataParse;
import cn.cc.core.web.http.utils.HttpURLConnectionUtil;
import cn.cc.core.io.utils.PrintWriterUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Novel_wenku8_back {

    // 章节 上下 名字问题 add

    //private static String url = "https://www.wenku8.net/novel/1/1973/index.htm";
    //private static String url = "https://www.wenku8.net/novel/1/1973/74161.htm";
    /*private static String url = "https://www.wenku8.net/novel/1/1973/";
    private static String novelName = "欢迎来到实力至上主义的教室5";
*/
    // https://www.wenku8.net/novel/1/1213/index.htm
    /*private static String url = "https://www.wenku8.net/novel/1/1213/";
    private static String novelName = "我的青春恋爱物语果然有问题";*/

    /*private static String url = "https://www.wenku8.net/novel/0/353/";
    private static String novelName = "叛逆的鲁路修";*/
    private static String url = "https://www.wenku8.net/novel/0/317/";
    private static String novelName = "Fate Zero";

    private static String splitChapter = "";
    private static String dirBase = "E:\\craw\\";
    private static String dir = "www.wenku8.net\\";

    // 上一章，下一章，类似小说的做法
    // 序号 卷 章节名 2.节点没数据要去掉  3.[]和md的目录格式冲突了(或许没冲突看4来说) 4.文件名空格问题 5.去空格出的问题 trim?
    private static String fileName = "%s.%s.%s";
    private static String fileMenu = "- [x] [%s](./" + novelName + "/%s.md)";
    private static String filetype = ".txt";


    //https://www.wenku8.net/novel/1/1973/index.htm

    public static void main(String[] args) {
        //novelContent("https://www.wenku8.net/novel/1/1973/69759.htm");
        flow();
    }

    // 文库的
    public static void flow() {
        int i = 1;
        DecimalFormat df = new DecimalFormat("0000");
        Map<String,String> map = new HashMap<>();
        try {

            // 1.返回一个html的串
            String result = HttpURLConnectionUtil.sendToUrlRequest(url + "index.htm","GET","","gbk");
            //System.out.println(result);
            Document document = Jsoup.parse(result);
            Elements elements_css = document.getElementsByClass("css");
            System.out.println(elements_css.size());

            Elements elements_css_trList = elements_css.get(0).getElementsByTag("tr");
            for(Element elements_css_tr:elements_css_trList ){
                // System.out.println(">>>" + elements_css_tr);
                // 组装文件地址，和文件名
                // System.out.println(elements_css_tr.getElementsByTag("td").size());
                Elements elements_css_tr_vcssList = elements_css_tr.getElementsByClass("vcss");
                if(elements_css_tr_vcssList.size()==1){
                    // 卷
                    splitChapter = elements_css_tr_vcssList.get(0).text();
                    //System.out.println(splitChapter);
                }else {
                    Elements elements_css_tr_ccssList = elements_css_tr.getElementsByClass("ccss");
                    for(Element elements_css_tr_ccss:elements_css_tr_ccssList){
                        String chapter = elements_css_tr_ccss.text();
                        if(!chapter.trim().isEmpty()) {
                            String page = elements_css_tr_ccss.getElementsByTag("a").attr("href");
                            //System.out.print( + " " + );
                            String menu = String.format(fileName, df.format(i), splitChapter, chapter).replaceAll(" ","");
                            map.put(url + page, menu);

                            // 同时生成文件目录
                            saveMDMenu(String.format(fileMenu, menu, menu) + "\r\n");
                            i++;
                        }
                    }
                    //System.out.println();
                }
            }

            /*for(int j=1 ; j<=i ; j++){
                System.out.println();
            }*/

            for(String key:map.keySet()){
                System.out.println(key + " >>> " + map.get(key));
                saveMD(String.format(map.get(key)),novelContent(key));
                //return;
            }
           /* // 2.用jsoup来解析
            Document document = PageProcessorCommon.byFile("F:\\aly\\wenku8\\page3.txt");
            Element element = document.getElementById("content");

            *//*String result = parseElse(element.toString());
            System.out.println(result);*//*
            // 这样也可以但是，样式会丢

            String divString = element.toString();
            String[] divAry = divString.split("\r\n");
            //System.out.println(element.toString());
            for(String s:divAry){
                System.out.println(s);
            }*/

            // 保存为md文件，既可以带格式，又不是那么麻烦

        } catch (Exception e) {
            System.out.println("反正就是异常了");
            e.printStackTrace();
        }
    }

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
            /*String content = CrawDataParse.parse(elements.toString(),new String[]{"content","contentdp"});
            return content;*/

            return MDUtils.font_SimSun(content);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void saveMDMenu(String content){
        //String formatNameResult = String.format(fileMDFormat,novelName,df.format(i),suffix);
        PrintWriterUtils.fileWriter(dirBase + dir, novelName + ".md",content);
    }

    public static void saveMD(String fileName,String content){
        //String formatNameResult = String.format(fileMDFormat,novelName,df.format(i),suffix);
        PrintWriterUtils.fileWriter(dirBase + dir + novelName, fileName + ".md",content);
    }

    private static String replaceToMD(){

        return "";
    }

    private static String parseElse(String element){
        // 首先去掉首位
        element = element.replaceAll("<br>","   ");
        element = element.replaceAll("&nbsp;"," ");

        // 图片
        String pic = regexTag(element);

        // 最后替换掉所有不需要的tag
        element = element.replaceAll("<.*>","");
        element+=pic;
        return element;
    }

    private static String regexTag(String string){
        //String c="《wo》 》";
        // ?一次 *号多次
        //Pattern pattern = Pattern.compile("<.* id=\"content\".*>");
        //        Pattern pattern = Pattern.compile("</div>");
        Pattern pattern = Pattern.compile("<img.*>");
        Matcher matcher = pattern.matcher(string);

        String pic = "";
        //是否匹配到了
        if (matcher.find()) {// 进入后可以全匹配
            String picTag = matcher.group(0);
            //                           src=\".*?\""
            Pattern pattern1 = Pattern.compile("src=\".*?\"");
            Matcher matcher1 = pattern1.matcher(picTag);
            if(matcher1.find()) {
                pic = matcher1.group(0);
                pic=pic.substring(5,pic.length()-1);
            }
        }
        return pic;
    }

}
