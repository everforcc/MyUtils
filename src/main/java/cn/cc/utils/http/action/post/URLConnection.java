package cn.cc.utils.http.action.post;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 */
public class URLConnection {


    /**
     *  用jdk自带的HttpURLConnection来发送请求
     */


    //
    public static void main(String[] args) throws Exception{
//1, 得到URL对象
        URL url = new URL("http://localhost:7007/invoice/queryWaitEditData");

        //2, 打开连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 模拟浏览器请求
        conn.setRequestProperty("Content-Type", "application/json");
        /*conn.setRequestProperty("Date", "Fri, 08 May 2020 09:25:14 GMT");
        conn.setRequestProperty("Transfer-Encoding", "chunked");
        conn.setRequestProperty("X-Application-Context", "vat:7007");*/
        //3, 设置提交类型
        conn.setRequestMethod("POST");

        //4, 设置允许写出数据,默认是不允许 false
        conn.setDoOutput(true);
        conn.setDoInput(true);//当前的连接可以从服务器读取内容, 默认是true

        String sendMsg="{\"operateType\":\"prepareEdit\",\n" +
                "\"batchno\":\"VKJ20000000202000027\",\n" +
                "\"pageNo\":\"1\",\n" +
                "\"pageSize\":\"1\",\n" +
                "\t\"userDto\":{\n" +
                "\t\t\"userCode\":\"0000000000\",\n" +
                "\t\t\"userName\":\"测试1\",\n" +
                "\t\t\"comCode\":\"0000000000\"\n" +
                "\t}\n" +
                "}\n";
        if(sendMsg!=null) {
            //5, 获取向服务器写出数据的流
            OutputStream os = conn.getOutputStream();
            //参数是键值队  , 不以"?"开始
            os.write(sendMsg.getBytes());
            //os.write("googleTokenKey=&username=admin&password=5df5c29ae86331e1b5b526ad90d767e4".getBytes());
            os.flush();
        }

        //6, 获取响应的数据
        //得到服务器写回的响应数据
        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        String str = br.readLine();

        System.out.println("返回值:"+str);
    }

    static String  urlPath ="https://game.eroge.xyz/tools/ons%E6%A8%A1%E6%8B%9F%E5%99%A8/ONScripter-Jh_0.7.2.arm64.apk";

    static String userAgent="Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20200220 Firefox/29.0";


    /**
     * post请求封装 参数为?a=1&b=2&c=3
     * @param urlPath 接口地址
     * @param Info 参数
     * @return
     * @throws IOException
     */
    public static JSONObject postResponse(String urlPath, String Info) throws IOException{

        //1, 得到URL对象
        URL url = new URL(urlPath);

        //2, 打开连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 模拟浏览器请求
        conn.setRequestProperty("User-agent", userAgent);
        //3, 设置提交类型
        conn.setRequestMethod("POST");

        //4, 设置允许写出数据,默认是不允许 false
        conn.setDoOutput(true);
        conn.setDoInput(true);//当前的连接可以从服务器读取内容, 默认是true

        //5, 获取向服务器写出数据的流
        OutputStream os = conn.getOutputStream();
        //参数是键值队  , 不以"?"开始
        os.write(Info.getBytes());
        //os.write("googleTokenKey=&username=admin&password=5df5c29ae86331e1b5b526ad90d767e4".getBytes());
        os.flush();
        //6, 获取响应的数据
        //得到服务器写回的响应数据
        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        String str = br.readLine();
        JSONObject json = JSONObject.parseObject(str);

        System.out.println("响应内容为:  " + json);

        return  json;
    }
    /**
     * post请求封装 参数为{"a":1,"b":2,"c":3}
     * @param path 接口地址
     * @param Info 参数
     * @return
     * @throws IOException
     */
    public static JSONObject postResponse(String path,JSONObject Info) throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(path);

        post.setHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Basic YWRtaW46");
        String result = "";

        try {
            StringEntity s = new StringEntity(Info.toString(), "utf-8");
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
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

}
