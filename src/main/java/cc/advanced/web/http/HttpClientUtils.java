package cc.advanced.web.http;

import cc.constant.ConstantFile;
import cc.utils.Print_Record;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.zip.GZIPInputStream;

/**
 * apache的工具包
 * @author c.c.
 * @date 2020/12/18
 */
public class HttpClientUtils {

    public static void main(String[] args) {
        /**
         * 1. get
         * 2. post
         * 2.1 表单
         * 2.2 访问接口 设置登录认证
         * 3. 上传文件
         * 4. 代理
         * 5. 流文件
         */
        /**
         * 登录认证
         * basic √
         * token
         * 请求头
         * 参数
         * 超时
         * 返回值
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

            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(
                    2000
            ).setConnectTimeout(
                    2000
            ).build();
            httppost.setConfig(requestConfig);

            /**
             * set 会更新
             * add 追加
             */
            httppost.setHeader("Authorization","Basic " + encoding);
            // httppost.addHeader("","");

            print_record.println("executing request " + httppost.getURI());

            // 执行拿到返回值
            response = httpclient.execute(httppost);
            StatusLine statusLine = response.getStatusLine();
            int code = statusLine.getStatusCode();

            if(HttpStatus.SC_OK == code) {
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

    /**
     * 4. 代理
     * @throws Exception
     */
    public static void withProxy()throws Exception{
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

    /**
     * 6. 流文件
     */
    public static void returnStream()throws Exception{
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

    /**** 兼容原始代码，慢慢调整 *****/

    /**
     * 根据给定的url地址访问网络，得到响应内容（这里为GET方式访问）
     * @param url 指定的url地址
     * @return web服务器响应的内容，为<code>String</code>类型，当访问失败时,返回null
     */
    @SuppressWarnings("deprecation")
    public static String getWebContent(String url,Map<String,String> map){
        //创建一个Http请求对象
        final HttpGet request = new HttpGet(url);

        if(map!=null&&map.size()>0){
            for(Map.Entry entry : map.entrySet()){
                request.setHeader((String) entry.getKey(), (String) entry.getValue());
            }
        }

        HttpParams params = new BasicHttpParams();

        //设置连接超时或响应超时
        HttpConnectionParams.setConnectionTimeout(params, 500);
        //HttpConnectionParams.setSoTimeout(params, 5000);
        //创建一个网络访问对象
        final HttpClient httpClient = new DefaultHttpClient(params);

        // 这个也不知道啥意思
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    //执行请求参数项
                    HttpResponse response = httpClient.execute(request);
                    //判断是否请求成功
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){ // 这个位置不是200 看看有啥数据
                        //获取响应信息
                        //noinspection UnnecessaryLocalVariable
                        String content = getResponseString(response.getEntity());
                        return content;
                    } else {
                        //网连接失败，使用Toast显示提示信息
                        //Toast.makeText(context, "网络访问失败，请检查您机器的联网设备!", Toast.LENGTH_LONG).show();
                        System.out.println("网络访问失败，请检查您机器的联网设备!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    //释放网络连接资源
                    httpClient.getConnectionManager().shutdown();
                }
                return null;
            }
        });
        new Thread(task).start();
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 处理文字类的
    /**
     * 解析网络响应信息，如果为gzip格式，先解压再转换
     * @param entity 网络返回的HttpEntity信息
     * @return 返回网络响应信息，数据类型为<code>String</code>
     *
     */
    @SuppressWarnings("deprecation")
    private static String getResponseString(HttpEntity entity){
        try {
            if ((entity.getContentEncoding() != null) && entity.getContentEncoding().getValue().contains("gzip")) {
                // 能不能直接从 乱码获取 bytes
                GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(EntityUtils.toByteArray(entity)));
                InputStreamReader isr = new InputStreamReader(gzip);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String temp;
                while((temp = br.readLine()) != null){
                    sb.append(temp);
                    sb.append("\r\n");
                }
                isr.close();
                gzip.close();

                return sb.toString();
            } else {
                return EntityUtils.toString(entity);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
