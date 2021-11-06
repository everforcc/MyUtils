package cc.use.url.http.game.ys;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author everforcc 2021-10-26
 */
public class FSCard_1 {

    // 表是否为需要的四星
    private FSCard_1 uncurrent;
    private FSCard_1 expect;
    private FSCard_1 unXxpect;
    // 是否为当期四星
    private Boolean flag;
    private Boolean isExpect = false;
    private Double probability;

    public FSCard_1() {
    }

    public FSCard_1(Boolean flag) {
        this.flag = flag;
    }

    public FSCard_1(Boolean flag, Double probability) {
        this.flag = flag;
        this.probability = probability;
    }

    public FSCard_1(Boolean flag, Double probability, Boolean isExpect) {
        this.flag = flag;
        this.probability = probability;
        this.isExpect = isExpect;
    }

    public FSCard_1 getUncurrent() {
        return uncurrent;
    }

    public void setUncurrent(FSCard_1 uncurrent) {
        this.uncurrent = uncurrent;
    }

    public FSCard_1 getExpect() {
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

    public void setExpect(FSCard_1 expect) {
        this.expect = expect;
    }

    public FSCard_1 getUnXxpect() {
        return unXxpect;
    }

    public void setUnXxpect(FSCard_1 unXxpect) {
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
        return JSON.toJSONString(this);
    }

    @Getter
    @Setter
    class CardType{

    // 表示是否为当期四星
    private FSCard_1 current;
    private FSCard_1 unCurrent;

        public CardType() {
        }
    }

    private static int jczy = 3;
    public static void main(String[] args) {
        // 共120发
        int totalCard = 120;
        // 平均每10发出一个四星
        int average = 10;

        double isCurrent = 0.5;
        double isexpect = 1.0/3.0;

        boolean isCurrentFlag = false;

        FSCard_1 fsCard = new FSCard_1(true);

        List<FSCard_1> fsCardList = new ArrayList<>();
        fsCardList.add(fsCard);
        for(int i=0; i<jczy; i++){

            /**
             * 一共有几种可能
             * 1. 是当期四星
             * 1.1 是1/3 不是 2/3
             * 1.2 下次1/2 是
             *
             * 2. 不是，那么下次必定是
             */
            fsCardList = randomCard(fsCardList);
        }

        // 最终要什么?
        // 一次 出非当期概率，当期是否为需要的概率

        //System.out.println(fsCardList.size());
        System.out.println(fsCard.toString());
        analyse(fsCard);

    }

    public static List<FSCard_1> randomCard(List<FSCard_1> fsCardList){
        List<FSCard_1> fsCard_1List = new ArrayList<>();
        // 已知当前是不是，求下一次的概率
        for(FSCard_1 fsCard:fsCardList) {
            if (fsCard.getFlag()) {
                // 1.1 如果是，那么本次还是1/2
                // 1.2 1/2 是当期 1/2不是当期
                // 当期的那个1/3概率是
                FSCard_1 fsCardUnCurrent = new FSCard_1(false, 0.5);
                FSCard_1 fsCardExpect = new FSCard_1(true, 0.5 * 1.0 / 3.0, true);
                FSCard_1 fsCardUnExpect = new FSCard_1(true, 0.5 * 2.0 / 3.0, false);

                fsCard.setUncurrent(fsCardUnCurrent);
                fsCard.setExpect(fsCardExpect);
                fsCard.setUnXxpect(fsCardUnExpect);

                fsCard_1List.add(fsCardUnCurrent);
                fsCard_1List.add(fsCardExpect);
                fsCard_1List.add(fsCardUnExpect);
            } else {
                // 2.1 如果不是,那么本次 1/1
                // 2.2 有1/3概率为需要的
                FSCard_1 fsCardExpect = new FSCard_1(true, 1.0 / 3.0, true);
                FSCard_1 fsCardUnExpect = new FSCard_1(true, 2.0 / 3.0, false);

                fsCard.setExpect(fsCardExpect);
                fsCard.setUnXxpect(fsCardUnExpect);

                fsCard_1List.add(fsCardExpect);
                fsCard_1List.add(fsCardUnExpect);
            }
        }

        //fsCard_1List.forEach(System.out::println);

        return fsCard_1List;
    }

    private static int depth = 0;
    public static void analyse(FSCard_1 fsCard){
        if(Objects.isNull(fsCard)){
            System.out.println("该层分析结束");
            return;
        }
        // 是否为当前四星
        if(fsCard.getFlag()){
            System.out.println("第[" + depth + "]次为当前四星");
            // 是
            depth++;
            FSCard_1 fsCardExpect = fsCard.getExpect();
            FSCard_1 fsCardUnexpect = fsCard.getUnXxpect();
            if(Objects.nonNull(fsCardExpect)){
                System.out.println("连续" + depth + "次是需要的概率:" + fsCardExpect.getProbability());
            }else {
                System.out.println("分析结束");
                return;
            }
            analyse(fsCardExpect);
            analyse(fsCard.getUncurrent());
        }else {
            System.out.println("第[" + depth + "]次 --- 不 --- 为当前四星");
            // 否
            depth++;
            analyse(fsCard.getExpect());
        }
    }

}



