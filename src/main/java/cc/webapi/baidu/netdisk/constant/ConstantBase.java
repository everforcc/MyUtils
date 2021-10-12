package cc.webapi.baidu.netdisk.constant;

/**
 * @author everforcc 2021-10-12
 */
public class ConstantBase {

    // https://pan.baidu.com/union/doc/Zksg0sb73

    /**
     * 1. 获取用户信息
     */
    public static String uinfo = "https://pan.baidu.com/rest/2.0/xpan/nas?method=uinfo&access_token=%s";

    /**
     * 2. 获取网盘容量信息
     */
    public static String quota = "https://pan.baidu.com/api/quota";

    /**
     * 3.1. 获取文件列表
     */
    public static String xpanFile = "https://pan.baidu.com/rest/2.0/xpan/file";

    /**
     * 3.2 递归获取文件列表
     */
    public static String xpanMultimedia = "http://pan.baidu.com/rest/2.0/xpan/multimedia";

}
