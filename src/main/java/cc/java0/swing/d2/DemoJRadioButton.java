package cc.java0.swing.d2;

import javax.swing.*;

/**
 * @author guokailong 2021-10-14
 */
public class DemoJRadioButton {

    public static void main(String[] args) {
            JFrame jf = new JFrame("测试窗口");
            jf.setSize(200, 200);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();

            // 创建两个单选按钮
            JRadioButton radioBtn01 = new JRadioButton("男");
            JRadioButton radioBtn02 = new JRadioButton("女");

            // 创建按钮组，把两个单选按钮添加到该组
            ButtonGroup btnGroup = new ButtonGroup();
            btnGroup.add(radioBtn01);
            btnGroup.add(radioBtn02);

            // 设置第一个单选按钮选中
            radioBtn01.setSelected(true);

            panel.add(radioBtn01);
            panel.add(radioBtn02);

            jf.setContentPane(panel);
            jf.setVisible(true);
    }

}
