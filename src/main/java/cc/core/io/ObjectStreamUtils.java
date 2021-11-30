package cc.core.io;



import cc.constant.ConstantFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Yukino
 * 2020/4/24
 */
public class ObjectStreamUtils {

    // 处理序列化文件的问题

    public void test(){
        //writeObj();
        readObj("","");
    }

    public void writeObj(String filePath,String fileName){
        try {
            FileOutputStream fos = new FileOutputStream(ConstantFile.L1_javaFilePath + "/test/integer");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Integer integer = new Integer(1);
            oos.writeObject(integer);
            oos.flush();
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readObj(String filePath,String fileName){
        try {
            FileInputStream fis = new FileInputStream(ConstantFile.L1_javaFilePath + "/test/integer");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Integer integer = (Integer)ois.readObject();
            System.out.println(integer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
