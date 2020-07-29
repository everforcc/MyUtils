package cn.cc.core.http.webservice.use;

import cn.cc.core.http.webservice.Axis.AxisUtils;
import cn.cc.core.http.webservice.del.AxisWebService;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

public class CostmcsClient {

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

    /**
     *
     * @param args
     */
    public static void main(String[] args) {


        /*call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
        call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
        call.addParameter("in2", XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
        parameter[0]="";
        parameter[1]="雅玛多国际物流有限公司上海分公司";
        parameter[1]="万泽润";
        parameter[2]="404045-00772202036";
        parameter[2]="00010002";*/

        callCRM();

    }
    /**
     *
     * 没有客户代码所以默认传""
     * @return
     */
    public static Map<String,String> callCRM(){
        //LOGGER.debug("EcAxisWebService-----------callCRM------start---name:"+name+",accNo:"+accNo);

        Map<String,String> map = new HashMap<String, String>();
        String result = AxisUtils.callService(endpoint,namespaceURI,useMethod,parametersName,new Object[]{"","刘昌明","6230582000002488610"});
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
