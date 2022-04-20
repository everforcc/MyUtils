package cc.advanced.web.http.use.pic;

import cc.sysconstant.ConstantFile;
import cc.core.file.utils.FileUtils;
import cc.core.io.base.StreamInputUtils;
import cc.core.io.base.PrintWriterUtils;
import cc.advanced.web.http.httpurlconnect.HttpURLConnectionUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.xsoup.Xsoup;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author c.c.
 * @date 2021/2/6
 */
public class Picture_zydk8 {

    public static void main(String[] args) {

        search("");

        //content("https://www.zydk8.com/post-16989.html");
        /*String string = "https://www.helloimg.com/images/2020/08/22/gteman-197896c037694269e.jpg";
        System.out.println(string.substring(string.lastIndexOf("/") + 1,string.length()));*/
    }

    static void search(String like){
        try {
            Document document = Jsoup.connect("https://www.zydk8.com/?keyword=" + like).get();
            //System.out.println(document.body());
            // li[@class='col-xs-12 clearfix']
            List<String> stringList = Xsoup.compile("li[@class='col-xs-12 clearfix']//a/@href").evaluate(document).list();

            stringList.forEach(Picture_zydk8::content);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void content(String url){
        try {
            Document document = Jsoup.connect(url).get();
            System.out.println(document.body());

            List<String> stringList = Xsoup.compile("//div[@id='mewsmian']//img/@src").evaluate(document).list();

            stringList.forEach(System.out::println);

            String path = url.substring(url.lastIndexOf("/") + 1,url.length());

            for (int i=0;i<stringList.size();i++){
                String string = stringList.get(i);
                String name = string.substring(string.lastIndexOf("/") + 1,string.length());

                downByUrl(string,path,name);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void downByUrl(String url, String dir, String fileName) throws Exception {
        InputStream in = HttpURLConnectionUtil.getStream(url, "GET", "", new HashMap<>());
        System.out.println("in.available():" + in.available());
        dir = FileUtils.checkFileName(dir);
        fileName = FileUtils.checkFileName(fileName);
        if (in.available() > 0) {
            // 下载字节流需要用到的
            StreamInputUtils.streamToFile(in, ConstantFile.L1_javaFilePath + "\\craw\\www.zydk8.com\\" + dir + "\\", fileName, new BigDecimal(1));
        } else {
            PrintWriterUtils.printWriter(ConstantFile.L1_javaFilePath + "\\craw\\www.zydk8.com\\" + dir + "\\", fileName + ".txt", url);
        }
    }


}
