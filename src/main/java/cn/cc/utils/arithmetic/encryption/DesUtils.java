package cn.cc.utils.arithmetic.encryption;

import cn.cc.core.io.utils.PrintWriterUtils;

import javax.crypto.Cipher;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.security.Security;

/**
 * DES加密和解密工具,可以对字符串进行加密和解密操作  。
 */
public class DesUtils {

  /** 字符串默认键值     */
  private static String strDefaultKey = "national";

  /** 加密工具     */
  private Cipher encryptCipher = null;

  /** 解密工具     */
  private Cipher decryptCipher = null;

  /**
   * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
   * hexStr2ByteArr(String strIn) 互为可逆的转换过程
   *
   * @param arrB
   *            需要转换的byte数组
   * @return 转换后的字符串
   * @throws Exception
   *             本方法不处理任何异常，所有异常全部抛出
   */
  public static String byteArr2HexStr(byte[] arrB) throws Exception {
    int iLen = arrB.length;
    // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
    StringBuffer sb = new StringBuffer(iLen * 2);
    for (int i = 0; i < iLen; i++) {
      int intTmp = arrB[i];
      // 把负数转换为正数
      while (intTmp < 0) {
        intTmp = intTmp + 256;
      }
      // 小于0F的数需要在前面补0
      if (intTmp < 16) {
        sb.append("0");
      }
      sb.append(Integer.toString(intTmp, 16));
    }
    return sb.toString();
  }

  /**
   * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
   * 互为可逆的转换过程
   *
   * @param strIn
   *            需要转换的字符串
   * @return 转换后的byte数组
   * @throws Exception
   *             本方法不处理任何异常，所有异常全部抛出
   * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
   */
  public static byte[] hexStr2ByteArr(String strIn) throws Exception {
    byte[] arrB = strIn.getBytes();
    int iLen = arrB.length;

    // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
    byte[] arrOut = new byte[iLen / 2];
    for (int i = 0; i < iLen; i = i + 2) {
      String strTmp = new String(arrB, i, 2);
      arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
    }
    return arrOut;
  }

  /**
   * 默认构造方法，使用默认密钥
   *
   * @throws Exception
   */
  public DesUtils() throws Exception {
    this(strDefaultKey);
  }

  /**
   * 指定密钥构造方法
   *
   * @param strKey
   *            指定的密钥
   * @throws Exception
   */
  private DesUtils(String strKey) throws Exception {
    Security.addProvider(new com.sun.crypto.provider.SunJCE());
    Key key = getKey(strKey.getBytes());

    encryptCipher = Cipher.getInstance("DES");
    encryptCipher.init(Cipher.ENCRYPT_MODE, key);

    decryptCipher = Cipher.getInstance("DES");
    decryptCipher.init(Cipher.DECRYPT_MODE, key);
  }

  /**
   * 加密字节数组
   *
   * @param arrB
   *            需加密的字节数组
   * @return 加密后的字节数组
   * @throws Exception
   */
  private byte[] encrypt(byte[] arrB) throws Exception {
    return encryptCipher.doFinal(arrB);
  }

  /**
   * 加密字符串
   *
   * @param strIn
   *            需加密的字符串
   * @return 加密后的字符串
   * @throws Exception
   */
  private String encrypt(String strIn) throws Exception {
    return byteArr2HexStr(encrypt(strIn.getBytes()));
  }

  /**
   * 解密字节数组
   *
   * @param arrB
   *            需解密的字节数组
   * @return 解密后的字节数组
   * @throws Exception
   */
  private byte[] decrypt(byte[] arrB) throws Exception {
    return decryptCipher.doFinal(arrB);
  }

  /**
   * 解密字符串
   *
   * @param strIn
   *            需解密的字符串
   * @return 解密后的字符串
   * @throws Exception
   */
  private String decrypt(String strIn) throws Exception {
    return new String(decrypt(hexStr2ByteArr(strIn)));
  }

  /**
   * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
   *
   * @param arrBTmp
   *            构成该字符串的字节数组
   * @return 生成的密钥
   * @throws Exception
   */
  public Key getKey(byte[] arrBTmp) throws Exception {
    // 创建一个空的8位字节数组（默认值为0）
    byte[] arrB = new byte[8];

    // 将原始字节数组转换为8位
    for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
      // byte 默认为 0
      arrB[i] = arrBTmp[i];
    }

    // 生成密钥
    Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

    return key;
  }

  public static void  open(String username) throws Exception {
    String param = "autoLogin=2&loginFrom=Portal&userCode="+ username ;
    DesUtils des = new DesUtils("PORTALSYSTEM");//自定义密钥   portalsystem
    String url="http://10.1.1.141:8001/costmcs/sign/signOn.jsp?"+des.encrypt(param);
    System.out.println(url);
    openUrl(url);
  }

  private static void openUrl(String www){
    URL url = null;
    try {
      url = new URL(www);
    } catch (MalformedURLException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    try {
      Runtime.getRuntime().exec(
              "rundll32 url.dll,FileProtocolHandler "
                      + url);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * main方法  。
   * @param args
   */
  public static void main1(String[] args) {
    //10.1.1.141:8001/costmcs/sign/signOn.jsp?1be51e1e3ea86a1d88ef3f5b0b59cfe6576878c1a7d332bc60d12cbf76eb4b3d
    //10.1.1.141:8001/costmcs/sign/signOn.jsp?1be51e1e3ea86a1d88ef3f5b0b59cfe6576878c1a7d332bc60d12cbf76eb4b3d7f64bbef7d39def2ecee0d183a8cb5ac00c6a1036be1bb6a
    //                                                                                                        f3cb916471ae26865b5f8741ce527c704706acfeb3838838
    try {
      String test = "autoLogin=2&loginFrom=Portal&userCode=zhengyongqiang";
      DesUtils des = new DesUtils("PORTALSYSTEM");//自定义密钥   portalsystem
      System.out.println("加密前的字符：" + test);
      System.out.println("加密后的字符：" + "http://10.1.1.141:8001/costmcs/sign/signOn.jsp?"+des.encrypt(test));
      //System.out.println("加密后的字符：" + "http://localhost:8080/costmcs/sign/signOn.jsp?"+des.encrypt(test));
      System.out.println("解密后的字符：" + des.decrypt(des.encrypt(test)));

      //System.out.println("解密后的字符：" + des.decrypt("a3f152ee74a28d11a1129f272a5580e7"));//f3cb916471ae26864d53a811fd3a7d85
      //System.out.println(des.decrypt("1be51e1e3ea86a1d88ef3f5b0b59cfe6576878c1a7d332bc60d12cbf76eb4b3d073e7a17b0c7ea4bb43fa1c84de8829d4d53a811fd3a7d85"));
      //System.out.println(des.decrypt("1be51e1e3ea86a1d88ef3f5b0b59cfe6576878c1a7d332bc60d12cbf76eb4b3d073e7a17b0c7ea4bb43fa1c84de8829d4d53a811fd3a7d85"));
      //autoLogin=2&loginFrom=Portal&userCode=caozhe
      System.out.println(des.decrypt("1be51e1e3ea86a1d88ef3f5b0b59cfe6576878c1a7d332bc60d12cbf76eb4b3d1f729dca7930af18bc7dd2b14858cf4b"));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    try {
      DesUtils des = new DesUtils("讲个真人真事吧");
      String name = "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";
      System.out.println(des.decrypt(name));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }



}