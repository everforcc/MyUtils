package cn.cc.core.web.http.use;

import cn.cc.core.web.http.utils.HttpURLConnectionUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Yukino
 * 2020/5/17
 */
public class Picture_costmcs {

    // 模拟表单提交

    public static void main(String[] args) {
        testUploadImage();
    }

    /**
     * 测试上传图片
     *
     */
    public static void testUploadImage(){
        String url = "http://localhost:7007/invoice/queryWaitEditData";
        String fileName = "D:\\1.中科软\\1.费控系统\\发票文件\\1.jpg";
        Map<String, String> textMap = new HashMap<String, String>();
        //可以设置多个input的name，value
        //textMap.put("name", "testname");
        textMap.put("type", "2");
        //设置file的name，路径
        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap.put("upfile", fileName);
        String contentType = "";//image/png
        String ret = HttpURLConnectionUtil.formUpload(url, textMap, fileMap,contentType);
        System.out.println(ret);
        //{"status":"0","message":"add succeed","baking_url":"group1\/M00\/00\/A8\/CgACJ1Zo-LuAN207AAQA3nlGY5k151.png"}
    }

}
