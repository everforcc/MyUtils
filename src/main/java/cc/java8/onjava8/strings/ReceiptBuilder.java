package cc.java8.onjava8.strings;

// strings/ReceiptBuilder.java
import java.util.*;

public class ReceiptBuilder {
    private double total = 0;
    private Formatter f = new Formatter(new StringBuilder());
    public ReceiptBuilder() {
        // 固定头部
        f.format("%-15s %5s %10s%n", "Item", "Qty", "Price");
        f.format("%-15s %5s %10s%n", "----", "---", "-----");
    }
    public void add(String name, int qty, double price) {
        // 单据信息
        f.format("%-15.15s %5d %10.2f%n", name, qty, price);
        total += price * qty;
    }
    public String build() {
        // 总计信息
        f.format("%-15s %5s %10.2f%n", "Tax", "",total * 0.06);
        f.format("%-15s %5s %10s%n", "", "", "-----");
        f.format("%-15s %5s %10.2f%n", "Total", "",total * 1.06);
        return f.toString();
    }
    public static void main(String[] args) {
        ReceiptBuilder receiptBuilder =new ReceiptBuilder();
        receiptBuilder.add("Jack's Magic Beans", 4, 4.25);
        receiptBuilder.add("Princess Peas", 3, 5.1);
        receiptBuilder.add("Three Bears Porridge", 1, 14.29);
        System.out.println("start");
        System.out.println(receiptBuilder.build());
        System.out.println("end");
    }
}
/* Output:
Item              Qty      Price
----              ---      -----
Jack's Magic Be     4       4.25
Princess Peas       3       5.10
Three Bears Por     1      14.29
Tax                         2.80
                           -----
Total                      49.39
*/
