package cn.cc.utils.math;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * Yukino
 * 2020/4/30
 */
public class MathMethod {

    @Test
    public void random(){
        Integer integer = new Random().nextInt(100); //  [0,n)
        System.out.println(integer);
    }

    // 总结常用的Math的方法
    //随机数
    public static Integer randomNum(){
        //Long num = new Random().nextLong();
        Integer integer = new Random().nextInt(100);
        //该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
        return integer;
    }
}
