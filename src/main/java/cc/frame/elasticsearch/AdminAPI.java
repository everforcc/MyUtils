package cc.frame.elasticsearch;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;

/**
 * @author c.c.
 * @date 2021/2/26
 */
public class AdminAPI {

    public static void main(String[] args) {
        try {
            init();
            createIndexWithSettings();
            elasticsearchSettingsMappings();
            elasticsearchSettingsPlayerMappings();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static TransportClient client = null;

    @SuppressWarnings("resource")
    @BeforeAll
    public static void init() throws Exception{
        // 设置集群名称biehl01
        Settings settings = Settings.builder().put("cluster.name", "biehl01")
                         // 自动感知的功能(可以通过当前指定的节点获取所有es节点的信息)
                         .put("client.transport.sniff", true).build();

        client = new PreBuiltTransportClient(settings).addTransportAddresses(
                // 用java访问ES用的端口是9300。es的9200是restful的请求端口号
                // 由于我使用的是伪集群,所以就配置了一台机器,如果是集群方式,将竞选主节点的加进来即可。
                // new InetSocketTransportAddress(InetAddress.getByName("192.168.110.133"),
                // 9300),
                // new InetSocketTransportAddress(InetAddress.getByName("192.168.110.133"),
                // 9300),
                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        // 搜索数据(.actionGet()方法是同步的，没有返回就等待)
        // 方式是先去索引里面查询出索引数据,再去文档里面查询出数据。
        /*GetResponse response = client.prepareGet("news", "fulltext", "2").execute().actionGet();
        // 输出结果
        System.out.println(response);
        client.close();*/
    }

    public static void createIndexWithSettings(){
        // 获取Admin的API
        AdminClient admin = client.admin();
        // 使用Admin API对索引进行操作
        IndicesAdminClient indices = admin.indices();
        // 准备创建索引
        indices.prepareCreate("food")
                // 配置索引参数
                .setSettings(
                        //参数配置器
                        Settings.builder()// 指定索引分区的数量。shards分区
                                .put("index.number_of_shards",5)
                                // 指定索引副本的数量(注意：不包括本身,如果设置数据存储副本为1,实际上数据存储了2份)
                                // replicas副本
                                .put("index.number_of_replicas",1))
                .get(); // 执行
    }

    /**
      * 你可以通过dynamic设置来控制这一行为,它能够接受以下的选项： true：默认值。
      *
      * 动态添加字段 false：忽略新字段
      *
      * strict：如果碰到陌生字段,抛出异常
      *
      * 给索引添加mapping信息(给表添加schema信息)
      *
      * @throws IOException
      */
    public static void elasticsearchSettingsMappings() throws IOException {
             // 1:settings
             HashMap<String, Object> settings_map = new HashMap<String, Object>(2);
             // shards分区的数量4
             settings_map.put("number_of_shards", 4);
             // 副本的数量1
             settings_map.put("number_of_replicas", 1);

             // 2:mappings(映射、schema)
             // field("dynamic", "true")含义是动态字段
             XContentBuilder builder = XContentFactory.jsonBuilder().startObject().field("dynamic", "true")
                     // 设置type中的属性
                     .startObject("properties")
                     // id属性
                     .startObject("id")
                      // 类型是integer
                      .field("type", "integer")
                      // 不分词,但是建索引
                      .field("index", "not_analyzed")
                      // 在文档中存储
                      .field("store", "yes").endObject()
                      // name属性
                      .startObject("name")
                      // string类型
                      .field("type", "string")
                      // 在文档中存储
                      .field("store", "yes")
                      // 建立索引
                      .field("index", "analyzed")
                      // 使用ik_smart进行分词
                      .field("analyzer", "ik_smart").endObject().endObject().endObject();

              CreateIndexRequestBuilder prepareCreate = client.admin().indices().prepareCreate("computer");
              // 管理索引（user_info）然后关联type（user）
              prepareCreate.setSettings(settings_map).addMapping("xiaomi", builder).get();
    }


    /**
     * index这个属性,no代表不建索引
     *
     * not_analyzed,建索引不分词
     *
     * analyzed 即分词,又建立索引
     *
     * expected [no],[not_analyzed] or [analyzed]。即可以选择三者任意一个值
     *
     * @throws IOException
     */
     public static void elasticsearchSettingsPlayerMappings() throws IOException {
         // 1:settings
         HashMap<String, Object> settings_map = new HashMap<String, Object>(2);
         // 分区的数量4
         settings_map.put("number_of_shards", 4);
         // 副本的数量1
         settings_map.put("number_of_replicas", 1);

         // 2:mappings
         XContentBuilder builder = XContentFactory.jsonBuilder().startObject()//
                 .field("dynamic", "true").startObject("properties")
                 // 在文档中存储、
                 .startObject("id").field("type", "integer").field("store", "yes").endObject()
                 // 不分词,但是建索引、
                 .startObject("name").field("type", "string").field("index", "not_analyzed").endObject()
                 //
                 .startObject("age").field("type", "integer").endObject()
                 //
                 .startObject("salary").field("type", "integer").endObject()
                 // 不分词,但是建索引、
                 .startObject("team").field("type", "string").field("index", "not_analyzed").endObject()
                 // 不分词,但是建索引、
                 .startObject("position").field("type", "string").field("index", "not_analyzed").endObject()
                 // 即分词,又建立索引、
                 .startObject("description").field("type", "string").field("store", "no").field("index", "analyzed")
                 .field("analyzer", "ik_smart").endObject()
                 // 即分词,又建立索引、在文档中存储、
                 .startObject("addr").field("type", "string").field("store", "yes").field("index", "analyzed")
                 .field("analyzer", "ik_smart").endObject()

                 .endObject()

                 .endObject();

         CreateIndexRequestBuilder prepareCreate = client.admin().indices().prepareCreate("player");
         prepareCreate.setSettings(settings_map).addMapping("basketball", builder).get();
     }


}
