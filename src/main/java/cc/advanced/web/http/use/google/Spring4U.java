package cc.advanced.web.http.use.google;

import cc.advanced.web.http.httpurlconnect.HttpURLConnectionUtil;
import cc.resource.PropertiesHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Spring4U {

    public static void main(String[] args) {

//        System.setProperty("https.proxyHost", "127.0.0.1");
//        System.setProperty("https.proxyPort", "41091");
//
//        System.setProperty("https.proxyHost", "127.0.0.1");
//        System.setProperty("https.proxyPort", "41091");

        // 要设置代理proxy，不明白为什么全区代理不生效
        String url = "http://spring4u.info/viewthread.php?tid=61626&extra=page%3D2";
        //String result = HttpClientUtils.getWebContent(url, PropertiesHeader.spring4uMap());
        //System.out.println(result);

        flow(url);
        //driver(url);
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
        System.setProperty("webdriver.gecko.driver","D:/java/environment/driver/chromedriver.exe");
        //实例化虚拟浏览器对象
        WebDriver driver = new ChromeDriver();
        //打开百度首页
        //String url = "http://www.biquge.info/10_10229";
        //String url = "http://www.biquge.info/10_10229";
        driver.get(url);

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
        //driver.close();
    }

}
