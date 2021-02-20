package cn.cc.core.web.jdkservice;

import javax.xml.ws.Endpoint;

/**
 * @author c.c.
 * @date 2021/2/19
 */
public class WebServicePublish {

    public static void main(String[] args) {
        // 1.url
        String address = "http://127.0.0.1/ws_service/hello"; // http://127.0.0.1/ws_service/hello?wsdl
        // 2、使用Endpoint的publish方法来发布这个服务
	   //Endpoint.publish(address, 接口实现类)
        Endpoint.publish(address, new JDKServiceImpl());
        System.out.println("发布webservice成功!");
    }

}
