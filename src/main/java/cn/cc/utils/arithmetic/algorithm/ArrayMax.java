package cn.cc.utils.arithmetic.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayMax {
//    static int[] ary = {-2,1,-3,4,-1,2,1,-5,4};
static int[] ary = {99,-98,97,-1,99};
    public static void main(String[] args) {
        //分析过程
        //a1();
        //基础算法
        //a2();
        //局部变量
        a3();
    }

    /* 题目：
     * 给定一个整数数组（有正数有负数），找出总和最大的连续数列，并返回总和。
     * 示例：
     * 输入： [-2,1,-3,4,-1,2,1,-5,4]
     *        1 -3 4 -1 3 -5 4
     * 输出： 6
     * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * 解题要求：
     * 1.不寻求网络答案，自主完成解题
     * 2.时间复杂度优化到O(N)的优先考虑
     * 3.在开发环境中完成测试并截图
     */
    public static void a1(){
        int[] arr={1,2,3,-5,7};
        // 取出数组连续的最大值
        // 1.找到第一个正数和最后一个正数,判断是否为同一个 位置 ，如果不是 下一步

        // 2.开头和结尾都是正数,

        // 3.分组  连续的正数相加，连续的负数相加，生成新的数组>>形成连续的  + - + - + 的结构 ,
        // 并找到最大的一个数 存起来 ,期间也要进行累加
        // 声明一个数组用来存坐标（两位的，一个存坐标，一个存偏移量）

        // 然后 判断 第二个负数是否大于一个正数，如果是那么就舍弃前两个 ，并记录第一个值
        // 问题变成 +-+-+ 怎么求最大值



        // 结尾也一定是 +-+ 的形式
        // 问题是中间那个大小咋判断
        //  {6,-5,7}


        // 把情况都列出来
        /**
         *  1.第一个最大 或 最后一个最大
         *  2.中间的最大
         *  3. +-+ 相加 是不是大于这两个正数中的任何一个
         */

        /**
         * 列举出所有的情况
         * 1.  1,0,1
         * 2.
         */

        /****************/
        // 找出最小的，然后每个都加上这个数
        // 不行很麻烦

        /****************/

        // 这个问题无论如何都要把所有情况都要算出来    也必须有个所有数的累加
        //
        //int[] temp = new int[]{0,1};//表示取出坐标1，只取一个元素
        //int[] temp2 = new int[]{0,2};//表示取出坐标1，取两个个元素

        // key存两个值，一个是坐标，一个是偏移量
        // value存加起来的和
        // 一次循环以后直接存所有的情况得值
        //Map<int[],Integer> map;
        Map<int[],Integer> map = new HashMap<>();
        // 每次存的时候先比较大小
        int[] resultAry;
        // 便利整个数组
        for(int i=0;i<arr.length;i++){
            // i 每次表示取出第几个，比如第五个 ,  1,2,3,4,5  假如第六个元素   从后向前辐射，6  设置 1-6,2-6,3-6,4-6,5-6,6-6
            int total = 0;
            // 每次都一个一个取出来累加
            for(int j=0 ; j<i ; j++){

            }
            // 先存入第一个元素
            map.put(new int[]{0,1},i);

        }


    }

    // 编码001
    public static void a2(){
        // 记录最大值结果
        int result = 0;
        // 临时记录中间值
        int temp = 0;
        // 原始数组
        //int[] ary={1,-1,2};
        // int[] ary = {-99,98,-99,98,-99,1};
        int[] ary = {-2,1,-3,4,-1,2,1,-5,4};
        // 开始结束坐标
        int start = 0;
        int end = 0;

        // 循环一次拿出结果
        for(int i=0;i<ary.length;i++) {
            // 1.每次取出当前元素与之前的数组累加
            for(int j=i;j>=0;j--){
                temp+=ary[j];
                // result 为历史最大
                if(temp>result){
                    // 记录坐标
                    start = j;
                    end = i;
                    result = temp;
                    System.out.println("i:"+i+",j:"+j+",temp:"+temp);
                }
            }
            // 一轮结束，重置temp
            temp = 0;
        }
        int[] resultAry = new int[end-start+1];
        int resultStart = 0;
        // 复制数组
        for(int i=start;i<=end;i++){
            resultAry[resultStart++]=ary[i];
        }
        System.out.println(Arrays.toString(resultAry));
        System.out.println("和:"+result);
    }

    // 编码002

    /**
     * 设计区域最大值，和全局最大值进行比较
     * 1. 区域最大值，全局最大值 \| 区域坐标，全局坐标
     *
     *
     */
    public static void a3(){

        // 原始数组
        // int[] ary={1,-1,2};
        // int[] ary = {-99,98,-99,98,-99,1};
        // int[] ary = {-2,1,-3,4,-1,2,1,-5,4};

        // 区域最大值
        int areaMax = -1;
        // 区域坐标
        int areaStart = -1;
        int areaEnd = -1;

        // 全局最大值
        int staticMax = -1;
        // 全局坐标
        int staticStart = -1;
        int staticEnd = -1;

        // 循环一次拿出结果
        for(int i=0;i<ary.length;i++) {
            /**
             * 判断逻辑
             * 1.取出当前值，判断是否大于0，
             * 2.如果大于0那么查看全局最大值和区域最大值,  >>  全局和局部最大值也需要判断>0
             */
            if(ary[i]>0){
                // 当前值大于0的情况
                // 局部计算
                // 如果局部最大值大于0
                if(areaMax>0){
                    // 那么和当前值相加，一定大于局部最大值
                    areaMax += ary[i];
                    // 只需要更新 结束坐标，开始坐标没有变化
                    areaEnd = i;
                }else {
                    //如果局部最大值小于0
                    //那么当前值就是最大的
                    areaMax = ary[i];
                    // 需要记录局部 开始结束坐标
                    areaStart = i;
                    areaEnd = i;
                }
                // 全局计算
                // 如果全局大于0
                if(staticMax<areaMax){
                    //如果全局最大值小于局部最大值，那么更新全局最大值和坐标
                    staticMax = areaMax;
                    staticStart = areaStart;
                    staticEnd = areaEnd;
                }
            }else {
                // 当前值小于0的情况
                // 只需要做累加就好了,只会影响局部最大值，全局没有影响
                areaMax += ary[i];
            }
        }

        if(staticStart>-1) {
            // 最后取出坐标即可
            int[] resultAry = new int[staticEnd - staticStart + 1];
            int resultStart = 0;
            // 复制数组
            for (int i = staticStart; i <= staticEnd; i++) {
                resultAry[resultStart++] = ary[i];
            }
            System.out.println(Arrays.toString(resultAry));
        }else {
            System.out.println("无");
        }
    }


}
