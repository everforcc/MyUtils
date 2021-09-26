package cc.comp;

/**
 * @author everforcc
 * @data 2021/8/31 0031
 */
public class FDUtils {

    /**
     * https://blog.csdn.net/qq_44543508/article/details/101982298
     * 浮点型简单来说就是表示带有小数的数据，而恰恰小数点可以在相应的二进制的不同位置浮动，可能是这样就被定义成浮点型了
     */

    // float和double放一起比较
    public static void main(String[] args) {
        baseMsg();
    }

    private static void baseMsg(){
        System.out.println(123E-1);
        System.out.println(123E1);

        //默认为double类型，float需要在末尾加f
//        double a = 1.1;
//        float b = 1.1f;

        // TODO 还不理解
        float a = 12345678.90123456789f;
        double b=0.12345678901234567890;
        float c=0.12345678901234567890f;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

    }

}
