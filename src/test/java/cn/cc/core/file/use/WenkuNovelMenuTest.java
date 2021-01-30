package cn.cc.core.file.use;

import org.junit.jupiter.api.Test;

/**
 * @author c.c.
 * @date 2020/12/24
 */
public class WenkuNovelMenuTest {
    private static ThreadGroup downGroup = new ThreadGroup("down");
    @Test
    public void t1(){

        String[] strings = new String[]{"富士见文库","集英社","讲谈社","角川文库","其他文库","少女文库","小学馆","一迅社","游戏剧本"};

        for (String s:strings) {
            WenkuNovelMenu wenkuNovelMenu = new WenkuNovelMenu(s);
            new Thread(downGroup,wenkuNovelMenu).start();
        }

        while(downGroup.activeCount() > 0) {

        }
    }
}
