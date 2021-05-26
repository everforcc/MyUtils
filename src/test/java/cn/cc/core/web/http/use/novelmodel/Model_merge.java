package cn.cc.core.web.http.use.novelmodel;

import cn.cc.core.io.utils.PrintWriterUtils;
import cn.cc.use.url.http.novelmodel.flow.CrawFlow2;
import cn.cc.use.url.http.novelmodel.model.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author c.c.
 * @date 2021/2/3
 */
public class Model_merge {

    // 链接问题

    // 加载json，初始化列表，然后搜索
    @Test
    void init(){
        try {
            search(initJSON(),"斗破");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static List<RootModel> initJSON(){
        List<RootModel> rootModelList = new ArrayList<RootModel>();
        File file = new File("src/main/resources/json");
        File[] files = file.listFiles();
        if(files!=null&&files.length>0){
            for(File f:files){
                String json = getJSON(f.getAbsolutePath());

                RootModel rootModel = (RootModel)JSON.parse(json);
                rootModelList.add(rootModel);
                //System.out.println("已有json列表:" + rootModel.getSearchUrl());
            }
        }else {
            System.err.println("请配置网站json");
        }
        rootModelList.forEach(System.out::println);
        return rootModelList;
    }

    void search(List<RootModel> rootModelList,String like) throws UnsupportedEncodingException {
        for(RootModel rootModel:rootModelList){

            System.out.println(" >>> " + rootModel.getSearchUrl());
            List<String> stringList = null;
            try {
                stringList = CrawFlow2.search(like,rootModel);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for(String s:stringList){
                System.out.println(s);
            }
        }

    }

    public static String getJSON(String resource){

        try {
            // 静态方法和非晶态方法不同
            return PrintWriterUtils.fileReader(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
