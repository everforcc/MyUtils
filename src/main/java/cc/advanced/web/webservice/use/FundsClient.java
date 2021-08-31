package cc.advanced.web.webservice.use;

import cc.core.date.utils.DateUtils;
import cc.advanced.web.webservice.Axis.AxisUtils;
import cc.advanced.web.webservice.del.AxisWebService;
import cc.core.io.PrintWriterUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * Yukino
 * 2020/6/30
 */
public class FundsClient {

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

    public static void main(String[] args)throws Exception {
        System.out.println("1");
        Date date1 = new Date();
        try {
            String aa = PrintWriterUtils.fileReader("D:\\test\\生产报文.txt");
            System.out.println("2");
            Object[] a= new Object[]{aa};
            System.out.println("3");
            AxisUtils.callService(endpoint,namespaceURI,useMethod,parametersName,a);
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date date2 = new Date();
        System.out.println( "耗时:" + DateUtils.timedif(date1,date2) );
    }




}
