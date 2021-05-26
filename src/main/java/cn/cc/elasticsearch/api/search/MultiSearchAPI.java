package cn.cc.elasticsearch.api.search;

import cn.cc.elasticsearch.api.ElasticsearchAPI;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.util.Iterator;

/**
 * @author c.c.
 * @date 2021/3/8
 */
public class MultiSearchAPI {

    public static void main(String[] args) {
        multiSearch();
    }

    private static TransportClient client = ElasticsearchAPI.init();

    public static void multiSearch(){
        SearchRequestBuilder srb1 = client
                .prepareSearch().setQuery(QueryBuilders.queryStringQuery("elasticsearch")).setSize(1);
        SearchRequestBuilder srb2 = client
                .prepareSearch().setQuery(QueryBuilders.matchQuery("user", "kimchy")).setSize(1);

        MultiSearchResponse sr = client.prepareMultiSearch()
                .add(srb1)
                .add(srb2)
                .get();

        // You will get all individual responses from MultiSearchResponse#getResponses()
        long nbHits = 0;
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse response = item.getResponse();
            System.out.println("response.getHits().getTotalHits():" + response.getHits().getTotalHits());
            nbHits += response.getHits().getTotalHits();
            if(1 <= response.getHits().getTotalHits()) {
                Iterator<SearchHit> searchHitIterator = response.getHits().iterator();
                while (searchHitIterator.hasNext()) {
                    //System.out.println("name:" + searchHitIterator.next().getSource().get("user"));
                    System.out.println("message:" + searchHitIterator.next().getSource().get("message"));
                }
            }
        }

        System.out.println(nbHits);
    }

}
