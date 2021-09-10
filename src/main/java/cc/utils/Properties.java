package cc.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Properties {

    // 配置文件根目录
    private static String path = "/json/";
    public static void main(String[] args) {
        System.out.println(ysCard());
    }
    private static String  getJSON(String resource){
        try {
            InputStream in = Properties.class.getResource(resource).openStream();

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
        String fileName = "YsCard.json";
        return getJSON(path + fileName);
    }

}
