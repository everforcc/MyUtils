package cc.advanced.web.http;

import cc.core.io.InputStreamUtils;
import cc.utils.UserException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class URLConnectionUtils {

    private static URLConnection urlconn = null;

    /**
     * 待对比完善
     */

    public static void send(String webUrl,Map<String,String> map,String sendMsg){
        // 1. 初始化数据
        init(webUrl,map);
        // 2. 向服务器写入数据
        getOutputStream(sendMsg);
        // 3. 从服务器读数据
        InputStream inputStream = receive();
        // 4.
        try {
            System.out.println(InputStreamUtils.inputStreamStr(inputStream, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化url和参数，返回URLConnection
     * @param webUrl
     * @param map
     * @return
     */
    public static URLConnection init(String webUrl,Map<String,String> map){
        URL url = null;
        try {
            url = new URL(webUrl);
            urlconn = url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setParams(urlconn,map);
        return urlconn;
    }

    /**
     * 如果是post需要这个，如果是get则在地址后面追加params
     * @param sendMsg
     */
    public static void getOutputStream(String sendMsg){
        try {
            if(StringUtils.isNotBlank(sendMsg)){
                urlconn.setDoOutput(true);
                //5, 获取向服务器写出数据的流
                OutputStream os = urlconn.getOutputStream();
                //参数是键值队  , 不以"?"开始
                os.write(sendMsg.getBytes());
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InputStream receive(){
        try {
            // 返回头的map
            Map<String, List<String>> map = urlconn.getHeaderFields();
            System.out.println(map.size());

            map.forEach((k,v) -> System.out.println(k + " >>> " + v.toString()));

            System.out.println(urlconn.getReadTimeout());

            urlconn.setDoInput(true);

            return urlconn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置头部字段
     * @param urlconn
     * @param map
     */
    private static void setParams(URLConnection urlconn, Map<String,String> map){
        if(Objects.isNull(urlconn)||Objects.isNull(map)){
            //throw new UserException("不能为空啊");
            return;
        }
        map.forEach((k,v) -> urlconn.setRequestProperty(k,v));
    }

    public static void main(String[] args) {
        send("https://www.baidu.com/",null,"");
    }

}
