package cc.structure.msgtype.md;

public class MDHelper {

    // 帮助将txt转化为带格式的md文件
    // 爬小说的时候会有格式的问题

    public static String linkImg(String url){
        return "<img src=\""+url+"\"  />";
    }

    public static String title(String url){
        return "##" + url + "/>" ;
    }

    public static String content(String url){
        return "#####" + url + "/>" ;
    }

}
