package cc.java0.loader;

import cc.java0.reflection.api.ObjRef;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author everforcc 2021-11-12
 */
public class PathClassLoader extends ClassLoader
{
    // C:\environment\test\MyUtils\target\classes
    public static final String drive = "C:/environment/test/MyUtils/target/classes/";
    public static final String fileType = ".class";

    public static void main(String[] args) throws Exception
    {

        PathClassLoader loader = new PathClassLoader();
        Class<?> clazz = loader.loadClass("ConstantCharSet", true);
        Object obj = clazz.newInstance();
        System.out.println(clazz.getName());
        System.out.println(clazz.getClassLoader());
        System.out.println(obj.getClass().toString());
        ObjRef.getField(clazz,obj);
    }

    public Class<?> findClass(String name) {
        byte[] data = loadClassData(name);
        // 将一个 byte 数组转换为 Class// 类的实例
        return defineClass(name, data, 0, data.length);
    }

    public byte[] loadClassData(String name) {
        FileInputStream fis = null;
        byte[] data = null;
        try {
            fis = new FileInputStream(new File(drive + name + fileType));
            // 只要是输入流就好，那么网络的输入流应该也可以，放gitee一个试试

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int ch = 0;
            while ((ch = fis.read()) != -1) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}