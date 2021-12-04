package cc.java0.pscp;

import cc.constant.ConstantFile;
import cc.java0.cmd.CmdUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author everforcc 2021-09-28
 */
@Slf4j
public class PSCPUtils {
    // 需要先填写信息
    private static String ip = "";
    //private static String port = "22";
    private static String userName = "root";
    private static String passWord = "";

    public static void main(String[] args) {
        // 递归传，支持单个文件或目录，第二个参数为文件所在的目录
        //send("C:\\test\\down.bat","/test");
        // 传递方可以是文件夹或文件，但接收方是目录
        down(ConstantFile.L1_javaFilePath + ConstantFile.L2_linux,"/test/dir/down.bat");
    }

    public static void send(String windowsFile,String linuxDir){
        String COMMAND = String.format(ConstantPSCP.SEND,
                passWord,windowsFile,userName,ip,linuxDir);
        System.out.println(COMMAND);
        CmdUtils.execCmd(COMMAND);
        log.info("pscp send>>> end");
    }

    public static void down(String windowsDir,String linuxFile){
        String COMMAND = String.format(ConstantPSCP.DOWN,
                passWord,userName,ip,linuxFile,windowsDir);
        System.out.println(COMMAND);
        CmdUtils.execCmd(COMMAND);
        log.info("pscp send>>> end");
    }


}
