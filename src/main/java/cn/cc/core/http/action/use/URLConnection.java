package cn.cc.core.http.action.use;


import cn.cc.core.http.action.common.HttpURLConnectionUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

/**
 *
 */
public class URLConnection {

    /**
     *  用jdk自带的HttpURLConnection来发送请求
     */

    //
    public static void main(String[] args) throws Exception{
        String urlPath="http://localhost:7007/invoice/queryWaitEditData";
        String sendMsg="{\"operateType\":\"prepareEdit\",\n" +
                "\"batchno\":\"VKJ20000000202000027\",\n" +
                "\"pageNo\":\"1\",\n" +
                "\"pageSize\":\"1\",\n" +
                "\t\"userDto\":{\n" +
                "\t\t\"userCode\":\"0000000000\",\n" +
                "\t\t\"userName\":\"测试1\",\n" +
                "\t\t\"comCode\":\"0000000000\"\n" +
                "\t}\n" +
                "}\n";
        String str = HttpURLConnectionUtil.sendToUrlRequest(urlPath,"POST",sendMsg,"UTF-8");
        System.out.println("返回值:"+str);
    }

    static String  urlPath ="https://game.eroge.xyz/tools/ons%E6%A8%A1%E6%8B%9F%E5%99%A8/ONScripter-Jh_0.7.2.arm64.apk";

    static String userAgent="Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20200220 Firefox/29.0";

    /**
     * post请求封装 参数为?a=1&b=2&c=3
     * @param urlPath 接口地址
     * @param Info 参数
     * @return
     * @throws IOException
     */
    public static JSONObject postResponse(String urlPath, String Info) throws Exception{
        String str = HttpURLConnectionUtil.sendToUrlRequest(urlPath,"POST",Info,"UTF-8");
        JSONObject json = JSONObject.parseObject(str);
        System.out.println("响应内容为:  " + json);
        return  json;
    }


}
