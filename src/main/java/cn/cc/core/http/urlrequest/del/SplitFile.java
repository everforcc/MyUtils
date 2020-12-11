package cn.cc.core.http.urlrequest.del;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class SplitFile {

    public static void main(String[] args) {
        URL url = null;
        try {
            for(int i=0;i<3;i++) {
                url = new URL("http://tiebapic.baidu.com/forum/pic/item/3074c813632762d053b206c7b7ec08fa503dc657.jpg");
                //2, 打开连接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                //conn = Header.getMap(conn);

                System.out.println("--------------------");
                if(i==0){
                    conn.setRequestProperty("Accept-Encoding", "identity");
                    //conn.setRequestProperty("Range", "bytes=0-1000000");
                    Map headers = conn.getHeaderFields();
                    Set<String> keys = headers.keySet();
                    for (String key : keys) {
                        String val = conn.getHeaderField(key);
                        System.out.println(key + "    " + val);
                    }
                    System.out.println("--------------------:" + conn.getContentLength());
                    // 不同的 网址返回的或许不同，
                    // 测试拆分
                    System.out.println(conn.getHeaderField("Access-Control-Max-Age"));
                    // 2592000
                    file(conn.getInputStream(), "E:\\linshi", i+"3.jpg");
                }
                if(i==3){
                    conn.setRequestProperty("Range", "bytes=1000000-2000000");
                    Map headers = conn.getHeaderFields();
                    Set<String> keys = headers.keySet();
                    for (String key : keys) {
                        String val = conn.getHeaderField(key);
                        System.out.println(key + "    " + val);
                    }
                    System.out.println("--------------------");
                    // 不同的 网址返回的或许不同，
                    // 测试拆分
                    System.out.println(conn.getHeaderField("Access-Control-Max-Age"));
                    // 2592000
                    file(conn.getInputStream(), "E:\\linshi", i+".jpg");
                }
                if(i==4){
                    conn.setRequestProperty("Range", "bytes=2000000-2592000");
                    Map headers = conn.getHeaderFields();
                    Set<String> keys = headers.keySet();
                    for (String key : keys) {
                        String val = conn.getHeaderField(key);
                        System.out.println(key + "    " + val);
                    }
                    System.out.println("--------------------");
                    // 不同的 网址返回的或许不同，
                    // 测试拆分
                    System.out.println(conn.getHeaderField("Access-Control-Max-Age"));
                    // 2592000
                    file(conn.getInputStream(), "E:\\linshi", i+".jpg");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 用来下载文件
     * @param in
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    private static void file(InputStream in, String filePath, String fileName)throws Exception{
        // 总时间
        Date begindate = new Date();

        /**
         * 创建文件夹和文件名
         */
        File saveFile = new File(filePath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        File file = new File(filePath+fileName);
        if(file.exists()){
            System.out.println( "文件已经存在:" + filePath + fileName );
            return;
        }
        System.out.println("开始下载");
        FileOutputStream fo = new FileOutputStream(file);

        /**
         * 以流的方式进行下载
         */
        byte[] buf = new byte[1024];
        int length = 0;
        while ((length = in.read(buf, 0, buf.length)) != -1) {
            fo.write(buf, 0, length);
        }
        in.close();
        fo.close();
        System.out.println(filePath+fileName + "下载完成");
        /**
         * 计算下载所用时间
         */
        Date enddate = new Date();
        double time = enddate.getTime() - begindate.getTime();
        System.out.println("耗时：" + time / 1000 + "s");
    }

}
