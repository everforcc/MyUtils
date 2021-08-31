package cc.advanced.web.http.utils;

/**
 * @author c.c.
 * @date 2020/12/9
 */
public class CrawDataParse {

    private String content;
    public CrawDataParse(String content){
        this.content = content;
    }
    // 处理业务
    // 处理爬虫获得的字符串
    public static String parse(String content,String... idList){
        // 回车还要在考虑
        // 这个位置要修改为正则表达式 <.{0,100}> content
        for(String id:idList) {
            content = content.replaceAll("<\\w{0,5} id=\"" + id + "\">", "");
        }
        content = content.replace("<span style=\"font-size:14px;\">","");
        // 删除注释
        content = content.replaceAll("<!--\\w{0,5}-->","");
        // 删除结束符
        content = content.replaceAll("</\\w{0,5}>","");
        // 空格转换
        content = content.replaceAll("&nbsp;","");
        // 换行处理
        //content = content.replaceAll("<br>\r","\r"); // 因为有连续两个 br 单独的<br> 后面追加了\r表示回车符号
        content = content.replaceAll("<br />\r",""); // 因为有连续两个 br 单独的<br> 后面追加了\r表示回车符号
        content = content.replaceAll("<br />","\n"); // 因为有连续两个 br 单独的<br> 后面追加了\r表示回车符号
        content = content.replaceAll("<br>\r",""); // 因为有连续两个 br 单独的<br> 后面追加了\r表示回车符号
        content = content.replaceAll("<br>","\n"); //

        // 图片，拼音处理

        return content;
    }

    // Jsoup 不知道怎么移除某个节点

    /** 这种情况 移除 <ul id="contentdp">123123></ul>
     <div id="content">
      文字
     <ul id="contentdp">123123></ul>
     </div>
     */
    public static String removeTagById(String content,String tag,String... idList){
        // 正则全部放到配置类
        for(String id:idList) {
            String regex = "\\<" + tag + " id=\"" + id + "\"\\>(\\n){0,1}([u4e00-u9fa5])*.*(\\n){0,1}.*\\</" + tag + "\\>";
            //System.out.println("regex:" + regex);
            content = content.replaceAll(regex, "");
        }
        return content;
    }

    public static String removeTagByClass(String content,String tag,String... classList){
        for(String id:classList) {
            content = content.replaceAll("\\<" + tag + " class=\"" + id + "\"\\>", "");
        }
        return content;
    }

    public static String removeTagByTag(String content,String... tagclassList){
        for(String id:tagclassList) {
            content = content.replaceAll("\\<" + id + " href\\=\".*\" target=\"_blank\"\\>", "");
        }
        return content;
    }

    public static void main(String[] args) {
        String html = "<ul id=\"contentdp\">\n" +
                "  本文来自 轻小说文库(http://www.wenku8.com)\n" +
                " </ul>";
        //System.out.println("match:" + removeTagById(html,"ul","contentdp"));
    }

    // 将中文替换为数字
    public static void replaceNum(){

    }

    @Override
    public String toString() {
        return content;
    }
}
