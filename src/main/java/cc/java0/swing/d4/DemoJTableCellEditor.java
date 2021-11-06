package cc.java0.swing.d4;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * @author everforcc 2021-10-19
 */
public class DemoJTableCellEditor {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建内容面板，使用边界布局
        JPanel panel = new JPanel(new BorderLayout());

        // 表头（列名）
        Object[] columnNames = {"姓名", "语文", "数学", "英语", "总分"};

        // 表格所有行数据
        Object[][] rowData = {
                {"张三", 80, 80, 80, 240},
                {"John", 70, 80, 90, 240},
                {"Sue", 70, 70, 70, 210},
                {"Jane", 80, 70, 60, 210},
                {"Joe", 80, 70, 60, 210}
        };

        // 创建一个表格，指定 所有行数据 和 表头
        JTable table = new JTable(rowData, columnNames);

        // 创建单元格编辑器，使用文本框作为编辑组件
        MyCellEditor cellEditor = new MyCellEditor(new JTextField());

        // 遍历表格中所有数字列，并设置列单元格的编辑器
        for (int i = 1; i < columnNames.length; i++) {
            // 根据 列名 获取 表格列
            TableColumn tableColumn = table.getColumn(columnNames[i]);
            // 设置 表格列 的 单元格编辑器
            tableColumn.setCellEditor(cellEditor);
        }

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
     * 只允许输入数字的单元格编辑器
     */
    public static class MyCellEditor extends DefaultCellEditor {

        public MyCellEditor(JTextField textField) {
            super(textField);
        }

        public MyCellEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        public MyCellEditor(JComboBox comboBox) {
            super(comboBox);
        }

        @Override
        public boolean stopCellEditing() {
            // 获取当前单元格的编辑器组件
            Component comp = getComponent();

            // 获取当前单元格编辑器输入的值
            Object obj = getCellEditorValue();

            // 如果当前单元格编辑器输入的值不是数字，则返回 false（表示数据非法，不允许设置，无法保存）
            if (obj == null || !obj.toString().matches("[0-9]*")) {
                // 数据非法时，设置编辑器组件内的内容颜色为红色
                comp.setForeground(Color.RED);
                return false;
            }

            // 数据合法时，设置编辑器组件内的内容颜色为黑色
            comp.setForeground(Color.BLACK);

            // 合法数据交给父类处理
            return super.stopCellEditing();
        }
    }

}
