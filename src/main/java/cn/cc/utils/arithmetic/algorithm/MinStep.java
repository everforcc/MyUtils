package cn.cc.utils.arithmetic.algorithm;

import java.math.BigDecimal;

/**
 * @author c.c.
 * @date 2020/12/17
 */
public class MinStep {
    public static void main(String[] args) {
        // 用这个比较准确
        summation(10);
    }

    //1. 100 层楼，两个球， 扩展为无穷个球，极限为 2分法
    public static void minStep(){

    }

    //2. 问题二，概率求总数
    /**
     $$
     f_N(0)=N\sum_1^n\frac{1}{i}
     $$
     */
    // 最终目的，计算出对方的数据总量
    // 此方法,在已知数据总量的情况下，多少次能取出全部数据
    public static void summation(int scale){
        int N = 1000; // 假设有1000个数据

        BigDecimal denominator = new BigDecimal(1);
        BigDecimal result = new BigDecimal(1);

        for(int i = 1;i <= N;i++){
            // 每次 1/i 递增的值累加
            result = result.add(denominator.divide(new BigDecimal(i), scale, BigDecimal.ROUND_HALF_UP));
        }
        // 最后 * 外面的 N
        result = result.multiply(new BigDecimal(N));
        System.out.println(result);
        // BigDecimal 还需要总结
        // b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }

    public static void summation(){
       Double double1 = new Double(1000);
        Double sum = new Double(0);

        for(int i=1000;i>0;i--){
            //System.out.println(i);
            sum = double1/i + sum;
            //System.out.println(sum);
        }
        System.out.println(sum);
    }

}
