package cc.core.io.base;



import cc.sysconstant.ConstantFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Yukino
 * 2020/4/24
 */
public class ObjectStreamUtils {

    /**
     * 处理序列化文件的问题
     * 将对象写入文件
     * 从文件恢复对象
     */

    public static void main(String[] args) {
        String writeFile = ConstantFile.L1_javaFilePath + "/test/integer";
        writeObj(writeFile);
        readObj(writeFile);
    }

    public static void writeObj(String filePN){
        try {
            FileOutputStream fos = new FileOutputStream(filePN);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Integer integer = new Integer(1);
            oos.writeObject(integer);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readObj(String filePN){
        try {
            FileInputStream fis = new FileInputStream(filePN);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Integer integer = (Integer)ois.readObject();
            System.out.println(integer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
