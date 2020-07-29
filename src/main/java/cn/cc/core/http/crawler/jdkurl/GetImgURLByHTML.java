package cn.cc.core.http.crawler.jdkurl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据HTML获得图片的URL
 * 
 * @author Administrator
 *
 */
public class GetImgURLByHTML {
	/**
	 * 步奏3
	 */
	/**
	 * 图片<img>标签的正则表达式
	 */
	private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";

	/**
	 * 返回imgUrl的地址集合
	 * 
	 * @param html
	 * @return
	 */
	public List<String> getImageUrl(String html) {
		// 将给定的正则表达式编译到模式中
		// 创建匹配给定输入与此模式的匹配器。
		Matcher matcher = Pattern.compile(IMGURL_REG).matcher(html);
		List<String> listimgurl = new ArrayList<String>();

		/**
		 * 重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列。 如果匹配成功，则可通过 start、fileio 和 group
		 * 方法获取更多信息，而 find() 方法的后续调用将从此匹配操作未匹配的第一个字符开始
		 */
		while (matcher.find()) {
			System.out.println(matcher.group());
			/**
			 * 返回在以前匹配操作期间由给定组捕获的输入子序列。
			 */
			listimgurl.add(matcher.group());
		}
		return listimgurl;
	}
}
