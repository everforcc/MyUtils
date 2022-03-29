package cc.arithmetic.encryption;

import com.google.common.base.Strings;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * AES 工具类
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1304227762667553
 *
 */
@Slf4j
public final class Aes {
    /**
     * https://docs.oracle.com/javase/7/docs/technotes/guides/security/crypto/CryptoSpec.html#trans
     */
    private static String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    /**
     * 参考文档: https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher
     * <pre>
     * Cipher (Encryption) Algorithms
     * Cipher Algorithm Names
     * The following names can be specified as the algorithm component in a transformation when requesting an instance of Cipher.
     *
     * <table border="5" cellpadding="5" frame="border" width="90%" summary="Cipher Algorithm Names">
     * <thead> <tr> <th>Algorithm Name</th> <th>Description</th> </tr> </thead>
     * <tbody>
     * <tr> <td>AES</td> <td>Advanced Encryption Standard as specified by NIST in <a href="http://csrc.nist.gov/publications/fips/index.html">FIPS 197</a>. Also known as the Rijndael algorithm by Joan Daemen and Vincent Rijmen, AES is a 128-bit block cipher supporting keys of 128, 192, and 256 bits.</td> </tr>
     * <tr> <td>AESWrap</td> <td>The AES key wrapping algorithm as described in <a href="http://www.ietf.org/rfc/rfc3394.txt">RFC 3394</a>.</td> </tr>
     * <tr> <td>ARCFOUR</td> <td>A stream cipher believed to be fully interoperable with the RC4 cipher developed by Ron Rivest. For more information, see K. Kaukonen and R. Thayer, "A Stream Cipher Encryption Algorithm 'Arcfour'", Internet Draft (expired), <a href="http://www.mozilla.org/projects/security/pki/nss/draft-kaukonen-cipher-arcfour-03.txt"> draft-kaukonen-cipher-arcfour-03.txt</a>.</td> </tr>
     * <tr> <td>Blowfish</td> <td>The <a href="http://www.schneier.com/blowfish.html">Blowfish block cipher</a> designed by Bruce Schneier.</td> </tr>
     * <tr> <td>CCM</td> <td>Counter/CBC Mode, as defined in <a href="http://csrc.nist.gov/publications/nistpubs/800-38C/SP800-38C_updated-July20_2007.pdf">NIST Special Publication SP 800-38C</a>.</td> </tr>
     * <tr> <td>DES</td> <td>The Digital Encryption Standard as described in <a href="http://csrc.nist.gov/publications/fips/index.html">FIPS PUB 46-3</a>.</td> </tr>
     * <tr> <td>DESede</td> <td>Triple DES Encryption (also known as DES-EDE, 3DES, or Triple-DES). Data is encrypted using the DES algorithm three separate times. It is first encrypted using the first subkey, then decrypted with the second subkey, and encrypted with the third subkey.</td> </tr>
     * <tr> <td>DESedeWrap</td> <td>The DESede key wrapping algorithm as described in <a href="http://www.ietf.org/rfc/rfc3217.txt">RFC 3217</a> .</td> </tr>
     * <tr> <td>ECIES</td> <td>Elliptic Curve Integrated Encryption Scheme</td> </tr>
     * <tr> <td>GCM</td> <td>Galois/Counter Mode, as defined in <a href="http://csrc.nist.gov/publications/nistpubs/800-38D/SP-800-38D.pdf">NIST Special Publication SP 800-38D</a>.</td> </tr>
     * <tr> <td>PBEWith&lt;digest&gt;And&lt;encryption&gt; PBEWith&lt;prf&gt;And&lt;encryption&gt;</td> <td>The password-based encryption algorithm found in (PKCS5), using the specified message digest (&lt;digest&gt;) or pseudo-random function (&lt;prf&gt;) and encryption algorithm (&lt;encryption&gt;). Examples: <ul> <li><b>PBEWithMD5AndDES</b>: The password-based encryption algorithm as defined in <a href="http://www.rsa.com/rsalabs/node.asp?id=2127">RSA Laboratories, "PKCS #5: Password-Based Encryption Standard," version 1.5, Nov 1993</a>. Note that this algorithm implies <a href="#cbcMode"><i>CBC</i></a> as the cipher mode and <a href="#pkcs5Pad"><i>PKCS5Padding</i></a> as the padding scheme and cannot be used with any other cipher modes or padding schemes.</li> <li><b>PBEWithHmacSHA256AndAES_128</b>: The password-based encryption algorithm as defined in <a href="http://www.rsa.com/rsalabs/node.asp?id=2127">RSA Laboratories, "PKCS #5: Password-Based Cryptography Standard," version 2.0, March 1999</a>.</li> </ul> </td> </tr>
     * <tr> <td>RC2</td> <td>Variable-key-size encryption algorithms developed by Ron Rivest for RSA Data Security, Inc.</td> </tr>
     * <tr> <td>RC4</td> <td>Variable-key-size encryption algorithms developed by Ron Rivest for RSA Data Security, Inc. (See note prior for ARCFOUR.)</td> </tr>
     * <tr> <td>RC5</td> <td>Variable-key-size encryption algorithms developed by Ron Rivest for RSA Data Security, Inc.</td> </tr>
     * <tr> <td>RSA</td> <td>The RSA encryption algorithm as defined in <a href="http://www.rsa.com/rsalabs/node.asp?id=2125">PKCS #1</a></td> </tr>
     * </tbody>
     * </table>
     */
    private static final String CIPHER_ALGORITHM = "AES";
    /**
     * 随机生成 IV 长度， 只能固定 16 位
     */
    private static final int IV_LENGTH = 16;
    private static final int ZERO = 0;
    /**
     * 固定 IV 。 设置固定 IV 之后相同内容每次加密结果一样
     */
    public static IvParameterSpec IV_PARAMETER;
    /**
     * jdk1.8.0_51之前的版本不支持 32为key加密，这里为了兼容所有版本设置为16位keu
     * 密钥， 长度:16字节(128位)
     *
     * @alias RandomStringUtils.randomAlphanumeric(16)
     * @alias RandomStringUtils.randomAscii(16)
     */
    public static byte[] SECRET_KEY;

