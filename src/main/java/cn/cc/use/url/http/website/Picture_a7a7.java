package cn.cc.use.url.http.website;

import cn.cc.core.web.http.webmagic.base.Constant;
import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.xsoup.Xsoup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author c.c.
 * @date 2021/3/11
 */
public class Picture_a7a7 {

    private static String rootUrl = "a7a7.net";
    private static String picTypeCollectUrlPattern = "//div[@id='masonry']//a/@href|//div[@class='item-link-text']/text()";

    private static String picCollectUrlPattern = "//div[@id='masonry']//img/@data-original|//div[@id='masonry']//img/@alt";
    private static String picCollectNamePattern = "//div[@id='masonry']//img/@alt";

    private static List<String> errList = new ArrayList<>();


    public static void main(String[] args) {
        String typeUrl = "https://a7a7.net/meitu/index.php/category/alpha/2/";
        connectType(typeUrl);

        String collectUrl = "https://a7a7.net/meitu/index.php/archives/1355/";
        //connectCollect(collectUrl,"ALPHA-021");

        //subSuffix("https://tvax1.sinaimg.cn/large/da6ac54fgy1gmswyekebpj256o3gg4qp.jpg");
    }

    public static void connectType(String url){
        try {
            Document document = Jsoup.connect(url).userAgent(Constant.userAgent).get();
            System.out.println(document.body());
            List<String> list = Xsoup.compile(picTypeCollectUrlPattern).evaluate(document).list();

            System.out.println(" >>> ");

            int half = list.size()/2;
            for(int i=0;i<half;i++){
                System.out.println(list.get(i));
                System.out.println(list.get(i + half));
                connectCollect(list.get(i),list.get(i + half));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void connectCollect(String url,String dir){
        try {
            Document document = Jsoup.connect(url).userAgent(Constant.userAgent).get();
            System.out.println(document.body());

            List<String> list = Xsoup.compile(picCollectUrlPattern).evaluate(document).list();

            System.out.println(" >>> ");

            int half = list.size()/2;
            for(int i=0;i<half;i++){
                System.out.println(list.get(i));
                System.out.println(list.get(i + half));
                down(list.get(i),list.get(i + half),dir);
            }

            errList.forEach(System.err::println);
            errList = new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int retry = 0;
    public static void down(String fileUrl,String fileName,String dir){
        dir = cn.cc.core.file.utils.FileUtils.checkFileName(dir);
        fileName = cn.cc.core.file.utils.FileUtils.checkFileName(fileName);
        Connection connection = Jsoup.connect(fileUrl);
        Connection.Response response = null;
        try {
            response = connection.method(Connection.Method.GET).ignoreContentType(true).timeout(6*1000).execute();
            InputStream inputStream = response.bodyStream();
            System.out.println(response.contentType());

            File file = new File("E:\\craw\\" + rootUrl + File.separator + dir + File.separator + fileName + subSuffix(fileUrl));

            if(file.exists()){
                return;
            }

            FileUtils.copyToFile(inputStream,file);
            retry = 0;
        } catch (IOException e) {
            //e.printStackTrace();
            // 重试
            if(retry < 3){
                retry++ ;
                System.out.println("重试第" + retry + "次");
               down(fileUrl,fileName,dir);
            }else {
                retry = 0;
                errList.add(fileUrl + "\n" + fileName);
            }
        }

    }

    public static String subSuffix(String url){
        url = url.substring(url.lastIndexOf("."),url.length());
        System.out.println(url);
        return url;
    }

}
