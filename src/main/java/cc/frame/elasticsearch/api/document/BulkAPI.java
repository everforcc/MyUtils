package cc.frame.elasticsearch.api.document;

import cc.frame.elasticsearch.api.ElasticsearchAPI;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;

import java.io.IOException;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author c.c.
 * @date 2021/3/4
 */
public class BulkAPI {

    public static void main(String[] args) {
        bulk();
    }

    private static TransportClient client = ElasticsearchAPI.init();

    public static void bulk(){
        BulkRequestBuilder bulkRequest = client.prepareBulk();

        // either use client#prepare, or use Requests# to directly build index/delete requests
        try {
            bulkRequest.add(client.prepareIndex("twitter", "tweet", "3")
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                            .endObject()
                    )
            );

            bulkRequest.add(client.prepareIndex("twitter", "tweet", "4")
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "another post")
                            .endObject()
                    )
            );

            bulkRequest.add(client.prepareDelete("twitter", "tweet", "1"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item

        }else {
            for(BulkItemResponse bulkItemResponse:bulkResponse){
                System.out.println(bulkItemResponse.status());
                System.out.println(bulkItemResponse.toString());
                System.out.println(bulkItemResponse.getFailureMessage());
            }
        }

    }

}
