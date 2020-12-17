package cn.cc.core.math.utils;

import java.math.BigDecimal;

/**
 * @author c.c.
 * @date 2020/12/17
 */
public class BigDecimalUtils {

    public static void main(String[] args) {
        ROUND_TYPE();
    }

    // 主要是除法保留位数的问题
    public void divideUtils(BigDecimal a,BigDecimal b){
        // 保留三位小数
        BigDecimal c = a.divide(b,3);
        System.out.println(c);
    }

    private static void ROUND_TYPE(){
        int a=1;
        int b=2;
        int c=3;
        double e=(double)a/c;
        double f=(double)b/c;

        System.out.println((float)a/c);
        System.out.println((float)b/c);

        System.out.println("e:"+e);
        System.out.println("f:"+f);
        System.out.println("---");
        // 变大
        double f11 = new BigDecimal(e).setScale(2, BigDecimal.ROUND_UP).doubleValue();
        double f12 = new BigDecimal(f).setScale(2, BigDecimal.ROUND_UP).doubleValue();
        System.out.println("ROUND_UP:"+f11);
        System.out.println("ROUND_UP:"+f12);
        System.out.println("---");
        // 变小
        double f21 = new BigDecimal(e).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        double f22 = new BigDecimal(f).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        System.out.println("ROUND_DOWN:"+f21);
        System.out.println("ROUND_DOWN:"+f22);
        System.out.println("---");
        // 变大
        double f31 = new BigDecimal(e).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        double f32 = new BigDecimal(f).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        double f33 = new BigDecimal(-e).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        double f34 = new BigDecimal(-f).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        System.out.println("ROUND_CEILING:"+f31);
        System.out.println("ROUND_CEILING:"+f32);
        System.out.println("ROUND_CEILING:"+f33);
        System.out.println("ROUND_CEILING:"+f34);
        System.out.println("---");
        //ROUND_FLOOR 小
        //ROUND_HALF_UP    四舍五入
        double f41 = new BigDecimal(-e).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f41);
        //ROUND_HALF_DOWN  五舍六入
        // ROUND_HALF_EVEN    银行家舍入法 ??
        // ROUND_UNNECESSARY  ??
    }


    public static void t(){
        BigDecimal denominator = new BigDecimal(1);
        BigDecimal result = new BigDecimal(1);
        // 两个 BigDecimal 相除保留几位小数，然后怎么舍入
        result = result.add(denominator.divide(new BigDecimal(1), 10, BigDecimal.ROUND_HALF_UP));
        System.out.println(result);
    }

}
