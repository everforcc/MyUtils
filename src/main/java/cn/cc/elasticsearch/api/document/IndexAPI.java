package cn.cc.elasticsearch.api.document;

import java.io.IOException;
import java.util.Date;

import cn.cc.elasticsearch.api.ElasticsearchAPI;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

/**
 * @author c.c.
 * @date 2021/3/4
 */
public class IndexAPI {

    public static void main(String[] args) {
        document_jsonBuilder();
    }

    /**
     *  建议的四种json格式
     *  1.自己写字符串json
     *  2.map
     *  3.使用jackson等包
     *  4.el自带
     */
    private static String json = "{" +
            "\"user\":\"kimchy\"," +
            "\"postDate\":\"2013-01-30\"," +
            "\"message\":\"trying out Elasticsearch\"" +
            "}";

    private static TransportClient client = ElasticsearchAPI.init();

    public static void document_jsonBuilder(){

        try {
            /*jsonBuilder().startArray().*/

            IndexResponse response = client.prepareIndex("twitter", "tweet", "1")
                    .setSource(jsonBuilder() // 数据类型非常丰富
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                            .endObject()
                    )
                    .get();
            System.out.println(jsonBuilder().toString());
            printResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void document_jsonString(){
        IndexResponse response = client.prepareIndex("twitter", "tweet")
                .setSource(json, XContentType.JSON) // 过时了
                .get();
    }

    public static void printResponse(IndexResponse response){
        StringBuilder stringBuilder = new StringBuilder("result:");
        stringBuilder.append("\n response.toString() : ");
        stringBuilder.append(response.toString());
        stringBuilder.append("\n response.status() : ");
        stringBuilder.append(response.status());
        stringBuilder.append("\n response.getId() : ");
        stringBuilder.append(response.getId());
        stringBuilder.append("\n response.getType() : ");
        stringBuilder.append(response.getType());
        stringBuilder.append("\n response.getIndex() : ");
        stringBuilder.append(response.getIndex());
        stringBuilder.append("\n response.getResult() : ");
        stringBuilder.append(response.getResult());
        stringBuilder.append("\n response.getShardId() : ");
        stringBuilder.append(response.getShardId());
        stringBuilder.append("\n response.getShardInfo() : ");
        stringBuilder.append(response.getShardInfo());
        stringBuilder.append("\n response.getVersion() : ");
        stringBuilder.append(response.getVersion());
        stringBuilder.append("\n response.getLocation() : ");
        stringBuilder.append(response.getLocation("postDate"));

        //response.getLocation();
        System.out.println(stringBuilder.toString());
    }

}
