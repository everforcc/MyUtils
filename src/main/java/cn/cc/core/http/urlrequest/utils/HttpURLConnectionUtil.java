package cn.cc.core.http.urlrequest.utils;

import cn.cc.core.io.utils.InputStreamUtils;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpURLConnectionUtil {

    private static boolean gzip = false;

    /**
     *  1.发送普通的请求
     *  2.发送String请求
     *  3.发送entity请求
     *  4.发送带文件的请求
     *  5.在读取流的时候，给编码
     */

    // 现在还没有遇到写入数据的情况这content先不用,下面的代码也屏蔽掉了,遇到了再增加,看懂意义
    // 地址，方法，内容，编码， 需要再加个请求头
    public static String sendToUrlRequest(String urlPath,String requestMethod,String content,String charset)throws Exception{
        System.out.println("sendToUrlRequest >>> " + urlPath);
        //1, 得到URL对象
        URL url = new URL(urlPath);
        //2, 打开连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        return forHttpURLConnection(conn,requestMethod,charset);
    }

    public static String  sendToUrlRequest(String urlPath,String requestMethod,String charset, Map<String,String> map){
        System.out.println("sendToUrlRequest():" + urlPath);
        //1, 得到URL对象
        URL url = null;
        try {
            url = new URL(urlPath);
            //2, 打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            return forHttpURLConnection(conn,requestMethod,charset,map);
        } catch (MalformedURLException e) {
            System.out.println("重试中 1>>>" + e.toString());
            return sendToUrlRequest(urlPath, requestMethod, charset, map);
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("重试中 2>>>" + e.toString());
            return sendToUrlRequest(urlPath, requestMethod, charset, map);
            //e.printStackTrace();
        }
    }

    // 重构
    public static String forHttpURLConnection(HttpURLConnection conn,String requestMethod,String charset) throws IOException {
        return forHttpURLConnection(conn,null,requestMethod,charset,null);
    }

    public static String forHttpURLConnection(HttpURLConnection conn,String requestMethod,String charset,Map<String,String> map) throws IOException {
        return forHttpURLConnection(conn,null,requestMethod,charset,map);
    }

    // 如果解析错误重试次数设置为三次，要不然多次就溢出了
    private static List<String> badUrl = new ArrayList<>();
    private static int i = 0;
    public static String forHttpURLConnection(HttpURLConnection conn,String content,String requestMethod,String charset,Map<String,String> map) {
        System.out.println("i >>> " + i);
        String url = conn.getURL().toString();
        if(badUrl.contains(url)){
            System.err.println("问题url:" + url);
        }

        if(i>3){
            try {
                i = 0;
                badUrl.add(url);
                throw new Exception("重试3次，自动退出");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("forHttpURLConnection():start >>>>>>>>>>>>>");
        String returnMsg = null;
        InputStream inputStream = null;
        try {
            i++;
            inputStream = getStream(conn,requestMethod,charset,map);
            // 编码在下面使用
            returnMsg = InputStreamUtils.inputStreamStr(inputStream,charset,gzip);
            gzip = false;// 重置为false 等待下一轮使用
            i = 0;
        } catch (IOException e) {
            // 获取流异常
            System.err.println("forHttpURLConnection():获取流异常");
            if(i<3) {
                forHttpURLConnection(conn, content, requestMethod, charset, null);
            }else {
                badUrl.add(url);
                i = 0;
            }
            //e.printStackTrace();
        } catch (Exception e) {
            // 从流读取数据异常
            System.err.println("forHttpURLConnection():从流读取数据异常");
            if(i<3) {
                forHttpURLConnection(conn, content, requestMethod, charset, map);
            }else {
                badUrl.add(url);
                i = 0;
            }
            //e.printStackTrace();
        }

        System.out.println("forHttpURLConnection():end >>>>>>>>>>>>>");
        //给返回等待下一步处理
        return returnMsg;
    }

    public static InputStream getStream(String urlPath,String requestMethod,String charset,Map<String,String> map) throws IOException {
        URL url = null;
        url = new URL(urlPath);
        //2, 打开连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        return getStream(conn,requestMethod,charset,map);
    }

    public static InputStream getStream(HttpURLConnection conn,String requestMethod,String charset,Map<String,String> map) throws IOException {
        // 设置请求头
        if(map!=null&&map.size()>0){
            for(Map.Entry entry : map.entrySet()){
                //System.out.println((String) entry.getKey() + "---" + conn.getRequestProperty((String) entry.getKey()));
                String key = (String) entry.getKey();
                if(conn.getRequestProperty(key)==null){
                    String value = (String) entry.getValue();
                    conn.setRequestProperty(key,value);
                    // 大小写有时候不一样
                    if("Accept-Encoding".equalsIgnoreCase(key)&&value.contains("gzip")){
                        gzip = true; // 需要解压
                    }
                }
            }
        }
        //3, 设置提交类型
        try {
            conn.setRequestMethod(requestMethod);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        //4, 设置允许写出数据,默认是不允许 false
        //conn.setDoOutput(true);
        conn.setDoInput(true);//当前的连接可以从服务器读取内容, 默认是true
        conn.setConnectTimeout(4000);
        // 如果不为空就进去, 没搞懂方式
        /*if(!StringUtils.isEmpty(content)) {
            //5, 获取向服务器写出数据的流
            OutputStream os = conn.getOutputStream();
            //参数是键值队  , 不以"?"开始
            os.write(content.getBytes());
            //os.write("googleTokenKey=&username=admin&password=5df5c29ae86331e1b5b526ad90d767e4".getBytes());
            os.flush();
        }*/
        //6, 获取响应的数据
        //得到服务器写回的响应数据
        // charset 在这里 <meta http-equiv="Content-Type" content="text/html; charset=gbk" />
        //System.out.println("getStream():1 >>>>>>>>>>>>>");

        //System.out.println("getStream():" + conn.getContentLength());

        return conn.getInputStream();
    }

    /* 使用统一的工具类
    private static String readMsg(InputStream inputStream,String charset) throws IOException {
        String str;
        BufferedReader br= null;
        StringBuffer stringBuffer = new StringBuffer();
        br = new BufferedReader(new InputStreamReader(inputStream,charset));
        while ( (str=br.readLine())!=null){
            stringBuffer.append(str + "\r\n");
        }
        return stringBuffer.toString();
    }*/

/***************************************************** ↓还没有用到↓ ****************************************************/


    /**
     *
     */

    /**
     * 上传图片
     * @param urlStr 第hi
     * @param textMap 字符数据map
     * @param fileMap 文件map
     * @param contentType 没有传入文件类型默认采用application/octet-stream
     * contentType 非空采用filename匹配默认的图片类型
     * @return 返回response数据
     */
    @SuppressWarnings("rawtypes")
    public static String formUpload(String urlStr, Map<String, String> textMap,
                                    Map<String, String> fileMap,String contentType) {
        String res = "";
        HttpURLConnection conn = null;
        // boundary就是request头和上传文件内容的分隔符
        String BOUNDARY = "---------------------------123821742118716";
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();

            // 设置强求属性
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            // conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);

            OutputStream out = new DataOutputStream(conn.getOutputStream());

            // text 字符参数
            if (textMap != null) {
                StringBuffer strBuf = new StringBuffer();
                Iterator iter = textMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
                    strBuf.append(inputValue);
                }
                out.write(strBuf.toString().getBytes());
            }

            // file 文件map
            if (fileMap != null) {
                Iterator iter = fileMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    File file = new File(inputValue);
                    String filename = file.getName();

                    //没有传入文件类型，同时根据文件获取不到类型，默认采用application/octet-stream
                    contentType = new MimetypesFileTypeMap().getContentType(file);
                    //contentType非空采用filename匹配默认的图片类型
                    if(!"".equals(contentType)){
                        if (filename.endsWith(".png")) {
                            contentType = "image/png";
                        }else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".jpe")) {
                            contentType = "image/jpeg";
                        }else if (filename.endsWith(".gif")) {
                            contentType = "image/gif";
                        }else if (filename.endsWith(".ico")) {
                            contentType = "image/image/x-icon";
                        }
                    }
                    if (contentType == null || "".equals(contentType)) {
                        contentType = "application/octet-stream";
                    }
                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
                    out.write(strBuf.toString().getBytes());
                    DataInputStream in = new DataInputStream(new FileInputStream(file));
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                    in.close();
                }
            }
            // file end

            // 发送数据
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();


            // 读取返回数据
            StringBuffer strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            // 返回信息
            res = strBuf.toString();
            reader.close();
            reader = null;

        } catch (Exception e) {
            System.out.println("发送POST请求出错。" + urlStr);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }

}
