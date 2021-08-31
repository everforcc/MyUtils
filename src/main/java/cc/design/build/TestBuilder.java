package cc.design.build;

/**
 * @author c.c.
 * @date 2021/2/26
 */
public class TestBuilder {
    public static void main(String[] args){
        Builder builder=new MacBookBuilder();
        Director pcDirector=new Director(builder);
        pcDirector.construct("英特尔主板","Retina显示器");

        Computer computer = builder.build();
        System.out.println(computer.toString());
    }
}
