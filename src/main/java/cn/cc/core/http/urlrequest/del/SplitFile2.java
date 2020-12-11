package cn.cc.core.http.urlrequest.del;

import java.io.*;
import java.util.Date;

public class SplitFile2 {

    public static void main(String[] args) {
/*
        // 多线程之间用来处理 通信的问题吧大概, 假如下载单个视频，多线程分段下载也行，那没有这个问题
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("","");
        URL url = null;
        try {
            for(int i=0;i<2;i++) {
                //url = new URL("http://tiebapic.baidu.com/forum/pic/item/3074c813632762d053b206c7b7ec08fa503dc657.jpg");
                // 这个链接是可以的
                // 7877
                url = new URL("https://www.baidu.com/img/bd_logo1.png");
                //2, 打开连接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                //conn = Header.getMap(conn);

                System.out.println("--------------------");
                if(i==0){
                    // 以下请求方式确实可以实现分段下载，但是为什么gif这个不行就不知道了
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Range", "bytes=0-7000");
                    //conn = Header.getMap(conn);
                    System.out.println(conn.getResponseCode());// 206
                    // 不同的 网址返回的或许不同，
                    // 测试拆分
                    // 2592000
                    file(conn.getInputStream(), "E:\\linshi\\", 1+".png");
                }
                if(i==1){
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Range", "bytes=7001-7877");
                    //conn = Header.getMap(conn);
                    System.out.println(conn.getResponseCode());
                    // 不同的 网址返回的或许不同，
                    // 测试拆分
                    // 2592000
                    file(conn.getInputStream(), "E:\\linshi\\", 2+".png");
                }
            }
            // 然后合并两个文件
            File file0 = new File("E:\\linshi\\1.png");
            File file1 = new File("E:\\linshi\\2.png");

            file(new FileInputStream(file0), "E:\\linshi\\", 3+".png");
            file(new FileInputStream(file1), "E:\\linshi\\", 3+".png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            file(new FileInputStream("E:\\test\\github\\视频\\19286458_陈翔六点半\\10000000aid329651628_cid237925043_陈翔六点半：就你这种人，也配暗恋我？_完整暧昧短信.flv"), "E:\\linshi\\", 3+".flv");
            file(new FileInputStream("E:\\test\\github\\视频\\19286458_陈翔六点半\\20000001aid329651628_cid237925043_陈翔六点半：就你这种人，也配暗恋我？_完整暧昧短信.flv"), "E:\\linshi\\", 3+".flv");
            file(new FileInputStream("E:\\test\\github\\视频\\19286458_陈翔六点半\\30000002aid329651628_cid237925043_陈翔六点半：就你这种人，也配暗恋我？_完整暧昧短信.flv"), "E:\\linshi\\", 3+".flv");
            file(new FileInputStream("E:\\test\\github\\视频\\19286458_陈翔六点半\\40000003aid329651628_cid237925043_陈翔六点半：就你这种人，也配暗恋我？_完整暧昧短信.flv"), "E:\\linshi\\", 3+".flv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DownLoadFile 利用这个来操作直接把输出流转给前端

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
       /* if(file.exists()){
            System.out.println( "文件已经存在:" + filePath + fileName );
            return;
        }*/
        System.out.println("开始下载");
        FileOutputStream fo = new FileOutputStream(file,true);// 这个在文件末尾追加，可以拼凑完整的文件
        // 那么问题，怎么发送文件流，也拆分成类似的文件，可以提前吧文件拆分好，放到隔壁的文件夹，请求时按顺序拿，类似m3u8的做法

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
