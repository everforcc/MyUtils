package cc.java0.swing.d5.d3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author everforcc 2021-10-19
 */
public class DemoTrueModel {

    public static void main(String[] args) {
        // 此处处于 主线程，提交任务到 事件调度线程 创建窗口
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        // 此处处于 事件调度线程
                        createGUI();
                    }
                }
        );
    }

    public static void createGUI() {
        // 此处处于 事件调度线程
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(300, 300);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JButton btn = new JButton("Btn");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 此处处于 事件调度线程（所有监听器的回调都在 事件调度线程 中回调）
            }
        });

        jf.setContentPane(panel);
        jf.setVisible(true);
    }

}
