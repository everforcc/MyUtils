package cn.cc.core.http.urlrequest.iptools;

import cn.cc.core.file.utils.ConstantCharSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Set;

/**
 * 透明匿名问题
 * 1.找代理ip和port
 * 2.设置代理
 * 3.检测ip
 *
 * Yukino
 * 2020/3/2
 */
public class IpProxyUtils {

    String xcdlProxy = "https://www.xicidaili.com/";

    /**
     * 找代理ip
     * @throws Exception
     */
    public void crawl_http() throws Exception {
        //String html = HttpUtils.getResponseContent(api + index);
        //System.out.println(html);
        URL xcUrl = new URL(xcdlProxy);
        HttpURLConnection conn = (HttpURLConnection) xcUrl.openConnection();
        conn.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");

        Document document = Jsoup.parse(conn.getInputStream(), ConstantCharSet.UTF_8,xcdlProxy);

        Element element_home = document.getElementById("home");

        //System.out.println(element_home);

        Elements elements_home_tr = element_home.getElementsByTag("tr");

        //System.out.println(elements_home_tr.size());

        //System.out.println(elements_home_tr);
        //解析岀ip
        for(Element element_tr:elements_home_tr){
            //Elements elements_tr_th = element_tr.getElementsByTag("th");
            Elements elements_tr_td_ary = element_tr.getElementsByTag("td");
            //System.out.println("th:"+elements_tr_th.size()+",td:"+elements_tr_td.size());
            /*for(Element element_tr_td:elements_tr_td_ary){
                //<tr class="odd">
                // <td class="country"><img src="//fs.xicidaili.com/images/flag/cn.png" alt="Cn"></td>
                // <td>27.154.34.146</td>
                // <td>31527</td>
                // <td>福建厦门</td>
                // <td class="country">高匿</td>
                // <td>HTTPS</td>
                // <td>480天</td>
                // <td>30分钟前</td>
                //</tr>
                System.out.print(element_tr_td.text()+" ");
                //获取ip，存入数据库开始做代理
                //element_tr_td.getElementsByTag()
                }
            }*/
            /*for(int i=0;i<elements_tr_td_ary.size();i++){
                System.out.print(i+","+elements_tr_td_ary.get(i).text());
            }*/
            if(elements_tr_td_ary.size()>0){
                //System.out.println(1+","+elements_tr_td_ary.get(1).text());
                //System.out.println(2+","+elements_tr_td_ary.get(2).text());
                System.out.println(isValid(elements_tr_td_ary.get(1).text(),Integer.valueOf(elements_tr_td_ary.get(2).text())));
            }
            System.out.println();
            //System.out.print(elements_tr_td.get(2).text()+" "+elements_tr_td.get(3).text());
            //System.out.println("elements_tr_td:"+elements_tr_td.size());
        }
    }
/*******************************************************/
//检测ip是否有效


    public int isValid(String ip,int port) {
        int code = 0;
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip,port));
            URLConnection httpCon = new URL("https://www.baidu.com/").openConnection(proxy);
            httpCon.setConnectTimeout(5000);
            httpCon.setReadTimeout(5000);

            code = ((HttpURLConnection) httpCon).getResponseCode();
            return code;
        } catch (IOException e) {
            code = 2;
            e.printStackTrace();
        }
        System.out.println(code);
            //return code == 200;
        return code;
    }


/*******************************************************/


