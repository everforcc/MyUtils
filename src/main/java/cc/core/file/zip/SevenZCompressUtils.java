package cc.core.file.zip;

import cc.sysconstant.ConstantFile;
import cc.java0.cmd.CmdUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;

/**
 * @author c.c.
 * @date 2021/2/7
 */
public class SevenZCompressUtils {

    public static void main(String[] args) {
        deCompression(ConstantFile.L1_javaFilePath + "/001.7z",ConstantFile.L1_javaFilePath + "/整理","pas");
        compress(ConstantFile.L1_javaFilePath + "/test-pas.7z","pas",ConstantFile.L1_javaFilePath + "/001/*");
    }

    // 7z路径
    private static final String z7filePath = "D:\\java\\environment\\7-Zip\\7z.exe";
    // 7z 源文件 目标文件 密码
    private static final String deCompressionCommand = "start /B %s x %s -o%s -aoa -bse1 %s";
    // 压缩文件 -tzip 参数为亚索格式，可以规定为zip,7z等支持的格式
    private static final String compressionCommand = "%s a -t7z -r %s \"%s\" \"%s\" ";

    private String passWord = "自己的密码";
    // 需要压缩的文件夹
    private String sourceFilePath = ConstantFile.L1_javaFilePath + "";
    // 是否进入文件夹
    private boolean isCD = true;
    // 文件后缀,可以多 "." 但不能少 "."
    private static final String fileType = ".7z";// 如果要别的格式，还要同步修改命令

   /* public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public boolean isCD() {
        return isCD;
    }

    public void setCD(boolean CD) {
        isCD = CD;
    }*/

    public SevenZCompressUtils() {
    }

    /*public SevenZCompressUtils(String passWord, boolean isCD) {
        this.passWord = passWord;
        this.isCD = isCD;
    }*/

    public SevenZCompressUtils(String passWord, String sourceFilePath, boolean isCD) {
        this.passWord = passWord;
        this.sourceFilePath = sourceFilePath;
        this.isCD = isCD;
    }

    public void getFileList(String path) {
        File file = new File(path);
        // 获取该文件夹下的所有文件
        File[] fileList = file.listFiles();
        // 便利所有的文件夹
        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                dealFile(fileList[i]);
            }
        }
    }

    private void dealFile(File file){
        String newPath = file.getAbsolutePath();
        //如果是文件夹
        if (file.isDirectory()) {
            if(isCD) {
                getFileList(newPath);
            }else {
                System.out.println("dir:" + file.getAbsolutePath());
                SevenZCompressUtils.compress(passWord, newPath + fileType, file.getAbsolutePath() + "/*");
            }
        }else {
            if(isCD) {
                System.out.println(sourceFilePath + " >>> " + newPath);
                newPath = newPath.replace(sourceFilePath, sourceFilePath + "7z");
                System.out.println(newPath);
            }
            SevenZCompressUtils.compress(passWord, newPath + fileType, file.getAbsolutePath());
        }
    }

    /**
     *
     * @param pas 密码
     * @param targetFile 目标文件
     * @param sourceFile 源文件 path/* path
     */
    private static void compress(String pas,String targetFile,String sourceFile){

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

    /**
     *
     * @param z7 压缩文件
     * @param targetFile 目标路径
     * @param pas 密码
     */
    private static void deCompression(String z7,String targetFile,String pas){
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

}
