package cn.cc.utils.math.basenum;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Base64Url {
    /**
     * 将base64编码字符串转换为图片
     * @param imgStr: base64编码字符串
     * @param path:   图片路径-具体到文件
     * @return
     */
    public static boolean getImage(String imgStr, String path){
        if (imgStr == null){
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @return
     * 需要注意的是，一般插件返回的base64编码的字符串都是有一个前缀的:"data:image/jpeg;base64," , 解码之前这个得去掉。
     */
    public static String getbase64Url(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        String fileName="base64.png";


        String strImg = getbase64Url("D:\\test\\invoice\\"+"base64.png");
        Map<String,String> map=new HashMap<String,String>();
        map.put("imageStreamStr",strImg);
        map.put("sessionID","122701");
        map.put("userCode","suixueyan");
        map.put("imageName",fileName);

        list.add(JSONObject.fromObject(map).toString());
        System.out.println(JSONObject.fromObject(map).toString());
        //System.out.println(strImg);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String sd = sdf.format(new Date(System.currentTimeMillis())); // 时间戳转换日期

        //System.out.println("时间:"+sd);

        try {
            File f = new File("D:\\test\\invoice\\invoice"+fileName+sd+".txt");
            FileWriter  fw=new FileWriter (f);
            BufferedWriter out = new BufferedWriter(fw);

            System.out.println("JSONArray.fromObject(list).toString():"+JSONArray.fromObject(list).toString());

            //System.out.println(JSONObject.fromObject(list).toString().length());

            out.write(JSONArray.fromObject(list).toString(), 0, JSONArray.fromObject(list).toString().length());
            out.close();
        }catch (Exception e){
            //return false;
        }
        //boolean b = getImage(strImg, "C:/Users/admin/Desktop/图片/桌面壁纸/2.jpg");
        //System.out.println("==========================================================");
        //System.out.println(b);
    }
}

