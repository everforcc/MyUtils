package cc.design.proxy.type2;

import cc.design.proxy.type2.impl.UserServiceImpl_1;

/**
 * @author everforcc 2021-09-25
 */
public class TUserService {
    public static void main(String[] args) {
        UserService userServiceImpl_1 = new UserServiceImpl_1();
        UserService proxy = new UserServiceProxy(userServiceImpl_1);

        proxy.method_1();
        proxy.method_2();
    }
}
