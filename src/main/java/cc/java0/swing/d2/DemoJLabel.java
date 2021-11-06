package cc.java0.swing.d2;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author everforcc 2021-10-14
 */
public class DemoJLabel {
    // JLabel，标签。标签主要用于展示 文本 或 图片，也可以 同时显示文本和图片。
    public static void main(String[] args) throws MalformedURLException {
        JFrame jf = new JFrame("测试窗口");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(200, 200);
        // 创建内容面板，默认使用流式布局
        JPanel panel = new JPanel();

        /*
         * 只显示文本
         */
        JLabel label01 = new JLabel();
        label01.setText("Only Text");
        label01.setFont(new Font(null, Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
        panel.add(label01);

        /*
         * 只显示图片
         */
        JLabel label02 = new JLabel();
        label02.setIcon(new ImageIcon(new URL("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png"),"C:\\test\\7z\\dir\\xm.jpg"));
        panel.add(label02);

        /*
         * 同时显示文本和图片
         */
        JLabel label03 = new JLabel();
        label03.setText("文本和图片");
        label03.setIcon(new ImageIcon(new URL("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png"),"C:\\test\\7z\\dir\\xm.jpg"));
        label03.setHorizontalTextPosition(SwingConstants.CENTER);   // 水平方向文本在图片中心
        label03.setVerticalTextPosition(SwingConstants.BOTTOM);     // 垂直方向文本在图片下方
        panel.add(label03);

        jf.setContentPane(panel);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
