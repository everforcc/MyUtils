package cc.advanced.web.http.utils;

import cc.core.file.utils.ConstantCharSet;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.zip.GZIPInputStream;

public class HttpClientUtils {

    /**
     * apache 的工具包
     * HttpClient
     * StringEntity
     * HttpPost
     * HttpGet
     * HttpParams
     * FutureTask
     */

    /**
     * 请两个方法一类的看看
     *
     */

    /**
     * 传一个对象过去
     * 直接获取返回的字符串处理
     * @param url
     * @param json
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendToUrlRequestByJson(String url,String json) throws ClientProtocolException, IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        StringEntity postingString = new StringEntity(json);// json传递
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(post);
        String content = EntityUtils.toString(response.getEntity());
        System.out.println(content);
        return content;
    }

    /**
     *
     *  处理返回的流
     *  post请求封装 参数为{"a":1,"b":2,"c":3}
     * @param path 接口地址
     * @param Info 参数
     * @return
     * @throws IOException
     */
    public static JSONObject postResponse(String path, JSONObject Info) throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(path);

        // set和add有啥区别?
        post.setHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Basic YWRtaW46");

        String result = "";

        try {
            StringEntity s = new StringEntity(Info.toString(), ConstantCharSet.UTF_8);
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, ConstantCharSet.UTF_8));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");
            inStream.close();

            result = strber.toString();
            System.out.println(result);

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                System.out.println("请求服务器成功，做相应处理");
            } else {
                System.out.println("请求服务端失败");
            }

        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }

        return JSONObject.parseObject(result);
    }

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
