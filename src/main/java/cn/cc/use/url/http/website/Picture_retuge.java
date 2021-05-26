package cn.cc.use.url.http.website;

import cn.cc.core.io.utils.PrintWriterUtils;
import cn.cc.core.web.http.webmagic.base.Constant;
import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.xsoup.Xsoup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author c.c.
 * @date 2021/3/14
 */
public class Picture_retuge {

    public static void main(String[] args) {
        type();
        //page("");
    }

    /**
     * 1.请求网页全部
     * 2. 分页一共14页
     *
     * 3.页面内容 ，down到本地
     * 4.归属信息，标题
     * 5.图片信息
     */



    public static void type(){
        try {
            String url = "https://www.retuge.com/category/wmxz/slct-2/page/%s?c2&c3&c4&t";
            for(int num=1;num<15;num++) {
                //Document document = Jsoup.parse(FileUtils.readFileToString(new File("C:\\Users\\71849\\Desktop\\森萝财团 - 热图阁.html")));
                Document document = Jsoup.connect(String.format(url,num)).userAgent(Constant.userAgent).get();
                System.out.println(document.body());
                save(document.body().toString(),"retuge" + num + ".html","E:\\craw\\www.retuge.com\\");
                //System.out.println(document);
                List<String> stringList = Xsoup.compile("//div[@class='post grid']//div[@class='img']//a/@href").evaluate(document).list();

                for (int i = 0; i < stringList.size(); i++) {
                    page(stringList.get(i));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void page(String url){
        try {
            //Document document = Jsoup.parse(FileUtils.readFileToString(new File("C:\\Users\\71849\\Desktop\\page.txt")));
            Document document = Jsoup.connect(url).userAgent(Constant.userAgent).get();

            String title = Xsoup.compile("//h1[@class='article-title']//text()").evaluate(document).get();
            List<String> picList = Xsoup.compile("//div[@class='article-content']//p//img/@src").evaluate(document).list();
            List<String> msg = Xsoup.compile("//div[@class='item item2']//allText()").evaluate(document).list();

            System.out.println("title:" + title);
            //picList.forEach(System.out::println);
            //msg.forEach(System.out::println);
            String dir = cn.cc.core.file.utils.FileUtils.checkFileName(title);

            //down pic
            for(int i=0;i<picList.size();i++){
                down(picList.get(i),dir,i);
            }

            // 保存html
            save(document.body().toString(), dir + ".html" , "E:\\craw\\www.retuge.com\\" + dir );

            // save msg
            for(int j=0;j<msg.size();j++){
                save(msg.get(j),dir + ".txt" , "E:\\craw\\www.retuge.com\\" + dir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(String content,String name,String path){
        PrintWriterUtils.fileWriter(path,name,content);
    }

    private static int retry = 0;
    public static void down(String fileUrl,String dir,int i){
        dir = cn.cc.core.file.utils.FileUtils.checkFileName(dir);
        //fileName = cn.cc.core.file.utils.FileUtils.checkFileName(fileName);
        Connection connection = Jsoup.connect(fileUrl).userAgent(Constant.userAgent);
        Connection.Response response = null;
        try {
            response = connection.method(Connection.Method.GET).ignoreContentType(true).timeout(60*1000).execute();
            InputStream inputStream = response.bodyStream();
            System.out.println(response.contentType());
            String[] suffix = response.contentType().split("/");

            File file = new File("E:\\craw\\www.retuge.com\\" + dir + File.separator  + i + "." + suffix[1]);

            if(file.exists()){
                return;
            }

            FileUtils.copyToFile(inputStream,file);
            retry = 0;
        } catch (IOException e) {
            e.printStackTrace();
            // 重试
            if(retry < 3){
                retry++ ;
                System.out.println("重试第" + retry + "次");
                down(fileUrl,dir,i);
            }else {
                retry = 0;
                //errList.add(fileUrl + "\n");
            }
        }

    }

}
