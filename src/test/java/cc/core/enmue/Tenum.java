package cc.core.enmue;

import cc.core.enume.ObjEnum;
import org.junit.jupiter.api.Test;

/**
 * @author everforcc
 * @data 2021/9/1 0001
 */
public class Tenum {

    @Test
    public void t1(){
        System.out.println(ObjEnum.DANCE_USER_TYPE.type());
        System.out.println(ObjEnum.getValue("Manage"));
        System.out.println(ObjEnum.getValue("Manage2"));
        System.out.println(ObjEnum.Manage());
    }

}
