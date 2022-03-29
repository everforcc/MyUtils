package maven.google.guava;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import com.google.common.math.IntMath;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Queue;

public class ByteUtilsTest {

    public static byte[] toByteArray(InputStream in) throws IOException {
        // 校验传入数据
        Preconditions.checkNotNull(in);
        //
        return toByteArrayInternal(in, new ArrayDeque(20), 0);
    }

    private static byte[] toByteArrayInternal(InputStream in, Queue<byte[]> bufs, int totalLen) throws IOException {

        //
        /**
         * 8192 = 8 * 1024
         * 2147483647 = 2^31-1
         * 2147483639
         *
         */
        /**
         * 1. 初始化bufSize
         * 2. totalLen < ？
         * 3. bufSize * 2 然后和 2147483647L（int最大值） 比较 确保在int范围内
         * 4. totalLen 从0开始每次新增
         * 5. 文件上限为2G，如果修改为long上限为2^
         */
        // 每次循环 bufSize * 2
        for(int bufSize = 8192; totalLen < 2147483639; bufSize = IntMath.saturatedMultiply(bufSize, 2)) {
            /**
             * 4. 取出两个数中较小的那个
             *
             */
            byte[] buf = new byte[Math.min(bufSize, 2147483639 - totalLen)];

            // 把buf放到bufs队列里面
            bufs.add(buf);

            // 每次读取的字节数
            int r;

            // totalLen 记录数据大小
            for(int off = 0; off < buf.length; totalLen += r) {
                //
                /**
                 * 1. 每次读buf个数据
                 * 2. 给off add r个字节
                 * 3. 下次从off都到
                 *
                 */
                // 读入缓冲区的字节总数，如果没有更多的数据，则为-1，因为已经到达流的末尾。
                r = in.read(buf, off, buf.length - off);
                if (r == -1) {
                    // bufs 每个元素最多存8字节
                    return combineBuffers(bufs, totalLen);
                }

                off += r;
            }

        }

        /**
         * 1. 当上面跑完后还没return
         * 2. 如果再次读取等于-1，那么说明文件大小为 2147483639
         * 3. 如果读取后还有值，那么抛错文件太大
         */
        if (in.read() == -1) {
            return combineBuffers(bufs, 2147483639);
        } else {
            throw new OutOfMemoryError("input is too large to fit in a byte array");
        }
    }

    private static byte[] combineBuffers(Queue<byte[]> bufs, int totalLen) {

        // 声明数组，大小为文件的大小
        byte[] result = new byte[totalLen];

        int bytesToCopy;
        for(int remaining = totalLen; remaining > 0; remaining -= bytesToCopy) {
            // 删除队列中的第一个元素，并返回该元素的值
            byte[] buf = (byte[])bufs.remove();
            /**
             * 取出两者中较小的那个
             * 1. 如果remaining小，一轮结束
             * 2. 如果buf.length小，那么依次取出赋值
             * 3.
             */
            bytesToCopy = Math.min(remaining, buf.length);
            /**
             * 1. totalLen 固定
             * 2. remaining初始值为totalLen
             * 3. 每轮 remaining减少bytesToCopy
             * 4. 那不就是 bytesToCopy ?
             *
             */
            int resultOffset = totalLen - remaining;
            /**
             * 要copy的数组
             * 起始位置
             * 目标文件
             * 开始位置
             * copy长度
             */
            System.arraycopy(buf, 0, result, resultOffset, bytesToCopy);
        }

        return result;
    }

    private static void Random(){
        /**
         *
         * <dependency>
         *        <groupId>org.apache.commons</groupId>
         *        <artifactId>commons-lang3</artifactId>
         *        <version>3.12.0</version>
         * </dependency>
         */
        System.out.println(RandomUtils.nextInt(-1, 1));
    }

    @Test
    public void testFor(){

        for(int i = 0;i<10;i++){
            System.out.println(i);
        }
        try {
            ByteStreams.toByteArray(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
