package cn.cc.elasticsearch.api;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.transport.TransportClient;

/**
 * @author c.c.
 * @date 2021/3/4
 */
public class MultiGetAPI {

    public static void main(String[] args) {
        multi();
    }

    private static TransportClient client = ElasticsearchAPI.init();

    /**
     * 1.唯一id
     * 2.同一个index type 下面的ids 集合
     * 3.也可以从其他的index下面取
     * 4.遍历结果set
     * 5.检验文档是否存在
     * 6.访问source字段
     */

    public static void multi(){
        MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
                .add("index", "type", "1")
                .add("twitter", "tweet", "2", "3", "4")
                .add("index", "type", "foo")
                .get();

        for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
            System.out.println(itemResponse.getId());
            System.out.println(itemResponse.getResponse());
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String json = response.getSourceAsString();
                System.out.println("json:" + json);
            }
        }
    }

}
