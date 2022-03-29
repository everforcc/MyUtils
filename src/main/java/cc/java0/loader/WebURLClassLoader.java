package cc.java0.loader;

import cc.java0.reflection.api.ObjRef;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 *
 */
public class WebURLClassLoader extends URLClassLoader {

    public WebURLClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public WebURLClassLoader(URL[] urls) {
        super(urls);
    }

    public WebURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    public static void main(String[] args) {
        String url = "https://gitee.com/MyYukino/media/raw/master/class/ObjectRef.class";
        URL[] urls = new URL[0];
        try {
            urls = new URL[]{new URL(url)};
            WebURLClassLoader webURLClassLoader = new WebURLClassLoader(urls);
            Class<?> objClass = webURLClassLoader.loadClass("ObjectRef",true);
            Object obj = objClass.newInstance();
            System.out.println(objClass.getName());
            System.out.println(objClass.getClassLoader());
            System.out.println(obj.getClass().toString());
            ObjRef.getField(objClass,obj);
            ObjRef.getMehtod(objClass);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


}
