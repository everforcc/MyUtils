package cc.java0.swing.d5.d1;

import javax.swing.*;
import java.awt.*;

/**
 * @author guokailong 2021-10-19
 */
public class DemoSizeLocation {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(250, 250);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建内容面板，使用浮动布局
        JPanel panel = new JPanel(new FlowLayout());

        // 创建标签组件
        JLabel label = new JLabel("一个标签");

        // 添加标签到内容面板
        panel.add(label);

        jf.setContentPane(panel);

        /*
         * 布局绘制并显示，组件的坐标和宽高在这里确定，并设置回组件，在这句代码之后才能获取到组件的坐标和宽高
         */
        jf.setVisible(true);

        // 获取标签的坐标（相对于父容器）
        Point labelLocation = label.getLocation();
        // 获取标签的坐标（相对于屏幕左上角）
        Point labelLocationOnScreen = label.getLocationOnScreen();
        // 获取标签的宽高
        Dimension labelSize = label.getSize();

        System.out.println("标签: Location = " + labelLocation
                + ", LocationOnScreen = " + labelLocationOnScreen
                + ", Size = " + labelSize);

        /*
         * 对于 JFrame（窗口）的 坐标 和 宽高 获取
         */
        // 获取窗口的坐标（相对于父容器，对于窗口而言，父容器就是屏幕，所以此坐标实际上就是相对于屏幕左上角的坐标）
        Point jfLocation = jf.getLocation();
        // 获取窗口的坐标（相对于屏幕左上角）
        Point jfLocationOnScreen = jf.getLocationOnScreen();
        // 获取窗口的宽高（包含标题栏）
        Dimension jfSize = jf.getSize();

        System.out.println("窗口: Location = " + jfLocation
                + ", LocationOnScreen = " + jfLocationOnScreen
                + ", Size = " + jfSize);
    }

}
