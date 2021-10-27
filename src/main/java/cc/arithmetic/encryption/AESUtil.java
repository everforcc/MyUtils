package cc.arithmetic.encryption;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * AES加密工具类
 *
 * @author shuRen
 */
public class AESUtil {

    private static Logger logger = Logger.getLogger(AESUtil.class);

    public static String generate_key() {
        return "cloud_work_vr";
    }

    private static Map<String, Cipher> keyMap = new HashMap<>();

    private static Map<String, Cipher> keyDecryptMap = new HashMap<>();

    private synchronized static Cipher getCipher(String password) {
        if (StringUtils.isBlank(password)) {
            return null;
        }
        Cipher cipher = keyMap.get(password);
        if (cipher == null) {
            try {
                byte[] keyBytes = Arrays.copyOf(password.getBytes("ASCII"), 16);

                SecretKey key = new SecretKeySpec(keyBytes, "AES");

                cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, key);
                keyMap.put(password, cipher);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cipher;
    }

    private synchronized static Cipher getDecryptCipher(String password) {
        if (StringUtils.isBlank(password)) {
            return null;
        }
        Cipher cipher = keyDecryptMap.get(password);
        if (cipher == null) {
            try {
                byte[] keyBytes = Arrays.copyOf(password.getBytes("ASCII"), 16);

                SecretKey key = new SecretKeySpec(keyBytes, "AES");

                cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, key);
                keyDecryptMap.put(password, cipher);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cipher;
    }

    public static String aes_encrypt(String password) {
        try {
            Cipher cipher = getCipher(generate_key());
            byte[] cleartext = password.getBytes("UTF-8");
            byte[] ciphertextBytes = cipher.doFinal(cleartext);
            return new String(Hex.encodeHex(ciphertextBytes));

        } catch (Exception e) {
            System.out.println("aes_encrypt error:" + password);
            e.printStackTrace();
        }
        return null;
    }

    public static String aes_decrypt(String password) {
        try {
            if (StringUtils.startsWith(password, "{AES}")) {
                password = StringUtils.replace(password, "{AES}", "");
            }
            Cipher cipher = getDecryptCipher(generate_key());

            byte[] cleartext = Hex.decodeHex(password.toCharArray());
            byte[] ciphertextBytes = cipher.doFinal(cleartext);
            return new String(ciphertextBytes, "UTF-8");

        } catch (Exception e) {
            System.out.println("aes_decrypt error:" + password);
            e.printStackTrace();
        }
        return null;
    }

    public static String safe_aes_decrypt(String password) {
        if (StringUtils.isEmpty(password)) {
            return password;
        }
        if (StringUtils.startsWith(password, "{AES}")) {
            password = StringUtils.replace(password, "{AES}", "");
        }
        try {
            Cipher cipher = getDecryptCipher(generate_key());

            byte[] cleartext = Hex.decodeHex(password.toCharArray());
            byte[] ciphertextBytes = cipher.doFinal(cleartext);
            return new String(ciphertextBytes, "UTF-8");

        } catch (Exception e) {
            System.out.println("safe_aes_decrypt error:" + password);
            e.printStackTrace();
        }
        return null;
    }

    public static String aes_encrypt(String password, String strKey) {
        try {
            if (StringUtils.isBlank(strKey)) {
                return password;
            }
            Cipher cipher = getCipher(strKey);

            byte[] cleartext = password.getBytes("UTF-8");
            byte[] ciphertextBytes = cipher.doFinal(cleartext);

            return new String(Hex.encodeHex(ciphertextBytes));

        } catch (Exception e) {
            System.out.println("aes_encrypt2 error:" + password);
            //logger.error(e);
        }
        return null;
    }

    public static String aes_decrypt(String password, String strKey) {
        if (StringUtils.isNotBlank(password) && password.contains("{AES}")) {
            password = password.substring(5);
        }
        try {
            Cipher cipher = getDecryptCipher(strKey);

            byte[] cleartext = Hex.decodeHex(password.toCharArray());
            byte[] ciphertextBytes = cipher.doFinal(cleartext);
            return new String(ciphertextBytes, "UTF-8");

        } catch (Exception e) {
            System.out.println("aes_encrypt2 error:" + password);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回数据格式为{AES}加密字符串
     *
     * @param str
     * @return
     */
    public static String aesEncryptFormat(String str) {
        return "{AES}" + aes_encrypt(str);
    }

    /**
     * 返回数据格式为{AES}加密字符串 安全
     *
     * @param str
     * @return
     */
    public static String safeEncryptFormat(String str) {
        if (StringUtils.isEmpty(str) || StringUtils.startsWith(str, "{AES}")) {
            return str;
        }
        return "{AES}" + aes_encrypt(str);
    }

    public static void main(String[] args) {
        String str = aes_encrypt("{\"phone\":\"13813813138\",\"lon\":\"120.6794\",\"lat\":\"31.31696\"}");
        System.out.println(str);
        System.out.println(aes_decrypt("e30c2ef93e72e6551456319993c4b32faf5665651be2fd7fb1185438a86c50999099790bcb198dd0145667f7034e5a56c546655e9051509d713d09404044313a"));
    }

}
