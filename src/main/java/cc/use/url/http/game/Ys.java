package cc.use.url.http.game;

import cc.resource.PropertiesHeader;
import cc.advanced.web.http.utils.HttpURLConnectionUtil;
import cc.core.io.PrintWriterUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author everforcc 2021-09-10
 */
@Log4j
public class Ys {

    //存每一页的信息 页码加信息
    //private static Map<String,List<YsCardVO>> map = new HashMap<>();
    //private static List<YsCardVO> ysCardVOAllList = new ArrayList<>();
    private static Set<YsCardVO> ysCardVOSet = new HashSet<>();
    private static String temp;
    //private static String urlPattern = "https://hk4e-api.mihoyo.com/event/gacha_info/api/getGachaLog?authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=301&gacha_id=4ae078b55a1609b395ec8119ac842395292580&timestamp=1630453186&lang=zh-cn&device_type=mobile&ext=%7b%22loc%22%3a%7b%22x%22%3a-475.4165344238281%2c%22y%22%3a233.8160400390625%2c%22z%22%3a465.0284423828125%7d%2c%22platform%22%3a%22Android%22%7d&game_version=CNRELAndroid2.1.0_R4272734_S4282242_D4302397&region=cn_gf01&authkey=RQhqUNiOsL8g98Vx0KRD1pFCX8WdINgtSPdjRGPe0t7fRnfm6Anr6rXuCRh8cddPHrH%2bOzx9YVmfPH7hLUIfjxBw5NktPDrzJskI%2fOUZBJqLYEu7%2fhGMh4H5IsmXSUQ3t%2bltWRcnJO8YN%2bNCrnPnsdWmsqXYXlhGdYuwDtJiQhAWgEMaLfqMZXl2ShaOnacO6JBGGrG20zFHZ7xkckVju3jI0MAfRy4GpRNuyrS9Z5Xb8OO6a2Ti117IZJhjVKVCg5g6U8D2slrYHmERxlXTkkXXA%2bfMLNo1HRG9LYFXsP%2fW4dOvEqH2h9SKjwGDPdd4BupWG18jKQ%2fMU6gikJvoq41SErVh9DJyA0frmSGMU1iejGKo%2bsJn0cbnxvyPGmngpaKa4Vmdg4TBzcjWTLEDswfR5gJQ1N5UMJTg74Ku3EUZIFKfeKde2BWbaoDhhA7tAXSImXnkfqge4KKb%2bG7Baihl3ZklvvqCFJMKUTfczWNJFnNEBApasA3HdkRbnAL0WutdR5lJviF7R1WlhWd1iL8W7TSENysXlVwcu0OW4%2bv4NAToa9ocuyl5ZQswH7%2fuOSTw9fEodwiFKOPF%2f54T%2ftCAZYJze3zvoosqDfuFC870Q67q2XvR00CqSJBh13qt9PAxbss4Q8RyMnm2%2bcpwWYeucY0o9nyK9YJUNbew0Hekr5GoaQQK1CQeKeb1Z5OzLSjrdElnMjcjtaMeSWJXIC0V8F1SNfsJ6DxtA2byHScmqEDQfDC%2fT0eB8fjzupLScelDL%2fJYkeastRZj%2fIB7OTzcwnGAZSVVxqPNDzEKEY%2fhHphVu7q8NHbZL98vYnAW6ARso5KvU2qhB9dE5W0ex%2fpzqTa5WGGrkdOnW2M49RYpG%2fEfZ5uFlRX4vwQlVoRJlv1zc7g7lcPRD5bPCWxTm%2bEMAirjVTBi6yejmrEL4DwRCaEcj6OaztStUCknd8x2ELfdSRTPTF5bM8S4LVfalsAxzJEvi%2fBIIzmZAlbOFLp13ZKQpM3PMCVpgcREbxwSfskp9pngfAqtm7isHafMgj8mkqDoFb8bYW4txO4oq2nQ4Jeavby9Lpo23TD1ZMDxuiNZFemVcgDIH33Ky5NOfVx4oSQRxn9QOZKCTUi4M3wV1Qsrik%2fZiLEiwiPWhN5hVZ98nxg%2fmE1Yo8daNN710nYRFA3uYzXIduLEsDQyKRs8J7IM7HlP24BY6u9kl%2fTTGTeq2yjI4ytFf4wVEIeIFVo9Ug9fnDxYKasjTw5YKVNC6722mG2bE2epmWDQM7nJrgs3VP2yo%2bHdkzgMGXE%2bzzgt8VlxGXxrIZ9p5DYoo12v7AY%2bd%2fXw9XMlZs0FlzN7gocAyVCf63rXAHXzXFDA5g%3d%3d&game_biz=hk4e_cn&gacha_type=301&size=6&page=%s&end_id=%s";
    private static String urlPattern = "https://hk4e-api.mihoyo.com/event/gacha_info/api/getGachaLog?authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=301&gacha_id=4ae078b55a1609b395ec8119ac842395292580&timestamp=1630453186&lang=zh-cn&device_type=mobile&ext=%7b%22loc%22%3a%7b%22x%22%3a-475.4165344238281%2c%22y%22%3a233.8160400390625%2c%22z%22%3a465.0284423828125%7d%2c%22platform%22%3a%22Android%22%7d&game_version=CNRELAndroid2.1.0_R4272734_S4282242_D4302397&region=cn_gf01&authkey=RQhqUNiOsL8g98Vx0KRD1pFCX8WdINgtSPdjRGPe0t7fRnfm6Anr6rXuCRh8cddPHrH%2bOzx9YVmfPH7hLUIfjxBw5NktPDrzJskI%2fOUZBJqLYEu7%2fhGMh4H5IsmXSUQ3t%2bltWRcnJO8YN%2bNCrnPnsdWmsqXYXlhGdYuwDtJiQhAWgEMaLfqMZXl2ShaOnacO6JBGGrG20zFHZ7xkckVju3jI0MAfRy4GpRNuyrS9Z5Xb8OO6a2Ti117IZJhjVKVCg5g6U8D2slrYHmERxlXTkkXXA%2bfMLNo1HRG9LYFXsP%2fW4dOvEqH2h9SKjwGDPdd4BupWG18jKQ%2fMU6gikJvoq41SErVh9DJyA0frmSGMU1iejGKo%2bsJn0cbnxvyPGmngpaKa4Vmdg4TBzcjWTLEDswfR5gJQ1N5UMJTg74Ku3EUZIFKfeKde2BWbaoDhhA7tAXSImXnkfqge4KKb%2bG7Baihl3ZklvvqCFJMKUTfczWNJFnNEBApasA3HdkRbnAL0WutdR5lJviF7R1WlhWd1iL8W7TSENysXlVwcu0OW4%2bv4NAToa9ocuyl5ZQswH7%2fuOSTw9fEodwiFKOPF%2f54T%2ftCAZYJze3zvoosqDfuFC870Q67q2XvR00CqSJBh13qt9PAxbss4Q8RyMnm2%2bcpwWYeucY0o9nyK9YJUNbew0Hekr5GoaQQK1CQeKeb1Z5OzLSjrdElnMjcjtaMeSWJXIC0V8F1SNfsJ6DxtA2byHScmqEDQfDC%2fT0eB8fjzupLScelDL%2fJYkeastRZj%2fIB7OTzcwnGAZSVVxqPNDzEKEY%2fhHphVu7q8NHbZL98vYnAW6ARso5KvU2qhB9dE5W0ex%2fpzqTa5WGGrkdOnW2M49RYpG%2fEfZ5uFlRX4vwQlVoRJlv1zc7g7lcPRD5bPCWxTm%2bEMAirjVTBi6yejmrEL4DwRCaEcj6OaztStUCknd8x2ELfdSRTPTF5bM8S4LVfalsAxzJEvi%2fBIIzmZAlbOFLp13ZKQpM3PMCVpgcREbxwSfskp9pngfAqtm7isHafMgj8mkqDoFb8bYW4txO4oq2nQ4Jeavby9Lpo23TD1ZMDxuiNZFemVcgDIH33Ky5NOfVx4oSQRxn9QOZKCTUi4M3wV1Qsrik%2fZiLEiwiPWhN5hVZ98nxg%2fmE1Yo8daNN710nYRFA3uYzXIduLEsDQyKRs8J7IM7HlP24BY6u9kl%2fTTGTeq2yjI4ytFf4wVEIeIFVo9Ug9fnDxYKasjTw5YKVNC6722mG2bE2epmWDQM7nJrgs3VP2yo%2bHdkzgMGXE%2bzzgt8VlxGXxrIZ9p5DYoo12v7AY%2bd%2fXw9XMlZs0FlzN7gocAyVCf63rXAHXzXFDA5g%3d%3d&game_biz=hk4e_cn&gacha_type=301&size=6&page=";
    private static int page = 0;
    private static String end_id = "0";
    private static boolean flag = true;
    private static String uid = "";

