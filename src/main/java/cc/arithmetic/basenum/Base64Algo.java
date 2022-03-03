package cc.arithmetic.basenum;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

public class Base64Algo {


    private static String basechar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    /**
     |=：两个二进制对应位都为0时，结果等于0，否则结果等于1；
     &=：两个二进制的对应位都为1时，结果为1，否则结果等于0；
     ^=：两个二进制的对应位相同，结果为0，否则结果为1。
     */

    /**
     * 1. 输入字符串
     * 2. 将字符串编码字节
     * 3. 每六位取出转换
     * 4. 不足补 = 号
     * 5. 测试还原
     */

    public static String encrypt(String inStr){
        if(StringUtils.isBlank(inStr)){
            return "";
        }

        byte[] bytes = inStr.getBytes(StandardCharsets.UTF_8);
        int length = bytes.length;

        StringBuilder out = new StringBuilder((length * 4) / 3);

        int b;

        for (int i = 0; i < length; i += 3) {

            /**
             * 0xFC = 11111100
             * 按位与， 高六位不变，低两位清0
             * 也可以直接右移，结果一样，感觉上面的步骤没必要
             */
            b = (bytes[i] & 0xFC) >> 2;
            out.append(basechar.charAt(b));

            /**
             * 0x03 = 00000011
             * 左移动四位，留给下一次循环用
             */
            b = (bytes[i] & 0x03) << 4;

            /**
             * 3种情况
             */
            if (i + 1 < length) {

                /**
                 * 11110000
                 * 第二个字节
                 * 右移四位，取出四位和上面的两位
                 * 将之前的两位和截取的四位拼接到一起
                 */
                b |= (bytes[i + 1] & 0xF0) >> 4;
                out.append(basechar.charAt(b));
                /**
                 * 00001111
                 * 左移两位，取出下一步的两位组合
                 */
                b = (bytes[i + 1] & 0x0F) << 2;

                /**
                 * 情况1.
                 * 如果还有一位，那么就取出
                 */
                if (i + 2 < length) {
                    /**
                     * 11000000
                     */
                    b |= (bytes[i + 2] & 0xC0) >> 6;
                    out.append(basechar.charAt(b));
                    /**
                     * 00111111
                     * 取出最后一个数字
                     */
                    b = bytes[i + 2] & 0x3F;
                    out.append(basechar.charAt(b));

                } else {
                    /**
                     * 情况2. 缺少一位，那么就补一个 =
                     */
                    out.append(basechar.charAt(b));
                    out.append('=');
                }

            } else {
                /**
                 * 情况3. 缺少两位，那么就补两个 =
                 */
                out.append(basechar.charAt(b));
                out.append("==");
            }
        }
        return out.toString();
    }

    // base64解密
    private static byte[] base64Decode(String input) {
        if (input.length() % 4 != 0) {
            throw new IllegalArgumentException("Invalid base64 input");
        }
        byte decoded[] = new byte[((input.length() * 3) / 4) - (input.indexOf('=') > 0 ? (input.length() - input.indexOf('=')) : 0)];
        char[] inChars = input.toCharArray();
        int j = 0;
        int b[] = new int[4];
        for (int i = 0; i < inChars.length; i += 4) {
            // This could be made faster (but more complicated) by precomputing
            // these index locations.
            b[0] = basechar.indexOf(inChars[i]);
            b[1] = basechar.indexOf(inChars[i + 1]);
            b[2] = basechar.indexOf(inChars[i + 2]);
            b[3] = basechar.indexOf(inChars[i + 3]);
            decoded[j++] = (byte) ((b[0] << 2) | (b[1] >> 4));
            if (b[2] < 64) {
                decoded[j++] = (byte) ((b[1] << 4) | (b[2] >> 2));
                if (b[3] < 64) {
                    decoded[j++] = (byte) ((b[2] << 6) | b[3]);
                }
            }
        }
        return decoded;
    }

    public static void main(String[] args) {

        int a = 0xFC;
        int b = 0x03;
        int c = 0xF0;
        int d = 0x0F;
        int e = 0xC0;
        int f = 0x3F;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(c));
        System.out.println(Integer.toBinaryString(d));
        System.out.println(Integer.toBinaryString(e));
        System.out.println(Integer.toBinaryString(f));

        System.out.println("------");
        for(byte bt:"a".getBytes(StandardCharsets.UTF_8)){
            System.out.println(bt);
            System.out.println(Integer.toBinaryString(bt));
        }
        System.out.println("------");

        System.out.println(encrypt("a"));
    }

}
