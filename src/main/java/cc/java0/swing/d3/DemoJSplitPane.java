package cc.java0.swing.d3;

import javax.swing.*;

/**
 * @author everforcc 2021-10-14
 */
public class DemoJSplitPane {

    // JSplitPane（分隔面板） 左右分
    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(300, 300);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        // 创建分隔面板,构造确认分隔方式 JSplitPane.VERTICAL_SPLIT
        JSplitPane splitPane = new JSplitPane();

        // 设置左右两边显示的组件
        splitPane.setLeftComponent(new JButton("左边按钮"));
        splitPane.setRightComponent(new JButton("右边按钮"));

        // 分隔条上显示快速 折叠/展开 两边组件的小按钮
        splitPane.setOneTouchExpandable(true);

        // 拖动分隔条时连续重绘组件
        splitPane.setContinuousLayout(true);

        // 设置分隔条的初始位置
        splitPane.setDividerLocation(149);

        // 设置分割条的大小(宽度)
        splitPane.setDividerSize(2);

        // 设置分割条的位置
        //splitPane.setDividerLocation(300/2);

        // 把分隔面板作为内容面板添加到窗口并显示
        jf.setContentPane(splitPane);
        jf.setVisible(true);
    }

}
