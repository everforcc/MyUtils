package cn.cc.elasticsearch.api;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author c.c.
 * @date 2021/3/4
 */
public class ElasticsearchAPI {

    // 初始化
    public static TransportClient init(){
        // on startup
        TransportClient client = null;
        Settings settings = Settings.builder()
                .put("cluster.name", "my-elasticsearch")
                .put("client.transport.sniff", true) // 启用嗅探功能
                .build();
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))// http 端口
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9301));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        // on shutdown
        //client.close();
        return client;
    }

}
