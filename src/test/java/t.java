
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Yukino
 * 2020/5/22
 */
public class t {

    @Test
    void t1(){
        System.out.println(1);

        StringBuffer stringBuffer = new StringBuffer();
        System.out.println(stringBuffer.toString());
        System.out.println(2);
    }

    @Test
    void t2(){
        String sql = " SELECT a.sharecenter,                                  "+
                "        case                                            "+
                "          when sum(a.amountofmoney) > 0 then            "+
                "           0                                            "+
                "          else                                          "+
                "           sum(a.amountofmoney)                         "+
                "        end                                             "+
                "   FROM accpremiumlist a                                "+
                "  where a.yearmonth = '" + "202101" + "'                          "+
                "    and a.t1code = 'VCTP'                               "+
                "  GROUP BY a.sharecenter                                ";

        System.out.println(sql);
    }

    @Test
    void t3(){
        String sql = " SELECT distinct a.itemcode FROM ACCITEMARTICLEBALANCE a    "
                + " where a.yearmonth = '"+ "202101" + "'                         "
                + " and a.sharingmethodcode = '"+ "C001" + "'           "
                + " and a.datafrom = 'M'                                        "
                + " and a.resourcestypecode is null                             ";
        System.out.println(sql);
    }

    @Test
    void t4(){
        String parentUrl = "http://www.yulinzhanye.la/20/20304/508768_3.html";
        String url = "508768_1.html";
        System.out.println(parentUrl.substring(0, parentUrl.lastIndexOf("/") + 1) + url);
    }

    @Test
    void t5(){
        String s = "http://www.paoshuzw.com/7/7877/3597385.html 第一千六百章 对话";

        System.out.println(s.substring(0,s.indexOf(" ")));
        System.out.println(s.substring(s.indexOf(" ") + 1,s.length()));
    }

    @Test
    void t6(){
        String path = "F:\\删除\\视频";
        File file = new File(path);
        System.out.println(file.getParent());
        System.out.println(file.getName());
    }

    @Test
    void t7(){
        Formatter f = new Formatter();
        f.format("%-20s","1");
        System.out.println("|" + f.toString() + "|");

        Formatter f1 = new Formatter();
        f1.format("%-20.20s","中文"); // 0123456789
        System.out.println("|" + f1.toString() + "|");
    }

    @Test
    void t8(){
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    void t9(){
        // FileUtils.copyToFile();
    }

    @Test
    void t10(){
        System.out.println(new Date().getTime());
        long ctime = 1606708854000L;
        Date date = new Date(ctime);
        System.out.println(date);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
        //FileUtils.copyFile()
    }

    @Test
    void t11(){
        String ip = "10.1.1.141";
        if(ip.startsWith("10.1")){
            System.out.println(1);
        }else {
            System.out.println(2);
        }
    }

    @Test
    void t12(){
        String url = "https://osss.moe16.com/image/sl/R15/022/DSCF8274.jpg?x-oss-process=style/ababbb.com";
        String url1= "https://imgg.cos16.com/images/slct/X/045/1%20%282%29.jpg/ababbb.com";

        try {
            URL u = new URL(url1);
            System.out.println(u.getFile());
            System.out.println(u.getPath());
            System.out.println(u.getHost());

            URL u0 = new URL(url);
            System.out.println(u0.getFile());
            System.out.println(u0.getPath());
            System.out.println(u0.getHost());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void t13(){
        List<String> stringList = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
    }

    @Test
    void t14(){
        List<String> stringList = new ArrayList<>();
        stringList.add("a\n");
        stringList.add("b\n");
        stringList.add("c\n");
        System.out.println(stringList.toString());
        //JSON.parse(stringList)
        //JSONObject.parse
        System.out.println(JSONObject.parseObject(stringList.toString()));
    }

    @Test
    void t15(){
        // 创建一个 HashMap
        HashMap<String, Integer> prices = new HashMap<>();

        // 往HashMap中添加映射项
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println("HashMap: " + prices);

        // 计算 Shirt 的值
        int shirtPrice = prices.computeIfAbsent("Shirt", k -> 280);
        System.out.println("Price of Shirt: " + shirtPrice);
        shirtPrice = prices.computeIfAbsent("Shirt", k -> 290);
        System.out.println("Price of Shirt: " + shirtPrice);

        // 输出更新后的HashMap
        System.out.println("Updated HashMap: " + prices);

        HashMap<String, String> msg = new HashMap<>();
        String test = msg.computeIfAbsent("test",k -> "123");
        System.out.println("test:" + test);
        test = msg.computeIfAbsent("test",k -> "321");
        System.out.println("test:" + test);
        //FileUtils.copyInputStreamToFile();
    }

}


