package cn.cc.core.design;

import org.junit.jupiter.api.Test;

/**
 * @author c.c.
 * @date 2021/2/26
 */
public class Computer {
    protected String mBoard;
    protected String mDisplay;
    protected String mOs;


    private Computer(Builder builder) {
        this.mOs = builder.mOs;
        this.mBoard = builder.mBoard;
        this.mDisplay = builder.mDisplay;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "mBoard='" + mBoard + '\'' +
                ", mDisplay='" + mDisplay + '\'' +
                ", mOs='" + mOs + '\'' +
                '}';
    }


    static class Builder {
        protected String mBoard;
        protected String mDisplay;
        protected String mOs;

        public Builder setmOs(String mOs) {
            this.mOs = mOs;
            return this;
        }

        public Builder setmBoard(String mBoard) {
            this.mBoard = mBoard;
            return this;
        }

        public Builder setmDisplay(String mDisplay) {
            this.mDisplay = mDisplay;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

class TestBuild{

    @Test
    void t1(){
        Computer macbook = new Computer.Builder()
                .setmBoard("board")
                .setmDisplay("sowhat")
                .setmOs("Mac")
                .build();
        System.out.println(macbook);
    }

}