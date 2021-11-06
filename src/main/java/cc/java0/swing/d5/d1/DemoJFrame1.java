package cc.java0.swing.d5.d1;

import javax.swing.*;
import java.awt.*;

/**
 * @author everforcc 2021-10-19
 */
public class DemoJFrame1 {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        // 设置内容面板的首选尺寸
        panel.setPreferredSize(new Dimension(300, 300));

        // 设置内容面板到窗口
        jf.setContentPane(panel);

        // 包裹内容
        jf.pack();

        // 必须在尺寸确定后（pack之后），再设置窗口位置
        jf.setLocationRelativeTo(null);

        /* 如此设置，在任何操作系统上，内容面板的尺寸都固定为 300*300，并且在屏幕居中显示*/

        jf.setVisible(true);
    }

}
