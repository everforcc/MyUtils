package cc.webapi.baidu.netdisk.utils;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author everforcc 2021-10-12
 */
public class ApacheUtils {

    public static String get(String url){
        return httpClient(url);
    }

    private static String httpClient(String url) {
        // 创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // HttpGet httpGet = new HttpGet("http://www.csdn.net/");
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            httpGet.setHeader("User-Agent","pan.baidu.com");
            response = httpClient.execute(httpGet);// 执行 http get请求
        } catch (ClientProtocolException e) {
            e.printStackTrace(); // http协议异常，
        } catch (IOException e) {
            // 网络都会有io异常
            e.printStackTrace();
        }
        HttpEntity httpEntity = response.getEntity(); // 获取返回实体
        try {
            return EntityUtils.toString(httpEntity);
        } catch (ParseException e) {// 解析异常
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.close(); // 关闭流
        } catch (IOException e) { // IO 异常
            e.printStackTrace();
        }
        try {
            httpClient.close();
        } catch (IOException e) { // IO 异常
            e.printStackTrace();
        }
        return "";
    }

}
