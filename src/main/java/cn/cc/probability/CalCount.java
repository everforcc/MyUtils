package cn.cc.probability;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * Yukino
 * 2020/6/28
 */
public class CalCount {

    /**
     * 用来处理概率论的问题
     */

    int a = 999;// 10   1000 都是2232

    int pow = 1;
    int dif = 10;

    @Test
    public void a1(){

        double endpre = 0 ;
        double startpre = 0;
        while (startpre<0.98){
            // 取出所有数据最可能概率的总数
            startpre = factorial100(a)/Math.pow(a,dif);
            //endpre = new BigDecimal(startpre).setScale(2, BigDecimal.ROUND_UP).doubleValue();
            System.out.println( "startpre:" + startpre );
            System.out.println( "a:" + a );
            a++;
            System.out.println(startpre<0.98);
        };
        System.out.println("a:"+a);

    }

    public double factorial100(double start){
        double result = 1;
        for(int i= 0;i<dif;i++){
            result*=start-i;
        }
        return result;
    }



    void Pn(){

    }


    // 取数然后算总数

    @Test
    public void a2(){
        double a=1;
        double b=100;
        System.out.println(a/b);
        double startpre = factorial100(a)/Math.pow(a,dif);
        System.out.println(startpre);
    }

}
