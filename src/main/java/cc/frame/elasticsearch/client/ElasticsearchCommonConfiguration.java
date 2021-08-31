package cc.frame.elasticsearch.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

/**
 * @author c.c.
 * @date 2021/3/1
 */
public class ElasticsearchCommonConfiguration {

    /**
     * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/5.1/_common_configuration.html
     * 四种类型
     */

    private static RestClient restClient;

    public static void main(String[] args) {

    }

    /* 公共配置 */

    // 1.超时
    static void timeout(){
        restClient = RestClient.builder(new HttpHost("localhost", 9200),
                                        new HttpHost("localhost", 9201)
                    ).setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                    @Override
                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                        return requestConfigBuilder.setConnectTimeout(5000)
                                .setSocketTimeout(30000);
                    }
                })
                .setMaxRetryTimeoutMillis(30000)
                .build();
    }

    // 2.线程
    static void thread(){
        System.out.println("本地:" + Runtime.getRuntime().availableProcessors());
        restClient = RestClient.builder(new HttpHost("localhost", 9200))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultIOReactorConfig(
                                IOReactorConfig.custom().setIoThreadCount(1).build());
                    }
                })
                .build();
    }

    // 3. 认证
    static void authentication(){
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("user", "password"));

        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                })
                .build();
    }

    // 4.加密
    /*static void encry() throws Exception {
        KeyStore
                truststore = KeyStore.getInstance("jks");

        try (InputStream is = Files.newInputStream(keyStorePath)) {
            String keyStorePass = "";
            truststore.load(is, keyStorePass.toCharArray());
        }
        SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(truststore, null);
        final SSLContext sslContext = sslBuilder.build();
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "https"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        SSLContext sslcontext;
                        return httpClientBuilder.setSSLContext(sslcontext);
                    }
                })
                .build();
    }*/

}
