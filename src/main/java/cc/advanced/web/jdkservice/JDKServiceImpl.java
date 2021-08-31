package cc.advanced.web.jdkservice;

import javax.jws.WebService;

/**
 * @author c.c.
 * @date 2021/2/19
 */
@WebService
public class JDKServiceImpl implements JDKService {
    @Override
    public String sayHel(String name) {
        System.out.println("hello world "+name);
        return "hello world "+name;
    }
}
