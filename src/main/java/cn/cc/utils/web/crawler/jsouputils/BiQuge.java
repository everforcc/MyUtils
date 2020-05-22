package cn.cc.utils.web.crawler.jsouputils;


import cn.cc.utils.date.DateModel;
import cn.cc.utils.fileio.io.InputStream_IO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.net.URL;

public class BiQuge {

    /**
     * 待解决问题 js，先用script 解析出js方法在组装数据请求
     * 计时问题，最后计算下载时间
     * 递归最好不要用，数据太多容易内存不足
     * 地址也存好，可以在创建一个类单独创建地址，还是要用数据库，把数据信息都存起来
     *
     *
     * @throws Exception
     */


    public BiQuge() throws Exception {
    }

    // 可以改为 while(true){}
    // 不要使用递归会出现内存问题
    public static void main(String[] args) {
        try {
            long timeStart=System.currentTimeMillis();
            System.out.println("开始时间");
            //DateModel.now();
            int i=0;
            while (!url.equals("/59_59305")){
                System.out.println("第"+ i++ +"章");
                noeContnet();
                System.out.println("能否进入下一章:"+!url.equals("/59_59305"));
            };
            //DateModel.now();
            /*System.out.println("结束时间");
            long timeEnd=System.currentTimeMillis();
            //共用
            System.out.println((timeEnd-timeStart)/1000/60+"分钟");*/
        } catch (Exception e) {
            DateModel.now();
            e.printStackTrace();
        }
    }

    private static Double interval;

    private static final String indexUrl="https://www.biqukan.com";

    private static final String novelName="我有一座恐怖屋.txt";

    private static StringBuilder url=new StringBuilder("start");

    //小说首页
    //private static String novelUrl="https://www.biqukan.com/3_3037/1349252.html";
    //807
//    private static String novelUrl="https://www.biqukan.com/20_20994/7956752.html";

    // 网站首页，小说地址//章节地址
    //private static String novelUrl="https://www.biqukan.com/20_20994/7958137.html";

    private static String novelUrl="https://www.biqukan.com/59_59305/417843561.html";

    private static InputStream_IO downFile;

    static {
        try {
            //"D:\\test",novelName
            downFile = new InputStream_IO();
        } catch (Exception e) {
            e.printStackTrace();
            DateModel.now();
        }
    }


    /**
     * 获取当前章节内容
     * @throws Exception
     */
    public static void noeContnet()throws Exception{
        System.out.println("当前章节时间:");
        DateModel.now();
        //interval = Math.random()*100+1;
        //Thread.sleep(interval.longValue());
        Document document = Jsoup.parse(new URL(novelUrl).openStream(),"gbk",novelUrl);// 转换为Dom树
        Elements elementsclass = document.getElementsByClass("content");
        Elements title = elementsclass.select("div h1");
        System.out.println("章节标题:"+title.text());
        downNovel(title.text());
        Element elementid = document.getElementById("content");

        System.out.println("当前章节内容"+elementid.text());
        downNovel(elementid.text());
        //page_chapter
        Elements elementsNextPageUrl = document.getElementsByClass("page_chapter");
        Elements elementsUrl = elementsNextPageUrl.select("div ul li");
        //System.out.println("地址个数"+elementsUrl.size());
        System.out.println("elementsUrl.toString():"+elementsUrl.toString());
        // 0上一章 1返回目录 2下一章 3加入书签           待解决问题 js，先用script 解析出js方法在组装数据请求
        url=new StringBuilder();
        url.append(elementsUrl.get(2).select("li a").attr("href"));
        System.out.println("下方菜单栏:"+elementsUrl.text());
        System.out.println("下一章地址:" + url);
        System.out.println("结束时间");
        //DateModel.now();
        if(url.toString()!="/59_59305"){
            novelUrl=indexUrl+url;
            noeContnet();
        }else{
            //如果等于就表示下载完毕，关闭流
            //downFile.writer.close();
            System.exit(0);
        }
        novelUrl=indexUrl+url;
        System.out.println("url:"+novelUrl);
        //3_3037 表示结束
        //br 为换行符所以无用
        /*Elements elements = elementid.select("div > br");
        System.out.println("内容");
        System.out.println(elements.size());
        System.out.println(elements.toString());
        for(Element element:elements){
            System.out.println(element.text());
        }*/
        //System.out.println(elements.text());

    }

    public static void downNovel(String content){

        try {
            downFile.IO_PrintWriter(new File("D:"+novelName),content);
        } catch (Exception e) {
            e.printStackTrace();
            DateModel.now();
        }
    }


}
