package cn.cc.core.io.utils;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author c.c.
 * @date 2020/12/9
 */
public class ByteArrayInputStreamUtils {

    /**
     * 得到字节的输入流
     * @param strBytes
     * @return
     * @throws Exception
     */
    public InputStream getByteStream(byte[] strBytes) throws Exception{
        //得到字节数组的输入流
        InputStream byteArrayStream=new ByteArrayInputStream(strBytes);
        byte[] bytes =new byte[1024];

        //字节读取
        InputStream inputStream =new ByteInputStream(bytes,64);
        return inputStream;
    }

    //利用byte[]数组写入文件
    protected void writerFile_Click()
    {
        //将string转为byte数组
        byte[] array ="".getBytes();
    }
    //利用byte[]数组读取文件
    protected void readFile_Click() {
        int size=1024;
        byte[] array = new byte[size];
    }

}
