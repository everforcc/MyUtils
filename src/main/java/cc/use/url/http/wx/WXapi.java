package cc.use.url.http.wx;

import cc.advanced.webrefactor.http.IHttp;
import cc.advanced.webrefactor.http.impl.HttpURLConnectionImpl;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author everforcc 2021-09-16
 */
public class WXapi {

    private String appid = "";
    private String secret = "";
    private static String accessToken = "49_swYAlx97p7Cz6iWF3W2Y_skySVS_odRp4e9fBlDyfP6E3AsbMCQAIn2QcvX3xP1rB_ms252FlFXJvI6VWeK1ZdwpTOW5y6P8qemSIElUqVIaufexxqfTcgsC8f7dc4cnIX6hDjCmzHX2ajrJAYXaAFAQON";
    private static String content = "{\n" +
            " \"scene\": \"www.baidu.com\"\n" +
            "}";
    private static IHttp iHttp = new HttpURLConnectionImpl();

    public static void main(String[] args) {
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=%s";
        try {
            byte[] bytes = iHttp.requestForFile(String.format(url,accessToken),"POST",content,"C:","1.jpg");
            if(bytes.length<1024){
                System.out.println(new String(bytes));
            }else {
                System.out.println(bytes.length);
            }
            //String json = HttpURLConnectionUtil.sendToUrlRequest(String.format(url,accessToken),"POST",content,"");
            //FileInputStream fileInputStream = new FileInputStream();
            FileOutputStream fo = new FileOutputStream(new File("C://5.jpg"));
            fo.write(bytes,0,bytes.length);
            fo.close();
            System.out.println(new BASE64Encoder().encode(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
