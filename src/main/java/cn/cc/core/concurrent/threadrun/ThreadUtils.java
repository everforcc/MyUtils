package cn.cc.core.concurrent.threadrun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Yukino
 * 2020/6/1
 */
public class ThreadUtils implements Runnable {

    private int mapKey;
    private Obj obj;
    private List<Obj> objList;
    private Map<Integer,List<Obj>> map ;

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }

    public ThreadUtils() {
    }

    public ThreadUtils(Obj obj) {
        this.obj = obj;
        objList = new ArrayList<Obj>();
        map = new HashMap<Integer,List<Obj>>();
    }

    public List<Obj> getObjList() {
        return objList;
    }

    public void setObjList(int i,Obj obj) {
        this.mapKey=i;
        objList.add(obj);
        map.put(i,objList);
    }

    @Override
    public void run() {
        System.out.println("run");
        //for(int i=0;i<10;i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (obj == null) {
                System.out.println("if");
                System.out.println("null");
            } else {
                System.out.println("else");
                System.out.println("map的尺寸:"+map.get(mapKey).size());
                for (Obj obj1:map.get(mapKey)) {
                    System.out.println(obj.toString()+"的第几:"+obj1.getAge());
                }
            }
       // }

    }
}
