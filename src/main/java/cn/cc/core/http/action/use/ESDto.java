package cn.cc.core.http.action.use;

import cn.cc.core.http.action.common.HttpURLConnectionUtil;
import cn.cc.utils.fileio.io.InputStream_IO;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class ESDto {


    public static void main(String[] args) throws Exception {

        String[] nameList={"msic_prpcridecarinfo","msic_prpcmaincargo","lossratio","msic_prpcmainliab",
                "msic_prpcmainconstruct","msic_prpccoins","msic_prpcmainprop",
                "msic_prpccommissiondetail","msic_prpcmain","msic_prpcitem",
                "msic_prpcclaimagent","msic_prpcplan","msic_prpcinsuredidvlist","msic_prpccommission",
                "msic_prpcname","msic_prpcinsured","msic_prpcengage","msic_prpcfee","msic_prpcration",
                "msic_prpcitemkind","msic_prpclimit","msic_prpcitemship","msic_prpcitemcargo",
                "sharerate","msic_prpcaddress"};

        System.out.println(nameList.length);

        // getName();
        for(String classname:nameList){
            getDto(classname);
        }

    }

    static void getName()throws Exception{
        String urlPath="http://127.0.0.1:9200/_cat/indices";
        String str = HttpURLConnectionUtil.sendToUrlRequest(urlPath,"GET","");
        System.out.println(str);
    }


    static void getDto(String classname)throws Exception{
        //String classname="loss_detail";
        //String classname="lossratio_base";
        //String classname="risk_accumulation_marine";
        //String classname="risk_accumulation_no_marine";
        String urlPath="http://localhost:9200/"+classname+"?pretty";
        //http://localhost:9200/msic_prpcaddress
        String str = HttpURLConnectionUtil.sendToUrlRequest(urlPath,"GET","");


        //System.out.println(str);
        JSONObject json = JSONObject.parseObject(str);

        JSONObject dto = json.getJSONObject(classname).getJSONObject("mappings").getJSONObject("properties");


        StringBuffer word=new StringBuffer("");
        word.append("package com.sinosoft.esplat.core.es.entity;");
        word.append("import java.io.Serializable;");
        word.append("import java.util.Date;");
        word.append("import com.sinosoft.esclientrhl.annotation.ESMetaData;");
        String ClassName=classname.substring(0,1).toUpperCase()+classname.substring(1,classname.length());
        word.append("@ESMetaData(indexName = \""+classname+"\",indexType = \""+ClassName+"\", number_of_shards = 5,number_of_replicas = 0)");
        word.append("public class "+ClassName+" implements Serializable  {");
        word.append("private static final long serialVersionUID = 1L;");
        for(Map.Entry<String,Object> entry:dto.entrySet()){
            word.append("private ");
            //System.out.println("value:"+entry.getValue());
            JSONObject jsonObject = (JSONObject)entry.getValue();

            if(jsonObject.getString("type").equals("text")){
                word.append(" String ");
            }else if(jsonObject.getString("type").equals("date")){
                word.append(" Date ");
            }else{
                word.append(" String ");
            }
            word.append(entry.getKey() +";");
            System.out.println(word);

        }
        word.append("}");
        //System.out.println("响应内容为:  " + dto);
        // File file = new File("E:\\es\\");
        InputStream_IO.IO_PrintWriter(new File("E:\\es\\"+ClassName+".java"),word.toString());
    }

}
