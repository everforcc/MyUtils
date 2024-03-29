package cc.core.regex.utils;

/**
 * @author c.c.
 * @date 2020/12/14
 */
public class RegexConstant {

    /**
     * 匹配汉字
     */
    public static String chinese = "[u4e00-u9fa5]";

    //public static String 全角字符 [uFE30-uFFA0]

    // https://www.cnblogs.com/alibai/p/3593168.html
    // 匹配双字节字符(包括汉字在内)：[^x00-xff]
    public static String doubleChar = "[^x00-xff]";
    // 匹配空行的正则表达式：n[s| ]*r
    public static String lineSpace = "n[s| ]*r";
    // 匹配HTML标记的正则表达式：/<(.*)>.*</1>|<(.*) />/
    public static String htmlTag = "/<(.*)>.*</1>|<(.*) />/";
    // 匹配首尾空格的正则表达式：(^s*)|(s*$)
    public static String borderSpace = "(^s*)|(s*$)";
    // 微信的表情包三个字符，可以看笔记
    public static String wxEmoji = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";

    public static String fileName = "\\<*\\>*\\/*\\\\*\\|*\\:*\"*\\**\\?*\\；*\\ *";


    /**
     一些常用的正则匹配规则
     匹配中文字符的正则表达式： [u4e00-u9fa5]
     　　评注：匹配中文还真是个头疼的事，有了这个表达式就好办了
     　　匹配双字节字符(包括汉字在内)：[^x00-xff]
     　　评注：可以用来计算字符串的长度（一个双字节字符长度计2，ASCII字符计1）
     　　匹配空白行的正则表达式：ns*r
     　　评注：可以用来删除空白行
     　　匹配HTML标记的正则表达式：<(S*?)[^>]*>.*?|<.*? />
     　　评注：网上流传的版本太糟糕，上面这个也仅仅能匹配部分，对于复杂的嵌套标记依旧无能为力
     　　匹配首尾空白字符的正则表达式：^s*|s*$
     　　评注：可以用来删除行首行尾的空白字符(包括空格、制表符、换页符等等)，非常有用的表达式
     　　匹配Email地址的正则表达式：w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*
     　　评注：表单验证时很实用
     　　匹配网址URL的正则表达式：[a-zA-z]+://[^s]*
     　　评注：网上流传的版本功能很有限，上面这个基本可以满足需求
     　　匹配帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)：^[a-zA-Z][a-zA-Z0-9_]{4,15}$
     　　评注：表单验证时很实用
     　　匹配国内电话号码：d{3}-d{8}|d{4}-d{7}
     　　评注：匹配形式如 0511-4405222 或 021-87888822
     　　匹配腾讯QQ号：[1-9][0-9]{4,}
     　　评注：腾讯QQ号从10000开始
     　　匹配中国邮政编码：[1-9]d{5}(?!d)
     　　评注：中国邮政编码为6位数字
     　　匹配身份证：d{15}|d{18}
     　　评注：中国的身份证为15位或18位
     　　匹配ip地址：d+.d+.d+.d+
     　　评注：提取ip地址时有用
     更多详细内容请查看：http://www.111cn.net/jsp/Java/46105.htm
     */

}
