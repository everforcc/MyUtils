package cc.design.proxy.type2;

/**
 * @author everforcc 2021-09-25
 */
public interface UserService {

    /**
     * 面相切面编程
     * 1. 针对同一类问题的统一处理
     * 某种业务类型，需要的同一类流程，可以统一起来
     * 如果使用公共方法，还需要重复调用，但是切面编程就可以一刀切
     * eg：1.1. 银行的黑名单，很多接口都需要有
     *     1.2. 文件下载，只要提供地址和路径
     *     1.3.
     */

    void method_1();
    void method_2();
    default void checkBlackList(String userID){
        System.out.printf("校验黑名单:[%s],然后切面读取返回值",userID);
    }
    default void downFile(String filePah){
        System.out.printf("下载文件:[%s]",filePah);
    }

}
