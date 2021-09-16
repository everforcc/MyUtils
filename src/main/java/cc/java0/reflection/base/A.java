package cc.java0.reflection.base;

/**
 * @author guokailong 2021-09-09
 */
public class A {

    public String publicProperty;
    private String privateProperty;

    private void methodA(String str){
        System.out.println("methodA : " + str);
    }
    public void methodB(String str){
        System.out.println("methodB : " + str);
    }

}

