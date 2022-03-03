package cc.advanced.web.http.use.novelmodel;

import cc.advanced.web.http.use.novelmodel.model.*;
import cc.core.io.base.StreamInputUtils;
import cc.resource.PropertiesHeader;
import cc.use.url.http.novelmodel.model.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author c.c.
 * @date 2021/2/4
 */
public class TT {

    static RootModel rootModel = new RootModel();
    static{

        // 根目录
        rootModel.setHeaders(PropertiesHeader.yulinzhanye_searchMap());
        rootModel.setCharset("gbk");
        rootModel.setRootUrl("http://www.yulinzhanye.la");
        rootModel.setSearchUrl("http://www.yulinzhanye.la/s.php");
        // 小说类型


        // 匹配小说的类型地址
        rootModel.setNovelTypeUrlPattern("//div[@class='channel']/a/@href/regex('/fenlei/.{1,10}')");
        TypeModel typeModel = new TypeModel();
        typeModel.setNovelUrlPattern("//div[@class='mod block rank-switch']//div[@class='bd']//ul[1]//a/@href");
        rootModel.setTypeModel(typeModel);

        SearchModel searchModel = new SearchModel();
        searchModel.setSearchType("POST");
        Map<String,String> map = new HashMap<>();
        map.put("objectType","2");
        map.put("type","articlename");
        searchModel.setKeyMap(map);
        searchModel.setSearchKey("s");
        searchModel.setNovelUrlPattern("//div@class='bd']//li[@class='column-2 ']//a[@class='name']/@href");
        rootModel.setSearchModel(searchModel);

        // 章节
        BookModel bookModel = new BookModel();
        bookModel.setMenuUrlPattern("");
        // 待办语法
        //bookModel.setChapterListPattern("//div[@class='mod block update chapter-list']/tag[2]//ul[@class='list']/a/@href");
        bookModel.setChapterListPattern("//div[@class='container']//div[7]//div[@class='bd']//ul[@class='list']//a/@href"); // /ul[@class='list']/a/@href
        //bookModel.setNextChapterListPattern("//a[@class='nextPage']/@href");
        rootModel.setBookModel(bookModel);

        // 内容
        ContentModel contentModel = new ContentModel();
        contentModel.setTitle("//h1[@class='page-title']//text()"); //
        contentModel.setContent("//div[@class='page-content font-large']/p//text()");
            /*contentModel.setTitle("//div[class='container']");
            contentModel.setContent("//div[class='container']");*/
        rootModel.setContentModel(contentModel);



    }

    /**
     * objectType: 2
     type: articlename
     s: (unable to decode value)
     * @throws IOException
     */

    @Test
    void tpost() throws IOException {
        String html = Jsoup.connect("http://www.yulinzhanye.la/s.php").headers(rootModel.getHeaders())
                .method(Connection.Method.POST)
                .timeout(6000)

                .data("objectType","2")
                .data("type","articlename")
                //.data("s","%C5%AE%B6%F9")
                .data("s","%C5%AE%B6%F9")
                .followRedirects(true)
                //.data("s","%C5%AE%B6%F9")
                .execute().charset(rootModel.getCharset()).body();
        Document document = Jsoup.parse(html);
        System.out.println(document);
    }

    @Test
    void encode(){
        try {
            System.out.println(URLEncoder.encode("女儿", "gbk"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    String sendToUrlRequestByJson() throws ClientProtocolException, IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://www.yulinzhanye.la/s.php");

        post.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(post);
        String content = EntityUtils.toString(response.getEntity());
        System.out.println(content);
        return content;
    }

    @Test
    void http(){
        try {
            URL url = new URL("http://www.yulinzhanye.la/s.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            Map<String,String> map = rootModel.getHeaders();
            if(map!=null&&map.size()>0){
                for(Map.Entry entry : map.entrySet()){
                    //System.out.println((String) entry.getKey() + "---" + conn.getRequestProperty((String) entry.getKey()));
                    String key = (String) entry.getKey();
                    if(conn.getRequestProperty(key)==null){
                        String value = (String) entry.getValue();
                        conn.setRequestProperty(key,value);
                        // 大小写有时候不一样
                    /*if("Accept-Encoding".equalsIgnoreCase(key)&&value.contains("gzip")){
                        gzip = true; // 需要解压
                    }*/
                    }
                }
            }
            //3, 设置提交类型
            try {
                conn.setConnectTimeout(60000);
                conn.setRequestMethod("POST");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            //4, 设置允许写出数据,默认是不允许 false
            conn.setDoOutput(true);
            conn.setDoInput(true);//当前的连接可以从服务器读取内容, 默认是true
            //conn.setConnectTimeout(6000);
            // 如果不为空就进去, 没搞懂方式

            //5, 获取向服务器写出数据的流
            OutputStream os = conn.getOutputStream();
            //参数是键值队  , 不以"?"开始
            //os.write(content.getBytes());
            os.write("objectType=2&type=articlename&s=%C5%AE%B6%F9".getBytes());
            os.flush();

            //6, 获取响应的数据
            //得到服务器写回的响应数据
            System.out.println("" + conn.getResponseCode());

            if( 200==conn.getResponseCode() ){
               String returnMsg = StreamInputUtils.streamToStr(conn.getInputStream(),rootModel.getCharset(),false);
               System.out.println(returnMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void tmap(){
        Map<String,String> map = new HashMap<>();
        map.put("a","a");
        map.put("b","b");

    }



}
