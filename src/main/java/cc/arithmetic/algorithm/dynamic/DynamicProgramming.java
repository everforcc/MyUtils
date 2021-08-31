package cc.arithmetic.algorithm.dynamic;

/**
 * @author c.c.
 * @date 2020/12/22
 */
public class DynamicProgramming {
    public static void main(String[] args) {
        getMax();
    }

    // 初始化三角形
    public static void init(int[][] ary){
        for(int i = 0;i < 10;i++){
            for(int j = 0;j <= i ;j++){
                // 每行的值，从左到右
                ary[i][j] = j + 1;
            }
        }
    }

    // 下面的算法会修改原来的数组，复制一份出来
    public static void copy(int[][] ary,int[][] newAry){
        for(int i = 0;i < 10;i++){
            for(int j = 0;j <= i ;j++){
                newAry[i][j] = ary[i][j];
            }
        }
    }

    public static void printAry(int[][] ary){
        for(int i = 0;i < 10;i++){
            for(int j = 0;j <= i ;j++){
                // 每行的值，从左到右
                System.out.print(ary[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static int max = 0;  // 最大值
    static int step = 0; // 计算一共走了多少步

    // 逐行求最大值
    public static int getMax(){
        // 最大值
        //1.初始化数组
        int[][] ary = new int[10][10]; // 数组
        init(ary);
        int[][] newAry = new int[10][10];
        copy(ary,newAry);

        //2. 递归求和 从ary[1][0]开始， 下一行ary[1][0],ary[1][1]
        //int sum = ary[0][0] + ary[0][1];
        // 修改为下行，向上求和

        // 如果每次相加后，就临时存储,除了首尾，剩下的有两个数据来源，比较大小后与自身相加，存到自身
        for(int row = 1;row < 10;row++){ // 行
            for(int line = 0;line <= row ;line++){ //列
                System.out.println(sum(newAry, row, line));
            }
        }
        printAry(ary);
        printAry(newAry);
        System.out.println("一共计算次数:" + step );

        return 0;
    }

    // 数组, n行
    public static int sum(int[][] ary,int row,int line){

        step++;

        // 从第二行开始
        // 计划的是 当前位置，加上左上，或者上
        if(line == 0){
            // 第一列 只能加正上方的
            ary[row][line] = ary[row][line] + ary[row - 1][line];
        }else if(line == row){
            // 最后一列 只能加左上的
            ary[row][line] = ary[row][line] + ary[row - 1][line -1];
        }else {
            // 需要正上和左上对比,计算出两个中比较大的那个
            int temp = ary[row - 1][line -1] > ary[row - 1][line] ? ary[row - 1][line -1]:ary[row - 1][line];;
            ary[row][line] = ary[row][line] + temp;
        }

        if(max < ary[row][line] ){
            max = ary[row][line];
        }

        // 计算出当前位置的最大值,开始计算下一个位置
        return max;
    }

}
