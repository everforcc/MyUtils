package cn.cc.core.http.urlrequest.use;

import cn.cc.core.file.utils.ConstantCharSet;
import cn.cc.core.http.urlrequest.header.Header;
import cn.cc.core.http.urlrequest.utils.HttpURLConnectionUtil;
import cn.cc.core.io.utils.PrintWriterUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author c.c.
 * @date 2020/12/13
 */
public class Picture_bilibili_album {

    public static void main(String[] args) {
        flow(postuid);
    }

    private static String fileBase = "E:\\java\\bilibili\\md\\";
    private static String postuid = "632887";
    private static String forCountUrl = "https://api.vc.bilibili.com/link_draw/v1/doc/upload_count?uid=";
    private static String forAllimgUrl =  "https://api.vc.bilibili.com/link_draw/v1/doc/doc_list?uid=%s&page_num=0&biz=all&page_size=%s" ;

    public static void flow(String uid){
        try {

            String result_count = HttpURLConnectionUtil.sendToUrlRequest(forCountUrl + uid,"POST", ConstantCharSet.UTF_8, Header.bilibiliMap());
            String count = albumCount(result_count);
            System.out.println(count);

            String result_url = HttpURLConnectionUtil.sendToUrlRequest(String.format(forAllimgUrl,uid,count),"POST", ConstantCharSet.UTF_8, Header.bilibiliMap());
            System.out.println(result_url);
            allImgUrl(result_url,false);
            //System.out.println(result_url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取相簿总个数
     * @param json
     * @return
     */
    public static String albumCount(String json){
        System.out.println("albumCount:"+json);
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONObject jsonObjectDate = jsonObject.getJSONObject("data");
        String count = jsonObjectDate.getString("all_count");
        return count;
    }

    /**
     * 获取所有照片
     * @param json
     * @return
     * @throws Exception
     */
    public static Map<String,Object> allImgUrl(String json, Boolean down)throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        System.out.println("allImgUrl:"+json);

        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONObject jsonObjectDate = jsonObject.getJSONObject("data");
        JSONArray jsonArray = jsonObjectDate.getJSONArray("items");

        for(int i = 0 ;i<jsonArray.size();i++){
            JSONObject jsonObj = (JSONObject)jsonArray.get(i);
            String doc_id = jsonObj.getString("doc_id");
            String description = jsonObj.getString("description") + "\r\n";
            JSONArray pic_array = jsonObj.getJSONArray("pictures");

            StringBuffer stringBuffer_img = new StringBuffer("");
            String imgTag = "<img src=\"%s\">";
            for(int j = 0 ; j < pic_array.size() ; j++ ){
                JSONObject json_pic = (JSONObject)pic_array.get(j);
                /*if(down) {
                    String urlPath = json_pic.getString("img_src");
                    *//**
                     * 截取网络图片的名字和参数
                     *//*
                    String imageName = urlPath.substring(urlPath.lastIndexOf("/") + 1, urlPath.length());
                    System.out.println("生成文件名:" + imageName);
                    if (imageName.contains("?")) {
                        System.out.println("处理字符串");
                        imageName = imageName.substring(0, imageName.lastIndexOf("@"));
                        System.out.println("再次生成文件名" + imageName);
                    }
                    //调用下载
                    //Method_down.downByUrl(urlPath,"相册\\"+poster_uid + "\\" + jsonObj.getString("doc_id"),imageName);
                }*/
                System.out.println("图片下载地址:"+json_pic.getString("img_src"));
                stringBuffer_img.append(String.format(imgTag,json_pic.getString("img_src")) + "\r\n");
            }

            saveMD(fileBase + postuid ,doc_id + ".md",description + stringBuffer_img.toString());
        }

        return map;
    }

    public static void saveMD(String filePath,String fileName,String content){
        //String formatNameResult = String.format(fileMDFormat,novelName,df.format(i),suffix);
        PrintWriterUtils.fileWriter(filePath,fileName,content + "\r\n");
    }


}
