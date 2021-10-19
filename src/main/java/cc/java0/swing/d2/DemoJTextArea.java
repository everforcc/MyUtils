package cc.java0.swing.d2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author guokailong 2021-10-15
 */
public class DemoJTextArea {

    public static void main(String[] args) {
        try
        {
            //设置本属性将改变窗口边框样式定义
            // BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
        }
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(250, 250);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        // 创建一个 5 行 10 列的文本区域
        final JTextArea textArea = new JTextArea(5, 10);
        // 设置自动换行
        textArea.setLineWrap(true);
        // 添加到内容面板
        panel.add(textArea);

        // 创建一个提交按钮，点击按钮获取输入文本
        JButton btn = new JButton("提交");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("提交: " + textArea.getText());
            }
        });
        panel.add(btn);

        jf.setContentPane(panel);
        jf.setVisible(true);
    }

}
