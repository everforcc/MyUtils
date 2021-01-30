package cn.cc.webapi.BaiduOCR;


import com.baidu.aip.ocr.AipOcr;
import net.sf.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "18008731";
    public static final String API_KEY = "VM9IzWPb6Qn73ZTm7eG8kk79";
    public static final String SECRET_KEY = "eNVEAVe0B6ag7g9rZkDgpjyZX4sjduCE";
    //public static final String SECRET_KEY = "25.ab3843e36e71d4769718e677e2db72f8.315360000.1905475599.282335-18008731";
    public static String content;
    //public static SaveToFile saveToFile=new SaveToFile("","1.txt",content);
    public static void sample(AipOcr client) throws Exception {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");


        // 参数为本地图片路径
        String image = "E:\\java\\test\\OCRmodel\\QQ图片20201222174346.jpg";
        JSONObject res = client.basicGeneral(image, options);
        content = res.toString(2);

        //System.out.println(content);

        System.out.println(getJSON(content));

        /*SaveToFile saveToFile=new SaveToFile("D:\\test\\OCRmodel","2.txt",content);
        saveToFile.save();*/
        // 参数为本地图片二进制数组
        /*byte[] file = readImageFile(image);
        res = client.basicGeneral(file, options);
        System.out.println(res.toString(2));*/


        // 通用文字识别, 图片参数为远程url图片
        /*String url="";
        JSONObject res = client.basicGeneralUrl(url, options);*/
        //System.out.println(res.toString(2));

    }


    public static String getJSON(String string) throws Exception{

        System.out.println(string);

        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(string);
        JSONArray jsonArray = jsonObject.getJSONArray("words_result");
        net.sf.json.JSONObject jsonObject1;
        System.out.println("获得分词数量:"+jsonArray.size());
        StringBuffer stringBuffer=new StringBuffer();
        for(int i=0;i<jsonArray.size();i++){
            jsonObject1 = (net.sf.json.JSONObject)jsonArray.get(i);
            //stringBuffer.append((net.sf.json.JSONObject) ((net.sf.json.JSONObject) jsonArray.get(0)).get("words"));
            stringBuffer.append(jsonObject1.get("words"));
        }
        //System.out.println(stringBuffer.toString());

        return stringBuffer.toString();
    }

    public static void main(String[] args) throws Exception {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // ??? 不懂
        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "/resources/log4j2.properties");

        // 调用接口
        String path = "test.jpg";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        //System.out.println(res.toString(2));

        sample(client);
        //getJSON(a);

    }

}