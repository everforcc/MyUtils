package cn.cc.utils.fileio.use.m3u8helper;

import java.io.File;

/**
 * 递归算法
 */
public class ChangeAllName2 {
	// 存循环前的path
	private static String oldPath = "";
	// 临时存储文件后缀名
	public static String extName;
	// 新文件的名字防止重名
	public static Integer j = 0;

	// 顶级文件夹
	// private static String path = "D:\\Test\\";

	// 依次寻找文件夹
	public void getFileList(String path) {
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
					// 修改文件名
					String newFileName = blvToMp4(fileName);
					System.out.println("newFileName" + newFileName);
					if (newFileName != null) {
						fileList[i].renameTo(new File("F:\\mp4\\", newFileName));
					}
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
				if (fileList[i].isDirectory()) {
					System.out.println("oldPath前" + oldPath);
					// 寻找下一级文件的路径
					oldPath += "\\" + fileList[i].getName();
					System.out.println("目录：" + fileList[i].getName());
					System.out.println("oldPath后" + oldPath);
					// 进入下一级文件夹，重复
					getFileList(oldPath);
				}
				oldPath = "";
			}
		}
	}

	// 判断是否是blv,如果是则修改为mp4
	public String blvToMp4(String fileName) {
		System.out.println("1.fileName" + fileName);
		extName = fileName.substring(fileName.lastIndexOf("."), fileName.length());

		System.out.println("extName:" + extName);

		if (extName.equals(".blv")) {
			j++;
			fileName = j.toString();
			fileName += ".mp4";
			return fileName;
		}

		return null;
	}

	public static void main(String[] args) {
		ChangeAllName2 ca = new ChangeAllName2();
		ca.getFileList("D:\\TTT");
	}
}
