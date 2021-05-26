package cn.cc.core.concurrent.threadrun;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Yukino
 * 2020/6/1
 */
public class RunThread {

    public static void main(String[] args) {
        ThreadUtils threadUtils = new ThreadUtils(new Obj("一",1));
        ThreadUtils threadUtils1 = new ThreadUtils(new Obj("二",2));

        threadUtils.run();

        threadUtils1.run();

        Thread thread1 = new Thread(threadUtils);

        Thread thread2 = new Thread(threadUtils1);

        thread1.start();
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread2.start();

    }

    private Integer index = 1 ;
    private Integer dataTotal = 100 ;

    /* map来放坐标和数据的位置 */
    private Map<Integer,String> mapdata = new HashMap<Integer, String>();

    @Test
    public void flow(){

        // 1.先获取数据文件,利用多线程快速获取
        // 2.放入map里，或者放到文件里
        // 3.从 map里或文件 取数
        // 4.合并数据

        // 10个


        // ThreadGroup
        ThreadUtils threadUtils_0 = new ThreadUtils(new Obj("零",0));
        ThreadUtils threadUtils_1 = new ThreadUtils(new Obj("一",1));
       for( int i=1 ; i<=dataTotal ; i++ ){
            if(i%10==0){
                threadUtils_0.setObjList(0,new Obj("",i));
            }else if(i%10==1){
                threadUtils_1.setObjList(1,new Obj("",i));
            }
       }

       /*threadUtils_1.run();
       threadUtils_0.run();*/


       /* ??? */
       Thread thread_0 = new Thread(threadUtils_0);
       Thread thread_1 = new Thread(threadUtils_1);
       thread_0.start();
       thread_1.start();
    }

    public void dealdData(){
        // 获取要处理的数据的总量
        dataTotal = 1;
        mapdata.put(1,"1");
    }


}

