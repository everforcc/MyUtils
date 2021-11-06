package cc.java0.swing.d1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author everforcc 2021-10-15
 */
public class Demo6 {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(300, 200);

        // 创建卡片布局，卡片间水平和竖直间隔为 10
        final CardLayout layout = new CardLayout(10, 10);

        // 创建内容面板容器，指定布局管理器
        // 内部类使用外部变量，外部变量需要final修饰，但是删了也没影响
        final JPanel panel = new JPanel(layout);

        JButton btn01 = new JButton("");
        btn01.setIcon(new ImageIcon("src/main/resources/pic/unclick.png"));
        JButton btn02 = new JButton("Button02");
        JButton btn03 = new JButton("Button03");

        panel.add(btn01, "btn01");
        panel.add(btn02, "btn02");
        panel.add(btn03, "btn03");


        // 先显示第二个
        layout.show(panel, "btn01");

        jf.setContentPane(panel);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

        // 每间隔0.1秒切换显示下一个
        new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.next(panel);
            }
        }).start();
    }

}
