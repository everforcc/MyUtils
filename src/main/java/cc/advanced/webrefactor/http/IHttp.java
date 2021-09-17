package cc.advanced.webrefactor.http;

import java.util.Map;

/**
 * @author guokailong 2021-09-16
 */
public interface IHttp {

    /**
     * 重构http常见的方法,调用者只需要拿到返回值即可，调用逻辑全在内部实现
     * 需要就在这里加方法
     *
     *
     */

    String requestForStr(String urlPath,String requestMethod);

    String requestForStr(String urlPath,String requestMethod,String content,String charset);

    String requestForStr(String urlPath,String requestMethod,String content,String charset, Map<String,String> map);

    byte[] requestForFile(String urlPath,String requestMethod,String content,String filePath,String fileName);

    // 设置本地web代理,方法总结

}
