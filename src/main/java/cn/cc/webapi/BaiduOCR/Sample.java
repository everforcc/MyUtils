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
        String image = "D:\\test\\OCRmodel\\20200611.jpg";
        JSONObject res = client.basicGeneral(image, options);
        content = res.toString(2);

        //System.out.println(content);

        getJSON(content);

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
        System.out.println(res.toString(2));

        sample(client);
        //getJSON(a);

    }
    static String a="{\n" +
            "  \"log_id\": 7759012966359207704,\n" +
            "  \"words_result\": [\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.827159,\n" +
            "        \"min\": 0.492782,\n" +
            "        \"variance\": 0.034832\n" +
            "      },\n" +
            "      \"words\": \"99S4295399G\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.998917,\n" +
            "        \"min\": 0.997982,\n" +
            "        \"variance\": 1.0E-6\n" +
            "      },\n" +
            "      \"words\": \"存根\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.5825,\n" +
            "        \"min\": 0.353054,\n" +
            "        \"variance\": 0.033374\n" +
            "      },\n" +
            "      \"words\": \"15*B9らロ*00\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.922019,\n" +
            "        \"min\": 0.767528,\n" +
            "        \"variance\": 0.008182\n" +
            "      },\n" +
            "      \"words\": \"上海方利\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.822455,\n" +
            "        \"min\": 0.473947,\n" +
            "        \"variance\": 0.060733\n" +
            "      },\n" +
            "      \"words\": \"在发票\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.851535,\n" +
            "        \"min\": 0.555812,\n" +
            "        \"variance\": 0.043726\n" +
            "      },\n" +
            "      \"words\": \"发票4\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.999579,\n" +
            "        \"min\": 0.998263,\n" +
            "        \"variance\": 0\n" +
            "      },\n" +
            "      \"words\": \"131001861629\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.955138,\n" +
            "        \"min\": 0.53254,\n" +
            "        \"variance\": 0.015127\n" +
            "      },\n" +
            "      \"words\": \"发票号吗:02159628\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.952097,\n" +
            "        \"min\": 0.893764,\n" +
            "        \"variance\": 0.002253\n" +
            "      },\n" +
            "      \"words\": \"统社会代\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.86209,\n" +
            "        \"min\": 0.399262,\n" +
            "        \"variance\": 0.024824\n" +
            "      },\n" +
            "      \"words\": \"91319874319649\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.867188,\n" +
            "        \"min\": 0.489104,\n" +
            "        \"variance\": 0.027478\n" +
            "      },\n" +
            "      \"words\": \"监督电话6454\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.904516,\n" +
            "        \"min\": 0.686592,\n" +
            "        \"variance\": 0.012536\n" +
            "      },\n" +
            "      \"words\": \"地共和新路5252\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.808311,\n" +
            "        \"min\": 0.565926,\n" +
            "        \"variance\": 0.035685\n" +
            "      },\n" +
            "      \"words\": \"参雪用章\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.810844,\n" +
            "        \"min\": 0.490401,\n" +
            "        \"variance\": 0.033744\n" +
            "      },\n" +
            "      \"words\": \"车DR3018\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.960231,\n" +
            "        \"min\": 0.960231,\n" +
            "        \"variance\": 0\n" +
            "      },\n" +
            "      \"words\": \"证\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.897306,\n" +
            "        \"min\": 0.496142,\n" +
            "        \"variance\": 0.028209\n" +
            "      },\n" +
            "      \"words\": \"3列47677\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.616363,\n" +
            "        \"min\": 0.452059,\n" +
            "        \"variance\": 0.02476\n" +
            "      },\n" +
            "      \"words\": \"0い18:86\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.998634,\n" +
            "        \"min\": 0.998634,\n" +
            "        \"variance\": 0\n" +
            "      },\n" +
            "      \"words\": \"效\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.754878,\n" +
            "        \"min\": 0.668779,\n" +
            "        \"variance\": 0.007413\n" +
            "      },\n" +
            "      \"words\": \":3\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.999224,\n" +
            "        \"min\": 0.99871,\n" +
            "        \"variance\": 0\n" +
            "      },\n" +
            "      \"words\": \"单价\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.832584,\n" +
            "        \"min\": 0.533058,\n" +
            "        \"variance\": 0.044906\n" +
            "      },\n" +
            "      \"words\": \"259\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.597925,\n" +
            "        \"min\": 0.445746,\n" +
            "        \"variance\": 0.007844\n" +
            "      },\n" +
            "      \"words\": \"四形常不kn\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.893955,\n" +
            "        \"min\": 0.893955,\n" +
            "        \"variance\": 0\n" +
            "      },\n" +
            "      \"words\": \"候\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.81371,\n" +
            "        \"min\": 0.520424,\n" +
            "        \"variance\": 0.036558\n" +
            "      },\n" +
            "      \"words\": \"819,24\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.672826,\n" +
            "        \"min\": 0.398564,\n" +
            "        \"variance\": 0.052471\n" +
            "      },\n" +
            "      \"words\": \"金領37,90式\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"probability\": {\n" +
            "        \"average\": 0.899177,\n" +
            "        \"min\": 0.807902,\n" +
            "        \"variance\": 0.008331\n" +
            "      },\n" +
            "      \"words\": \"余额\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"words_result_num\": 26,\n" +
            "  \"language\": -1,\n" +
            "  \"direction\": 0\n" +
            "}";
}