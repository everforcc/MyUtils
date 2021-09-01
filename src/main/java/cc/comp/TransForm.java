package cc.comp;

/**
 * Yukino
 * 2020/6/18
 */
public class TransForm {

    /**
     * 数据类型互相转换
     */

    public void DoubleToInt(){
        int a =new Double(1.0).intValue();
    }

    private void baseMsg(){

        Integer integer = 1;
        integer.doubleValue();
        integer.floatValue();

        Double doubled = 1.0;
        doubled.intValue();
        doubled.floatValue();

        Float floatt = 1.0f;
        floatt.intValue();
        floatt.doubleValue();

    }

}
