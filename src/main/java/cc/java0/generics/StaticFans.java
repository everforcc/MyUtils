package cc.java0.generics;

import lombok.extern.slf4j.Slf4j;

/**
 * 4. 泛型函数定义及使用
 * @author guokailong 2021-09-13
 */
@Slf4j
public class StaticFans {
    //静态函数
    public static  <T> void StaticMethod(T a){
        log.info("StaticMethod: "+a.toString());
    }
    //普通函数
    public  <T> void OtherMethod(T a){
        log.info("OtherMethod: "+a.toString());
    }
}
class StaticFansT{
    public static void main(String[] args) {
        //静态方法
        StaticFans.StaticMethod("adfdsa");//使用方法一
        StaticFans.<String>StaticMethod("adfdsa");//使用方法二

        //常规方法
        StaticFans staticFans = new StaticFans();
        staticFans.OtherMethod(new Integer(123));//使用方法一
        staticFans.<Integer>OtherMethod(new Integer(123));//使用方法二
    }
}