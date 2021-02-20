package cn.cc.use.url.http.website;

import cn.cc.core.web.http.header.Header;
import cn.cc.core.web.http.utils.HttpURLConnectionUtil;

/**
 * @author c.c.
 * @date 2021/1/22
 */
public class Community_spring4u {

    public static void main(String[] args) {
        t1();
    }

    static void t1(){

        String url = "http://spring4u.info/viewthread.php?tid=434&extra=page%3D1";

        String result = HttpURLConnectionUtil.sendToUrlRequest(url,"GET","big5", Header.spring4uMap());

        System.out.println(result);

    }

}
