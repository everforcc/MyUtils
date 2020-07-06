package cn.cc.utils.http.action.post;


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

    static String url1 = "http://localhost:7007/invoice/queryWaitEditData";
    static String json1 = "{\"operateType\":\"prepareEdit\",\n" +
            "\"batchno\":\"VKJ20000000202000027\",\n" +
            "\"pageNo\":\"1\",\n" +
            "\"pageSize\":\"1\",\n" +
            "\t\"userInfo\":{\n" +
            "\t\t\"userCode\":\"0000000000\",\n" +
            "\t\t\"userName\":\"测试1\",\n" +
            "\t\t\"comCode\":\"0000000000\"\n" +
            "\t}\n" +
            "}\n";
    static String url="http://localhost:8666/prpJTransactionMain/queryByPrpJTransactionMain";
    static String json="{\"transactionNo\":\"\",\"certiNoList\":\"\",\"earlierMonth\":\"2020-05-20\",\"laterMonth\":\"2020-06-20\",\"earlierSumFee\":\"\",\"laterSumFee\":\"\",\"jfqrStartDate\":\"\",\"jfqrEndDate\":\"\",\"tranoStatus\":\"\",\"currenCY\":\"CNY\",\"appliName\":\"\",\"printFlag\":\"\",\"globalUserCode\":\"00000000\",\"powerSystemCode\":\"payment\",\"webUserCode\":\"0000000004\",\"webComCode\":\"00000000\",\"webCenterCode\":\"00000000\",\"webTaskCode\":\"payment.collection.nonpayment\",\"pageNo\":\"\",\"pageSize\":\"15\"}";
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
