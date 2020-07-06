package cn.cc.utils.http.webservice.Axis;

import cn.cc.utils.date.DateUtils;
import cn.cc.utils.fileio.io.InputStream_IO;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;
import java.util.Date;

/**
 * Yukino
 * 2020/6/30
 */
public class FundsClient {

    public static void main(String[] args) {
        System.out.println("1");
        Date date1 = new Date();
        InputStream_IO inputStream_io = new InputStream_IO();
        try {
            String aa = inputStream_io.IO_BufferReader_Content("D:\\test\\生产报文.txt");
            System.out.println("2");
            Object[] a= new Object[]{aa};
            System.out.println("3");
            callService(a);
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date date2 = new Date();
        System.out.println( "耗时:" + DateUtils.timedif(date1,date2) );
    }


    // 输出日志
    private static  final Log LOGGER = LogFactory.getLog(AxisWebService.class);
    // 参数名List，参数类型String，入参,相同的用 "," 隔开
    private static  final String parametersName = "arg0";
    //  ip                                                           时间
    //  192.168.43.45:8081/funds/capital_service/synvoucher_service
    //  127.0.0.1:8081/funds/capital_service/synvoucher_service
    //  localhost
    // private static  final String endpoint = "http://ms18-uat.ms-ins.com.cn/costmcs/capital_service/synvoucher_service";
    private static  final String endpoint = "http://localhost:8081/funds/capital_service/synvoucher_service";
    // 调用的接口名
    private static  final String useMethod = "SaveSynVoucherDto";
    // 命名空间
    //private static  final String namespaceURI = "webservices.services.weaver.com.cn" ;
    private static  final String namespaceURI = "" ;


    public static  String callService(Object[] sendMsg) {
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
            LOGGER.debug(className+"-----------"+method+"------error:"+e.toString());
        }
        LOGGER.debug(className+"-----------"+method+"------fileio---result:"+result);
        return result;
    }

}
