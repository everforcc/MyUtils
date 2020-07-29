package cn.cc.core.http.webservice.Xfire;

import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import java.net.URL;


public class XfireClient {

    public static void main(String[] args) {
        //第一种 -----------通过服务端提供的端口来创建客户端
        /**
         * 通过Web服务端提供的接口来创建客户端
         * @see 客户端必须提供一个与服务端完全一致的接口，包名也要一致
         * @see 并且此时需要在项目中引入XFire 1.2 Core Libraries和XFire 1.2 HTTP Client Libraries
         */

        String serviceUrl = "http://localhost:8080/Demo/services/MyService";
        //Service serviceModel = new ObjectServiceFactory().create(IMyService.class, null, "http://localhost:8080/Demo/services/MyService?wsdl", null);
        Service serviceModel = new ObjectServiceFactory().create(null, null, "http://localhost:8080/Demo/services/MyService?wsdl", null);
        XFireProxyFactory serviceFactory = new XFireProxyFactory();
        try{
            Object object = serviceFactory.create(serviceModel,serviceUrl);
            //String hello = service.example("11111111111111");
            System.out.println(object);
        }catch(Exception e){
            e.printStackTrace();
        }

        //第二种  -------通过WSDL地址来创建动态客户端
        /**
         * 通过WSDL来创建动态客户端
         * @see 此时需要在项目中引入XFire 1.2 Core Libraries和XFire 1.2 HTTP Client Libraries
         */
        Client client=null;
        try {
            client = new Client(new URL("http://localhost:8080/Demo/services/MyService?wsdl"));

            Object[] results = client.invoke("example", new Object[]{"22222222222222"});
            System.out.println(results[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
