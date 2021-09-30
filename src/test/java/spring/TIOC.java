package spring;

import cc.frame.spring.ioc.ServiceIOC;
import org.junit.jupiter.api.Test;

/**
 * @author everforcc 2021-09-29
 */
public class TIOC {
    ServiceIOC serviceIOC = new ServiceIOC();
    @Test
    void timpl(){
        serviceIOC.TIOC();
    }

}
