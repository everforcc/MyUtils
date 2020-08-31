package cn.cc.jdk0.cmd;



import cn.cc.utils.fileio.StreamUtils;

import java.io.File;
import java.io.IOException;

public class Cmd {

    public static void main(String[] args) {
        try{
            //System.out.println(runCMD(openUrl+"http://www.baidu.com"));
            //executeProcess(startFir,"startup.bat");


            //executeProcess(startFir,"ipconfig");

            systemRunTime();

        }catch (Exception e){

        }


    }

    // + Url 在浏览器打开网址
    final static String openUrl="rundll32 url.dll,FileProtocolHandler";
    public static String precessType="cmd /c ";
    static String startFir="C:\\enviroment\\tomcat-6-list\\tomcat-6\\bin";


    //Logger logger= org.slf4j.LoggerFactory.getLogger("");

    /**
     * model
     * @param cmd
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static boolean runCMD(String cmd) {
        final String METHOD_NAME = "runCMD";
        Process p ;
        try {
            p = Runtime.getRuntime().exec(cmd);
            String errorMsg = StreamUtils.inputStreamStr(p.getErrorStream());
            System.out.println(METHOD_NAME + "#readLine: " + errorMsg );
            // 导致当前线程等待，如有必要，一直要等到由该 Process 对象表示的进程已经终止。
            // 如果已终止该子进程，此方法立即返回。如果没有终止该子进程，调用的线程将被阻塞，直到退出子进程。
            p.waitFor();
            // 返回子进程的出口值。
            int i = p.exitValue();
            System.out.println(METHOD_NAME + "#exitValue = " + i);
            // destroy 杀掉子进程。强制终止此 Process 对象表示的子进程。
            if (i == 0) { //0 表示正常终止
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                throw e;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*if (br != null) {
                br.close();
            }*/
        }
        return false;
    }

    /**
     *
     *
     * @param path
     * @param command
     * @throws Exception
     */

    /**
     *  执行指定目录的程序
     *  类型，默认cmd
     * @param path 指定进程的目录
     * @param command 开启进程的命令
     * @throws Exception
     */
    public static void executeProcess(String path,String command) throws Exception{
        Process p;
        Runtime runtime =   Runtime.getRuntime();
        p = runtime.exec(precessType + command ,null,new File(path));
        System.out.println("执行命令返回:" + StreamUtils.inputStreamStr(p.getInputStream()));
        System.out.println("休眠一秒");
        Thread.sleep(1000);
        System.out.println("runtime.gc();主动清理垃圾");
        runtime.gc();
        System.out.println("p.destroy();强行终止当前进程");
        p.destroy();
    }

    /**
     * 获取系统信息
     */
    public static void systemRunTime() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("本机CPU内核数："+runtime.availableProcessors());
        System.out.println("最大可用内存空间"+runtime.maxMemory()/1024/1024 +"MB,默认为系统的1/4");
        System.out.println("可用内存空间:"+runtime.totalMemory()/1024/1024 +"MB,默认为系统的1/64");
        System.out.println("空闲内存空间:"+runtime.freeMemory()/1024/1024 +"MB");
        System.out.println("手工GC处理gc()");
        runtime.gc();
        System.out.println("什么是GC？可以由系统自动调用的垃圾释放功能，或者RunTime手工调用的垃圾释放功能");

    }

}
