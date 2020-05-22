package cn.cc.utils.fileio.io;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Yukino
 * 2020/4/24
 */
public class ObjectStream {


    public void test(){
        //writeObj();
        readObj();
    }

    public void writeObj(){
        try {
            FileOutputStream fos = new FileOutputStream("D:\\test\\integer");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Integer integer = new Integer(1);
            oos.writeObject(integer);
            oos.flush();
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readObj(){

        try {
            FileInputStream fis = new FileInputStream("D:\\test\\integer");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Integer integer = (Integer)ois.readObject();
            System.out.println(integer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
