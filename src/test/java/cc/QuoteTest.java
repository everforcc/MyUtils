package cc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author everforcc 2021-09-18
 */
public class QuoteTest {

    @Test
    public void t1() {
        // 引用问题
        List<byte[]> byteList = new ArrayList<>();
        // 放循环里面重新new可以，但是有些必须在外面声明，例如IO
        byte[] bytes = new byte[1];
        for(int i =0;i<10;i++){
            bytes[0] = (byte) i;
            byte[] b = bytes;
            byteList.add(b);
            System.out.println(i + ":"+byteList.get(0)[0]);
        }
    }
}
