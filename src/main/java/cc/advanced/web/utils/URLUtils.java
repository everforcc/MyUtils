package cc.advanced.web.utils;

import cc.utils.Print_Record;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class URLUtils {

    private static Print_Record print_record = Print_Record.getInstanse("");

    // 请求要素，

    /**
     * 1.请求头
     * 2.请求方法
     * 3.编码
     * 4.返回数据（如果加密需要解密 gzip 等）
     */

    // url基本含义
    public static void main(String[] args) {
        String u1 = "http://127.0.0.1:8080/open/file/read?fileId=";
        String u2 = "http://boc.91tests.net/open/file/read?fileId=";
        urlMethod(u1);
        urlMethod(u2);
        // 路径中文的加密解密
        //nextUrl = URLDecoder.decode(nextUrl);
    }

    // url参数含义

    static void urlMethod(String picUrl) {
        try {
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
            print_record.println("定位位置：[%s]",url.getRef());
            System.out.println(url.getProtocol() + "://" + url.getHost());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
