package cn.cc.utils.fileio.use.m3u8helper;

import java.io.File;

public class GetAllName {
	public static String extName;

	/*
	 * 读取指定路径下的文件名和目录名
	 */
	public void getFileList() {
		String oldName = null;
		String path = "D:\\Test\\817183\\2\\lua.flv.bili2api.80";
		File file = new File(path);
		File test = null;
		File[] fileList = file.listFiles();

		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				String fileName = fileList[i].getName();
				System.out.println("文件：" + fileName);
				// fileList[i] = new File(path, oldName);
				String newFileName = blvToMp4(fileName);
				System.out.println("newFileName" + newFileName);
				if (newFileName != null) {
					fileList[i].renameTo(new File("D:\\Test\\", newFileName));
				}
				System.out.println("path" + path);
			}

			if (fileList[i].isDirectory()) {
				String fileName = fileList[i].getName();
				// System.out.println("目录：" + fileName);
			}
		}
	}

	// 判断是否是blv,如果是则修改为mp4
	public String blvToMp4(String fileName) {
		System.out.println("1.fileName" + fileName);
		extName = fileName.substring(fileName.lastIndexOf("."), fileName.length());

		System.out.println("extName:" + extName);

		if (extName.equals(".blv")) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
			fileName += ".mp4";
			return fileName;
		}

		return null;
	}

	// 测试运行
	public static void main(String[] args) {
		GetAllName rf = new GetAllName();
		rf.getFileList();
		/* fileList[i] = new File("D:\\Test\\", "1.flv"); */
	}
}
