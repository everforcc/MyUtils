package cn.cc.utils.web.jdkservice;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebClient {

    public static void main(String[] args) throws IOException {
        // 第一步：创建服务地址，不是WSDL地址
        URL url = new URL("http://localhost:8080/costmcs/invoiceAuto_service/invoiceAuto_service");
        // 第二步：打开一个通向服务地址的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 第三步：设置参数
        // 3.1发送方式设置：POST必须大写
        connection.setRequestMethod("POST");
        // 3.2设置数据格式：content-type
        connection.setRequestProperty("content-type", "text/xml;charset=UTF-8");
        // 3.3设置输入输出，因为默认新创建的connection没有读写权限，
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("queryInvoiceData");
        // 第四步：组织SOAP数据，发送请求
        String soapXML = getSOAPXML(123);
        OutputStream os = connection.getOutputStream();
        os.write(soapXML.getBytes());
        // 第五步：接收服务端响应，打印
        int responseCode = connection.getResponseCode();
        if (200 == responseCode) {// 表示服务端响应成功
            InputStream is = connection.getInputStream();
            // 将字节流转换为字符流
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            // 使用缓存区
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String temp = null;
            while (null != (temp = br.readLine())) {
                sb.append(temp);
            }
            System.out.println(sb.toString());

            is.close();
            isr.close();
            br.close();
        }

        os.close();
    }

    // 将数据拼接成SOAP需要的格式，这是调用getUserInfoById方法时封装的方法，调用其他方法得另外重新封
    //装
    public static String getSOAPXML(int id){
       /* String soapXML="<?xml version=\"1.0\" ?>"
                +"<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                +"<S:Body><ns2:getUserInfoById xmlns:ns2=\"http://impl.service.server2.dmf.com/\">"
                +"<arg0>"+id+"</arg0></ns2:getUserInfoById></S:Body></S:Envelope>";*/

        String soapXML="";
        return soapXML;
    }

}
