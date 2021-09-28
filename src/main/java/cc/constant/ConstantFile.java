package cc.constant;

/**
 * @author everforcc
 * @data 2021/8/31 0031
 */
public class ConstantFile {

    /**
     * 用来处理代码中操作系统文件的问题，
     * 比较乱，公共定义到这个位置
     */

    public static String javaFilePath = ConstantFile.javaFilePath + "/test";

    static {
        // 可以根据系统判断下windows和linux取不同的文件夹
        System.out.println("ConstantFile:" + System.getProperty("os.name"));
    }

}
