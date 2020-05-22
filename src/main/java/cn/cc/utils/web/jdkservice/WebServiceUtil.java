package cn.cc.utils.web.jdkservice;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.ParameterMode;
import java.util.ArrayList;
import java.util.List;

public class WebServiceUtil {


    public static void main(String[] args) {
        String endpoint = "http://192.1.6.68/CRMAllowedService?wsdl";
        String nameSpace = "webservices.services.weaver.com.cn";
        String methods = "getCRMSanctionsAllowedInfo";
        String parameter = "";
        List<String> list = new ArrayList<>();
        list.add(parameter);
        list.add("1");
        list.add("2");
        webserviceFunction(nameSpace, endpoint, methods, list);
    }

    public static String webserviceFunction(String nameSpace, String endpoint, String methods,List<String> list) {
        String result = null;
        Object[] parameter = new Object[3];
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName(new javax.xml.namespace.QName(nameSpace, methods));//WSDL里面描述的接口名称
            for (int i = 0; i < 3; i++) {
                call.addParameter("in" + i, XMLType.XSD_STRING, ParameterMode.IN);//接口的参数
                parameter[i] = list.get(i);
            }
            call.setReturnType(XMLType.XSD_STRING);//设置返回类型
            call.setUseSOAPAction(true);
            call.setSOAPActionURI(nameSpace + methods);
            result = (String) call.invoke(parameter);
            //给方法传递参数，并且调用方法
            System.out.println("result is :" + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
//        	e.printStackTrace();
//            logger.error(e.toString());
        }
        return result;


    }
}
