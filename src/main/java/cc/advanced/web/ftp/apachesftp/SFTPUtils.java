package cc.advanced.web.ftp.apachesftp;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @author everforcc 2021-09-28
 */
@Slf4j
public class SFTPUtils {

    private static String sftpHost = "180.76.156.43";
    //private static String port = "";
    private static String sftpUserName = "root";
    private static String sftpPassword = "c!c!5664";
    private static int sftpPort = 22;
    private static int timeout = 6000;

    public static void main(String[] args) {
        try {
            String localFile = ""; // 本地文件名
            String targetDir = ""; // 目标文件
            //put(localFile,targetDir);
            get();
            closeChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Session session = null;
    private static Channel channel = null;

    public static void put(String localFile,String targetDir) throws Exception {
        ChannelSftp chSftp = getChannel();
        chSftp.put(localFile,targetDir,ChannelSftp.OVERWRITE);
        chSftp.quit();
        closeChannel();
    }

    public static void get() throws Exception {
        ChannelSftp chSftp = getChannel();
        java.util.Vector vector = chSftp.ls("/home");
        Object[] objects =  vector.toArray();
        for(Object o:objects){
            System.out.println(o);
        }
    }

    // 生成可传输的对象
    private static ChannelSftp getChannel() throws JSchException {
        JSch jsch = new JSch(); // 创建JSch对象
        session = jsch.getSession(sftpUserName, sftpHost, sftpPort); // 根据用户名，主机ip，端口获取一个Session对象
        if (sftpPassword != null) {
            session.setPassword(sftpPassword); // 设置密码
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); // 为Session对象设置properties
        session.setTimeout(timeout); // 设置timeout时间
        session.connect(); // 通过Session建立链接

        channel = session.openChannel("sftp"); // 打开SFTP通道
        channel.connect(); // 建立SFTP通道的连接

        log.info("开始链接");

        return (ChannelSftp) channel;
    }

    private static void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }


}