    private Aes() {
    }

    /**
     * 设置密钥
     *
     * @param secretKey {@link String} RandomStringUtils.randomAlphanumeric(32)
     */
    public static void setSecretKey(final String secretKey) {
        if (Strings.isNullOrEmpty(secretKey) || secretKey.length() != 16) {
            throw new IllegalArgumentException("secretKey 长度必须是 16 位");
        }
        SECRET_KEY = secretKey.getBytes(UTF_8);
    }


    /**
     * 设置 IV ， 长度必须是 16 位自负
     *
     * @param iv {@link String} RandomStringUtils.randomAlphanumeric(16)
     */
    @SneakyThrows
    public static void setIV(final String iv) {
        if (Strings.isNullOrEmpty(iv) || iv.length() != 16) {
            throw new IllegalArgumentException("secretKey 长度必须是 16 位");
        }
        IV_PARAMETER = new IvParameterSpec(iv.getBytes(UTF_8));
    }

    /**
     * <pre>
     * aes 加密， 有两种模式：
     * 动态IV： （默认），相同内容每次获得的密文不一样
     * 固定IV： 通过设置 {@link Aes#setIV(String)} ，固定 IV 之后相同内容每次获得的密文都一样
     * </pre>
     *
     * @param data {@link String} 明文
     * @return {@link String} 密文
     */
    @SneakyThrows
    public static String encrypt(final String data) {
        if (Strings.isNullOrEmpty(data)) {
            return null;
        }
        if (Objects.isNull(IV_PARAMETER)) { // 未指定 iv 使用动态 iv 加密
            // CBC模式需要生成一个 16 bytes 的 IV（initialization vector）。  IV不需要保密，把IV和密文一起返回，返回随机IV的好处是每次加密都能获得不同的密文
            final byte[] ivBytes = RandomStringUtils.randomAlphanumeric(IV_LENGTH).getBytes(UTF_8);
            final IvParameterSpec iv = new IvParameterSpec(ivBytes);

            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            final SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY, CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            final byte[] dataBytes = cipher.doFinal(data.getBytes(UTF_8));

            final byte[] bytes = new byte[IV_LENGTH + dataBytes.length];
            System.arraycopy(ivBytes, ZERO, bytes, ZERO, IV_LENGTH);
            System.arraycopy(dataBytes, ZERO, bytes, IV_LENGTH, dataBytes.length);
            final String result = Base64.encodeBase64String(bytes);
            log.debug("{} => {}", data, result);
            return result;
        }
        // 使用指定的 IV 加密
        final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        final SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY, CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, IV_PARAMETER);
        final String result = Base64.encodeBase64String(cipher.doFinal(data.getBytes(UTF_8)));
        if (log.isDebugEnabled()) {
            log.debug("{} => {}", data, result);
        }
        return result;
    }

    @SneakyThrows
    public static String decrypt(final String data) {
        if (Strings.isNullOrEmpty(data)) {
            return null;
        }
        if (Objects.isNull(IV_PARAMETER)) { // 未指定 iv 使用动态 iv 解密
            // 将 data 分割成 IV 和密文
            final byte[] bytes = Base64.decodeBase64(data);
            final byte[] ivBytes = new byte[IV_LENGTH];
            final byte[] dataBytes = new byte[bytes.length - IV_LENGTH];
            System.arraycopy(bytes, ZERO, ivBytes, ZERO, IV_LENGTH);
            System.arraycopy(bytes, IV_LENGTH, dataBytes, ZERO, dataBytes.length);

            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            final SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY, CIPHER_ALGORITHM);
            final IvParameterSpec iv = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            final String result = new String(cipher.doFinal(dataBytes));
            if (log.isDebugEnabled()) {
                log.debug("{} => {}", data, result);
            }
            return result;
        }
        // 使用指定的 IV 解密
        final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        final SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY, CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IV_PARAMETER);
        final String result = new String(cipher.doFinal(Base64.decodeBase64(data)));
        if (log.isDebugEnabled()) {
            log.debug("{} => {}", data, result);
        }
        return result;
    }

    @SneakyThrows
    public static void main(String[] args) {
        /*{
            log.info("16位秘钥: {}", RandomStringUtils.randomAlphanumeric(16));
            log.info("16位IV: {}", RandomStringUtils.randomAlphanumeric(16));
        }
        final String secretKey = "JaqZS6k8VSY9s21I";
        final String iv = "h3p261h9KGPJ01Ay";
        Aes.setSecretKey(secretKey);
        // 动态 IV
        {
            final String planText = "测试";
            log.info("1.明文：{} => 加密：{}", planText, Aes.encrypt(planText));
            log.info("2.明文：{} => 加密：{}", planText, Aes.encrypt(planText));

            String encryptText = Aes.encrypt("测试");
            log.info("3.明文：{} => 加密：{} => 解密：{}", planText, encryptText, Aes.decrypt(encryptText));
            encryptText = Aes.encrypt("测试");
            log.info("4.明文：{} => 加密：{} => 解密：{}", planText, encryptText, Aes.decrypt(encryptText));
        }

        // 固定 IV
        {
            final String planText = "测试";
            log.info("5.明文：{} => 加密：{}", planText, Aes.encrypt(planText));
            log.info("6.明文：{} => 加密：{}", planText, Aes.encrypt(planText));

            String encryptText = Aes.encrypt("测试");
            log.info("7.明文：{} => 加密：{} => 解密：{}", planText, encryptText, Aes.decrypt(encryptText));
            encryptText = Aes.encrypt("测试");
            log.info("8.明文：{} => 加密：{} => 解密：{}", planText, encryptText, Aes.decrypt(encryptText));
        }

        {
            // 解密响应给金科的用户信息
            Aes.setSecretKey("tzLlg9LoIEfjCjs6");
            Aes.setIV("3XsbIlKf3DtE1fJe");
            String userInfo = Aes.decrypt("v678knO+326MKo+Fgfwx+Q==");
            log.info("解密出来的用户信息:{}", userInfo);
        }*/
        // {"sign":"LR3FP7auwpi2QdwNH3g6W7CVHvNj3JmapIcP4vvE6fPN4JKUQXyzKIcX4Zo9DN0gqAxJEdYBxc6yldzzKOH0EQ=="}
        TRANSFORMATION = "";
        Aes.setSecretKey("1234567890adbcde");
        Aes.setIV("1234567890hjlkew");
        String userInfo = Aes.decrypt("LR3FP7auwpi2QdwNH3g6W7CVHvNj3JmapIcP4vvE6fPN4JKUQXyzKIcX4Zo9DN0gqAxJEdYBxc6yldzzKOH0EQ==");
        log.info("解密出来的用户信息:{}", userInfo);
    }
}
