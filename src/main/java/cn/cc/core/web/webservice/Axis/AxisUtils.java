package cn.cc.core.web.webservice.Axis;

import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;

public class AxisUtils {

    /**
     * 调用的时候要注意地址，有的需要?wsld, 有的不要, 接口不通的时候可以切换看看
     *
     * 就算传参有问题，也不影响接口通
     *
     */

    /**
     * 接口公共调用方法
     * 地址，方法名，入参类型为String 返回
     * @param
     * @return
     */

    public static void main(String[] args) {
        String endpoint = "http://192.1.6.35//services/WorkflowServiceXml";
        String namespaceURI = "";
        String userMethod = "getCRMSanctionsAllowedInfo";
        // 参数列表，参数值
        callService(endpoint,namespaceURI,userMethod,"in0,in1,in2",new Object[]{"1","1","1"});
    }

    /**
     * @param endpoint 站点地址
     * @param namespaceURI 命名空间
     * @param useMethod 使用方法
     * @param parametersName 参数列表，隔开
     * @param sendMsg 发送的信息
     * @return
     */
    public static  String callService(String endpoint,String namespaceURI,String useMethod,String parametersName,Object[] sendMsg) {
        //  获得类名
        //  String className = this.getClass().getName();
        String className = Thread.currentThread().getStackTrace()[1].getClassName();
        String method = Thread.currentThread() .getStackTrace()[1].getMethodName();

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
        }
        return result;
    }

}