    // 四星
    private static List<Integer> analyseFour = new ArrayList<>();
    // 五星
    private static List<Integer> analyseFive = new ArrayList<>();

    //分析详情
    private static void inputUrl(String url){
        // 拿到链接，后取?出后面的参数
        // 然后替换

        // GET
        // 追加参数，两个固定，一个页码变化，一个 end_id
        while (flag) {
            // 格式化url
            url = parseUrl(page,end_id);
            // 请求json
            String json = getJSON(url);
            // 输出json
            System.out.println(json);
            // 保存，为下一步准备数据
            getCardVO(json);
            page++;
        }
        // 跑完最后保存
        System.out.println(ysCardVOSet.size());
        saveMap(ysCardVOSet);
    }

    private static String parseUrl(int page,String id){
        // 由于格式问题只能手动拼接 %s%s 否则需要转义，比较麻烦
        String url = urlPattern + page + "&end_id=" + id;
        return url;//String.format(urlPattern,page,id);
    }

    /*public static String getJSON(){
        String json = Properties.ysCard();
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject.getJSONObject("data").getString("list");
    }*/

    public static String getJSON(String url){
//        String json = Properties.ysCard();
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        return jsonObject.getJSONObject("data").getString("list");

        // 随机数
        // 默认写出数据
        String result = HttpURLConnectionUtil.flow(url,"GET","UTF-8", PropertiesHeader.ysMap());

        //System.out.println(result);
        log.info(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.getJSONObject("data").getString("list");

    }

    public static void getCardVO(String json){
          if("[]".equals(json)){// ?
              flag = false;
              return;
          }
        // 两种方式都可以推出，不过下面的可以记录数据
//        if(json.equals(temp)){
//            return;
//        }
//        temp = json;

        //System.out.println(json);
        List<YsCardVO> ysCardVOList = JSON.parseArray(json,YsCardVO.class);
        //map.put(page,ysCardVOList);
        //ysCardVOList.forEach(System.out::println);

        for(YsCardVO ysCardVO:ysCardVOList){
            end_id = ysCardVO.getId();
            if("".equals(uid)){
                uid = ysCardVO.getUid();
            }
            if(!ysCardVOSet.add(ysCardVO)){
                flag = false;
                return;
            }
        }
        // 写到map里面，如果已经有了就break,然后把map存到文件
    }

    public static void saveMap(Set<YsCardVO> ysCardVOSet){
        System.out.println(JSONArray.toJSONString(ysCardVOSet));

        // uid
        PrintWriterUtils.fileWriter("temp", uid + ".json", JSONArray.toJSONString(ysCardVOSet));

    }

    public static void analyse(String uid){
        try {
            String jsonAry = PrintWriterUtils.fileReader("temp/" + uid + ".json");
            System.out.println(jsonAry);
            List<YsCardVO> ysCardVOList = JSON.parseArray(jsonAry,YsCardVO.class);

            List<YsCardVO> ysCardVOListOrderByTime = ysCardVOList.stream()
                    .sorted(Comparator.comparing(YsCardVO::getTime)) // .reversed()降序
                    .collect(Collectors.toList());
            // ysCardVOListOrderByTime.forEach(System.out::println);

            int four = 0;
            int five = 0;

            for(YsCardVO ysCardVO:ysCardVOListOrderByTime){
                four++;
                five++;
                if("4".equals(ysCardVO.getRank_type())){
                    analyseFour.add(four);
                    four = 0;
                }else if("5".equals(ysCardVO.getRank_type())){
                    analyseFive.add(five);
                    five = 0;
                }
            }

            System.out.println(" 结果 >>> ");
            System.out.println(" 四星出货 " + analyseFour.toString());
            System.out.println(" 五星出货 " + analyseFive.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        String url = "";
//        inputUrl(url);
        //System.out.println(parseUrl(page, end_id));
        analyse("101606716");
    }

}
