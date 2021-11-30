package cc.advanced.web.http.utils.httpclient;

import cc.constant.ConstantFile;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author c.c.
 * @date 2020/12/18
 */
public class HttpClientDemo {
    // 后端如何识别头信息
    // 头信息数组
    public static void main(String[] args) {
        try {
            t4();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void t1() {
        // 创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // HttpGet httpGet = new HttpGet("http://www.csdn.net/");
        HttpGet httpGet = new HttpGet("http://www.tuicool.com/");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);// 执行 http get请求
            HttpEntity httpEntity = response.getEntity(); // 获取返回实体
            System.out.println(EntityUtils.toString(httpEntity, "GB2312"));
            response.close(); // 关闭流
            httpClient.close();
        } catch (IOException e) { // IO 异常
            e.printStackTrace();
        }
    }

    public static void t2() throws Exception {
        // 创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // HttpGet httpGet = new HttpGet("http://www.csdn.net/");
        HttpGet httpGet = new HttpGet("http://www.tuicool.com/");
        httpGet.setHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);// 执行 http get请求
        HttpEntity httpEntity = response.getEntity(); // 获取返回实体
        System.out.println(response.getStatusLine().getStatusCode()); // 400未找到页面，总结一下状态码
        // 类型信息,用来过滤信息，只需要html,js之类的数据 jpg
        System.out.println(httpEntity.getContentType());
        //System.out.println(EntityUtils.toString(httpEntity, "GB2312"));
        response.close(); // 关闭流
        httpClient.close();
    }

    // 小文件还行，大文件有点问题
    public static void t3()throws Exception{
        // 创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // HttpGet httpGet = new HttpGet("http://www.csdn.net/");
        // 都是 image 类型
        HttpGet httpGet = new HttpGet("http://www.java1234.com/images/javaline.gif");
        httpGet.setHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);// 执行 http get请求
        HttpEntity httpEntity = response.getEntity(); // 获取返回实体
        if(httpEntity != null) {
            System.out.println(response.getStatusLine().getStatusCode()); // 400未找到页面，总结一下状态码
            // 类型信息,用来过滤信息，只需要html,js之类的数据 jpg
            System.out.println(httpEntity.getContentType().getValue());
            InputStream inputStream = httpEntity.getContent();
            // 快啊.! 这俩好想没啥区别 // 框架更快
            // FileUtils.copyInputStreamToFile(inputStream,new File(ConstantFile.javaFilePath + "/1.gif"));
            FileUtils.copyToFile(inputStream,new File(ConstantFile.L1_javaFilePath + "//2.gif"));
        }
        // buffer 包裹一下更快
        response.close(); // 关闭流
        httpClient.close();
    }

    // 4.代理, 目前都是代理ip有问题，大概 HTTP @ dyn.horocn.com/106.53.80.120:50000
    public static void t4()throws Exception{
        // 创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // HttpGet httpGet = new HttpGet("http://www.csdn.net/");
        // 都是 image 类型
        HttpGet httpGet = new HttpGet("http://www.tuicool.com/");
        HttpHost httpHost = new HttpHost("106.53.80.120",50000);
        RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);// 执行 http get请求
        HttpEntity httpEntity = response.getEntity(); // 获取返回实体
        if(httpEntity != null) {
            System.out.println(response.getStatusLine().getStatusCode()); // 400未找到页面，总结一下状态码
            // 类型信息,用来过滤信息，只需要html,js之类的数据 jpg
            System.out.println(httpEntity.getContentType().getValue());
            System.out.println(EntityUtils.toString(httpEntity, "UTF-8"));
            //InputStream inputStream = httpEntity.getContent();
            // 快啊.! 这俩好想没啥区别 // 框架更快
            // FileUtils.copyInputStreamToFile(inputStream,new File(ConstantFile.javaFilePath + "//1.gif"));
            //FileUtils.copyToFile(inputStream,new File(ConstantFile.javaFilePath + "//2.gif"));
        }
        // buffer 包裹一下更快
        response.close(); // 关闭流
        httpClient.close();
    }

}
