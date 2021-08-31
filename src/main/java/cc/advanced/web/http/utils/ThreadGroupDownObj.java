package cc.advanced.web.http.utils;

public class ThreadGroupDownObj {
    /*private static Print_Record println = Print_Record.getInstanse("");

    // 拿到全部数据做线程池, 写个线程池的工具类方便用

    *//**
     * 十个一组来操作下载
     *//*

    private static List<Object> objAryList;
    // 下载队列的数量
    private static int downQueue = 10;
    private static ThreadGroup downGroup = new ThreadGroup("down");

    public ThreadGroupDownObj(List<Object> objAryList) {
        this.objAryList = objAryList;
    }

    public  void run(){
        int keepActive= 10;
        if(null!=objAryList&&objAryList.size()!=0){
           if(objAryList.size()<=downQueue){
               objAryList.forEach(ThreadGroupDownObj::down);
               while(downGroup.activeCount() > 0) {

               }
           } else {
               for(int i=0;i<downQueue;i++){
                   down(strAryList.get(i));
               }
               while(downGroup.activeCount() > 0) {
                   while (downGroup.activeCount()<downQueue && keepActive<strAryList.size() ){
                       println.println("下载队列增加新文件:" + strAryList.get(keepActive));
                       down(strAryList.get(keepActive));
                       keepActive++;
                   }
               }
           }
        }else {
            println.println(" 没有可以下载的集合 ");
        }
    }

    private static void down(Object obj){
        //ThreadDown threadDown = new ThreadDown(str);
        // 证明一个对象
        new Thread(downGroup, obj, "").start();
    }
*/
    /*private static List<String[]> splitList(List<String[]> strAryListLocal){
        List<String[]> resultList = new ArrayList<>();
        if(strAryListLocal.size()>10){
           for(int i=0;i<downQueue;i++){
               resultList.add(strAryList.get(0));
               strAryListLocal.remove(0);
           }
           return resultList;
        }else {
           return strAryListLocal;
        }
    }*/
}
