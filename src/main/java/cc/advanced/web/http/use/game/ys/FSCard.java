package cc.advanced.web.http.use.game.ys;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author everforcc 2021-10-26
 */
public class FSCard {

    // 表是否为需要的四星
    private FSCard expect;
    private FSCard unXxpect;
    // 是否为当期四星
    private Boolean flag;
    private Boolean isExpect = false;
    private Double probability;

    public FSCard() {
    }

    public FSCard(Boolean flag) {
        this.flag = flag;
    }

    public FSCard(Boolean flag, Double probability) {
        this.flag = flag;
        this.probability = probability;
    }

    public FSCard(Boolean flag, Double probability, Boolean isExpect) {
        this.flag = flag;
        this.probability = probability;
        this.isExpect = isExpect;
    }

    public FSCard getExpect() {
        return expect;
    }

    public void setExpect(Boolean expect) {
        isExpect = expect;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public void setExpect(FSCard expect) {
        this.expect = expect;
    }

    public FSCard getUnXxpect() {
        return unXxpect;
    }

    public void setUnXxpect(FSCard unXxpect) {
        this.unXxpect = unXxpect;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "FSCard{" +
                "expect=" + expect +
                ", unXxpect=" + unXxpect +
                ", flag=" + flag +
                ", isExpect=" + isExpect +
                ", probability=" + probability +
                '}';
    }

    @Getter
    @Setter
    class CardType{

    // 表示是否为当期四星
    private FSCard current;
    private FSCard unCurrent;

        public CardType() {
        }
    }

    public static void main(String[] args) {
        // 共120发
        int totalCard = 120;
        // 平均每10发出一个四星
        int average = 10;

        double isCurrent = 0.5;
        double isexpect = 1.0/3.0;

        boolean isCurrentFlag = false;

        FSCard fsCard = new FSCard(true);

        List<FSCard> fsCardList = new ArrayList<>();

        for(int i=0; i<1; i++){

            /**
             * 一共有几种可能
             * 1. 是当期四星
             * 1.1 是1/3 不是 2/3
             * 1.2 下次1/2 是
             *
             * 2. 不是，那么下次必定是
             */

            // 1. 判断是否是当前四星
            if(fsCard.getFlag()){
                // 1.1 如果是，那么本次还是1/2
                // 1.2 1/2 是当期 1/2不是当期
                fsCardList.add(new FSCard(true,0.5));
                // 当期的那个1/3概率是
                fsCardList.add(new FSCard(true,0.5 * 1.0/3.0,true));
                fsCardList.add(new FSCard(true,0.5 * 2.0/3.0));
                fsCard.setFlag(true);
            }else {
                // 2.1 如果不是,那么本次 1/1
                // 2.2 有1/3概率为需要的
                fsCardList.add(new FSCard(false,1.0/3.0,true));
                fsCardList.add(new FSCard(false,2.0/3.0));
            }

        }

        // 最终要什么?
        // 一次 出非当期概率，当期是否为需要的概率

        for(FSCard f:fsCardList){
            System.out.println(f.toString());
        }


    }

}



