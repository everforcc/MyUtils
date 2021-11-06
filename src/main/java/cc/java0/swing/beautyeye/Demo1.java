package cc.java0.swing.beautyeye;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;

/**
 * @author everforcc 2021-10-19
 */
public class Demo1 {

    // Java Swing 图形界面开发简介
    public static void main(String[] args) {

        try
        {
            // 单独去网盘下载jar包
            //设置本属性将改变窗口边框样式定义
            // BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
        }

        // 1. 创建一个顶层容器(窗口)
        // 创建窗口
        JFrame jFrame = new JFrame("测试窗口");
        // 设置窗口大小
        jFrame.setSize(250,250);
        // 窗口位置，中心屏幕
        jFrame.setLocationRelativeTo(null);
        // 当点击窗口的关闭按钮时退出程序（没有这一句，程序不会退出）
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 2. 创建中间容器（面板容器）
        JPanel panel = new JPanel();

        // 3. 创建一个基本组建（按钮），并添加到 面板容器 中
        JButton jButton = new JButton("测试按钮");
        panel.add(jButton);

        // 4. 把面板容器 作为窗口的内容面板设置到窗口
        jFrame.setContentPane(panel);

        // 5. 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
        jFrame.setVisible(true);

    }

}
