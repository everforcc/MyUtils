package cc.frame.spring.ioc.impl;

import cc.frame.spring.ioc.Acla;

/**
 * @author everforcc 2021-09-29
 */
public class AclaImpl implements Acla {
    @Override
    public void method(String str) {
        System.out.printf("first annotation : [%s]",str);
    }
}
