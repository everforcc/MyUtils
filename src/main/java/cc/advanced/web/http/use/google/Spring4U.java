package cc.advanced.web.http.use.google;

import cc.advanced.web.http.httpurlconnect.HttpURLConnectionUtil;
import cc.resource.PropertiesHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class Spring4U {

    public static void main(String[] args) {

//        System.setProperty("https.proxyHost", "127.0.0.1");
//        System.setProperty("https.proxyPort", "41091");
//
//        System.setProperty("https.proxyHost", "127.0.0.1");
//        System.setProperty("https.proxyPort", "41091");

        // 要设置代理proxy，不明白为什么全区代理不生效
        //String url = "http://spring4u.info/viewthread.php?tid=61626&extra=page%3D2";
        //String result = HttpClientUtils.getWebContent(url, PropertiesHeader.spring4uMap());
        //System.out.println(result);

        //flow(url);
        //driver(url);

        driver("http://www.3diyibanzhu.xyz/13/13320/282630_4.html");
    }

    public static void flow(String url){
        String requestMethod = "GET";
        String charset = "big5";

        // PropertiesHeader.spring4uMap()

        //String result = HttpURLConnectionUtil.flow(url, requestMethod, charset, PropertiesHeader.spring4uMap());
        String result = HttpURLConnectionUtil.flow(url, requestMethod, charset,PropertiesHeader.spring4uMap());
        //String result = HttpClientUtils.getWebContent(url, null);

        System.out.println("result:" + result);
    }

    public static void driver(String url) {
        //String driverPath=System.getProperty("user.dir")+"....(路径)/geckodriver.exe";
        //System.setProperty("webdriver.gecko.driver","D:/java/environment/driver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        //方法一：通过setHeadless方法直接设置
        //options.setHeadless(true);
        //方法二:通过addArguments方法添加参数设置
        options.addArguments("headless");

        //options.addArguments("headless");

        //禁用沙盒nage
        //options.addArguments("no-sandbox");
        //options.addArguments("disable-gpu");

        cap.setCapability(ChromeOptions.CAPABILITY, options);

        //实例化虚拟浏览器对象
        WebDriver driver = new ChromeDriver(cap);
        //打开百度首页
        //String url = "http://www.biquge.info/10_10229";
        //String url = "http://www.biquge.info/10_10229";
        driver.get(url);

        System.out.println("driver.getPageSource(): ");
        System.out.println(driver.getPageSource());

        driver.get("http://www.3diyibanzhu.xyz/13/13320/319272_2.html");

        System.out.println("driver.getPageSource(): ");
        System.out.println(driver.getPageSource());

        /* 1.submit */
        //定位搜索框元素
       /* WebElement ele = driver.findElement(By.id("wd"));
        //输入需查询内容
        ele.sendKeys("斗破");
        ele.submit();
        // 支持xpath,  driver 浏览器处理页面
        List<WebElement> webElement_list_a = driver.findElements(By.xpath("//table[@class='grid']//td[@class='odd']"));
        //List<WebElement> webElement_list_a = ele.findElements(By.xpath("//table[@class='grid']//td[@class='odd']"));
        for (WebElement webElement : webElement_list_a) {
            System.out.println(webElement.getText());
        }*/

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
        System.out.println("关闭driver");
    }

}
