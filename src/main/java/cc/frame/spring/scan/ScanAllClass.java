package cc.frame.spring.scan;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author everforcc 2021-09-29
 */
@Slf4j
public class ScanAllClass {


    /**
     * BeanDefinitionLoader
     * load() 看看spring是怎么扫描的
     *
     */

    public static List<Class> getAllClassByInterface(Class c) throws IOException {
        List<Class> returnClassList = null;

        if (!c.isInterface()) {
            // 获取当前的包名
            String packageName = c.getPackage().getName();
            log.info("packageName:" + packageName);

            Enumeration resourceUrls = ClassLoader.getSystemResources("cc.frame.spring.scan");
            System.out.printf("resourceUrls:" + resourceUrls.hasMoreElements());
            //System.out.printf("resourceUrls:" + resourceUrls.nextElement());
            /*try {
                // 获取当前包下以及子包下所以的类
                List<Class> allClass = getClasses(packageName);
                if (allClass != null) {
                    returnClassList = new ArrayList<Class>();
                    for (Class classes : allClass) {
                        // 判断是否是同一个接口
                        if (c.isAssignableFrom(classes)) {
                            // 本身不加入进去
                            if (!c.equals(classes)) {
                                returnClassList.add(classes);
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }

        return returnClassList;
    }

    }
