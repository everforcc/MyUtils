package cc.design.design5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CopySerialize {

    public static void main(String[] args) {
        copy(new Square());
    }

    public static void copy(Object object){
        System.out.println("123");
        ObjectOutputStream objectOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectInputStream objectInputStream;
        ByteArrayInputStream byteArrayInputStream;
        try{
            /**
             * 序列化
             * 1. 创建一个字节数组输出流
             * 2. 创建一个对象输出流，并且将先前的字节数组输出流包起来
             * 3. 将boj写入oos中的bos
             * 4. 刷写
             */
            // 序列化
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            // 反序列化
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object readObject = objectInputStream.readObject();
            System.out.println("object:" + object.toString());
            System.out.println("readObject:" + readObject.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
