package cn.cc.jdk0;

import java.net.InetAddress;

/**
 * @author c.c.
 * @date 2021/3/5
 */
public class SystemMsg {


    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            // InetAddress.getLocalHost();
            // InetAddress ia = null;
            InetAddress ia = InetAddress.getLocalHost();
            // byte[] a = ia.getAddress();
            String b = ia.getHostAddress();
            String c = ia.getHostName();
            // System.out.println(a);
            System.out.println(b);
            System.out.println(c);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 获取系统信息
     */
    public static void systemRunTime() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("本机CPU内核数："+runtime.availableProcessors());
        System.out.println("最大可用内存空间"+runtime.maxMemory()/1024/1024 +"MB,默认为系统的1/4");
        System.out.println("可用内存空间:"+runtime.totalMemory()/1024/1024 +"MB,默认为系统的1/64");
        System.out.println("空闲内存空间:"+runtime.freeMemory()/1024/1024 +"MB");
        System.out.println("手工GC处理gc()");
        runtime.gc();
        System.out.println("什么是GC？可以由系统自动调用的垃圾释放功能，或者RunTime手工调用的垃圾释放功能");

    }


}
