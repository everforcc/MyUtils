package cn.cc.utils.web.http.post;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Yukino
 * 2020/5/16
 */
public class ApacheRawJSON {


    /**
     *
     * apache的httpclient来发送请求
     */

    //
    public static void main(String[] args) {
        try {
            httpTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String result;

    public static void httpTest() throws ClientProtocolException, IOException {
        //String token = "R5amyr6NyXCtWdScmNiuvVwBCJztfByZDUGaE2V0NwOUheW4XYlvUusYkrViTYt584RgcyXRhjxAJZG3rFlPLg";
        String url = "http://localhost:7007/invoice/queryWaitEditData";
        String json = "{\"operateType\":\"prepareEdit\",\n" +
                "\"batchno\":\"VKJ20000000202000027\",\n" +
                "\"pageNo\":\"1\",\n" +
                "\"pageSize\":\"1\",\n" +
                "\t\"userInfo\":{\n" +
                "\t\t\"userCode\":\"0000000000\",\n" +
                "\t\t\"userName\":\"测试1\",\n" +
                "\t\t\"comCode\":\"0000000000\"\n" +
                "\t}\n" +
                "}\n";

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        StringEntity postingString = new StringEntity(json);// json传递
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(post);
        String content = EntityUtils.toString(response.getEntity());
        // Log.i("test",content);
        System.out.println(content);
        result = content;
    }

}
