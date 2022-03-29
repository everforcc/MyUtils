package cc.design.design20observer;

/**
 * @author c.c.
 * @date 2021/3/25
 */
public class ObserverPatternDemo {

    public static void main(String[] args) {
        Subject subject = new Subject();

        HexaObserver hexaObserver = new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println();

        //取消通知
        hexaObserver.remove();
        System.out.println("Second state change: 10");
        subject.setState(10);
    }

}