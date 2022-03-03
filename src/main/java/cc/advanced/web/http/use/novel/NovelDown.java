package cc.advanced.web.http.use.novel;

import cc.resource.PropertiesHeader;
import cc.advanced.web.http.httpurlconnect.HttpURLConnectionUtil;
import cc.constant.ConstantFile;
import cc.core.io.base.PrintWriterUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author c.c.
 * @date 2020/12/26
 */
public class NovelDown implements Runnable {

    private String filePath = ConstantFile.L1_javaFilePath + "\\craw\\www.loveyuedu.com";

    private String menuName;
    private String url;
    private String index;

    public void bookDown(){
        String result = HttpURLConnectionUtil.flow(url,"GET","gbk", PropertiesHeader.loveyueduMap());
        Document document = Jsoup.parse(result);
        //System.out.println(document.body());
        String content = document.getElementById("content").toString();
        //System.out.println(content);
        content = content.replace("&nbsp;"," ").replace("<br> ","")
                .replace("<div id=\"content\">","").replace("</div>","");
        //System.out.println(content);

        //System.out.println("开始下载章节:" + index + menuName);

        PrintWriterUtils.printWriter(filePath + "\\",index + "." + menuName + ".txt",content);
    }

    @Override
    public void run() {
        bookDown();
    }

    public NovelDown(String menuName, String url, String index) {
        this.menuName = menuName;
        this.url = url;
        this.index = index;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
