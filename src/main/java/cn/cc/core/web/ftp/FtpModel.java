package cn.cc.core.web.ftp;

import sun.net.ftp.FtpClient;

import java.io.*;

public class FtpModel {

    public static void main(String[] args) throws Exception {
        FtpClient ftpClient=FtpClient.create("139.196.183.193");
        ftpClient.setAsciiType();
        ftpClient.login("vsftpd","123456".toCharArray());
        System.out.println("服务器系统类型:"+ftpClient.getSystem());
        System.out.println("是否连接成功:"+ftpClient.isConnected());
        System.out.println("是否登入ftp:"+ftpClient.isLoggedIn());
    }

    /**
     * 1.登录
     * 2.查看
     * 3.上传
     * 4.下载
     */
    //待完善，分方法，



    public void model()throws Exception{
        //ftp://192.168.5.128
        FtpClient ftpClient=FtpClient.create("139.196.183.193");

        ftpClient.login("ftpadmin","cc566411".toCharArray());
        System.out.println("服务器系统类型:"+ftpClient.getSystem());
        System.out.println("是否连接成功:"+ftpClient.isConnected());
        System.out.println("是否登入ftp:"+ftpClient.isLoggedIn());
        InputStream is = ftpClient.list("/");


        //列出系统所有文件
        InputStreamReader isr = new InputStreamReader(is,"GBK");
        BufferedReader br = new BufferedReader(isr);
        String s = null;
        ftpClient.nameList("");
        System.out.println("------------------");
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }

        //上传文件
        OutputStream outputStream = ftpClient.putFileStream("/ftpfile/aa.txt");
        FileInputStream fileInputStream = new FileInputStream("D:\\a.txt") ;
        byte[] bytes = new byte[1024];
        int c;
        while ((c = fileInputStream.read(bytes)) != -1)
        {
            outputStream.write(bytes, 0, c);
        }
        outputStream.flush();
        outputStream.close();
        System.out.println("over");

        //553返回用户权限问题

        /*******************************************************************************************/

        //下载
        InputStream inputStream = ftpClient.getFileStream("/ftpfile/f3d3572c11dfa9ec39ee0b2a6cd0f703908fc1cf.jpg");
        FileOutputStream os = new FileOutputStream("D:\\FTP\\b.jpg");
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



    }

}
