package cn.cc.utils.fileio.m3u8helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class M3u8ToMp4 {
	// 存循环前的path
	private static String oldPath = "";
	// 输出路径
	private static String path1 = "D:\\resources\\1.视频\\m3u8\\out\\";
	// 临时存储文件后缀名
	public static String extName;
	// 新文件的名字防止重名
	public static Integer m3u8Num = 0;
	// private static final String path = "D:\\gkl";

	/**
	 * 传入video路径
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static void getFileList(String path) throws Exception {
		// 根据路径获取文件夹
		File file = new File(path);
		// 获取该文件夹下的所有文件
		File[] fileList = file.listFiles();
		// 便利所有的文件夹
		if (fileList != null) {
			for (int i = 0; i < fileList.length; i++) {
				// 如果是文件
				if (fileList[i].isFile()) {
					String fileName = fileList[i].getName();
					System.out.println("文件：" + fileName);
					// 修改文本内容
					// 判断是否存在文件夹如果不存在就创建
					File saveFile = new File(path1);
					if (!saveFile.exists()) {
						// 如果不存在
						saveFile.mkdir();
					}
					blvToMp4(fileName, path + "\\" + fileName, path1);

				}

				// 如果是文件夹
				// 则循环进入直到不是退出
				oldPath = "";
				oldPath += path;
				/*
				 * if(){
				 * 
				 * }
				 */
				/*if (fileList[i].isDirectory()) {
					System.out.println("oldPath前" + oldPath);
					// 寻找下一级文件的路径
					oldPath += "\\" + fileList[i].getName();
					System.out.println("目录：" + fileList[i].getName());
					System.out.println("oldPath后" + oldPath);
					// 进入下一级文件夹，重复
					getFileList(oldPath);
				}*/
				oldPath = "";
			}
		}
	}

	// 判断是否是m3u8,如果是,则修改内容
	private static void blvToMp4(String fileName, String path, String path1) throws Exception {
		System.out.println("1.fileName" + fileName);
		extName = fileName.substring(fileName.lastIndexOf("."), fileName.length());

		System.out.println("extName:" + extName);

		if (extName.equals(".m3u8")) {
			m3u8Num++;
			changeMessage(path, path1 + m3u8Num + ".m3u8");
		}
	}

	// 修改内容的操作 原路径 输出路径
	private static void changeMessage(String path, String path1) throws Exception {
		// String path =
		// "D:\\video\\31677\\9e63b15be9009f59e1e5f68425016405\\local.m3u8";
		// String path1 = "D:\\2.m3u8";
		System.out.println("path:" + path);
		System.out.println("path1:" + path1);
		File file = new File(path);
		File file1 = new File(path1);
		BufferedReader br = new BufferedReader(new FileReader(file));
		PrintWriter pw = new PrintWriter(file1);
		String str = null;
		while ((str = br.readLine()) != null) {
			// 修改文件内容
			str = str.replace("/", "\\");
			str = str.replaceFirst("\\\\storage\\\\emulated\\\\0\\\\Quark", "D:\\\\resources\\\\m3u8");
			pw.print(str + "\r\n");
		}
		pw.close();// 这个主要是结束打印文本
	}

	public static void main(String[] args) {

		try {
			new M3u8ToMp4().getFileList("D:\\resources\\1.视频\\m3u8\\Download\\");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
