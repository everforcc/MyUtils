package cc.design.proxy.type2;

import java.util.Date;

/**
 * @author guokailong 2021-09-25
 */
public class UserServiceProxy implements UserService{

    private UserService target; // 被代理的对象

    public UserServiceProxy(UserService target) {
        this.target = target;
    }

    @Override
    public void method_1() {
        before();
        target.method_1();   // 这里才实际调用真实主题角色的方法
        after();
    }

    @Override
    public void method_2() {
        before();
        target.method_2();   // 这里才实际调用真实主题角色的方法
        after();
    }


    private void before() {     // 在执行方法之前执行
        System.out.println(String.format("log start time [%s] ", new Date()));
    }
    private void after() {      // 在执行方法之后执行
        System.out.println(String.format("log end time [%s] ", new Date()));
    }

}
