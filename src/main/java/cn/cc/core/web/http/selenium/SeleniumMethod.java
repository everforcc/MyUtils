package cn.cc.core.web.http.selenium;

import cn.cc.core.io.utils.PrintWriterUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author c.c.
 * @date 2020/12/8
 */
public class SeleniumMethod {

    // 是慢，但是也没有那么慢，
    // 禁止启动浏览器打开怎么做
    //headless
    public static void main(String[] args) {

        /*String driverPath=System.getProperty("user.dir")+"....(路径)/geckodriver.exe";
        System.setProperty("webdriver.gecko.driver","D:\\environment\\driver\\geckodriver.exe");
        FirefoxDriver firefoxDriver = new FirefoxDriver();

        WebDriver driver = new FirefoxDriver();*/

        //openChromeTest("");
        openHtml();

    }

    public static void openHtml(){
        //实例化虚拟浏览器对象
       WebDriver driver = new ChromeDriver();
       //打开百度首页
       //String url = "http://www.biquge.info/10_10229";
       String url = "http://spring4u.info/viewthread.php?tid=434&extra=page%3D1";
       driver.get(url);
       /*//定位搜索框元素
       WebElement ele = driver.findElement(By.id("kw"));
       //输入需查询内容
       ele.sendKeys("Cheese");
       ele.submit();*/

       //获取页面标题
       System.out.println("Page title is :" + driver.getTitle());
       //获取页面url
       System.out.println("Page url is :" + driver.getCurrentUrl());
       //关闭driver
       driver.close();

    }

    public static void openChromeTest(String url){
        Map<String,String> map = new HashMap<>();
        ChromeDriver chromeDriver = new ChromeDriver();

        WebDriver driver = new ChromeDriver();

        //driver.get("http://www.itest.info");
        driver.get("http://www.biquge.info/10_10229");
        String title = driver.getTitle();
        System.out.println(title);
        WebElement webElement_list = driver.findElement(By.id("list"));
        List<WebElement> webElement_list_a = webElement_list.findElements(By.tagName("a"));
        for(WebElement webElement:webElement_list_a) {
            System.out.println(webElement.getText());
            map.put(webElement.getAttribute("href"),webElement.getText());
            saveUrl(webElement.getAttribute("href") + " " + webElement.getText());
        }

        for(Map.Entry entry: map.entrySet()){
            oneCatalogueContent((String) entry.getKey(),(String)entry.getValue());
        }
        driver.close();
    }


    public static void oneCatalogueContent(String href,String title){
        String content = "";
        WebDriver driver_novel = new ChromeDriver();
        try{
            //driver_novel.manage().window().setPosition();
            driver_novel.get(href);
            WebElement webElement_content = driver_novel.findElement(By.id("content"));
            content = webElement_content.getText();
            driver_novel.close();
        }catch (Exception e){
            oneCatalogueContent(href,title);
        }finally {
            save(title);
            save(content);
        }
    }

    public static void saveUrl(String content){
        PrintWriterUtils.fileWriter("E:\\novel\\biquge","斗破苍穹Url.txt",content);
    }

    public static void save(String content){
        PrintWriterUtils.fileWriter("E:\\novel\\biquge","斗破苍穹.txt",content);
    }
}
