package cc.advanced.web.craw.selenium;

import cc.constant.ConstantFile;
import cc.core.io.base.PrintWriterUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.mobile.NetworkConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author c.c.
 * @date 2020/12/8
 */
public class SeleniumMethod {

    // 是慢，但是也没有那么慢，
    // 禁止启动浏览器打开怎么做
    //headless
    public static void main(String[] args) {
        openSpring4U();
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

    public static void openSpring4U(){

        //Map<String, String> mobileEmulation = new HashMap<String, String>();
        //mobileEmulation.put("deviceName", "Laptop with touch");

        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        //chromeOptions.put("mobileEmulation", mobileEmulation);
        //chromeOptions.put("binary", binarylocation);
//        chromeOptions.put("Cookie", "cdb_cookietime=2592000; cdb_oldtopics=D32924D10625D; cdb_visitedfid=9D57D34D73D96D103D124; cdb_sid=5iy5Eu; cdb_auth=B1xVAgMEBA8FBQECBgdVV1AOBwoNAlEFAgACVAQHBQM5bAJUBVRSAQ");
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//        capabilities.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);

        WebDriver driver = new ChromeDriver();
        //((NetworkConnection) driver).setNetworkConnection(NetworkConnection.ConnectionType.ALL);
        //打开百度首页
        String url = "http://spring4u.info/search.php?searchid=11361166&orderby=lastpost&ascdesc=desc&searchsubmit=yes";
        Cookie everforcc = new Cookie("Cookie", "cdb_cookietime=2592000; cdb_oldtopics=D32924D10625D; cdb_visitedfid=9D57D34D73D96D103D124; cdb_sid=5iy5Eu; cdb_auth=B1xVAgMEBA8FBQECBgdVV1AOBwoNAlEFAgACVAQHBQM5bAJUBVRSAQ");
        driver.get(url);
//        driver.manage().deleteAllCookies();
//        driver.manage().addCookie(everforcc);

        WebElement username =  driver.findElement(By.name("username"));
        username.sendKeys("cc5664");
        WebElement password  =  driver.findElement(By.name("password"));
        password.sendKeys("c.c.c.c.");
        WebElement loginsubmit  =  driver.findElement(By.name("loginsubmit"));
        loginsubmit.click();

        driver.navigate().refresh();

        Set<Cookie> cookieSet =  driver.manage().getCookies();
        cookieSet.forEach(System.out::println);
        //获取页面标题
        System.out.println("Page title is :" + driver.getTitle());
        //获取页面url
        System.out.println("Page url is :" + driver.getCurrentUrl());
        //关闭driver
        driver.close();

    }

    public static void oneCatalogueContent(String href,String title){
        String content = "";
        WebDriver driver_srping4u = new ChromeDriver();
        ((NetworkConnection) driver_srping4u).setNetworkConnection(NetworkConnection.ConnectionType.ALL);
        try{
            //driver_novel.manage().window().setPosition();
            driver_srping4u.get(href);
            WebElement webElement_content = driver_srping4u.findElement(By.id("content"));
            content = webElement_content.getText();
            driver_srping4u.close();
        }catch (Exception e){
            oneCatalogueContent(href,title);
        }finally {
            save(title);
            save(content);
        }
    }

    public static void saveUrl(String content){
        PrintWriterUtils.printWriter(ConstantFile.L1_javaFilePath + "/novel/biquge","斗破苍穹Url.txt",content);
    }

    public static void save(String content){
        PrintWriterUtils.printWriter(ConstantFile.L1_javaFilePath + "/novel/biquge","斗破苍穹.txt",content);
    }
}
