package cc.use.url.http.game.ys;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author guokailong 2021-10-26
 */
public class FSCard_3 {

    /**
     * 一共有几种可能
     * 1. 是当期四星
     * 1.1 是1/3 不是 2/3
     *
     * 2. 不是，那么下次必定是
     * 2.1 下次1/6 不是2/6
     *
     *
     */
    private static int jczy = 10;//...十几次内存就爆了
    // 123,非，当非，是, 4,5，当非，是
    private static int[] depthFlow = new int[jczy];
    // 非当期,需,不需, 上次非档期本次 需，不需
    private static Double[] probabilityAry = new Double[]{0.5, 0.5/3.0, 1.0/3.0, 1.0/3.0, 2.0/3.0};
    private static Map<Integer,Double> map = new HashMap<>();

    public static void main(String[] args) {
        // 共120发
        int totalCard = 120;
        // 平均每10发出一个四星
        int average = 10;

        double isCurrent = 0.5;
        double isexpect = 1.0/3.0;

        boolean isCurrentFlag = false;
        List<int[]> root_List = new ArrayList<>();
        int[] rootFlow = new int[jczy];
        root_List.add(rootFlow);

        for(int i=0; i<jczy; i++){
            root_List = randomCard(root_List);
        }

        for(int[] ints: root_List){
            //System.out.println(Arrays.toString(ints));
            analyse(ints);
        }
        //System.out.println(root_List.size());

        System.out.println("概率");
        Double checkResult = 0.0;
        for(Map.Entry entry:map.entrySet()){
            System.out.println("出" + entry.getKey() + "次的概率为" + entry.getValue());
            checkResult += (Double)entry.getValue();
        }

        System.out.println("checkResult:" + checkResult);
        System.out.println("排序 >>>");
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(e -> System.out.println("个数: "+ e.getKey() +", 概率: "+ round(e.getValue())));

    }

    private static int depth = 0;
    public static List<int[]> randomCard(List<int[]> int_List){
        List<int[]> ary_List = new ArrayList<>();
        for(int[] intAry:int_List){
            int[] aryUnCurrent = copyAry(intAry);
            int[] aryUnExpect = copyAry(intAry);
            int[] aryExpect = copyAry(intAry);
            if (0==depth||1!=intAry[depth-1]) {
                // 1.1 如果是，那么本次还是1/2
                // 1.2 1/2 是当期 1/2不是当期
                // 当期的那个1/3概率是

                aryUnCurrent[depth] = 1;
                aryUnExpect[depth] = 2;
                aryExpect[depth] = 3;

                ary_List.add(aryUnCurrent);
                ary_List.add(aryUnExpect);
                ary_List.add(aryExpect);
            } else {
                // 2.1 如果不是,那么本次 1/1
                // 2.2 有1/3概率为需要的
                aryUnExpect[depth] = 4;
                aryExpect[depth] = 5;
                ary_List.add(aryUnExpect);
                ary_List.add(aryExpect);
            }
        }
        depth++;
        return ary_List;
    }

    public static int[] copyAry(int[] ary){
        int length = ary.length;
        int[] copyAry = new int[length];
        for(int i=0;i<length;i++){
            if(0==ary[i]){
                return copyAry;
            }
            copyAry[i] = ary[i];
        }
        return copyAry;
    }

    public static double round(Double d){
        return new BigDecimal(d).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void analyse(int[] ary){
        int total = 0;
        Double probability = 1.0;
        for(int i=0;i<ary.length;i++){
            probability *= probabilityAry[ary[i]-1];
            /*if(3==ary[i]||5==ary[i]){
                total++;
            }*/
            if(3==ary[i]||5==ary[i]){
                total++;
            }
        }
        if(map.containsKey(total)){
            map.put(total,map.get(total) + probability);
        }else {
            map.put(total,probability);
        }
    }

}




