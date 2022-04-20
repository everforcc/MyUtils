import org.junit.jupiter.api.Test;

/**
 * @author everforcc
 * @data 2021/8/27 0027
 */
public class Compile {

    public static final int STATIC           = 0x00000008;

    @Test
    public void t(){
        // idea运行会编译检测，修改后跑一下看有没有问题
        System.out.println(1);
        {
            System.out.println(1);
        }
        System.out.println("static: " + STATIC);

        boolean a = (1 & STATIC) != 0;
        boolean b = (0 & STATIC) != 0;
        boolean c = (STATIC & STATIC) != 0;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

}
