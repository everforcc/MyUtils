package cc.design.build;

/**
 * @author c.c.
 * @date 2021/2/26
 */
public class MacBookBuilder extends Builder {

    private Computer mComputer=new MacBook();
    @Override
    void buildBoard(String board) {
        mComputer.setBoard(board);
    }

    @Override
    void buildDisplay(String display) {
        mComputer.setDisplay(display);
    }

    @Override
    void buildOs() {
        mComputer.setOs();
    }

    @Override
    Computer build() {
        return mComputer;
    }
}