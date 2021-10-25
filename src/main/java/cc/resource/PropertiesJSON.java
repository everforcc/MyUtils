package cc.resource;

import java.io.*;

public class PropertiesJSON {

    // 配置文件根目录
    private static String path = "/json/";
    public static void main(String[] args) {
        System.out.println(ysCard());
    }
    private static String  getJSON(String resource){
        try {
            InputStream in = PropertiesJSON.class.getResource(path + resource).openStream();

            StringBuilder builder = new StringBuilder();

            String readLine = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while((readLine = br.readLine()) != null){
                builder.append(readLine);
            }

            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
    }

    public static String ysCard(){
        String fileName = "/ys/YsCard.json";
        return getJSON(fileName);
    }

    public static String fastjson(){
        String fileName = "/code/fastjson.json";
        return getJSON(fileName);
    }

    public static String jsonPath(){
        String fileName = "/jsonpath/jsonpath.json";
        return getJSON(fileName);
    }

    public static String jpexample(){
        String fileName = "/jsonpath/jp-example.json";
        return getJSON(fileName);
    }

}
