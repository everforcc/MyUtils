package cc.java0.annotation.base4;

/**
 * @author everforcc 2021-09-08
 */
@NameScanner
public class NameScannerTest {

    @NameScanner
    private String name;

    @NameScanner
    private int age;

    @NameScanner
    public String getName(){
        return this.name;
    }

    @NameScanner
    public void setName(String name){
        this.name = name;
    }

    public static void main(String[] args){
        System.out.println("--finished--");
    }
}
