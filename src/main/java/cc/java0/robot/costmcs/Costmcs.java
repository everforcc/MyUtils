package cc.java0.robot.costmcs;

import cc.java0.robot.utils.KeyUtils;
import cc.java0.robot.utils.MouseUtil;

import java.awt.*;

public class Costmcs {

    /**
     * 适用 1920*1080
         * 全屏 100% 1    1   1
     * 登陆后，登陆没写随后加
     * @param args
     */

    public static void main(String[] args) {
        try{
            /**
             * 选中招待费进入
             */
            //chooseZD();
            /**
             * 填写最基础的信息
             */
            writeMsg();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 填写招待费申请单
     */

    /**
     * 随后计算所有偏移量
     */

    //1.打开页面选择招待费
    //支付申请单创建 72，340  2s
    //选择 1217，297
    //     1217，317
    //     1021.337
    public static void chooseZD()throws Exception{
        Robot robot=new Robot();
        int ms = 100;
        //选中下面菜单
        robot.mouseMove(220,1060);
        MouseUtil.leftClick(robot,ms);

        //单击创建
        robot.mouseMove(72,340);
        MouseUtil.leftClick(robot,ms);
        robot.delay(5000);

        //点选择
        robot.mouseMove(1217,297);
        //robot.delay(3000);
        //robot.mousePress(InputEvent.BUTTON1_MASK); //按下左键
        //robot.mouseRelease(InputEvent.BUTTON1_MASK); //松开左键
        MouseUtil.leftClick(robot,ms);
        robot.delay(500);

        //移动到招待费
        //for(int i=0;i<5;i++) {
            robot.mouseMove(1217, 317);
        //}
        //robot.delay(1000);
        MouseUtil.leftClick(robot,ms);

        robot.delay(1000);

        robot.mouseMove(1021,337);
        //robot.delay(3000);
        MouseUtil.leftClick(robot,ms);
        robot.delay(8000); //读取页面延迟
    }

    public static void writeMsg()throws Exception{
        Robot robot=new Robot();
        int ms = 100;

        /* 最后删除 */
        //选中下面菜单
        /*robot.mouseMove(220,1060);
        MouseUtil.leftClick(robot,ms);*/
        /* 最后删除 */

        // 发票类型613,624
        robot.mouseMove(613,624);
        //robot.mouseMove(613,604);
        MouseUtil.leftClick(robot,ms);
        robot.delay(300);//需要下拉框弹出至少需要300毫秒
        //其他602，686
        robot.mouseMove(602,686);
        MouseUtil.leftClick(robot,ms);


        //支付申请单明细  都有公共的height
        //公司465，845
        robot.mouseMove(465,845);
        MouseUtil.leftClick(robot,ms);
        robot.delay(3000);//3s 延迟
        //江苏 1023,529
        robot.mouseMove(1023,529);
        MouseUtil.leftDoubleClick(robot,ms);

        //费用code 665 844
        robot.mouseMove(665,844);
        MouseUtil.leftClick(robot,ms);
        robot.delay(3000);//3s 延迟
        //选中第一个
        robot.mouseMove(1031,442);
        MouseUtil.leftDoubleClick(robot,ms);

        //日期 797 844
        robot.mouseMove(797,844);
        MouseUtil.leftClick(robot,ms);
        KeyUtils.strKey(robot,"2019-12-20",100);
        KeyUtils.ENTER(robot,ms);

        //公司名称
        for(int i=0;i<7;i++) {
            KeyUtils.TAB(robot, ms);
            KeyUtils.strKey(robot, "1", 100);
        }

        for(int i=0;i<7;i++) {
            KeyUtils.TAB(robot, ms);
            KeyUtils.strKey(robot, "1", 100);
        }

        //险种 1488 845
        robot.mouseMove(1488,845);
        MouseUtil.leftClick(robot,ms);
        robot.delay(3000);//3s 延迟
        //0000
        robot.mouseMove(980,500);
        MouseUtil.leftDoubleClick(robot,ms);

        // 渠道类型 1742 844
        robot.mouseMove(1742,844);
        MouseUtil.leftClick(robot,ms);
        robot.delay(3000);//3s 延迟
        //0000
        robot.mouseMove(974,441);
        MouseUtil.leftDoubleClick(robot,ms);

        //支付申请单明细 fileio


        MouseUtil.doRollMove(robot,100,4);


        //填写附件相关信息 start
        robot.delay(1000);
        robot.mouseMove(250,668);
        MouseUtil.leftClick(robot,ms);
        //a.txt
        KeyUtils.strKey(robot,"a.txt",100);
        robot.delay(100);
        KeyUtils.ENTER(robot,1);
        robot.delay(100);

        //附件张数 965 687
        robot.delay(2000);
        robot.mouseMove(965,687);
        MouseUtil.leftClick(robot,5);
        MouseUtil.leftClick(robot,5);
        KeyUtils.strKey(robot,"1",100);
        robot.delay(1000);
        //附件金额
        robot.mouseMove(1254,687);
        MouseUtil.leftClick(robot,5);
        MouseUtil.leftClick(robot,5);
        MouseUtil.leftClick(robot,5);
        KeyUtils.strKey(robot,"1.01",100);
        robot.delay(1000);
        //填写附件相关信息 fileio


        //本申请人对发票的真实性合法性负责
        //  238 767
        robot.delay(1000);
        robot.mouseMove(238,767);
        MouseUtil.leftClick(robot,ms);
        robot.delay(1000);
        // 提交 1077 801
        robot.mouseMove(1077,801);
        MouseUtil.leftClick(robot,ms);
    }



}
