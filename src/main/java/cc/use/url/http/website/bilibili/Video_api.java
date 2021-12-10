package cc.use.url.http.website.bilibili;

import cc.constant.ConstantCharSet;
import cc.resource.PropertiesHeader;
import cc.advanced.web.http.utils.HttpURLConnectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

/**
 * @author c.c.
 * @date 2021/1/7
 */
public class Video_api {

    public static void main(String[] args) {
        t1();
    }

    static void t1(){
        // https://www.bilibili.com/video/BV1QA411G78i?spm_id_from=333.851.b_7265636f6d6d656e64.3
        String l = "https://api.mwx.mx/video/BV1QA411G78i/";
        //String u = "http://upos-sz-mirrorcos.bilivideo.com/upgcxcode/68/15/275911568/275911568-1-208.mp4?e=ig8euxZM2rNcNbhjnWdVhwdlhzTHhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&uipk=5&nbs=1&deadline=1610020124&gen=playurl&os=akam&oi=1736557629&trid=3d01fc2d5c574011a133705076e0b83eT&platform=html5&upsig=5b898c0e941dad8d757264a627540b0d&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&hdnts=exp=1610020124~hmac=304cd74361a8cbe8700f794a03b768ec5505f1437f1d1f4ba803ce749de9a4f9&mid=0&orderid=0,1&logo=80000000";

        String result_count = HttpURLConnectionUtil.flow(l,"GET", ConstantCharSet.UTF_8, PropertiesHeader.bilibiliMap());

        System.out.println(result_count);

        //String url = "https://api.mwx.mx/video/BV14T4y1T7Wk/";

    }

    static void t2(){
        //String url = "https://api.bilibili.com/x/player/playurl?cid=2239646&bvid=BV1Hx411K7AY&aid=1490329&fourk=1&platform=html5&high_quality=1&qn=120";
        //String result = HttpURLConnectionUtil.sendToUrlRequest(url,"GET", ConstantCharSet.UTF_8, Header.bilibiliMap());
        String result = "{\"code\":0,\"message\":\"0\",\"ttl\":1,\"data\":{\"from\":\"local\",\"result\":\"suee\",\"message\":\"\",\"quality\":208,\"format\":\"mp4\",\"timelength\":370451,\"accept_format\":\"mp4\",\"accept_description\":[\"高清 1080P\"],\"accept_quality\":[208],\"video_codecid\":7,\"seek_param\":\"start\",\"seek_type\":\"second\",\"durl\":[{\"order\":1,\"length\":370451,\"size\":55501663,\"ahead\":\"\",\"vhead\":\"\",\"url\":\"https://upos-sz-mirrorcos.bilivideo.com/upgcxcode/46/96/2239646/2239646-1-208.mp4?e=ig8euxZM2rNcNbRMnWdVhwdlhWKHhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=\\u0026uipk=5\\u0026nbs=1\\u0026deadline=1610426980\\u0026gen=playurl\\u0026os=cosbv\\u0026oi=3524704618\\u0026trid=0f87a92a69e342e689da0d0e7cf62f45T\\u0026platform=html5\\u0026upsig=472fc16bf3a4132792a9c208107a42d7\\u0026uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform\\u0026mid=58572396\\u0026orderid=0,1\\u0026logo=80000000\",\"backup_url\":null}],\"support_formats\":[{\"quality\":208,\"format\":\"mp4\",\"new_description\":\"1080P 高清\",\"display_desc\":\"1080P\",\"superscript\":\"\"}]}}";
        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result);

        // jsonpath
        DocumentContext documentContext = JsonPath.parse(result);
        String string = documentContext.read("$.data.durl[0].url");

        System.out.println(string);
    }

}
