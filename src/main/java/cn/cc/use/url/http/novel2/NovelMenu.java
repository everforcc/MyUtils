package cn.cc.use.url.http.novel2;

import cn.cc.core.web.http.header.Header;
import cn.cc.core.web.http.utils.HttpURLConnectionUtil;
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

    /* 线程池还没设计好 */
    /* 每个类的多线程是针对自己的，由上一个类来调用自己的线程，自己调用下一个类的线程 */

    private static int data;

    public NovelMenu() {
    }

    public NovelMenu(NovelStruct novelStruct) {

    }



    public void bookMenu(String url){
        ThreadUtils threadUtils = new ThreadUtils();
        //Map<String,String> map = new HashMap<>();

        String result = HttpURLConnectionUtil.sendToUrlRequest(url,"GET","gbk", Header.loveyueduMap());
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
            //map.put(menuUrl,df.format(index) + "." + temp.text() + ".txt");
            threadUtils.setRunnableList(new NovelDown(temp.text(),menuUrl,df.format(index)));
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
