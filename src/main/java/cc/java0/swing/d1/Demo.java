package cc.java0.swing.d1;

import javax.swing.*;
import java.awt.*;

/**
 * @author everforcc 2021-10-14
 */
public class Demo {

    public static void main(String[] args) {
        //
        JPanel panel = getJPanelGridLayout();

        // 添加按钮
        addPanel(panel);

        // 展示按钮
        showJFrame(panel);
    }


    public static void addPanel(JPanel panel){
        for(int i=0;i<9;i++){
            JButton jButton = new JButton("按钮" + i);
            panel.add(jButton);
        }
    }

    public static JFrame getJFrame(){
        JFrame jFrame = new JFrame("测试窗口");
        jFrame.setSize(300, 300);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        return jFrame;
    }

    public static void showJFrame(JPanel panel){
        JFrame jFrame = getJFrame();
        jFrame.setContentPane(panel);
        jFrame.setVisible(true);        // PS: 最后再设置为可显示(绘制), 所有添加的组件才会显示
    }

    // 1.流式布局
    public static JPanel getJPanelFlowLayout(){
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEADING);
        JPanel panel = new JPanel(flowLayout);
        return panel;
    }

    // 2.流式布局
    public static JPanel getJPanelGridLayout(){
        // 创建 3 行 3 列 的网格布局
        GridLayout layout = new GridLayout(3,3);
        //layout.setColumns(3);
        // 设置 水平 和 竖直 间隙
        layout.setHgap(10);
        layout.setVgap(10);

        JPanel panel = new JPanel(layout);
        return panel;
    }

    // Demo3 GridBagLayout（网格袋布局）


}
