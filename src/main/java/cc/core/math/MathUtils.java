package cc.core.math;


import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

/**
 * @author c.c.
 * @date 2020/12/17
 */
public class MathUtils {

    public static void main(String[] args) {
        //randomNum();
        safeRandomNum();
        //t();
        //equation();
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

    /**
     * 安全随机数
     */
    public static void safeRandomNum(){
        /**
         *
         * <dependency>
         *        <groupId>org.apache.commons</groupId>
         *        <artifactId>commons-lang3</artifactId>
         *        <version>3.12.0</version>
         * </dependency>
         */
        System.out.println(RandomUtils.nextInt(-1, 1));
    }

    //
    private static void equation(){
        System.out.println("弧度: " + Math.toRadians(60.0)/Math.PI);
        System.out.println("一个double值的正平方根: " + Math.sqrt(4.0));
        System.out.println("a的b次方: " + Math.pow(4,2));
        System.out.println("正弦: " + Math.sin(Math.toRadians(30)));
    }

    public static void t(){
        /**
         * floor 向下取整
         ceil  向上取整
         round 则是4舍5入的计算，算法为Math.floor(x+0.5)，即将原来的数字加上0.5后再向下取整，
         所以，Math.round(11.5)的结果为12，Math.round(-11.5)的结果为-11。
         */
        System.out.println(Math.floor(1.4));
        System.out.println(Math.round(1.4));
        System.out.println(Math.ceil(1.4));
        System.out.println(Math.floor(1.5));
        System.out.println(Math.round(1.5));
        System.out.println(Math.ceil(1.5));
    }



}
