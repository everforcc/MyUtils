package cc.advanced.web.utils;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public class HttpParamUtils {



    public static String asUrlParamsGuava(Map<String, String> source){
        // TODO 如果要编码的话自己加下编码逻辑
        return Joiner.on("&")
                // 用指定符号代替空值,key 或者value 为null都会被替换
                .useForNull("")
                .withKeyValueSeparator("=")
                .join(source);
    }

    /**
     * 只要确保你的编码输入是正确的,就可以忽略掉 UnsupportedEncodingException
     */
    public static String asUrlParams(Map<String, String> source){
        Iterator<String> it = source.keySet().iterator();
        StringBuilder paramStr = new StringBuilder();
        while (it.hasNext()){
            String key = it.next();
            String value = source.get(key);
            if (StringUtils.isBlank(value)){
                continue;
            }
            try {
                // URL 编码
                value = URLEncoder.encode(value, "utf-8");
            } catch (UnsupportedEncodingException e) {
                // do nothing
            }
            paramStr.append("&").append(key).append("=").append(value);
        }
        // 去掉第一个&
        return paramStr.substring(1);
    }



}
