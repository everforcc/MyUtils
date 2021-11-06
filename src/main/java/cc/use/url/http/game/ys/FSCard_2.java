package cc.use.url.http.game.ys;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * @author everforcc 2021-10-26
 */
public class FSCard_2 {

    // 表是否为需要的四星
    private FSCard_2 uncurrent;
    private FSCard_2 expect;
    private FSCard_2 unXxpect;
    // 是否为当期四星
    private Boolean flag;
    private Boolean isExpect = false;
    private Double probability;
    private String name;

    public FSCard_2() {
    }

    public FSCard_2(Boolean flag) {
        this.flag = flag;
    }

    public FSCard_2(Boolean flag, Double probability) {
        this.flag = flag;
        this.probability = probability;
    }

    public FSCard_2(Boolean flag, Double probability, Boolean isExpect) {
        this.flag = flag;
        this.probability = probability;
        this.isExpect = isExpect;
    }

    public FSCard_2 getUncurrent() {
        return uncurrent;
    }

    public void setUncurrent(FSCard_2 uncurrent) {
        this.uncurrent = uncurrent;
    }

    public FSCard_2 getExpect() {
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

    public void setExpect(FSCard_2 expect) {
        this.expect = expect;
    }

    public FSCard_2 getUnXxpect() {
        return unXxpect;
    }

    public void setUnXxpect(FSCard_2 unXxpect) {
        this.unXxpect = unXxpect;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    @Getter
    @Setter
    class CardType{

    // 表示是否为当期四星
    private FSCard_2 current;
    private FSCard_2 unCurrent;

        public CardType() {
        }
    }

    private static int jczy = 2;
    public static void main(String[] args) {
        // 共120发
        int totalCard = 120;
        // 平均每10发出一个四星
        int average = 10;

        double isCurrent = 0.5;
        double isexpect = 1.0/3.0;

        boolean isCurrentFlag = false;

        FSCard_2 fsCard = new FSCard_2(true);
        fsCard.setProbability(1.0);
        List<FSCard_2> fsCardList = new ArrayList<>();
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
        //System.out.println(fsCard.toString());
        analyse(fsCard);
        System.out.println();
        System.out.println(" >>> ");
        for(Map.Entry entry:map.entrySet()){
            System.out.println(entry.getKey() + " >>> " + entry.getValue());
        }

    }

    private static int nameNum = 0;
    public static List<FSCard_2> randomCard(List<FSCard_2> fsCardList){
        nameNum++;
        List<FSCard_2> fsCard_1List = new ArrayList<>();
        // 已知当前是不是，求下一次的概率
        for(FSCard_2 fsCard:fsCardList) {
            if (fsCard.getFlag()) {
                // 1.1 如果是，那么本次还是1/2
                // 1.2 1/2 是当期 1/2不是当期
                // 当期的那个1/3概率是
                FSCard_2 fsCardUnCurrent = new FSCard_2(false, 0.5);
                fsCardUnCurrent.setName("is,Current层级" + nameNum);
                FSCard_2 fsCardExpect = new FSCard_2(true, 0.5 * 1.0 / 3.0, true);
                fsCardExpect.setName("is,Expect层级" + nameNum);
                FSCard_2 fsCardUnExpect = new FSCard_2(true, 0.5 * 2.0 / 3.0, false);
                fsCardUnExpect.setName("is,UnExpect层级" + nameNum);

                fsCard.setUncurrent(fsCardUnCurrent);
                fsCard.setExpect(fsCardExpect);
                fsCard.setUnXxpect(fsCardUnExpect);

                fsCard_1List.add(fsCardUnCurrent);
                fsCard_1List.add(fsCardExpect);
                fsCard_1List.add(fsCardUnExpect);
            } else {
                // 2.1 如果不是,那么本次 1/1
                // 2.2 有1/3概率为需要的
                FSCard_2 fsCardExpect = new FSCard_2(true, 1.0 / 3.0, true);
                fsCardExpect.setName("Expect层级" + nameNum);
                FSCard_2 fsCardUnExpect = new FSCard_2(true, 2.0 / 3.0, false);
                fsCardUnExpect.setName("un,Expect层级" + nameNum);

                fsCard.setExpect(fsCardExpect);
                fsCard.setUnXxpect(fsCardUnExpect);

                fsCard_1List.add(fsCardExpect);
                fsCard_1List.add(fsCardUnExpect);
            }
        }

        //fsCard_1List.forEach(System.out::println);

        return fsCard_1List;
    }

    /*private static int unCurrentTotal = 0;
    private static int unExpectTotal = 0;
    private static int expectTotal = 0;
    private static Double probabilityTotal = 1.0;*/
    private static Map<String,Double> map = new HashMap<>();
    public static void analyse(FSCard_2 fsCard){
        int unCurrentTotal = 0;
        int unExpectTotal = 0;
        int expectTotal = 0;
        Double probabilityTotal = 1.0;
        //System.out.println(fsCard.toString());
        Double probability = fsCard.getProbability();
        probabilityTotal = probabilityTotal*probability;
        if(Objects.nonNull(fsCard.getUncurrent())){
            //System.out.println("1>>> " + fsCard.getName());
            unCurrentTotal++;
            // 如果是非当前
            FSCard_2 fsCard2_Uncurrent = fsCard.getUncurrent();
            if(Objects.nonNull(fsCard2_Uncurrent)) {
                analyse(fsCard2_Uncurrent);
            }
        }
        /*{
            System.out.println("结束_1");
            map.put(unCurrentTotal+","+unExpectTotal+","+expectTotal,probabilityTotal);
            unCurrentTotal = 0;
            unExpectTotal = 0;
            expectTotal = 0;
            probabilityTotal = 1.0;
        }*/

        if(Objects.nonNull(fsCard.getUnXxpect())){
            //System.out.println("2>>> " + fsCard.getName());
            unExpectTotal++;
            // 如果是当前非需要
            FSCard_2 fsCard2_Unexpect = fsCard.getUnXxpect();
            if(Objects.nonNull(fsCard2_Unexpect)) {
                analyse(fsCard2_Unexpect);
            }
        }
        /*{
            System.out.println("结束_2");
            map.put(unCurrentTotal+","+unExpectTotal+","+expectTotal,probabilityTotal);
            unCurrentTotal = 0;
            unExpectTotal = 0;
            expectTotal = 0;
            probabilityTotal = 1.0;
        }*/

        if(Objects.nonNull(fsCard.getExpect())){
            //System.out.println("3>>> " + fsCard.getName());
            expectTotal++;
            // 如果是需要
            FSCard_2 fsCard2_expect = fsCard.getExpect();
            if(Objects.nonNull(fsCard2_expect)) {
                analyse(fsCard2_expect);
            }
        }

        {
            System.out.println("结束_3: " + unCurrentTotal+","+unExpectTotal+","+expectTotal);
            map.put(unCurrentTotal+","+unExpectTotal+","+expectTotal,probabilityTotal);
            /*unCurrentTotal = 0;
            unExpectTotal = 0;
            expectTotal = 0;
            probabilityTotal = 1.0;*/
        }

        {
            /*System.out.println("可能性？>>> 结束");
            map.put(unCurrentTotal+","+unExpectTotal+","+expectTotal,probabilityTotal * probability);
            unCurrentTotal = 0;
            unExpectTotal = 0;
            expectTotal = 0;
            probabilityTotal = 1.0;*/
        }

    }

}




