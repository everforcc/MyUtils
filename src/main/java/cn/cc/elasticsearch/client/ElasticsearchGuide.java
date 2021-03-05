package cn.cc.elasticsearch.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;

/**
 * @author c.c.
 * @date 2021/3/1
 */
public class ElasticsearchGuide {

    private static String defaultJson = "{\n" +
            "    \"user\" : \"kimchy\",\n" +
            "    \"post_date\" : \"2009-11-15T14:12:12\",\n" +
            "    \"message\" : \"trying out Elasticsearch\"\n" +
            "}";
    private static RestClient restClient;

    public static void main(String[] args) {
        init();
        /*insert();
        search();*/
        insertAsy();
        try {
            restClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 初始化client
    static void init(){
        restClient = RestClient.builder(
                new HttpHost("localhost", 9200, "http")).build();
                //new HttpHost("localhost", 9201, "http")).build();
    }

    static void insert(){
        // 参数 json
        //index a document
        HttpEntity entity = new NStringEntity(defaultJson
                , ContentType.APPLICATION_JSON);
        try {
            Response indexResponse = restClient.performRequest(
                    "PUT",
                    "/twitter/tweet/1",
                    Collections.<String, String>emptyMap(),
                    entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void insertAsy() {
        int numRequests = 10;
        //String[] entities = new String[numRequests];
        final CountDownLatch latch = new CountDownLatch(numRequests);
        // 带上传的实体数组
        HttpEntity[] entities = new HttpEntity[numRequests];


        for (int i = 0; i < numRequests; i++) {
            try {
                String json = new UserDto(i + "_usercc",i + "_post_date",i + "_messagecc").toString();
                /*System.out.println(defaultJson);*/
                System.out.println(json);
                entities[i] = new NStringEntity(json);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            restClient.performRequestAsync(
                    "PUT",
                    "/twitter/tweet/" + i,
                    Collections.<String, String>emptyMap(),
                    //assume that the documents are stored in an entities array
                    entities[i],
                    new ResponseListener() {
                        @Override
                        public void onSuccess(Response response) {
                            System.out.println(response);
                            latch.countDown();
                        }

                        @Override
                        public void onFailure(Exception exception) {
                            latch.countDown();
                        }
                    }
            );
        }

        //wait for all requests to be completed
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void search(){
        try {
            Response response = restClient.performRequest("GET", "/twitter/tweet/1",
                    Collections.singletonMap("pretty", "true"));
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
