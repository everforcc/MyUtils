package cc.advanced.web.http.jdkcraw;

import cc.constant.ConstantFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 给定网址获取里面的网址
 * 
 * @author Administrator
 *
 */
public class GetUrlByUrl {
	/**
	 * 步奏1
	 */

	/**
	 * url匹配规则
	 */
	private static final String regex1 = "https://[\\w+\\.?/?]+\\.[A-Za-z]+";
	private static final String regex = "https://imgsa\\.baidu\\.com/forum/w%3D580/sign=\\w*/{1}\\w*\\.jpg";
	//
	private static final String regex2 = "//i0\\.hdslb\\.com/bfs/article/\\w*@\\w*\\.webp";
	/**
	 * 所有符合表达式的URL
	 */
	private static String downUrl = "";
	/**
	 * 图片地址的集合= new ArrayList<String>()
	 */
	private static List<String> listImgSrc;
	/**
	 * 所有地址的集合
	 */
	private static List<String> listPathSrc = new ArrayList<String>();
	/**
	 * 向指定URL发送请求
	 */
	private static URL url = null;
	/**
	 * 获得发开的链接
	 */
	private static URLConnection urlconn = null;
	/**
	 * 流读取操作
	 */
	private static BufferedReader br = null;
	/**
	 * 流写入操作
	 */
	private static PrintWriter pw = null;
	/**
	 * 临时存储一行HTML
	 */
	private static String buf = null;
	// private static FileWriter fw = null;
	/**
	 * 匹配正则表达式
	 */
	private static Pattern p = null;
	/**
	 * 匹配
	 */
	private static Matcher matchURL = null;

	private static DownImgByImgURL down = new DownImgByImgURL();

	/**
	 * 根据给定的URL获得内部的链接URL
	 * 
	 * @param URL
	 */
	public static void getAllURL(String URL, int i) {

		listImgSrc = new ArrayList<String>();

		/**
		 * 给定匹配规则
		 */
		p = Pattern.compile(regex);

		try {
			/**
			 * 打开链接读取HTML
			 */
			url = new URL(URL);
			urlconn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));

			/**
			 * 设置存储URL
			 */
			pw = new PrintWriter(new FileWriter(ConstantFile.javaFilePath + "/SaveURL.txt"), true);

			while ((buf = br.readLine()) != null) {
				System.out.println(buf);

				/**
				 * 判断本行HTML是否有URL
				 */
				matchURL = p.matcher(buf);

				/**
				 * 如果找到
				 */
				while (matchURL.find()) {

					/**
					 * 返回在以前匹配操作期间由给定组捕获的输入子序列。
					 */
					downUrl = matchURL.group();

					/**
					 * 将所有URL存入磁盘
					 */
					pw.println(downUrl);

					/**
					 * png
					 */
					/**
					 * 判断是否包含已知图片的后缀，因为可能带参?,随后优化为正则表达式
					 */
					if (downUrl.endsWith(".webp") || downUrl.endsWith(".jpg") || downUrl.endsWith(".jpeg")
							|| downUrl.endsWith(".jpg?") || downUrl.endsWith(".jpeg?")) {
						/**
						 * 存放符合规则的图片的地址集合
						 */
						listImgSrc.add(downUrl);
						System.out.println("路径" + downUrl);
					} else {

						/**
						 * 存放其他不是图片的其他的链接
						 */
						// listPathSrc.add(downUrl);
					}
				}
			}
			System.out.println("获取网址结束");

			down.Download(listImgSrc, i);
			/**
			 * 清空集合准备下次下载
			 */
			// listImgSrc=null;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (pw != null) {
				pw.close();
			}
		}
	}
}
