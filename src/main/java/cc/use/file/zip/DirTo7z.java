package cc.use.file.zip;

import cc.constant.ConstantFile;
import cc.core.file.zip.SevenZCompressUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author c.c.
 * @date 2021/2/8
 */
@Slf4j
public class DirTo7z {

    // 密码
    private static final String passWord = "自己的密码";
    // 需要压缩的文件夹
    private static final String sourceFilePath = ConstantFile.L1_javaFilePath + "";
    // 是否进入文件夹
    private static final boolean isCD = true;
    // 文件后缀,可以多 "." 但不能少 "."
    // private static final String fileType = ".7z";// 如果要别的格式，还要同步修改命令

    /**
     * 1.不进入文件夹
     * 给定一个目录，把文件文件夹都压缩了,放到当前目录下
     * 2.进入文件夹
     * 给定一个目录例如 /temp 创建文件夹 /temp7z
     * 遍历压缩
     */
    public static void main(String[] args) {
        log.info("是否进入目录:" + isCD);
        // 加个构造，这个位置不写逻辑
        SevenZCompressUtils sevenZCompressUtils = new SevenZCompressUtils(passWord,sourceFilePath,isCD);
        sevenZCompressUtils.getFileList(sourceFilePath);
    }

}
