package cc.java8.java8.Lambda;

/**
 * Yukino
 * 2020/6/12
 */
public class T1 {

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("T1");
        }).start();
    }
}
