package cn.cc.core.http.crawler.use;

import cn.cc.core.date.DateModel;
import cn.cc.utils.fileio.io.InputStream_IO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Yukino
 * 2020/6/2
 */
public class NowsFun {

    private static final String novelName="NOWSFUN.txt";

    private static final String NOWSFUN = "http://www.nows.fun/" ;

    private static  Set<String> set = new HashSet<String>();

    public static void main(String[] args) {
        try {
            flow();
            down();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void flow() throws Exception {
        while (set.size()<1000) {
            Document document = Jsoup.parse(new URL(NOWSFUN).openStream(), "gbk", NOWSFUN);// 转换为Dom树
            //System.out.println(document.body());
            Element element = document.getElementById("sentence");
            System.out.println(element.text());
            set.add(element.text());
            System.out.println("当前有个"+set.size()+"句子");
        }
    }

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

    public static void down(){
        for(String s:set){
            downtxt(s);
        }
    }

    public static void downtxt(String content){

        try {
            downFile.IO_PrintWriter(new File("D:"+novelName),content);
        } catch (Exception e) {
            e.printStackTrace();
            DateModel.now();
        }
    }

}
