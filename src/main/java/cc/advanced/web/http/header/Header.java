package cc.advanced.web.http.header;

import java.io.InputStream;
import java.util.*;

public class Header {

    // 配置文件根目录
    private static String path = "/properties/header-properties/";
    public static void main(String[] args) {
        //getMap();
    }
    public static Map<String,String>  getMap(String resource){

        try {
            // 静态方法和非晶态方法不同
            //InputStream in = this.getClass().getResourceAsStream("/properties/basecom.properties");
            InputStream in = Header.class.getResource(resource).openStream();
            Properties properties = new Properties();
            properties.load(in);
            /*System.out.println( properties.getProperty("a"));
            System.out.println(Header.class.getResource("/").getPath());*/
            Map<String,String> map = new HashMap<>();
            Iterator it = properties.entrySet().iterator();
            // 每个配置文件单独处理一个请求的请求头，只需要便利就可以获取所有的请求头信息, 可以把配置文件的名字也独立出来
            while(it.hasNext()){
                Map.Entry entry=(Map.Entry)it.next();
                //System.out.println(entry.getKey().toString()+"="+entry.getValue());
                map.put((String)entry.getKey(),(String) entry.getValue());
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
    }

    // 每个网站的请求头的位置,可以再加个网站的目录
    public static Map<String,String> qimiaoxsMap(){
        String fileName = "qimiaoxs.properties";
        return getMap(path + fileName);
    }

    public static Map<String,String> orzgirlMap(){
        String fileName = "orzgirl.properties";
        return getMap(path + fileName);
    }

    public static Map<String,String> imglolitaMap(){
        String fileName = "imglolita.properties";
        return getMap(path + fileName);
    }

    public static Map<String,String> biqugeMap(){
        String fileName = "biquge.properties";
        return getMap(path + fileName);
    }

    public static Map<String,String> bkimgMap(){
        String fileName = "bkimg.properties";
        return getMap(path + fileName);
    }

    public static Map<String,String> qinxiaoshuoMap(){
        String fileName = "qinxiaoshuo.properties";
        return getMap(path + fileName);
    }
    public static Map<String,String> pictureWenku8(){
        String fileName = "picture.wenku8.com.properties";
        return getMap(path + fileName);
    }
    public static Map<String,String> bilibiliMap(){
        String fileName = "bilibili.properties";
        return getMap(path + fileName);
    }

    public static Map<String,String> wenku8Map(){
        String fileName = "wenku8.properties";
        return getMap(path + fileName);
    }

    public static Map<String,String> ipipMap(){
        String fileName = "ipip.properties";
        return getMap(path + fileName);
    }

    public static Map<String,String> biquge2Map(){
        String fileName = "biquge2.properties";
        return getMap(path + fileName);
    }

    public static Map<String,String> mingyantongMap(){
        String fileName = "mingyantong.properties";
        return getMap(path + fileName);
    }

    public static Map<String,String> loveyueduMap(){
        String fileName = "loveyuedu.properties";
        return getMap(path + fileName);
    }

    // spring4u.info.properties
    public static Map<String,String> spring4uMap(){
        String fileName = "spring4u.info.properties";
        return getMap(path + fileName);
    }

    public static Map<String,String> lanwlMap(){
        String fileName = "www.lanwl.com.properties";
        return getMap(path + fileName);
    }
    //www.yulinzhanye.la.properties
    public static Map<String,String> yulinzhanyeMap(){
        String fileName = "www.yulinzhanye.la.properties";
        return getMap(path + fileName);
    }

    // www.yulinzhanye.la-search.properties
    public static Map<String,String> yulinzhanye_searchMap(){
        String fileName = "www.yulinzhanye.la-search.properties";
        return getMap(path + fileName);
    }

    //www.paoshuzw.com.properties
    public static Map<String,String> paoshuzwMap(){
        String fileName = "www.paoshuzw.com.properties";
        return getMap(path + fileName);
    }

    //www.paoshuzw.com.properties
    public static Map<String,String> bqkanMap(){
        String fileName = "www.bqkan.com.properties";
        return getMap(path + fileName);
    }

    //www.37zw.net.properties
    public static Map<String,String> zw37Map(){
        String fileName = "www.37zw.net.properties";
        return getMap(path + fileName);
    }

    // www.qidian.com.properties
    public static Map<String,String> qidianMap(){
        String fileName = "www.qidian.com.properties";
        return getMap(path + fileName);
    }
}
