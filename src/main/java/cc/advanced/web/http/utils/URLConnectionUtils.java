package cc.advanced.web.http.utils;

import cc.utils.UserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Objects;

public class URLConnectionUtils {

    private static URLConnection urlconn = null;

    public static OutputStream send(String webUrl){
        try {
            URL url = new URL(webUrl);
            urlconn = url.openConnection();
            //urlconn.setRequestProperty();
            return urlconn.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream receive(String webUrl){
        try {
            URL url = new URL(webUrl);
            urlconn = url.openConnection();
            return urlconn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void setParams(URLConnection urlconn, Map<String,String> map){
        if(Objects.isNull(urlconn)||Objects.isNull(map)){
            throw new UserException("不能为空啊");
        }
    }

    public static void main(String[] args) {
        setParams(null,null);
    }

}
