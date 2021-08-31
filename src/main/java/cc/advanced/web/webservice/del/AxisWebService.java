package cc.advanced.web.webservice.del;

import org.apache.axis.client.Service;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;

/**
 * 测试黑名单
 */
public class AxisWebService {
    public static void main(String[] args) {
        //System.out.println("是否允许交易:"+callCRM("wuyingrong","123").get("SanctionsAllowed"));
        String[] strArray={"","",""};
        System.out.println(callService(strArray));
    }
    static Object[] parameter = new Object[3];;

    //private  final Log LOGGER = LogFactory.getLog(AxisWebService.class);

    /**
     * 接口公共调用方法
     * 地址，方法名，入参类型为String 返回
     * @param sendMsg
     * @return
     */
    public static  String callService(Object sendMsg) {
        //LOGGER.debug("EcAxisWebService-----------getService------start---sendMsg:"+sendMsg);
        String result = "" ;
        //Properties properties = PropertiesContext.getApplicationProperties();
        try {
            String endpoint = "http://192.1.6.68/CRMAllowedService";
            Service service = new Service();
            Call call  = service.createCall();

            call.setTargetEndpointAddress(endpoint);
            //String parametersName = "sessionID"; 		// 参数名//对应的是 public String printWord(@WebParam(name = "settle_num") String settle_num);


            //call.setOperationName("printWord");  		// 调用的方法名//当这种调用不到的时候,可以使用下面的,加入命名空间名

            //getAutoInvoiceReimbure,queryInvoiceData
            call.setOperationName(new QName("webservices.services.weaver.com.cn", "getCRMSanctionsAllowedInfo"));// 调用的方法名

            ParameterDesc p;

            // 参数名List，参数类型String，入参
            /*String parametersName = "crmCode,name,accNo";
            String[] parameter = parametersName.split(",");
            for (String str : parameter) {
                call.addParameter(str, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
            }*/

            //返回类型
            call.setReturnType(XMLType.XSD_STRING); 	// 返回值类型：String

            //            result = (String) call.invoke(new Object[] {sendMsg});// 远程调用
            result = (String) call.invoke(parameter);// 远程调用

            System.out.println("result is : " + result);
        } catch (Exception e) {
            System.err.println(e.toString());
            //LOGGER.debug("EcAxisWebService-----------getService------error:"+e.toString());
        }
        //LOGGER.debug("EcAxisWebService-----------getService------fileio---result:"+result);
        return result;
    }



}
