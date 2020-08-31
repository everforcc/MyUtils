package cn.cc.jdk0.cmd;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class OpenChrome {

    public void cmd(){
        URL url = null;
        try {
            url = new URL("http://www.baidu.com");
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        try {
            Runtime runtime =   Runtime.getRuntime();
            runtime.exec(
                    "rundll32 url.dll,FileProtocolHandler "
                            + url);
            runtime.gc();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 启动Tomcat程序
     *
     * @param programPath
     * @param batName
     */
    public static void runbat(String programPath, String batName) {
        Runtime rt = Runtime.getRuntime();
        Process ps = null;
        try {
            // 提示
            // The CATALINA_HOME environment variable is not defined correctly
            // This environment variable is needed to run this program
            //  ps = rt.exec("cmd.exe /c " + batName);

            ps = rt.exec("cmd /c " + programPath + File.separator + batName, null, new File(programPath));

            InputStream is = ps.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            ps.waitFor();
            is.close();
            reader.close();
            ps.destroy();
        } catch (Exception e) {
            //LoggerUtil.error(AutoStartup.class.getName(), e.getMessage(), e);
            e.printStackTrace();
        }

    }

    /**
     * 在不同文件夹操作
     * @throws Exception
     */
    public static void startTomcat(String path,String command) throws Exception{
        Process p;
        String startFir="C:\\enviroment\\tomcat-6-list\\tomcat-6\\bin";
        //Runtime.getRuntime().exec           startup.bat
        // cmd /k command 执行完命令不关闭窗口
        // cmd /c dir 是执行完dir命令后关闭命令窗口
        // cmd /c start dir 会打开一个新窗口后执行dir指令，原窗口会关闭。
        // cmd /k start dir 会打开一个新窗口后执行dir指令，原窗口不会关闭。
        //p = Runtime.getRuntime().exec("cmd /k startup.bat",null,new File(startFir));
        //p = Runtime.getRuntime().exec("cmd /c ipconfig ",null,new File(startFir));
        Runtime runtime =   Runtime.getRuntime();
        p = runtime.exec("cmd /c  " +command ,null,new File(path));

        //C:\enviroment\tomcat-6-list\tomcat-6\bin
        //读取有问题，暂时不用
        //p = Runtime.getRuntime().exec("C:\\enviroment\\tomcat-6-list\\tomcat-6\\bin\\startup.bat");
        //取得命令结果的输出流
        /*InputStream fis=p.getInputStream();
        //用一个读输出流类去读                    CMD 对应的编码GBK
        InputStreamReader isr=new InputStreamReader(fis,"GBK");
        //用缓冲器读行
        BufferedReader br=new BufferedReader(isr);
        String line=null;
        //直到读完为止
        while((line=br.readLine())!=null)
        {
            System.out.println(line);
        }*/

        Thread.sleep(2000);


        System.out.println("runtime.gc();");
        //runtime.gc();
        System.out.println("p.destroy();");
        p.destroy();
        //p.destroyForcibly();
        //Runtime.getRuntime().addShutdownHook(p);
        //System.out.println("start");
        //Runtime.getRuntime().exec("cmd /c exit");
        //System.out.println("over");
    }

    public static void main(String[] args) {

        try{
            /*System.out.println(1);
            startTomcat("C:\\enviroment\\tomcat-6-list\\tomcat-6\\bin","startup.bat");
            System.out.println(2);
            Thread.sleep(1000);
            System.out.println(3);
            startTomcat("C:\\enviroment\\tomcat-6-list\\tomcat-6\\bin","shutdown.bat");
            System.out.println(4);*/

            //启动Tomcat
            //runbat("C:\\Users\\lzy\\Desktop\\apache-tomcat-7.0.52", "bin\\startup.bat");
            //关闭Tomcat
            //runbat("C:\\Users\\lzy\\Desktop\\apache-tomcat-7.0.52", "bin\\shutdown.bat");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
