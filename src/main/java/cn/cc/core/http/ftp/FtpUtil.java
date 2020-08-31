package cn.cc.core.http.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class FtpUtil {
  static Logger logger= LoggerFactory.getLogger(FtpUtil.class);
	public static void main(String[] args) {
		// 1.设置ftp 被动传输模式
		//
		/*FtpClient ftpClient = FtpClient.create();
		ftpClient.enterLocalPassiveMode();*/
		//FTPClient

		System.out.println(isEmpty(null));
		System.out.println(isEmpty(""));
		System.out.println(isEmpty("a"));
	}

	static Boolean isEmpty(String str){
		if(str!=null&&!"".equals(str)){
			return false;
		}
		return true;
	}

	/**
	 * 注意用户名的权限问题
	 *
	 *  String charset="GBK";
	    int size = 2048;
	    FileInputStream fileInputStream = new FileInputStream(file1);
	    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
	 *
	 */


	/**
	 * @description FTP被动模式上传文件
	 * @param ip 需要上传到的服务器IP地址 -必传字段
	 * @param port 端口号 -必传字段
	 * @param userName FTP用户名 -必传字段
	 * @param password FTP登录密码 -必传字段
	 * @param sourceStream 需要上传的文件流 -必传字段
	 * @param ftpFileName 上传到服务器的生成的文件名 -必传字段
	 * @param ftpDir 如果FTP服务器的地址目录有多级，可以添加相对路径。也可为空
	 * @param charset 上传文件的格式//UTF-8默认，可传空
	 * @param size 上传文件的大小//必传字段
	 * @return
	 * @throws Exception
	 */
	public static String ftpPasvModeupload(String ip, int port, String userName, String password, InputStream sourceStream, String ftpDir,
										   String ftpFileName, String charset, int size) throws Exception{
		FTPClient ftpClient = new FTPClient();
		StringBuffer resultStr = new StringBuffer("");
		/*if(StringUtil.isEmpty(ip)){
			logger.debug("服务器IP地址不可为空!\r\n");
			resultStr.append("服务器IP地址不可为空!\r\n");
		}
		if(StringUtil.isEmpty(userName)){
			logger.debug("服务器登陆用户名不可为空!\r\n");
			resultStr.append("服务器登陆用户名不可为空!\r\n");
		}
		if(StringUtil.isEmpty(password)){
			logger.debug("服务器登陆密码不可为空!\r\n");
			resultStr.append("服务器登陆密码不可为空!\r\n");
		}
		if(StringUtil.isEmpty(ftpFileName)){
			logger.debug("服务器目标文件名不可为空!\r\n");
			resultStr.append("服务器目标文件名不可为空!\r\n");
		}
		if(sourceStream == null){
			logger.debug("上传文件流不可为空!\r\n");
			resultStr.append("上传文件流不可为空!\r\n");
		}
		if(StringUtil.isNotEmpty(resultStr.toString())){
			return resultStr.toString();
		}*/
		try {
			//FTP连接
			ftpClient.connect(ip, port);
			logger.info("FTP连接成功！");
			//登录
			boolean login = ftpClient.login(userName, password);
			if(login){
				logger.info("FTP登陆成功！");
				//设置上传目录
				//if(StringUtil.isNotEmpty(ftpDir)){
					ftpClient.changeWorkingDirectory(ftpDir);
				//}
				ftpClient.setBufferSize(size);
				ftpClient.setControlEncoding(charset);
				//设置被动模式
				ftpClient.enterLocalPassiveMode();
				//设置文件类型（二进制）
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				if(ftpClient.storeFile(ftpFileName, sourceStream)){
					logger.info("上传文件成功！");
				} else {
					resultStr.append("上传文件失败!\r\n");
				}
			} else {
				logger.info("ftp登录异常");
				resultStr.append("ftp登录异常!\r\n");
			}
		} catch (IOException e) {
			logger.info("ftp上传文件有错，具体原因如下:"+e.getMessage());
			resultStr.append("ftp上传文件有错，具体原因如下:"+e.getMessage());
			return resultStr.toString();
		} finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				System.err.println("断开ftp连接异常");
				logger.info("断开ftp连接异常，具体原因如下"+e.getMessage());
				resultStr.append("断开ftp连接异常，具体原因如下:"+e.getMessage());
				return resultStr.toString();
			}
		}
		return resultStr.toString();
	}
}
