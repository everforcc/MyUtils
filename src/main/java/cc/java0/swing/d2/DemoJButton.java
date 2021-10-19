package cc.java0.swing.d2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author guokailong 2021-10-14
 */
public class DemoJButton {

    public static void main(String[] args) {
        //t1();
        t2();
    }

    private static void t1(){
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(200, 200);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        // 创建一个按钮
        final JButton btn = new JButton("测试按钮");

        // 添加按钮的点击事件监听器
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取到的事件源就是按钮本身
                // JButton btn = (JButton) e.getSource();

                System.out.println("按钮被点击");
            }
        });

        panel.add(btn);

        jf.setContentPane(panel);
        jf.setVisible(true);
    }

    public static void t2(){
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(200, 200);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        final JButton btn = new JButton();

        System.out.println(System.getProperty("os.name"));
        if(System.getProperty("os.name").contains("Windows")){
            btn.setContentAreaFilled(false);    //不绘制默认按钮背景
            btn.setFocusPainted(false);           //不绘制图片或文字周围的焦点虚框
        }

        // 设置按钮的默认图片
        btn.setIcon(new ImageIcon("src/main/resources/pic/unclick.png"));

        // 设置按钮被点击时的图片
        btn.setPressedIcon(new ImageIcon("src/main/resources/pic/click.png"));

        // 不绘制边框
        btn.setBorderPainted(false);

        // 添加按钮点击事件监听器
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("按钮被点击了");
            }
        });

        panel.add(btn);

        jf.setContentPane(panel);
        jf.setVisible(true);
    }

}
