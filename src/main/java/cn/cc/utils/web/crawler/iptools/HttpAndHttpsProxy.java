package cn.cc.utils.web.crawler.iptools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpAndHttpsProxy {
    public static HttpURLConnection conn(String urlPath)throws Exception{
        URL url = new URL(urlPath);
        HttpURLConnection httpsConn = (HttpURLConnection) url.openConnection();
        httpsConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        //conn.setRequestProperty("Accept-Encoding","gzip, deflate, br");
        httpsConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
        httpsConn.setRequestProperty("Cache-Control", "max-age=0");
        httpsConn.setRequestProperty("Connection", "keep-alive");
        httpsConn.setRequestProperty("Cookie", "_uuid=B12C70F0-DCA2-46BC-D28D-8C8E1FB3A17324567infoc; buvid3=64E2389B-F33E-4BFB-997E-EC791DBE770353923infoc; sid=hxyu20pz; CURRENT_FNVAL=16; stardustvideo=1; LIVE_BUVID=AUTO4315775147642650; rpdid=|(J|)J|ukluk0J'ul~~luR~YY; CURRENT_QUALITY=80; DedeUserID=58572396; DedeUserID__ckMd5=20e54bd3090b9e60; SESSDATA=d7d5a3fc%2C1582879497%2C1e398911; bili_jct=383cba77528b0d98f4ea45f1e56b0cbb; im_notify_type_58572396=0; bp_t_offset_0=350877589470090521; flash_player_gray=false; INTVER=1; bp_t_offset_58572396=360609908056480357");
        httpsConn.setRequestProperty("Host", "api.bilibili.com");
        httpsConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
        httpsConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        httpsConn.setRequestMethod("GET");
        return httpsConn;
    }
    public static String HttpsProxy(String url, String param, String proxy, int port) {
        HttpsURLConnection httpsConn = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        BufferedReader reader = null;
        try {
            URL urlClient = new URL(url);
            System.out.println("请求的URL========：" + urlClient);

            SSLContext sc = SSLContext.getInstance("SSL");
            // 指定信任https
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
            //创建代理虽然是https也是Type.HTTP
            Proxy proxy1=new Proxy(Type.HTTP, new InetSocketAddress(proxy, port));
            //设置代理
            httpsConn = (HttpsURLConnection) urlClient.openConnection(proxy1);

            //httpsConn.setSSLSocketFactory(sc.getSocketFactory());
            //httpsConn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            // 设置通用的请求属性
//            httpsConn.setRequestProperty("accept", "*/*");
//            httpsConn.setRequestProperty("connection", "Keep-Alive");
//            httpsConn.setRequestProperty("user-agent",
//                    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");

            httpsConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            //conn.setRequestProperty("Accept-Encoding","gzip, deflate, br");
            httpsConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            httpsConn.setRequestProperty("Cache-Control", "max-age=0");
            httpsConn.setRequestProperty("Connection", "keep-alive");
            //httpsConn.setRequestProperty("Cookie", "_uuid=B12C70F0-DCA2-46BC-D28D-8C8E1FB3A17324567infoc; buvid3=64E2389B-F33E-4BFB-997E-EC791DBE770353923infoc; sid=hxyu20pz; CURRENT_FNVAL=16; stardustvideo=1; LIVE_BUVID=AUTO4315775147642650; rpdid=|(J|)J|ukluk0J'ul~~luR~YY; CURRENT_QUALITY=80; DedeUserID=58572396; DedeUserID__ckMd5=20e54bd3090b9e60; SESSDATA=d7d5a3fc%2C1582879497%2C1e398911; bili_jct=383cba77528b0d98f4ea45f1e56b0cbb; im_notify_type_58572396=0; bp_t_offset_0=350877589470090521; flash_player_gray=false; INTVER=1; bp_t_offset_58572396=360609908056480357");
            httpsConn.setRequestProperty("Host", "space.bilibili.com");
            httpsConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
            httpsConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
            httpsConn.setRequestMethod("GET");

            // 发送POST请求必须设置如下两行
            httpsConn.setDoOutput(true);
            httpsConn.setDoInput(true);
            // 解决方法，这个outputStream有问题  http://www.it1352.com/931696.html
            //httpsConn.connect();
           /* // 获取URLConnection对象对应的输出流
            out = new PrintWriter(httpsConn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();*/
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(httpsConn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

            //getMyIp();

            // 断开连接
            httpsConn.disconnect();
            System.out.println("====result===="+result);
            System.out.println("返回结果：" + httpsConn.getResponseMessage());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (out != null) {
                out.close();
            }
        }

        return result;
    }

    public static String HttpProxy(String url, String param, String proxy, int port) {
        HttpURLConnection httpConn = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        BufferedReader reader = null;
        try {
            URL urlClient = new URL(url);
            System.out.println("请求的URL========：" + urlClient);
            //创建代理
            Proxy proxy1=new Proxy(Type.HTTP, new InetSocketAddress(proxy, port));
            //设置代理
            httpConn = (HttpURLConnection) urlClient.openConnection(proxy1);
            // 设置通用的请求属性

            // 发送POST请求必须设置如下两行
           httpConn.setDoOutput(true);
            httpConn.setDoInput(true);

            httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            httpConn.setRequestProperty("Accept-Encoding", " gzip, deflate, br");
            httpConn.setRequestProperty("Accept-Language", " zh-CN,zh;q=0.9");
            httpConn.setRequestProperty("Connection", " keep-alive");
            httpConn.setRequestProperty("Host", "www.bilibili.com");
            httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
            httpConn.setRequestMethod("GET");



            //httpConn.connect();
            // 设置通用的请求属性


            // 获取URLConnection对象对应的输出流
            OutputStream outputStream = httpConn.getOutputStream();
            out = new PrintWriter(outputStream);
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(httpConn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            //getMyIp();
            // 断开连接
            httpConn.disconnect();
            System.out.println("====result===="+result);
            System.out.println("返回结果：" + httpConn.getResponseMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (out != null) {
                out.close();
            }
        }

        return result;
    }

    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public static void main(String[] args) {
        // 确认可用不会报错
        HttpsProxy("https://space.bilibili.com/11605312", "", "58.209.4.226", 8118);
        //HttpsProxy("https://space.bilibili.com/11605312", "", "113.140.1.82", 53281);
        // http代理ip和地址可用,确定可用，如果报错 connect就是ip没了，或者多试几次
        // HttpProxy("http://www.aseoe.com/", "", "123.7.17.237", 8060);
         //HttpProxy("http://www.aseoe.com/", "", "118.250.2.157", 8060);
         getMyIp("113.140.1.82",53281);
        //匿名问题
    }

    private static final String MY_IP_API = "https://www.ipip.net/ip.html";
    public static void getMyIp(String proxy,int port) {
        try {

            URL xcUrl = new URL(MY_IP_API);

            SSLContext sc = SSLContext.getInstance("SSL");
            // 指定信任https
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
            //创建代理虽然是https也是Type.HTTP
            Proxy proxy1=new Proxy(Type.HTTP, new InetSocketAddress(proxy, port));

            HttpURLConnection conn = (HttpURLConnection) xcUrl.openConnection(proxy1);
            conn.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
            Document doc = Jsoup.parse(conn.getInputStream(),"UTF-8",MY_IP_API);

            //Document document = Jsoup.parse(conn.getInputStream(),"UTF-8",MY_IP_API);

            //String html = HttpUtils.getResponseContent(MY_IP_API);

            //Document doc = Jsoup.parse(html);
            Element element = doc.selectFirst("div.tableNormal");

            Element ele = element.selectFirst("table").select("td").get(1);

            String ip = element.selectFirst("a").text();

            System.out.println(ip);
            // System.out.println(ip);
            //return ip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return null;
    }

}
