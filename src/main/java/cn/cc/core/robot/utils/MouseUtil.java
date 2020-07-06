package cn.cc.core.robot.utils;

import java.awt.*;
import java.awt.event.InputEvent;

public class MouseUtil {


    /**
     * 鼠标常见的五个操作
     * 1.单击鼠标左键
     * 2.双击鼠标左键
     * 3.单击鼠标右键
     * 4.双击鼠标右键
     * 5.滚轮问题        还是有瑕疵
     */

    /**
     *
     mouseMove(int x, int y) ;//移动鼠标到(x,y)坐标处

     void mousePress(int buttons) ;//按下鼠标上按键

     void mouseRelease(int buttons) ;//释放鼠标上按键

     void mouseWheel(int wheelAmt) ;//滚动鼠标滑轮 此方法中的wheelAmt指的是滑动滚轮上的刻度数. 如果此参数小于0,则表示向上滚动滑轮 如果此参数大于0,则表示向下滚动滑轮

     鼠标按键 参数  InputEvent.BUTTON1_MASK 左 InputEvent.BUTTON2_MASK 中 InputEvent.BUTTON3_MASK 右
     */



    /**
     * 单击鼠标左键
     * @param robot
     * @param ms
     */
    public static void leftClick(Robot robot,int ms) {
        robot.delay(ms);
        robot.mousePress(InputEvent.BUTTON1_MASK); //按下左键
        robot.mouseRelease(InputEvent.BUTTON1_MASK); //松开左键
    }

    /**
     * 双击鼠标左键
     * @param robot
     * @param ms
     */
    public static void leftDoubleClick(Robot robot,int ms) {
        robot.delay(ms);
        robot.mousePress(InputEvent.BUTTON1_MASK); //按下左键
        robot.mouseRelease(InputEvent.BUTTON1_MASK); //松开左键
        robot.mousePress(InputEvent.BUTTON1_MASK); //按下左键
        robot.mouseRelease(InputEvent.BUTTON1_MASK); //松开左键
    }

    /**
     * 右键单击
     * @param robot
     * @param ms
     */
    public static void rightClick(Robot robot,int ms) {
        robot.delay(ms);
        robot.mousePress(InputEvent.BUTTON3_MASK); //按下右键
        robot.mouseRelease(InputEvent.BUTTON3_MASK); //松开右键
    }

    /**
     * 右键双击
     * @param robot
     * @param ms
     */
    public static void rightDoubleClick(Robot robot,int ms) {
        robot.delay(ms);
        robot.mousePress(InputEvent.BUTTON3_MASK); //按下右键
        robot.mouseRelease(InputEvent.BUTTON3_MASK); //松开右键
        robot.mousePress(InputEvent.BUTTON3_MASK); //按下右键
        robot.mouseRelease(InputEvent.BUTTON3_MASK); //松开右键
    }

    /**
     *  1 约等于125px
     * @param robot
     * @param ms      操作延迟
     * @param mouseY  移动距离
     */
    public static void doRollMove(Robot robot,int ms,int mouseY){
        robot.delay(ms);
        robot.mouseWheel(mouseY);

    }
}
