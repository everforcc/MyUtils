package cn.cc.core.web.http.use;

import cn.cc.use.url.http.novel.NovelDown;
import cn.cc.use.url.http.novel.NovelMenu;
import org.junit.jupiter.api.Test;

/**
 * @author c.c.
 * @date 2020/12/26
 */
public class Novel_loveyueduTest {
    //Novel_loveyuedu novel_loveyuedu = new Novel_loveyuedu();

    @Test
    void book(){
        //novel_loveyuedu.book("https://www.loveyuedu.com/yuedu/21531/");
    }

    NovelMenu novelMenu = new NovelMenu();

    @Test
    void menu(){
        novelMenu.bookMenu("https://www.loveyuedu.com/yuedu/21531/");
    }


    NovelDown novelDown = new NovelDown("","https://www.loveyuedu.com/yuedu/21531/13449731.html","");
    @Test
    void down(){
        novelDown.bookDown();
    }

}
