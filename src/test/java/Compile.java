import org.junit.jupiter.api.Test;

/**
 * @author 郭凯龙
 * @data 2021/8/27 0027
 */
public class Compile {

    @Test
    public void t(){
        // idea运行会编译检测，修改后跑一下看有没有问题
        System.out.println(1);
        {
            System.out.println(1);
        }
    }

}
