package cc.advanced.web.http.webmagic.pageprocessor;

import cc.constant.ConstantCharSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class PageProcessorCommon {
    // 代码忘记提交再来一份，问题不大
    public static Document byHtml(String html){
        Document document = Jsoup.parse(html);
        return document;
    }

    public static Document byFile(String path){
        Document document = null;
        try {
            document = Jsoup.parse(new File(path), ConstantCharSet.UTF_8);
        } catch (IOException e) {
            System.out.println("格式化文件异常");
            e.printStackTrace();
        }
        return document;
    }

}
