package cc.advanced.web.http;

import cc.utils.Print_Record;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * apache的工具包
 */
public class HttpClientUtils {

    public static void main(String[] args) {
        /**
         * 1. get
         * 2. post
         * 2.1 表单
         * 2.2 访问接口 设置登录认证
         * 3. 上传文件
         */
        /**
         * 登录认证
         * basic √
         * token
         */
        //get();
        //postForm();
        postWebInterface();
    }

    private static Print_Record print_record = Print_Record.getInstanse();

    /**
     * 1. get
     */
    public static void get(){
        try (
              CloseableHttpClient httpClient = HttpClients.createDefault();
              // 1. 创建httpget
              CloseableHttpResponse response = httpClient.execute(new HttpGet("http://www.baidu.com/"));
            ){
            HttpEntity entity = response.getEntity();
            if(Objects.nonNull(entity)){
                print_record.println("response content length : " + entity.getContentLength());
                print_record.println("response content : " + EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2.1 表单
     */
    public static void postForm() {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        // 创建httppost
        HttpPost httppost = new HttpPost("http://127.0.0.1:8081/open/auth/login");

        // 创建参数队列
        //
//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        formparams.add(new BasicNameValuePair("username", "admin"));
//        formparams.add(new BasicNameValuePair("password", "1yUyds2Bxgr3R8u"));
//        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams,"UTF-8");

        // 请求体 为json类的
        Map<String,String> map = new HashMap<>();
        map.put("username","admin");
        map.put("password","1yUyds2Bxgr3R8u");
        StringEntity stringEntity;

        try {
            stringEntity = new StringEntity(JSONObject.toJSONString(map),"UTF-8");
            httppost.setHeader("Content-Type","application/json;charset=UTF-8");
            httppost.setEntity(stringEntity);
            System.out.println("executing request " + httppost.getURI());
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                print_record.println("--------------------------------------");
                print_record.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                print_record.println("--------------------------------------");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                response.close();
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  3. 请求后台接口，带登录认证
     */
    public static void postWebInterface() {
        String userName = "176boc@user6703";
        String passWord = "password#!";
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost("http://127.0.0.1:8080/old-school-video/save");
        CloseableHttpResponse response = null;
        // 创建参数队列
//        List<NameValuePair> formparams = new ArrayList<>();
//        formparams.add(new BasicNameValuePair("videoLink", "https://img.ytdinfo.cn/wxact-staging/202012250955037713314526a94f.mp4"));
//        formparams.add(new BasicNameValuePair("videoName", "老年大学的视频"));

        // 设置请求参数
        Map<String,String> map = new HashMap<>();
        map.put("username","admin");
        map.put("password","1yUyds2Bxgr3R8u");
        StringEntity stringEntity;

        // 设置登录认证
        String encoding = DatatypeConverter.printBase64Binary((userName + ":" + passWord).getBytes(StandardCharsets.UTF_8));
        UrlEncodedFormEntity uefEntity;
        try {
            // uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            stringEntity = new StringEntity(JSONObject.toJSONString(map),"UTF-8");
            httppost.setEntity(stringEntity);
            httppost.setHeader("Authorization","Basic " + encoding);

            print_record.println("executing request " + httppost.getURI());

            // 执行拿到返回值
            response = httpclient.execute(httppost);
            StatusLine statusLine = response.getStatusLine();
            int code = statusLine.getStatusCode();
            if(200 == code) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    print_record.println("--------------------------------------");
                    print_record.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                    print_record.println("--------------------------------------");
                }
            }else {
                print_record.printErrln("响应码: " + code,"[%s]");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                response.close();
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
