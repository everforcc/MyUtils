package cn.cc.core.web.http.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class URLDemo {

    // 请求要素，

    /**
     * 1.请求头
     * 2.请求方法
     * 3.编码
     * 4.返回数据（如果加密需要解密 gzip 等）
     */

    // url基本含义
    public static void main(String[] args) {
        urlMethod();
        // 路径中文的加密解密
        //nextUrl = URLDecoder.decode(nextUrl);
    }

    // url参数含义

    static void urlMethod() {
        try {
            String picUrl = "https://www.wenku8.net/book/366.htm";
            URL url = new URL(picUrl);
            System.out.println("URL 为：" + url.toString());
            System.out.println("协议为：" + url.getProtocol());
            System.out.println("验证信息：" + url.getAuthority());
            System.out.println("文件名及请求参数：" + url.getFile());
            // 作为文件夹的根目录
            System.out.println("主机名：" + url.getHost());
            // 作为分级目录
            System.out.println("路径：" + url.getPath());
            System.out.println("端口：" + url.getPort());
            System.out.println("默认端口：" + url.getDefaultPort());
            System.out.println("请求参数：" + url.getQuery());
            System.out.println("定位位置：" + url.getRef());
            System.out.println(url.getProtocol() + "://" + url.getHost());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // url 校验
    void URLConnectionMethod() {
        try {
            URL url = new URL("http://www.runoob.com");
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;
            if (urlConnection instanceof HttpURLConnection) {
                connection = (HttpURLConnection) urlConnection;
            } else {
                System.out.println("请输入 URL 地址");
                return;
            }
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String urlString = "";
            String current;
            while ((current = in.readLine()) != null) {
                urlString += current;
            }
            System.out.println(urlString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
