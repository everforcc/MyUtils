package cn.cc.core.robot.utils;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyUtils {



    /**
     * 目前不支持中英文混合输入
     * @param robot
     * @param string
     */
    public static void strKey(Robot robot,String string,int ms){
        //开始和结束前各调一次
        robot.delay(ms);
        char[] chars = string.toCharArray();
        int value;
        for(char c:chars){

            System.out.println("c:"+c);
            /**
             * 处理数字 0-9 和符号 "-" line 244
             */
            if(44<=(c-0)&&(c-0)<=57){ //48<=c&& 符号
                robot.keyPress(c);
                robot.keyRelease(c);
                System.out.println("if:"+c);
            }
            /**
             * 处理大写字母
             */
            else if(65<=(c-0)&&(c-0)<=90){
                CAPS_LOCK(robot,1);
                robot.keyPress(c);
                robot.keyRelease(c);
                System.out.println("else if 大写 :"+c);
                CAPS_LOCK(robot,1);
            }
            /**
             * 处理小写字母
             */
            else if(97<=(c-0)&&(c-0)<=122){
                robot.keyPress(c-32);
                robot.keyRelease(c-32);
                System.out.println("else if 小写 :"+c);
            }
            /**
             * 处理特殊标点符号 - ,line 244     暂时不需要
             */
            else if(0<=c&&c<=45){
                robot.keyPress(KeyEvent.VK_MINUS);
                robot.keyRelease(KeyEvent.VK_MINUS);
                /*KeyEvent.VK_PERIOD; .
                KeyEvent.VK_MINUS; -
                KeyEvent.VK_COMMA; ，
                KeyEvent.VK_A*/
            }

        }

        //
        //robot.keyPress(KeyEvent.VK_SEMICOLON);
        //: Constant for the "^" key.
        //robot.keyPress(KeyEvent.VK_COLON);
        //robot.delay(ms);
    }

    /**
     * 复制            暂时用来处理位置的字符串
     * @param robot
     */
    public static void CTRL_C(Robot robot,int ms){
        //17
        robot.delay(ms);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * 黏贴
     * @param robot
     */
    public static void CTRL_V(Robot robot,int ms){
        robot.delay(ms);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * 单击回车
     * @param robot
     * @param ms
     */
    public static void ENTER(Robot robot,int ms){
        robot.delay(ms);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * 单击空格键
     * @param robot
     * @param ms
     */
    public static void SPACE(Robot robot,int ms){
        robot.delay(ms);
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
    }

    /**
     * 打开桌面
     * @param robot
     * @param ms
     */
    public static void WIN_D(Robot robot,int ms){
        robot.delay(ms);
        robot.keyPress(KeyEvent.VK_WINDOWS);
        robot.keyPress(KeyEvent.VK_D);
        robot.keyRelease(KeyEvent.VK_D);
        robot.keyRelease(KeyEvent.VK_WINDOWS);
    }

    /**
     * TAB
     * @param robot
     * @param ms
     */
    public static void TAB(Robot robot,int ms){
        robot.delay(ms);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
    }

    /**
     * CAPS_LOCK
     * @param robot
     * @param ms
     */
    public static void CAPS_LOCK(Robot robot,int ms){
        robot.delay(ms);
        robot.keyPress(KeyEvent.VK_CAPS_LOCK);
        robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
    }

    /**
     * 切换当前窗口
     * @param robot
     * @param ms
     * @param num
     */
    public static void ALT_TAB(Robot robot,int ms,int num){

        String str = "" + num ;
        if("".equals(str)||num==0){
            num=1;
        }

        robot.delay(ms);
        robot.keyPress(KeyEvent.VK_ALT);

        //表示切第几次
        for(int i=0;i<num;i++){
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }

        robot.keyRelease(KeyEvent.VK_ALT);
    }

}
