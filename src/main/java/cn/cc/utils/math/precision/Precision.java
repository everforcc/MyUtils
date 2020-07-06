package cn.cc.utils.math.precision;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * Yukino
 * 2020/4/30
 */
public class Precision {

    // 保留小数相关
    @Test
    public void money(){
        int a=1;
        int b=2;
        int c=3;
        System.out.println((double)a/c);
        System.out.println((double)b/c);

        System.out.println((float)a/c);
        System.out.println((float)b/c);

        double f1 = new BigDecimal((double)a/c).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        double f2 = new BigDecimal((float)b/c).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        System.out.println(f1);
        System.out.println(f2);
    }

    @Test
    public void ROUND_(){
        int a=1;
        int b=2;
        int c=3;
        double e=(double)a/c;
        double f=(double)b/c;
        System.out.println("e:"+e);
        System.out.println("f:"+f);

        // 变大
        double f11 = new BigDecimal(e).setScale(2, BigDecimal.ROUND_UP).doubleValue();
        double f12 = new BigDecimal(f).setScale(2, BigDecimal.ROUND_UP).doubleValue();
        System.out.println("ROUND_UP:"+f11);
        System.out.println("ROUND_UP:"+f12);

        // 变小
        double f21 = new BigDecimal(e).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        double f22 = new BigDecimal(f).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        System.out.println("ROUND_DOWN:"+f21);
        System.out.println("ROUND_DOWN:"+f22);

        // 变大
        double f31 = new BigDecimal(e).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        double f32 = new BigDecimal(f).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        double f33 = new BigDecimal(-e).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        double f34 = new BigDecimal(-f).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        System.out.println("ROUND_CEILING:"+f31);
        System.out.println("ROUND_CEILING:"+f32);
        System.out.println("ROUND_CEILING:"+f33);
        System.out.println("ROUND_CEILING:"+f34);

        //ROUND_FLOOR 小
        //ROUND_HALF_UP    四舍五入
        new BigDecimal(-e).setScale(2, BigDecimal.ROUND_HALF_UP);
        //ROUND_HALF_DOWN  五舍六入
        // ROUND_HALF_EVEN    银行家舍入法 ??
        // ROUND_UNNECESSARY  ??
    }

    // 科学记数法 1.60105E9
    @Test
    public void math_E(){
        long l = Math.round(1.60105E9);
        System.out.println(l);
    }

}
