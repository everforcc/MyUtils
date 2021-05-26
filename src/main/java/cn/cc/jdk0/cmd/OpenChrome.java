package cn.cc.jdk0.cmd;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class OpenChrome {

    private static String defaultUrl = "http://www.baidu.com";
    private static String openDefaultBrowser = "rundll32 url.dll,FileProtocolHandler ";

    // 1.打开某网址
    public static void cmd(){
        URL url = null;
        try {
            url = new URL(defaultUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        try {
            Runtime runtime =   Runtime.getRuntime();
            runtime.exec(openDefaultBrowser + url);
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
    public static void operatorBat(String path,String command) throws Exception{
        Process p;
        Runtime runtime =   Runtime.getRuntime();
        p = runtime.exec("cmd /c  " +command ,null,new File(path));

        //取得命令结果的输出流
        InputStream fis=p.getInputStream();
        CmdUtils.outStream(fis);

        System.out.println("runtime.gc();");
        runtime.gc();
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
            String tomcat_path = "D:\\environment\\middleware\\apache-tomcat-6.0.45-8080\\bin";

            operatorBat(tomcat_path,"startup.bat");
            operatorBat(tomcat_path,"shutdown.bat");

            // 1.cmd
            //cmd();
            // 2.runbat
            //runbat();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
