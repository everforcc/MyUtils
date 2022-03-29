package cc.advanced.webrefactor.http.impl;

import cc.advanced.webrefactor.http.IHttp;
import cc.core.io.base.StreamInputUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.axis.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

/**
 * @author everforcc 2021-09-16
 */
@Slf4j
public class HttpURLConnectionImpl implements IHttp {

    private static final int defaultTimeout = 6000;

    @Override
    public String requestForStr(String urlPath, String requestMethod) {
        return null;
    }

    @Override
    public String requestForStr(String urlPath, String requestMethod, String content, String charset) {
        return null;
    }

    @Override
    public String requestForStr(String urlPath, String requestMethod, String content, String charset, Map<String, String> map) {
        return null;
    }

    @Override
    public byte[] requestForFile(String urlPath, String requestMethod, String content, String filePath, String fileName) {
        String file = filePath + File.separator + fileName;
        return allParamsToFile(urlPath,requestMethod,null,file,content,null);
    }


    // 这里写所有的参数
    public String allParamsToStr(String urlPath,String requestMethod, Map<String,String> map,String charset, String content,Integer timeout){
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        String returnMsg = null;
        try {
            //1. 获取connect
            httpURLConnection = getConnection(urlPath);
            //2. 流
            httpURLConnection = getConn(httpURLConnection,requestMethod,map,content,timeout);
            //3. 获取内容
            returnMsg = StreamInputUtils.streamToStr(httpURLConnection.getInputStream(),charset);
            //3. copy文件
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMsg;
    }

    public byte[] allParamsToFile(String urlPath,String requestMethod, Map<String,String> map,String file, String content,Integer timeout){
        HttpURLConnection httpURLConnection = null;
        byte[] returnMsg = new byte[0];
        try {
            //1. 获取connect
            httpURLConnection = getConnection(urlPath);
            //2. 流
            httpURLConnection = getConn(httpURLConnection,requestMethod,map,content,timeout);
            //3. copy文件
            //if(httpURLConnection.getContentLength()<1024){
                //returnMsg = InputStreamUtils.inputStreamStr(httpURLConnection.getInputStream(),null);
                returnMsg = StreamInputUtils.copyStreamByteAry(httpURLConnection.getInputStream(),null);
            /*}else {
                FileUtils.copyToFile(httpURLConnection.getInputStream(),new File(file));
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMsg;
    }

    private HttpURLConnection getConnection(String urlPath) throws IOException {
        HttpURLConnection conn = null;
        URL url = new URL(urlPath);
        //2, 打开连接
        //HttpURLConnection conn = (HttpURLConnection) url.openConnection(ProxyDemo.getUrlProxyContent(""));
        return (HttpURLConnection) url.openConnection();
    }

    public static HttpURLConnection getConn(HttpURLConnection conn, String requestMethod, Map<String,String> map, String content,Integer timeout) throws IOException {
        if(map!=null&&map.size()>0){
            for(Map.Entry entry : map.entrySet()){
                String key = (String) entry.getKey();
                if(conn.getRequestProperty(key)==null){
                    String value = (String) entry.getValue();
                    conn.setRequestProperty(key,value);
                }
            }
        }
        //3, 设置提交类型
        try {
            conn.setConnectTimeout(timeout==null?defaultTimeout:timeout);
            conn.setRequestMethod(requestMethod);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        //4, 设置允许写出数据,默认是不允许 false
        conn.setDoInput(true);
        // 如果不为空就进去
        if(!StringUtils.isEmpty(content)) {
            conn.setDoOutput(true);
            //5, 获取向服务器写出数据的流
            OutputStream os = conn.getOutputStream();
            //参数是键值队  , 不以"?"开始
            os.write(content.getBytes());
            //os.write("googleTokenKey=&username=admin&password=5df5c29ae86331e1b5b526ad90d767e4".getBytes());
            os.flush();
        }

        log.info("返回数据类型" + conn.getHeaderField("Content-Type"));
        log.info("返回数据大小" + conn.getContentLength());
        log.info("conn.getResponseCode(): " + conn.getResponseCode());
        return conn;
    }

    public void resquest(HttpURLConnection conn,String content,String requestMethod,String charset,Map<String,String> map){

    }


}
