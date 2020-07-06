package cn.cc.utils.http.webservice.Axis;

import net.sf.json.JSONObject;
import org.apache.axis.client.Service;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;
import java.util.HashMap;
import java.util.Map;

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
            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
            call.addParameter("in2", XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
            parameter[0]="";
            parameter[1]="雅玛多国际物流有限公司上海分公司";
            parameter[1]="万泽润";
            parameter[2]="404045-00772202036";
            parameter[2]="00010002";
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


    /**
     *
     * 没有客户代码所以默认传""
     * @param name  客户姓名
     * @param accNo 银行账户
     * @return
     */
    public static Map<String,String> callCRM(String name,String accNo){
        //LOGGER.debug("EcAxisWebService-----------callCRM------start---name:"+name+",accNo:"+accNo);

        Map<String,String> map = new HashMap<String, String>();
        String[] strArray={"",name,accNo};
        String result = callService(strArray);
        // 加入返回json 转换为MAP
        // 根据接口来处理返回数据
        JSONObject jsonObj = JSONObject.fromObject(result);

        if(jsonObj.has("result")){
            if(jsonObj.get("result").equals("0")){//表示CRM接口发生错误
                if(jsonObj.has("errmsg")){
                    try {
                        throw new Exception("错误信息:"+jsonObj.get("errmsg"));
                    } catch (Exception e) {
                       //LOGGER.debug("读取CRM返回异常:"+e.toString());
                        e.printStackTrace();
                    }
                }
            }else{
                //接口正常返回
                if(jsonObj.has("resultdata")){
                    JSONObject resultdata = jsonObj.getJSONObject("resultdata");
                    //开始获取数据
                    try {
                        if(resultdata.has("CRMCode")){ //客户代码
                            map.put("CRMCode", resultdata.getString("CRMCode"));
                        }else{
                            throw new Exception("CRM没有返回客户代码");
                        }
                        if(resultdata.has("name")){ //客户名称
                            map.put("name", resultdata.getString("name"));
                        }else{
                            throw new Exception("CRM没有返回客户名称");
                        }
                        if(resultdata.has("AccNo")){ //银行账户
                            map.put("AccNo", resultdata.getString("AccNo"));
                        }else{
                            throw new Exception("CRM没有返回银行账户");
                        }
                        if(resultdata.has("SanctionsType")){ //制裁名单类型
                            map.put("SanctionsType", resultdata.getString("SanctionsType"));
                        }else{
                            throw new Exception("CRM没有返回名称");
                        }
                        if(resultdata.has("SanctionsAllowed")){ //制裁对象是否允许交易
                            map.put("SanctionsAllowed", resultdata.getString("SanctionsAllowed"));
                        }else{
                            throw new Exception("CRM没有返回:制裁对象是否允许交易");
                        }
                    } catch (Exception e) {
                        //LOGGER.debug("读取CRM返回用户参数异常:"+e.toString());
                        e.printStackTrace();
                    }
                }
            }
        }
        return map;
    }
}
