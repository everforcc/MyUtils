package cc.core.io.base;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StrToStreamUtils {
    /**
     * 从str转换到输出流
     */
    public InputStream getByteStream(String string) throws Exception{
        return getByteStream(string.getBytes());
    }
    public InputStream getByteStream(byte[] strBytes) throws Exception{
        //得到字节数组的输入流
        InputStream byteArrayStream=new ByteArrayInputStream(strBytes);
        byte[] bytes =new byte[1024];

        //字节读取
        InputStream inputStream =new ByteInputStream(bytes,64);
        return inputStream;
    }
}
