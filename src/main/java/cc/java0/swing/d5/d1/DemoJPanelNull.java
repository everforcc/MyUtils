package cc.java0.swing.d5.d1;

import javax.swing.*;
import java.awt.*;

/**
 * @author everforcc 2021-10-19
 */
public class DemoJPanelNull {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(250, 250);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null);

        // 创建一个标签，设置其背景为灰色（方便查看组件边界）
        JLabel label = new JLabel("一个标签");
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);

        // 设置标签的坐标和宽高
        label.setLocation(50, 50);  // 坐标指的是相对于父容器左上角的坐标
        label.setSize(100, 100);     // 绝对布局的宽高设置，使用 setSize(...)

        // 也可以使用下面一句代码一次性设置坐标和宽高
        // label.setBounds(50, 50, 100, 50);

        panel.add(label);

        jf.setContentPane(panel);
        jf.setVisible(true);
    }

}
