package cc.java0.robot;



import cc.core.date.utils.DateTimestamp;
import cc.java0.robot.utils.KeyUtils;
import cc.java0.robot.utils.MouseUtil;
import cc.java0.robot.utils.SystemUtils;

import java.awt.*;

public class TestRobot {

    public static void main(String[] args) {
        try {
            //LOL();
            //QQ();
            QQ2();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void click()throws Exception{
        Robot robot=new Robot();
        // 移动到指定位置，单击
        robot.mouseMove(1468, 985);
        for(int i=0;i<1000;i++) {
            MouseUtil.leftClick(robot, 10);
        }
    }


    public static void QQ2() throws Exception {

        Robot robot = new Robot();
        int ms = 2;//设置延迟
        System.out.println("系统时间:" + DateTimestamp.timestampToDateStr(System.currentTimeMillis()));

        //1.win+d 打开桌面
        KeyUtils.WIN_D(robot, ms);
        //2.打开qq聊天框
        robot.mouseMove(465, 1060);
        MouseUtil.leftClick(robot, ms);

        //3.选中会话窗口
        robot.mouseMove(1000, 500 );
        MouseUtil.leftClick(robot, ms);
        //MouseUtil.leftClick(robot, ms);
        for (int i = 0; i < 9999; i++) {
            //4.输入 测试
            //KeyUtils.strKey(robot, "zaizuodegeweidoushilese", 2);
            //KeyUtils.SPACE(robot, 200);
            SystemUtils.setClipbordContents(i+"只羊");
            KeyUtils.CTRL_V(robot,ms);
            //5.发送
            KeyUtils.ENTER(robot, 200);
        }
        System.out.println("系统时间:" + DateTimestamp.timestampToDateStr(System.currentTimeMillis()));
    }


    public static void QQ() throws Exception {

        Robot robot = new Robot();
        int ms = 2;//设置延迟
        System.out.println("系统时间:" + DateTimestamp.timestampToDateStr(System.currentTimeMillis()));

        //1.win+d 打开桌面
        KeyUtils.WIN_D(robot, ms);
        //2.打开qq聊天框
        robot.mouseMove(465, 1060);
        MouseUtil.leftClick(robot, ms);

        //3.选中会话窗口
        robot.mouseMove(1000, 500 );
        MouseUtil.leftClick(robot, ms);


        MouseUtil.leftClick(robot, ms);
        for (int i = 0; i < 10; i++) {
            //4.输入 测试
            // 在座的各位都是乐色zaizuodegeweidoushilese
            // liangsongjiushigelaji
            KeyUtils.strKey(robot, "zaizuodegeweidoushilese", 2);
            KeyUtils.SPACE(robot, 200);
            //5.发送
            KeyUtils.ENTER(robot, 10);
            System.out.println("发送" + i);
            KeyUtils.strKey(robot, "liangsongjiushigelaji", 2);
            KeyUtils.SPACE(robot, 200);
            //5.发送
            KeyUtils.ENTER(robot, 10);
        }
        System.out.println("系统时间:" + DateTimestamp.timestampToDateStr(System.currentTimeMillis()));
    }


    public static void LOL() throws Exception {
        //回车
        //打字
        //回车
        //打字
        Robot robot = new Robot();
        //1060
        robot.mouseMove(465, 1060);
        MouseUtil.leftClick(robot, 100);
        robot.delay(5000);
        //robot.mousePress(KeyEvent.VK_ALT);
        //robot.mousePress(KeyEvent.VK_TAB);
        //robot.mouseRelease(KeyEvent.VK_TAB);
        //robot.mouseRelease(KeyEvent.VK_ALT);
        robot.delay(5000);

        KeyUtils.ENTER(robot, 1);
        //for(int i=0;i<10;i++) {
        //4.输入 测试
        // 在座的各位都是乐色zaizuodegeweidoushilese
        KeyUtils.strKey(robot, "zaizuodegeweidoushilese", 2);
        KeyUtils.SPACE(robot, 100);
        //5.发送
        KeyUtils.ENTER(robot, 1);
        //System.out.println("发送"+i);
        KeyUtils.strKey(robot, "liangsongjiushigelaji", 2);
        KeyUtils.SPACE(robot, 100);
        //5.发送
        KeyUtils.ENTER(robot, 1);
        KeyUtils.strKey(robot, "zaizuodegeweidoushilese", 2);
        KeyUtils.SPACE(robot, 100);
        //5.发送
        KeyUtils.ENTER(robot, 1);
        KeyUtils.strKey(robot, "liangsongjiushigelaji", 2);
        KeyUtils.SPACE(robot, 100);
        //5.发送
        KeyUtils.ENTER(robot, 1);
        KeyUtils.strKey(robot, "zaizuodegeweidoushilese", 2);
        KeyUtils.SPACE(robot, 100);
        //5.发送
        KeyUtils.ENTER(robot, 1);

        KeyUtils.strKey(robot, "liangsongjiushigelaji", 2);
        KeyUtils.SPACE(robot, 100);
        //5.发送
        KeyUtils.ENTER(robot, 1);
        //}
    }

}
