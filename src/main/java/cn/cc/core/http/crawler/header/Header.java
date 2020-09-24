package cn.cc.core.http.crawler.header;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class Header {

    public static void main(String[] args) {
        //getMap();
    }
    public static HttpURLConnection getMap(HttpURLConnection conn){

        try {
            // 静态方法和非晶态方法不同
            //InputStream in = this.getClass().getResourceAsStream("/properties/basecom.properties");
            InputStream in = Header.class.getResource("/properties/header-properties/pictest.properties").openStream();
            Properties properties = new Properties();
            properties.load(in);
            /*System.out.println( properties.getProperty("a"));
            System.out.println(Header.class.getResource("/").getPath());*/
            Iterator it = properties.entrySet().iterator();
            // 每个配置文件单独处理一个请求的请求头，只需要便利就可以获取所有的请求头信息, 可以把配置文件的名字也独立出来
            while(it.hasNext()){
                Map.Entry entry=(Map.Entry)it.next();
                System.out.println(entry.getKey().toString()+"="+entry.getValue());
                conn.setRequestProperty(entry.getKey().toString(),entry.getValue().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return conn;
    }
}
