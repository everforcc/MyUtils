package cn.cc.elasticsearch.api.search;

import cn.cc.elasticsearch.api.ElasticsearchAPI;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * @author c.c.
 * @date 2021/3/8
 */
public class ScrollsAPI {

    public static void main(String[] args) {
        scrolls();
    }

    private static TransportClient client = ElasticsearchAPI.init();

    public static void scrolls(){
        QueryBuilder qb = termQuery("message", "o");

        SearchResponse scrollResp = client.prepareSearch("twitter")
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
                .setScroll(new TimeValue(60000))
                .setQuery(qb)
                .setSize(100).get(); //max of 100 hits will be returned for each scroll

        System.out.println("scrollResp.getHits().getHits().length:" + scrollResp.getHits().getHits().length);
        //Scroll until no hits are returned
        do {
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                //Handle the hit...
                //System.out.println(hit.getField("postDate"));
                System.out.println(hit.getSource().get("user"));
                System.out.println(hit.getSource().get("postDate"));
                System.out.println(hit.getSource().get("message"));
            }

            scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        } while(scrollResp.getHits().getHits().length != 0); // Zero hits mark the end of the scroll and the while loop.
    }

}
