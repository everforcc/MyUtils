package cc.advanced.web.craw.jdkcraw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 根据URL获得HTML
 * 
 * @author Administrator
 *
 */
public class GetHTMLByURL {
	/**
	 * 步奏2
	 */
	// 读取一行HTML
	private static String line;
	// 获得HTML
	private static StringBuffer sb = new StringBuffer();

	/**
	 * 根据url获取HTML的内容
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String getHtml(String url) throws Exception {
		URL url1 = new URL(url);
		// 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
		URLConnection connection = url1.openConnection();
		// 返回从此打开的连接读取的输入流。
		// 以流的形式读取html
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((line = br.readLine()) != null) {
			sb.append(line, 0, line.length());
			sb.append('\n');
			System.out.println(line);
		}
		br.close();
		return sb.toString();
	}
}
