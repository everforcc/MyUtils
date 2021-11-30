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

    public static String L1_javaFilePath = "C:/test";

    static {
        // 可以根据系统判断下windows和linux取不同的文件夹
        System.out.println("ConstantFile:" + System.getProperty("os.name"));
        String userName = System.getProperty("user.name");
        if("everforcc".equals(userName)){
            L1_javaFilePath = "E:/test";
        }
    }

    // 20211130 确定以后使用包use就放到当前工具包的同级
    /**
     * 不同业务情况下的下一级
     * L 几表示第几层级
     */
    public static String L_businessFilePath = "/business";

    public static String L2_excle = "/excle";

    public static String L2_craw = "/craw";

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.vm.name"));
    }

}
