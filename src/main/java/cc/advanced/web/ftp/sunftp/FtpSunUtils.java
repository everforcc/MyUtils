package cc.advanced.web.ftp.sunftp;

import cc.sysconstant.ConstantFile;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.*;
import java.net.InetSocketAddress;

/**
 * 应该是只支持被动模式，不支持主动，随后调整测试
 */
public class FtpSunUtils {


    private static String ip = "180.76.156.43";
    private static String userName = "everforcc";
    private static String passWord = "c.c.5664";
    private static int port = 21;

    public static void main(String[] args) throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(ip,port);
        FtpClient ftpClient=FtpClient.create(inetSocketAddress);
        //ftpClient.setAsciiType();
        ftpClient.enablePassiveMode(true);
        ftpClient.login(userName,passWord.toCharArray());
        System.out.println("服务器系统类型:"+ftpClient.getSystem());
        System.out.println("是否连接成功:"+ftpClient.isConnected());
        System.out.println("是否登入ftp:"+ftpClient.isLoggedIn());

        // 列出文件
        listFile(ftpClient);

        // 上传文件
        //upload(ftpClient);

        // 下载
        //down(ftpClient);
    }

    /**
     * 1.登录
     * 2.查看
     * 3.上传
     * 4.下载
     */
    //待完善，分方法，

    public static void listFile(FtpClient ftpClient){
        try {
            InputStream is = ftpClient.list("test");
            //列出系统所有文件
            InputStreamReader isr = new InputStreamReader(is);//,"GBK"
            BufferedReader br = new BufferedReader(isr);
            String s = null;

            //ftpClient.nameList("dbpic"); 没看懂

            System.out.println("------------------");
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void upload(FtpClient ftpClient){
        //上传文件
        OutputStream outputStream = null;
        try {
            outputStream = ftpClient.putFileStream("/a.txt");
            FileInputStream fileInputStream = new FileInputStream(ConstantFile.L1_javaFilePath + "\\java\\a.txt") ;
            byte[] bytes = new byte[1024];
            int c;
            while ((c = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, c);
            }
            outputStream.flush();
            outputStream.close();
            System.out.println("over");
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void down(FtpClient ftpClient){
        //553返回用户权限问题
        /*******************************************************************************************/

        //下载
        InputStream inputStream = null;
        try {
            inputStream = ftpClient.getFileStream("/2.txt");
            FileOutputStream os = new FileOutputStream(ConstantFile.L1_javaFilePath + "\\java\\b.txt");
            byte[] bytesin = new byte[1024];
            int cin;
            while ((cin = inputStream.read(bytesin)) != -1)
            {
                System.out.println("cin:"+cin+" | "+"bytes:"+bytesin);
                os.write(bytesin, 0, cin);
            }
            os.close();
            ftpClient.close();
            //最后需要关闭

            ftpClient.completePending();
            System.out.println("down:over");
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
