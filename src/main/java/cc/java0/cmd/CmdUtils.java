package cc.java0.cmd;

import cc.sysconstant.ConstantFile;
import cc.core.io.base.StreamInputUtils;
import cc.sysutils.Print_Record;

import java.io.*;

public class CmdUtils {

    public static void main(String[] args) {
        try{
            //System.out.println(runCMD(openUrl+"http://www.baidu.com"));
            //executeProcess(startFir,"startup.bat");
            //executeProcess(startFir,"ipconfig");

            //execCmd("ipconfig");

        }catch (Exception e){

        }
    }



    Print_Record print_record = Print_Record.getInstanse(ConstantFile.L1_javaFilePath + "");
    // 目前使用这个，下面的其他方法还需要再整理
    public static String precessType="cmd /c ";

    /**
     * 1.直接执行
     * 2.指定目录执行
     */

    // 执行命令
    public static void execCmd(String command) {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/C", command);
        Process process = null;
        try {
            process = builder.redirectErrorStream(true).start();
            InputStream in = process.getInputStream();
            outStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  执行指定目录的程序
     *  类型，默认cmd
     * @param path 指定进程的目录
     * @param command 开启进程的命令
     * @throws Exception
     */
    public static void execCmdWithPath(String path,String command) throws Exception{
        Process p;
        Runtime runtime =   Runtime.getRuntime();
        p = runtime.exec(precessType + command ,null,new File(path));
        outStream(p.getInputStream());
        System.out.println("runtime.gc();主动清理垃圾");
        runtime.gc();
        System.out.println("p.destroy();强行终止当前进程");
        p.destroy();
    }

    // 命令行返回
    public static void outStream(InputStream in) throws UnsupportedEncodingException {
        // 用一个读输出流类去读
        BufferedReader br = new BufferedReader(new InputStreamReader(in,"GBK"));
        String line;
        // 逐行读取输出到控制台
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*****************************************************************************************************************/

    // + Url 在浏览器打开网址
    final static String openUrl="rundll32 url.dll,FileProtocolHandler";


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
            String errorMsg = StreamInputUtils.streamToStr(p.getErrorStream(),"GBK");
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
        } catch (Exception e) { // 很多异常，就简单处理了
            e.printStackTrace();
        } finally {
            /*if (br != null) {
                br.close();
            }*/
        }
        return false;
    }

}
