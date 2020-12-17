package cn.cc.core.math.utils;

import java.util.Random;

/**
 * @author c.c.
 * @date 2020/12/17
 */
public class MathUtils {

    public static void main(String[] args) {

        randomNum();
    }

    // 科学记数法 1.60105E9
    private static void math_E(){
        // 处理数学上的问题
        long l = Math.round(1.60105E9);
        System.out.println(l);
        // Math
        System.out.println("e:"+Math.E);
        System.out.println("π:"+Math.PI);
        System.out.println(Math.cos(0));
        System.out.println(Math.cos(Math.PI));
        System.out.println(Math.pow(2,3));
    }

    //随机数
    public static void randomNum(){
        //Long num = new Random().nextLong();
        Integer integer = new Random().nextInt(100);
        //该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。\
        System.out.println(integer);
    }
}
