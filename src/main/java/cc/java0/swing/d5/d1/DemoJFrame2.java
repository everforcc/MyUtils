package cc.java0.swing.d5.d1;

import javax.swing.*;
import java.awt.*;

/**
 * @author everforcc 2021-10-19
 */
public class DemoJFrame2 {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 设置窗口的宽高（包括标题栏）
        jf.setSize(250, 250);

        // 设置窗口坐标（相对于父容器，对于窗口而言，父容器就是屏幕左上角）
        jf.setLocation(0, 0);

        // 设置窗口坐标，此方法是间接设置窗口坐标，把窗口设置到指定组件的中心，null 表示屏幕
        jf.setLocationRelativeTo(null);

        // 如果需要把窗口设置到屏幕左边或右边，可以通过下面方法获取屏幕的宽高
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel panel = new JPanel();

        jf.setContentPane(panel);

        // 包裹内容，此方法会根据内容重新计算窗口的宽高（之前设置的宽高将无效），
        // 此方法会改变窗口宽高，坐标却不会改变，如果需要将窗口设置到屏幕中心，
        // 需要重新调用 jf.setLocationRelativeTo(null) 方法计算窗口的坐标。
        jf.pack();

        jf.setVisible(true);
    }

}
