package cn.cc.elasticsearch;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import java.io.IOException;
import java.net.InetAddress;

/**
 * @author c.c.
 * @date 2021/2/26
 */
public class ElasticsearchCRUDDemo {

    private static TransportClient client = null;

    public static void main(String[] args) {
        try {
            init();
            addIndex();

            //createIndex();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 初始化 client， 修改为单例模式
    public static void init() throws Exception {
        // 创建client
        client = new PreBuiltTransportClient(Settings.builder().put("cluster.name", "my-elasticsearch").build()).addTransportAddresses(
                // new InetSocketTransportAddress(InetAddress.getByName("192.168.110.133"),
                // 9300),
                // new InetSocketTransportAddress(InetAddress.getByName("192.168.110.133"),
                // 9300),
                // 建议指定2个及其以上的节点。
                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        // TCP的端口不同,实例名问题,
    }

    // 新增索引和数据
    public static void addIndex() throws IOException {
        IndexResponse indexResponse = client.prepareIndex("msg","tweet","1")
                .setSource(XContentFactory.jsonBuilder().startObject()
                .field("abc","2")
                .endObject())
                .get();
        System.out.println("索引名称:" + indexResponse.getIndex() + "\n类型:" + indexResponse.getType()
        + "\n文档ID:" + indexResponse.getId() + "\n当前实例状态:" + indexResponse.status() );
        client.close();
    }

    public static void search(){

    }

    public static void createIndex() throws Exception {
        //1) 创建一个Settings对象，相当于配置信息，主要配置集群名称。
        Settings settings = Settings.builder()
                .put("cluster.name", "my-elasticsearch")
                .build();
        //2) 创建一个客户端client对象
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        //3) 使用client对象创建一个索引库
        client.admin().indices().prepareCreate("index_hello")
                //执行操作
                .get();
        //4) 关闭client对象
        client.close();
    }


}
