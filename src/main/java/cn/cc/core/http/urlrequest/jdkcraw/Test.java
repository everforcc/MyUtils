package cn.cc.core.http.urlrequest.jdkcraw;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public void testInt() {
		String i = "1";
		// Integer.valueOf(i);
		System.out.println(Integer.parseInt("1"));
		
		//System.out.println(Double.valueOf("1.1"));

	}

	/**
	 * 1
	 */
	private static GetUrlByUrl getUrl = new GetUrlByUrl();

	/**
	 * 2
	 */
	private static GetHTMLByURL getHTML = new GetHTMLByURL();

	/**
	 * 3
	 */
	private static GetImgURLByHTML getImgUrl = new GetImgURLByHTML();

	/**
	 * 4
	 */
	private static DownImgByImgURL down = new DownImgByImgURL();

	private static String HTML;

	private static List<String> HTMLlist;


	public void getUrl() throws Exception {
		// getHTML.getHtml("https://www.bilibili.com/read/cv432020");
		// getHTML.getHtml("http://www.bilibili.com/read/mobile/432020");
		// getUrl.getAllURL("https://www.bilibili.com/read/cv432020", 0);
		int i = 1;
		while (i < 3) {
			getUrl.getAllURL("https://tieba.baidu.com/p/6030677277?pn=" + i, i);
			i++;
		}
		// HTML=getHTML.getHtml("https://tieba.baidu.com/p/2837363232?red_tag=1229133878");
		// HTMLlist=getImgUrl.getImageUrl(HTML);
		// down.Download(HTMLlist);

	}

	private static URL url = null;
	private static URLConnection urlconn = null;


	public void gUrl() throws Exception {

		int i = 1;
		while (true) {
			url = new URL("http://www.ailvxing.com/");
			urlconn = url.openConnection();
			i++;
			System.out.println(i);
		}

	}


	public void testGetAllUrl() {
		List<String> list = null;
		try {
			/**
			 * 根据给定的URL获得内部的链接URL
			 */
			// getUrl.getAllURL("");
			/**
			 * 根据url获取HTML的内容
			 */
			HTML = getHTML.getHtml("");
			/**
			 * 返回imgUrl的地址集合
			 */
			HTMLlist = getImgUrl.getImageUrl("");
			/**
			 * 根据图片的URL进行下载
			 */
			// down.Download(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void getImg() {
		String pattern = "((([0-1]{0,1})([0-9]{2}))|(2([1-4]{1})([0-9]{1}))|(25([0-5]{1})))";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher("211");
		System.out.println(m.matches());

	}
}
