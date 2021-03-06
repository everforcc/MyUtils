package cc.core.file.zip;

import cc.constant.ConstantFile;
import cc.java0.cmd.CmdUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author c.c.
 * @date 2021/2/7
 */
public class SevenZCompressUtils {

    public static void main(String[] args) {
        deCompression(ConstantFile.javaFilePath + "/001.7z",ConstantFile.javaFilePath + "/整理","pas");
        compress(ConstantFile.javaFilePath + "/test-pas.7z","pas",ConstantFile.javaFilePath + "/001/*");
    }

    // 7z路径
    private static final String z7filePath = "D:\\app\\7z\\7-Zip\\7z.exe";
    // 7z 源文件 目标文件 密码
    private static final String deCompressionCommand = "start /B %s x %s -o%s -aoa -bse1 %s";
    // 压缩文件 -tzip 参数为亚索格式，可以规定为zip,7z等支持的格式
    private static final String compressionCommand = "%s a -t7z -r %s \"%s\" \"%s\" ";

    /**
     *
     * @param z7 压缩文件
     * @param targetFile 目标路径
     * @param pas 密码
     */
    public static void deCompression(String z7,String targetFile,String pas){
        z7 = z7.replaceAll(" ", "\" \"");
        targetFile = targetFile.replaceAll(" ", "\" \"");
        if(StringUtils.isNotEmpty(pas)){
            pas = "-p" + pas;
        }else {
            pas = "";
        }
        String cmd = String.format(deCompressionCommand,z7filePath,z7,targetFile,pas);
        System.out.println(cmd);
        //CmdUtils.execCmd(cmd);
    }

    // 压缩

    /**
     *
     * @param pas 密码
     * @param targetFile 目标文件
     * @param sourceFile 源文件 path/* path
     */
    public static void compress(String pas,String targetFile,String sourceFile){

        /*targetFile = targetFile.replaceAll(" ", "\" \"").replaceAll("&","");
        sourceFile = sourceFile.replaceAll(" ", "\" \"").replaceAll("&","");*/

        /*targetFile = targetFile.replaceAll("&","");
        sourceFile = sourceFile.replaceAll("&","");*/

        if(StringUtils.isNotEmpty(pas)){
            pas = "-p" + pas;
        }else {
            pas = "";
        }
        String cmd = String.format(compressionCommand,z7filePath,pas,targetFile,sourceFile);
        System.out.println("cmd: >>> " + cmd);
        CmdUtils.execCmd(cmd);
    }


}
