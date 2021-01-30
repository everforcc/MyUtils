package cn.cc.core.novel2;

import cn.cc.core.web.http.use.novel2.NovelMenu;
import org.junit.jupiter.api.Test;

/**
 * @author c.c.
 * @date 2020/12/26
 */
public class Novel2Test {

    NovelMenu novelMenu = new NovelMenu();

    @Test
    void menu(){
        novelMenu.bookMenu("https://www.loveyuedu.com/yuedu/21531/");
    }

}
