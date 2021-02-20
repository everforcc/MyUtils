package cn.cc.use.url.http.novel2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author c.c.
 * @date 2020/12/26
 */
public class ThreadUtils {

    // 其他类都实现 thread 接口，向这个类add文件，结束后怎么合并文件呢，类似数据库，先不合并了
    private static ThreadUtils threadUtils;
    private static int downQueue = 10;
    private static ThreadGroup downGroup = new ThreadGroup("queue");
    private List<Runnable> runnableList = new ArrayList<>() ;

    public ThreadUtils() {

    }

    public void setRunnableList(Runnable runnable){
        runnableList.add(runnable);
    }

    /*private Print_Record(String filePath,String fileName){
        this.filePath = filePath;
        this.fileName = fileName ;
    };*/

    /*public static synchronized  ThreadUtils getInstanse(){
        if(threadUtils == null){
            threadUtils = new ThreadUtils();
        }
        return threadUtils;
    }*/

    private static void threadGroup(Runnable runnable){
        new Thread(downGroup, runnable, "").start();
    }

    public  void run(){
        int keepActive= 10;// 的想办法默认十个

        int size = runnableList.size();
        if(null!=runnableList&&size!=0){
            if(size<=downQueue){ // 如果小于10那么全部加入等待结束
                runnableList.forEach(ThreadUtils::threadGroup);
                while(downGroup.activeCount() > 0) {

                }
            } else { // 如果大于十
                // 先取出十个
                for(int i=0;i<downQueue;i++){
                    threadGroup(runnableList.get(i));
                }
                // 保证整体大于0
                while(downGroup.activeCount() > 0) {
                    // 活动数小于10，并且 已经下载的数量小于总数，新增一个线程
                    while (downGroup.activeCount()<downQueue && keepActive<runnableList.size() ){
                        //println.println("下载队列增加新文件:"+strAryList.get(keepActive)[2]);
                        threadGroup(runnableList.get(keepActive));
                        keepActive++;
                    }
                }
            }
        }else {
            //println.println(" 没有可以下载的集合 ");
        }
    }

    /*public List<Runnable> getRunnableList() {
        return runnableList;
    }

    public void setRunnableList(List<Runnable> runnableList) {
        this.runnableList = runnableList;
    }*/



}