//    /**
//     * @param url
//     * @param headerMap 请求头部
//     * @return
//     * @throws Exception
//     */

    /**
     * 设置https代理
     * @throws Exception
     */
    public void getResponseContent() throws Exception {
        HttpsURLConnection connection = null;

        String urlpath="https://www.baidu.com";
        // 设置代理

        //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("183.48.35.21", 8118));
        //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("175.155.238.145", 1133));
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("117.88.176.215", 3000));
            connection = (HttpsURLConnection) new URL(urlpath).openConnection(proxy);


                SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, new TrustManager[]{(TrustManager) new TrustAnyTrustManager()}, new java.security.SecureRandom());
                connection.setSSLSocketFactory(sslContext.getSocketFactory());
                connection.setHostnameVerifier((HostnameVerifier) new TrustAnyHostnameVerifier());



        if (connection == null) {
            connection = (HttpsURLConnection) new URL(urlpath).openConnection();
            System.out.println("if");
        }
        System.out.println("else");
  /*      InetAddress inetAddress=InetAddress.getLocalHost();
        String ip=inetAddress.getHostAddress().toString();//获得本机Ip

        System.out.println("IP:"+ip);*/


        // 添加请求头部
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");
        /*if (headerMap != null) {
            Iterator<Map.Entry<String, List<String>>> iterator = headerMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<String>> entry = iterator.next();
                List<String> values = entry.getValue();
                for (String value : values)
                    connection.setRequestProperty(entry.getKey(), value);
            }
        }*/

        InputStream inputStream = connection.getInputStream();

        Map headers = connection.getHeaderFields();
        Set<String> keys = headers.keySet();
        for( String key : keys ){
            String val = connection.getHeaderField(key);
            System.out.println(key+"    "+val);
        }

        /*BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        inputStream.close();
        System.out.println(stringBuilder.toString());*/
        //return stringBuilder.toString();
        connection.disconnect();
    }

    public void getResponseContent1() throws Exception {
        String MY_IP_API = "https://www.ipip.net/ip.html";
        HttpsURLConnection connection = null;

        //String urlpath="https://www.baidu.com";
        // 设置代理

        //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("183.48.35.21", 8118));
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("175.155.238.145", 1133));

        connection = (HttpsURLConnection) new URL(MY_IP_API).openConnection(proxy);


        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{(TrustManager) new TrustAnyTrustManager()}, new java.security.SecureRandom());
        connection.setSSLSocketFactory(sslContext.getSocketFactory());
        connection.setHostnameVerifier((HostnameVerifier) new TrustAnyHostnameVerifier());



        if (connection == null) {
            connection = (HttpsURLConnection) new URL(MY_IP_API).openConnection();
            System.out.println("if");
        }

  /*      InetAddress inetAddress=InetAddress.getLocalHost();
        String ip=inetAddress.getHostAddress().toString();//获得本机Ip

        System.out.println("IP:"+ip);*/


        // 添加请求头部
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");
        /*if (headerMap != null) {
            Iterator<Map.Entry<String, List<String>>> iterator = headerMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<String>> entry = iterator.next();
                List<String> values = entry.getValue();
                for (String value : values)
                    connection.setRequestProperty(entry.getKey(), value);
            }
        }*/

        connection.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        Document doc = Jsoup.parse(connection.getInputStream(),ConstantCharSet.UTF_8,MY_IP_API);

        //Document document = Jsoup.parse(conn.getInputStream(),ConstantCharSet.UTF_8,MY_IP_API);

        //String html = HttpUtils.getResponseContent(MY_IP_API);

        //Document doc = Jsoup.parse(html);
        Element element = doc.selectFirst("div.tableNormal");

        Element ele = element.selectFirst("table").select("td").get(1);

        String ip = element.selectFirst("a").text();

        System.out.println(ip);


        connection.disconnect();
    }

    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }



    public void setSystem()throws Exception{
        System.setProperty("proxyType", "4");
        System.setProperty("proxyPort", "3000");
        System.setProperty("proxyHost", "117.88.5.53");
        System.setProperty("proxySet", "true");
        InetAddress inetAddress=InetAddress.getLocalHost();
        String ip=inetAddress.getHostAddress().toString();//获得本机Ip

        System.out.println(ip);
    }

/*******************************************************/


private static final String MY_IP_API = "https://www.ipip.net/ip.html";

    // 获取当前ip地址，判断是否代理成功
    public void getMyIp() {
        try {

            URL xcUrl = new URL(MY_IP_API);
            HttpURLConnection conn = (HttpURLConnection) xcUrl.openConnection();
            conn.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
            Document doc = Jsoup.parse(conn.getInputStream(),ConstantCharSet.UTF_8,MY_IP_API);

            //Document document = Jsoup.parse(conn.getInputStream(),ConstantCharSet.UTF_8,MY_IP_API);

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


