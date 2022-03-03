package cc.advanced.web.http.use.website.bilibili;

import cc.constant.ConstantFile;
import cc.core.io.base.StreamInputUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author c.c.
 * @date 2020/12/18
 */
public class Video_bilibili {
    public static void main(String[] args) {
        try {
            String movie = "https://cn-ahhf-cmcc-v-06.bilivideo.com/upgcxcode/76/06/221410676/221410676-1-112.flv?expires=1608320643&platform=pc&ssig=Zq9skjWjrjyLeUPq0pk0kw&oi=3083010725&trid=f9c52111c2384bdda0eb60d08db6bc78p&nfc=1&nfb=maPYqpoel5MI3qOUX6YpRA==&cdnid=3005&mid=58572396&orderid=0,3&agrr=0&logo=80000000";
            requestHeard_downFlv(movie);
            //requestHeard_downFlv();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 使用apatch框架处理持久化问题
    // 媒体元素还是要使用流
    public static void t4(String url)throws Exception{
        // 创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // HttpGet httpGet = new HttpGet("http://www.csdn.net/");
        // 都是 image 类型

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", " */*");
        httpGet.setHeader("Accept-Encoding", " gzip, deflate, br"); // 视频这个没发现问题就先这样吧
        httpGet.setHeader("Accept-Language", " zh-CN,zh;q=0.9");
        httpGet.setHeader("Connection", " keep-alive");
        httpGet.setHeader("Host", " upos-sz-mirrorcos.bilivideo.com");
        httpGet.setHeader("Referer", "https://www.bilibili.com/video/BV1Hy4y1i71y");
        httpGet.setHeader("X-Requested-With", " ShockwaveFlash/29.0.0.171");
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);// 执行 http get请求

        HttpEntity httpEntity = response.getEntity(); // 获取返回实体

        if(httpEntity != null) {
            // 快啊.! 这俩好想没啥区别 // 框架更快
            // FileUtils.copyInputStreamToFile(inputStream,new File(ConstantFile.javaFilePath + "//1.gif"));
            System.out.println(httpEntity.getContent().available());
            StreamInputUtils.streamToFile(httpEntity.getContent(), ConstantFile.L1_javaFilePath + "\\java\\test","3.flv");
            //FileUtils.copyToFile(httpEntity.getContent(),new File(ConstantFile.javaFilePath + "\\java\\test\\2.flv"));
        }
        // buffer 包裹一下更快
        response.close(); // 关闭流
        httpClient.close();
    }



    public static void requestHeard_downFlv(String flvUrl) throws Exception {
        //String flvUrl = "https://upos-sz-mirrorkodo.bilivideo.com/upgcxcode/50/06/267290650/267290650-1-112.flv?e=ig8euxZM2rNcNbRahbhBhwdlhWughzUVhoNvNC8BqJIzNbfqXBvEqxTEto8BTrNvN0GvT90W5JZMkX_YN0MvXg8gNEV4NC8xNEV4N03eN0B5tZlqNxTEto8BTrNvNeZVuJ10Kj_g2UB02J0mN0B5tZlqNCNEto8BTrNvNC7MTX502C8f2jmMQJ6mqF2fka1mqx6gqj0eN0B599M=&uipk=5&nbs=1&deadline=1608311509&gen=playurl&os=kodobv&oi=3083010725&trid=d284386f8b8846e8aec1782c579cb018u&platform=pc&upsig=843d320dd5420c28d4bbf091eed8a543&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=58572396&orderid=0,3&agrr=0&logo=80000000";
        URL url = new URL(flvUrl);
        //2, 打开连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Accept", " */*");
        conn.setRequestProperty("Accept-Encoding", " gzip, deflate, br"); // 视频这个没发现问题就先这样吧
        conn.setRequestProperty("Accept-Language", " zh-CN,zh;q=0.9");
        conn.setRequestProperty("Connection", " keep-alive");
        conn.setRequestProperty("Host", " upos-sz-mirrorcos.bilivideo.com");
        conn.setRequestProperty("Referer", "https://www.bilibili.com/bangumi/play/ep336055?theme=movie&spm_id_from=333.851.b_62696c695f7265706f72745f6d6f766965.21");
        conn.setRequestProperty("X-Requested-With", " ShockwaveFlash/29.0.0.171");
        // 测试 io速度 网址的 缓存写法
        FileUtils.copyToFile(conn.getInputStream(),new File(ConstantFile.L1_javaFilePath + "\\java\\test\\ep336055.flv"));// 23:55
    }

}
