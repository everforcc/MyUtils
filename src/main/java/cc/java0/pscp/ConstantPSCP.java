package cc.java0.pscp;

/**
 * @author everforcc 2021-09-28
 */
public class ConstantPSCP {
    // 官网安装 https://www.chiark.greenend.org.uk/~sgtatham/putty/

    /**
     * 密码
     * 文件目录（win）
     * 用户名
     * ip
     * 目标文件目录
     */
    public static String SEND = "pscp -pw %s -r %s %s@%s:%s"; // 如果pscp不存在 set PATH=C:\path\to\putty\directory;%PATH%
    /**
     * 密码
     * 用户名
     * ip
     * 文件目录
     * 目标件目录（win）
     */
    public static String DOWN = "pscp -pw %s -r %s@%s:%s %s";

}
