package cc.advanced.web.http.use.website.txt;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TXT_Bdsearch {

    // http://webtools3.skf.com/engcalc/CalcBearingFrequencies.do

    public static void main(String[] args) {
        Map<Integer,String[]> map = search("11206 TN9");
        for(Map.Entry entry:map.entrySet()){
            System.out.println(entry.getKey());
            String[] strAry = (String[])entry.getValue();
            System.out.println(strAry[0]+","+strAry[1]+","+strAry[2]+",");
        }
    }
    public static Map<Integer,String[]> search(String searchWord) {
         // String searchWord = "11206 TN9";
        // 空格变加号
        String key = "http://webtools3.skf.com/bdsearch/bdsearch?designation=" + searchWord.replace(" ","+") + "&key=freqcalc&action=search";
        try {
            Document document = Jsoup.parse(new URL(key).openStream(),"gbk",key);// 转换为Dom树
            //
            Elements elements  = document.getElementsByTag("a");
            // 数据返回1
            System.out.println(elements.toString());
            String resultAry[] = matchupdateParent(elements.toString(),searchWord);

            // 第二个表单提交
            String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.34 Safari/537.36 Edg/83.0.478.25";
            String Cookie = "JSESSIONID=207A75A7465FDA764596C944EA268418; engcalc_disclaimer=yes; _ga=GA1.2.1425813029.1604377542; _gid=GA1.2.454562052.1604377542";
            Connection connection = Jsoup.connect("http://webtools3.skf.com/engcalc/CalcBearingFrequencies.do")
                    .cookie("Cookie",Cookie)
                    .userAgent(userAgent)
                    .method(Connection.Method.POST)
                    .data("measurementSystem","metric")
                    //.data("bearingType","ACBB")
                    .data("bearingType",resultAry[1])
                    //.data("pitchDiameter","20")
                    .data("pitchDiameter",resultAry[3])
                    //.data("rollingElementDiameter","4.763")
                    .data("rollingElementDiameter",resultAry[4])
                    //.data("nrOfRollingElements","8")
                    .data("nrOfRollingElements",resultAry[5])
                    //.data("contactAngle","30.0")
                    .data("contactAngle",resultAry[6])
                    .data("rotationSpeed","3000") //
                    .data("rotatingRing","inner")
                    //.data("designation","5200 A-2RS1TN9")
                    .data("designation",searchWord)
                    .data("calculateAction","calculate");
            String response = connection.execute().body();
            // System.out.println( response );
            String responseAry[] = response.split("\r\n");

            //function changeResult(resultFormat)
            // System.out.println(responseAry.length);
            int functionLocation = -1;
            for (int i=0;i<responseAry.length;i++) {
                if(responseAry[i].contains("function changeResult(resultFormat)")){
                    functionLocation = i;
                }
            }
            //System.out.println(responseAry[59].split("\r\n").length);
            return matchDate(responseAry[functionLocation]);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] matchupdateParent(String aHtml,String searchWord) {
        System.out.println(searchWord);
        String regex = "onclick=\"updateParent\\('" + searchWord + "',.{0,200}'\\)\"" ;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(aHtml);
        //是否匹配到了 , 只能匹配一个
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            String result = matcher.group(0);
            String resultAry[] = result.substring(22, result.length() - 2).split(", ");
            for(int i =0;i<resultAry.length;i++){
                resultAry[i] = resultAry[i].substring(1,resultAry[i].length()-1);
                System.out.println(i+","+resultAry[i]);
            }
            return resultAry;
        }
        return null;
    }

    public static Map<Integer,String[]> matchDate(String html){
        Map<Integer,String[]> map = new HashMap<>();
        String regex[] = {
                "shaftSpeed.innerHTML = \".{0,20}\".{0,10};"
                ,"innerRaceDefect.innerHTML = \".{0,20}\".{0,10};"
                ,"outerRaceDefect.innerHTML = \".{0,20}\".{0,10};"
                ,"cageDefect.innerHTML = \".{0,20}\".{0,10};"
                ,"ballSpin.innerHTML = \".{0,20}\".{0,10};"
                ,"rollingElementDefect.innerHTML = \".{0,20}\".{0,10};"};
        for(int i =0 ; i<regex.length ; i++){
            String[] matchResult = new String[3];
            int j = 0;
            Pattern pattern = Pattern.compile(regex[i]);
            Matcher matcher = pattern.matcher(html);
            //是否匹配到了
            System.out.print(i+"---");
            while (matcher.find()) {
                matchResult[j] = matcher.group(0);
                matchResult[j] = matchResult[j].substring(matchResult[j].indexOf("\"")+1,matchResult[j].lastIndexOf("\""));
                System.out.print(matchResult[j]+" ");
                j++;
            }
            // System.out.println(i+","+matchResult.length);
            System.out.println();
            map.put(i,matchResult);
        }
            return map;
    };
}
