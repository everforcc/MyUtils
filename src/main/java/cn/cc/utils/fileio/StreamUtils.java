package cn.cc.utils.fileio;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *  封装 流 常用的方法
 */
public class StreamUtils {


    static byte a=(byte)128;
    static byte b=(byte)129;
    static byte[] c = new byte[]{'d',(byte)0xff,-1,(byte)255,(byte)0x80,(byte) 128,-128};

    /* 设置为public是为了在不同的包里面调用  */
    /**
     * 默认为GBK字符编码
     */
    public static String charEncoding="GBK";

    public static void main(String[] args) {

        byte a=(byte)127;
        System.out.println("a:"+a);
        //byte b=(byte)129;
        //System.out.println(a);
        //System.out.println(b);
        String stra="abc";
        byte[] bytestr = stra.getBytes();
        System.out.println(bytestr.length);
        System.out.println(new String(bytestr));
        System.out.println(bytestr);
        System.out.println("----------");
        for(byte b:c){
            System.out.println(b);
        }
        System.out.println("----------");
        System.out.println(stra.getBytes());

        System.out.println(c);

    }

    byte[] bytes1= new byte[]{'b','b','b','b','b'};
    static byte[] bytes2= new byte[5];



    static {
        bytes2[0]='a';
        bytes2[1]='a';
        bytes2[2]='a';
        bytes2[3]='a';
        bytes2[4]='a';
    }

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

    /**
     * 根据inputStream获得输入的字符串
     * @param inputStream 输入流
     * @return
     * @throws Exception
     */
    public static String inputStreamStr(InputStream inputStream)throws Exception{
        BufferedReader br = null;
        // 默认字符编码GBK
        br = new BufferedReader(new InputStreamReader(inputStream,charEncoding));
        String readLine = br.readLine();
        StringBuilder builder = new StringBuilder();
        while (readLine != null) {
            readLine = br.readLine();
            builder.append(readLine);
        }
        return builder.toString();
    }


    }
