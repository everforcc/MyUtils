package cn.cc.elasticsearch.api;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;

/**
 * @author c.c.
 * @date 2021/3/4
 */
public class GetAPI {

    public static void main(String[] args) {
        get();
    }

    private static TransportClient client = ElasticsearchAPI.init();

    public static void get(){
        GetResponse response = client.prepareGet("twitter", "tweet", "1")
                //.setOperationThreaded(false)
                .get();
        System.out.println(response.toString());
    }

    public static void printResponse(GetResponse response){

    }

}
