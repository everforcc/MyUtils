package cn.cc.utils.fileio.m3u8helper;

import java.io.*;

/**
 * 
 * @author SunnyBoy
 * @version Time：2017年7月29日 下午7:55:39
 */
public class ChangeLine {

	public static void main(String[] args) throws IOException {
		String path = "D:\\video\\31677\\97efa1aeca076e761368d884a205f57c\\local.m3u8";
		String path1 = "D:\\4.m3u8";
		File file = new File(path);
		File file1 = new File(path1);
		BufferedReader br = new BufferedReader(new FileReader(file));
		PrintWriter pw = new PrintWriter(file1);
		String str = null;
		while ((str = br.readLine()) != null) {
			/*
			 * int n = 0; n = str.indexOf(" "); if (n > 0)
			 * {//if语句主要用来判断，该单词是否有空格为分隔符的 String s = str.substring(0, n);
			 * pw.print(s+"\r\n");//这里主要是将一字符串打入一个文本中 } else
			 * pw.print(str+"\r\n");
			 */
			str = str.replace("/", "\\");
			str = str.replaceFirst("http:\\\\\\\\127.0.0.1:12345\\\\storage\\\\emulated\\\\0\\\\kgc", "D:");
			pw.print(str + "\r\n");
		}
		pw.close();// 这个主要是结束打印文本
	}
}
