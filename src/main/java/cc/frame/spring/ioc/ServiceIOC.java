package cc.frame.spring.ioc;

import cc.frame.spring.ioc.iocannotation.IOCImplAnnotation;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author everforcc 2021-09-29
 */
public class ServiceIOC {

    @IOCImplAnnotation()
    public Acla acla;

    @JSONField
    public Acla acla2;

    public void TIOC(){
        acla.method("123");
    }


}
