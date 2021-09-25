package cc.java0.thread.create;

/**
 * @author guokailong 2021-09-23
 */
public class ThreadET extends Thread{
    public ThreadET(String name){
        //重写构造，可以对线程添加名字
        super(name);
    }
    @Override
    public void run() {
        while(true){
            System.out.println("good time");
            //在run方法里，this代表当前线程
            System.out.println(this);
        }
    }
    public static void main(String[] args){
        ThreadET threadRuning = new ThreadET("线程名");
        threadRuning.start();
    }
}
