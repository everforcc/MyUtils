package cn.cc.core.web.ftp;

import sun.net.ftp.FtpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class testFTP {


    /**
     * jdk 1.8 的方式
     * @throws Exception
     */
    public void connectFTP() throws Exception {
        //网址
        FtpClient ftpClient=FtpClient.create("192.168.137.1");
        System.out.println(ftpClient.getSystem());
        //
        System.out.println("是否连接"+ftpClient.isConnected());

        //账号，密码
        ftpClient.login("ccs","ccs".toCharArray());

        System.out.println("是否登入ftp:"+ftpClient.isLoggedIn());

        InputStream is = ftpClient.list("/");

        InputStreamReader isr = new InputStreamReader(is,"GBK");
        BufferedReader br = new BufferedReader(isr);
        String s = null;
        ftpClient.nameList("");
        //Iterator<FtpDirEntry> ftpDirEntryIterator = ftpClient.listFiles("/");
        System.out.println("------------------");
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }

       /* while(ftpDirEntryIterator.hasNext()){
            System.out.println("------------------");
            FtpDirEntry ftpDirEntry = ftpDirEntryIterator.next();
            System.out.println(ftpDirEntry.getName());
        }*/

       // System.out.println(inputStream);

        ftpClient = ftpClient.changeDirectory("dbpic");

        is = ftpClient.list("/");

        isr = new InputStreamReader(is,"GBK");
        br = new BufferedReader(isr);
        s = null;
        ftpClient.nameList("");
        //Iterator<FtpDirEntry> ftpDirEntryIterator = ftpClient.listFiles("/");
        System.out.println("------------------");
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }

    }

}
