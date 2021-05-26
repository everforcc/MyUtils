package cn.cc.elasticsearch.api.document;

import cn.cc.elasticsearch.api.ElasticsearchAPI;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;

/**
 * @author c.c.
 * @date 2021/3/4
 */
public class DeleteAPI {

    public static void main(String[] args) {
        //delete();
        //deleteByQueryGet();
        deleteByQueryExecute();
    }

    private static TransportClient client = ElasticsearchAPI.init();

    public static void delete(){
        DeleteResponse response = client.prepareDelete("twitter", "tweet", "1")
                .get();
        printResponse(response);
    }

    // 先查询后删除
    public static void deleteByQueryGet(){
        BulkByScrollResponse response =
                DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                        .filter(QueryBuilders.matchQuery("gender", "male")) // 搜索条件
                        .source("persons") // 索引
                        .get();

        long deleted = response.getDeleted();
        System.out.println(deleted);
    }

    // 如果查询时间过长 就使用异步执行
    public static void deleteByQueryExecute(){
        DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery("gender", "male"))
                .source("persons")
                .execute(new ActionListener<BulkByScrollResponse>() {
                    @Override
                    public void onResponse(BulkByScrollResponse response) {
                        long deleted = response.getDeleted();
                        System.out.println("删除结果:" + deleted);
                    }
                    @Override
                    public void onFailure(Exception e) {
                        // Handle the exception
                        System.out.println("失败:" + e.toString());
                    }
                });
    }

    public static void printResponse(DeleteResponse response){
        System.out.println(response.toString());
        System.out.println(response.getResult());
        System.out.println(response.status());
    }

}
