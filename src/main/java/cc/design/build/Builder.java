package cc.design.build;

/**
 * @author c.c.
 * @date 2021/2/26
 */
public abstract class Builder {

    abstract void buildBoard(String board);
    abstract void buildDisplay(String display);
    abstract void buildOs();
    abstract Computer build();

}