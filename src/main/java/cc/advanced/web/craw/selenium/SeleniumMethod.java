package cc.advanced.web.craw.selenium;

import cc.constant.ConstantFile;
import cc.core.io.PrintWriterUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

/**
 * @author c.c.
 * @date 2020/12/8
 */
public class SeleniumMethod {

    // 是慢，但是也没有那么慢，
    // 禁止启动浏览器打开怎么做
    //headless
    public static void main(String[] args) {

        openHtml();

    }

    public static void openHtml(){

        //String driverPath=System.getProperty("user.dir")+"....(路径)/geckodriver.exe";
        //System.setProperty("webdriver.gecko.driver",ConstantFile.javaFilePath + "/driver/chromedriver-89.exe");
        //实例化虚拟浏览器对象
        WebDriver driver = new ChromeDriver();
        //打开百度首页
        //String url = "http://www.biquge.info/10_10229";
        String url = "http://www.biquge.info/10_10229";
        driver.get(url);

        /* 1.submit */
        //定位搜索框元素
        WebElement ele = driver.findElement(By.id("wd"));
        //输入需查询内容
        ele.sendKeys("斗破");
        ele.submit();
        // 支持xpath,  driver 浏览器处理页面
        List<WebElement> webElement_list_a = driver.findElements(By.xpath("//table[@class='grid']//td[@class='odd']"));
        //List<WebElement> webElement_list_a = ele.findElements(By.xpath("//table[@class='grid']//td[@class='odd']"));
        for(WebElement webElement:webElement_list_a) {
            System.out.println(webElement.getText());
        }

        /* 2.click */
        /*WebElement search_text =  driver.findElement(By.className("search"));
        WebElement search_button  =  driver.findElement(By.className("button"));
        search_text.sendKeys("测试");
        search_button.click();*/

        //获取页面标题
        System.out.println("Page title is :" + driver.getTitle());
        //获取页面url
        System.out.println("Page url is :" + driver.getCurrentUrl());
        //关闭driver
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
        PrintWriterUtils.fileWriter(ConstantFile.javaFilePath + "/novel/biquge","斗破苍穹Url.txt",content);
    }

    public static void save(String content){
        PrintWriterUtils.fileWriter(ConstantFile.javaFilePath + "/novel/biquge","斗破苍穹.txt",content);
    }
}
