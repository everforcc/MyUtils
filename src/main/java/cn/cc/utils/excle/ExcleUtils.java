package cn.cc.utils.excle;

import cn.cc.utils.excle.read.LoadExcle;

/**
 * Yukino
 * 2020/5/22
 */
public class ExcleUtils {

    //把旁边的总结为一个

    // ((x,y),z)
    // x>z
    // y>z
    // xy>z

    /**
     * 指定坐标来操作
     *
     * 原点  (x,y) xL  yL
     *
     */
    public static void main(String[] args) {
        LoadExcle loadExcle = new LoadExcle("C:\\Users\\Yukino\\Desktop\\OA人员清单\\门户用户清单.xls",1,1,3,1);
        //loadExcle     xy   xL  yL
        loadExcle.flow();
    }
}
