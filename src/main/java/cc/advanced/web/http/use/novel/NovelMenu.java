package cc.advanced.web.http.use.novel;

import cc.sysresource.PropertiesHeader;
import cc.advanced.web.http.httpurlconnect.HttpURLConnectionUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DecimalFormat;

/**
 * @author c.c.
 * @date 2020/12/26
 */
public class NovelMenu implements Runnable {

    private static int data;

    public NovelMenu() {
    }

    public NovelMenu(String menuName, String menuUrl, String index) {
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.index = index;
    }

    private static ThreadUtils threadUtils = new ThreadUtils();

    public void bookMenu(String url){

        //List<NovelMenu> novelMenuList = new ArrayList<>();
        String result = HttpURLConnectionUtil.flow(url,"GET","gbk", PropertiesHeader.loveyueduMap());
        Document document = Jsoup.parse(result);
        Element element_list = document.getElementById("list");
        Elements elements_ddList = element_list.getElementsByTag("dd");
        int size = elements_ddList.size();
        System.out.println("elements_ddList.size():" + size);
        Element temp;
        int index = 9;
        data = size - index;
        for(index = 9;index < size;index++){
            temp = elements_ddList.get(index);
            //System.out.println(temp.html() + " >>> " +temp.attr("href"));
            //novelMenuList.add(new NovelMenu(temp.html(),temp.attr("href"),df.format(index)));
            menuUrl = "https://www.loveyuedu.com/" + temp.getElementsByTag("a").get(0).attr("href");
            threadUtils.setRunnableList(new NovelMenu(temp.text(),menuUrl,df.format(index)));
        }
        //System.out.println(document.body());

        threadUtils.run();
    }

    private String menuName;
    private String menuUrl;
    private String index;

    private static DecimalFormat df = new DecimalFormat("0000");
    private static ThreadUtils threadUtilsDown = new ThreadUtils();
    private static int i = 0; // 当前线程的数据量 作为 下一个线程的启动条件
    @Override
    public void run() {
        i++;
        System.out.println("run():" + this.menuName + " >>> " + this.menuUrl + " >>> " + this.index);
        threadUtilsDown.setRunnableList(new NovelDown(menuName,menuUrl,index));
        System.out.println("i:" + i + " >>> data:" + data );
        if(i==data){
            i = 0;
            data = 0; // 尽量少使用全局变量,多线程会会出问题
            threadUtilsDown.run();
        }
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
