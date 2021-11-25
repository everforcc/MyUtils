package cc.design.design24template;

/**
 * @author c.c.
 * @date 2021/3/25
 */
public class TemplatePatternDemo {

    public static void main(String[] args) {

        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();

    }

}
