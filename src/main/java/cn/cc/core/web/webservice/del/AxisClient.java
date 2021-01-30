package cn.cc.core.web.webservice.del;


import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;
import java.util.ArrayList;
import java.util.List;

/**
 * xfire
 * 基于Axis的WebService客户端
 */
public class AxisClient {

    /**
     * 调用的时候要注意地址，有的需要?wsld, 有的不要, 接口不通的时候可以切换看看
     *
     * 就算传参有问题，也不影响接口通
     *
     * 报错参考
     * org.xml.sax.SAXException: Bad envelope tag:  definitions
     * https://blog.csdn.net/wangjinwei6912/article/details/8512598
     */



    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        callService(new Object[]{"","刘昌明","6230582000002488610"});
    }
    // 输出日志
    private static  final Log LOGGER = LogFactory.getLog(AxisWebService.class);
    // 参数名List，参数类型String，入参,相同的用 "," 隔开
    private static  final String parametersName = "crmCode,name,accNo";
    //private static  final String endpoint = "http://192.1.6.68/CRMAllowedService?wsdl";
    private static  final String endpoint = "http://192.1.6.68/CRMAllowedService";
    // 调用的接口名
    private static  final String useMethod = "getCRMSanctionsAllowedInfo";
    // 命名空间
    //private static  final String namespaceURI = "webservices.services.weaver.com.cn" ;
    private static  final String namespaceURI = "" ;

    List<String> list = new ArrayList<>();




    /**
     * 接口公共调用方法
     * 地址，方法名，入参类型为String 返回
     * @param sendMsg
     * @return
     */
    public static  String callService(Object[] sendMsg) {
        //  获得类名
        //  String className = this.getClass().getName();
        String className = Thread.currentThread().getStackTrace()[1].getClassName();
        String method = Thread.currentThread() .getStackTrace()[1].getMethodName();
        LOGGER.debug(className+"-----------"+method+"------start---sendMsg:"+sendMsg);

        String result = "" ;
        try {
            Service service = new Service();
            Call call  = service.createCall();
            call.setTargetEndpointAddress(endpoint);

            //String parametersName = "sessionID"; 		// 参数名//对应的是 public String printWord(@WebParam(name = "settle_num") String settle_num);
            //call.setOperationName("printWord");  		// 调用的方法名//当这种调用不到的时候,可以使用下面的,加入命名空间名

            call.setOperationName(new QName(namespaceURI, useMethod));// 调用的方法名

            String[] parameter = parametersName.split(",");
            for (String str : parameter) {
                 call.addParameter(str, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
            }
            //返回类型
            call.setReturnType(XMLType.XSD_STRING); 	// 返回值类型：String

            result = (String) call.invoke(sendMsg);// 远程调用

            System.out.println("result is : " + result);
        } catch (Exception e) {
            System.err.println(e.toString());
            LOGGER.debug(className+"-----------"+method+"------error:"+e.toString());
        }
        LOGGER.debug(className+"-----------"+method+"------fileio---result:"+result);
        return result;
    }




}
