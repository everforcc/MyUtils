package cc.java0.swing.d4;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

/**
 * @author everforcc 2021-10-19
 */
public class DemoJTableModel {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建内容面板，使用边界布局
        JPanel panel = new JPanel(new BorderLayout());

        // 使用表格模型创建一个表格
        JTable table = new JTable(new MyTableModel());

        // 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        // 把 表格内容 添加到容器中心
        panel.add(table, BorderLayout.CENTER);

        jf.setContentPane(panel);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    /**
     * 表格模型实现，表格显示数据时将调用模型中的相应方法获取数据进行表格内容的显示
     */
    public static class MyTableModel extends AbstractTableModel {
        /**
         * 表头（列名）
         */
        private Object[] columnNames = {"姓名", "语文", "数学", "英语", "总分"};

        /**
         * 表格所有行数据
         */
        private Object[][] rowData = {
                {"张三", 80, 80, 80, 240},
                {"John", 70, 80, 90, 240},
                {"Sue", 70, 70, 70, 210},
                {"Jane", 80, 70, 60, 210},
                {"Joe", 80, 70, 60, 210}
        };

        /**
         * 返回总行数
         */
        @Override
        public int getRowCount() {
            return rowData.length;
        }

        /**
         * 返回总列数
         */
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        /**
         * 返回列名称（表头名称），AbstractTableModel 中对该方法的实现默认是以
         * 大写字母 A 开始作为列名显示，所以这里需要重写该方法返回我们需要的列名。
         */
        @Override
        public String getColumnName(int column) {
            return columnNames[column].toString();
        }

        /**
         * 返回指定单元格的显示的值
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return rowData[rowIndex][columnIndex];
        }
    }

}
