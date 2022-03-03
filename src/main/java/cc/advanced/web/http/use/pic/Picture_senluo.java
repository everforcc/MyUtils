package cc.advanced.web.http.use.pic;

import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * @author c.c.
 * @date 2021/3/15
 */
public class Picture_senluo {

    // https://www.senlo.club/category/jdly/senro
    private static Map<String,String> map = new HashMap<>();
    static {
        map.put("rootUrl","https://www.senlo.club/category/jdly/senro");
        map.put("rootPath","www.senlo.club");
        map.put("","//div[@class='post grid']//div[@class='img']//a/@href");
    }

    public static void main(String[] args) {
        //System.out.println(Picture_Model.commonGet(map.get("rootUrl")).body());
        //Picture_Model.root()
        Document document = Picture_Model.commonGet("https://www.senlo.club");
        System.out.println(document.body());
        //String s = Picture_Model.xsoupString("//div[@class='title_style_01']//h2[1]//@text()",document);
        /*String s = Xsoup.compile("//div[@class='title_style_01']//h2[1]/text()").evaluate(document).get();
        System.out.println(" >>> ");
        System.out.println(s);*/
    }

}
