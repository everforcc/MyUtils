package cn.cc.core.http.urlrequest.iptools;


import java.io.File;
import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;

import cn.cc.core.http.urlrequest.header.Header;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author c.c.
 * @date 2020/12/18
 */
public class ProxyDemo {

    // 这个可以,说明代理是可以用的，但是需要购买... 如果以后买服务器爬虫的话加上这个，但是名言通好想拒绝代理，要看一下代理的几种模式

    // 代理隧道验证信息
    private final static String ProxyUser = "WA111686423789496900";
    private final static String ProxyPass = "v8dR6o6DJ0PhK8XY";

    // 代理服务器
    private final static String ProxyHost = "dyn.horocn.com";
    private final static Integer ProxyPort = 50000;

    public static Proxy getUrlProxyContent(String url) {
        /*Authenticator.setDefault(new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ProxyUser, ProxyPass.toCharArray());
            }
        });

        System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ProxyHost, ProxyPort));
        System.out.println(proxy);*/

        try {
            //System.out.println(proxy);
            Document doc = Jsoup.connect(url).data(Header.mingyantongMap()).timeout(6000).get();


            if (doc != null) {
                System.out.println(doc.body().html());


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) throws Exception {
        // 要访问的目标页面
        String targetUrl = "https://www.mingyantong.com/article/813656";

        getUrlProxyContent(targetUrl);
    }
}

