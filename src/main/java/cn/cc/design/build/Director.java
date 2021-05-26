package cn.cc.design.build;

/**
 * @author c.c.
 * @date 2021/2/26
 */
public class Director {
    Builder mBuilser=null;

    public Director(Builder builer) {
        this.mBuilser = builer;
    }

    public void construct(String board,String display){
        mBuilser.buildDisplay(display);
        mBuilser.buildBoard(board);
        mBuilser.buildOs();
    }
}