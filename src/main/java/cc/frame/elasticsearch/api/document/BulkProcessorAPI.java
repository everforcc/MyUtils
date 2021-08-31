package cc.frame.elasticsearch.api.document;

import cc.frame.elasticsearch.api.ElasticsearchAPI;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;

import java.util.concurrent.TimeUnit;

/**
 * @author c.c.
 * @date 2021/3/5
 */
public class BulkProcessorAPI {

    public static void main(String[] args) {

    }

    private static TransportClient client = ElasticsearchAPI.init();
    private static BulkProcessor bulkProcessor;

    /**
     * 1.添加客户端
     * 2.在执行bulk之前调用此方法。例如，您可以通过request. numberofaction()查看numberofaction
     * 3.此方法在批量执行后调用。例如，您可以使用response.hasFailures()检查是否有失败的请求
     * 4.当bulk失败并引发Throwable时，将调用此方法
     * 5.我们希望每10,000个请求执行一次bulk
     * 6.每5M 刷一次
     * 7.每5秒刷新一次无论请求什么情况
     * 8.设置并发请求数。值为0意味着只允许执行单个请求。值为1意味着在累积新的批量请求时允许执行1个并发请求。
     * 9.设置一个自定义的后退策略，首先等待100ms，然后成倍增加，然后重试三次。当一个或多个批量项目请求通过EsRejectedExecutionException失败时，将尝试重试，该异常表明用于处理请求的可用计算资源太少。要禁用backoff，输入BackoffPolicy.noBackoff()。
     */
    public static void bulkProcessor(){
        bulkProcessor = BulkProcessor.builder(
                client, //1
                new BulkProcessor.Listener() {
                    @Override
                    public void beforeBulk(long executionId,
                                           BulkRequest request) {
                        System.out.println("beforeBulk");//2
                    }

                    @Override
                    public void afterBulk(long executionId,
                                          BulkRequest request,
                                          BulkResponse response) {
                        System.out.println("afterBulk");//3
                    }

                    @Override
                    public void afterBulk(long executionId,
                                          BulkRequest request,
                                          Throwable failure) {
                        System.out.println("afterBulk failure");//4
                    }
                }
        ).setBulkActions(10000)//5 默认1000
         .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))//6 默认5M
         .setFlushInterval(TimeValue.timeValueSeconds(5))//7 默认不设置
         .setConcurrentRequests(1)//8 默认为1 可异步执行
         .setBackoffPolicy(
                 // 默认 设置回退策略为指数回退，重试8次，启动延迟为50ms。总的等待时间大约是5.1秒。
                 BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))//9
         .build();
    }

    public static void bulkProcessorAddRequests(){
        bulkProcessor.add(new IndexRequest("twitter", "tweet", "1").source("your doc here"/* your doc here */));
        bulkProcessor.add(new DeleteRequest("twitter", "tweet", "2"));
    }

    public static void bulkProcessorClose(){
        try {
            bulkProcessor.awaitClose(10, TimeUnit.MINUTES);
            // bulkProcessor.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void t(){
        BulkProcessor bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long l, BulkRequest bulkRequest) {

            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {

            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {

            } /* Listener methods */ })
                .setBulkActions(10000)
                .setConcurrentRequests(0)
                .build();

        // Add your requests
        //bulkProcessor.add(/* Your requests */);

        // Flush any remaining requests
        bulkProcessor.flush();

        // Or close the bulkProcessor if you don't need it anymore
        bulkProcessor.close();

        // Refresh your indices
        client.admin().indices().prepareRefresh().get();

        // Now you can start searching!
        client.prepareSearch().get();
    }

}
