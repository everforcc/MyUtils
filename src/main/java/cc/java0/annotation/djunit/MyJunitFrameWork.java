package cc.java0.annotation.djunit;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guokailong 2021-09-08
 */
public class MyJunitFrameWork {

    public static void main(String[] args) throws Exception {

        //1. 先找到测试类的字节码
        Class clazz = EmployeeDAOTest.class;
        Object obj = clazz.newInstance();

        //2. 获取EmploeeDAOTest类中的所有公共方法
        Method[] methods = clazz.getMethods();

        //3. 迭代出每一个Method对象，判断哪些方法上使用了@MyBefore/@MyAfter/@MyTest注解
        List<Method> myBeforeList = new ArrayList<>();
        List<Method> myAfterList = new ArrayList<>();
        List<Method> myTestList = new ArrayList<>();
        for(Method method:methods){
            if(method.isAnnotationPresent(MyBefore.class)){
                myBeforeList.add(method);
            }else if(method.isAnnotationPresent(MyTest.class)){
                myTestList.add(method);
            }else if(method.isAnnotationPresent(MyAfter.class)){
                myAfterList.add(method);
            }
        }

        // 4. 执行方法测试
        for(Method testMethod:myTestList){
            // 先执行@MyBefore的方法
            for(Method beforeMethod:myBeforeList){
                beforeMethod.invoke(obj);
            }
            // 测试方法
            testMethod.invoke(obj);
            // 最后执行@MyAfter的方法
            for(Method afterMethod:myAfterList){
                afterMethod.invoke(obj);
            }
        }

    }

}
