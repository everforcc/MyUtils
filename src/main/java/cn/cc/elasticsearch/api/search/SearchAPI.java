package cn.cc.elasticsearch.api.search;

import cn.cc.elasticsearch.api.ElasticsearchAPI;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author c.c.
 * @date 2021/3/8
 */
public class SearchAPI {

    public static void main(String[] args) {
        search();
    }

    private static TransportClient client = ElasticsearchAPI.init();

    public static void search(){
        /*SearchResponse response = client.prepareSearch("index1", "index2")// 可以逗号隔开
                .setTypes("type1", "type2")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .get();*/

        // MatchAll on the whole cluster with all default options
        SearchResponse response = client.prepareSearch("twitter").get();

        System.out.println(response.toString());
    }

}
