package cc.webapi.baidu.ocr;

import cc.constant.ConstantFile;
import net.sf.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 获取token类
 */
public class AuthService {

    public static void main(String[] args) {
        System.out.println(getAuth());
        //24.2e4250ae0a8f7a0bc045441824a909c9.2592000.1578727349.282335-18008731
    }
    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        /**ID KEY   Secret
         * 18008731
         * VM9IzWPb6Qn73ZTm7eG8kk79
         * eNVEAVe0B6ag7g9rZkDgpjyZX4sjduCE
         */
        // 官网获取的 API Key 更新为你注册的
        String clientId = "VM9IzWPb6Qn73ZTm7eG8kk79";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "eNVEAVe0B6ag7g9rZkDgpjyZX4sjduCE";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 2019:12:12
     * 30天
     */
    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            /**
             * 保存token信息
             */
            saveToken(result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    /**
     * 保存token信息
     * @param line
     * @throws Exception
     */
    public static void saveToken(String line) throws Exception{
        File f = new File(ConstantFile.javaFilePath + "/test/BaiduOCR/token.txt");
        FileWriter fw=new FileWriter (f);
        BufferedWriter out = new BufferedWriter(fw);
        out.write(line, 0, line.length());
        out.close();
    }

}