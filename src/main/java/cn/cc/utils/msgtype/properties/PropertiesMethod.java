package cn.cc.utils.msgtype.properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 读取各种情况的配置文件
 */
public class PropertiesMethod {
	private static Properties commonProperties;

	/**
	 * 基于ClassLoder读取配置文件
	 * @throws Exception
	 */
	public Properties classLoad()throws Exception{
		Properties properties = new Properties();
		// 使用ClassLoader加载properties配置文件生成对应的输入流
		InputStream in = PropertiesMethod.class.getClassLoader().getResourceAsStream("config/config.properties");
		// 使用properties对象加载输入流
		properties.load(in);
		//获取key对应的value值
		//properties.getProperty(String key);
		return properties;
	}

	/**
	 * 读取系统的
	 * @return
	 * @throws Exception
	 */
	public Properties sysLoad()throws Exception{
		Properties properties = new Properties();
		// 使用InPutStream流读取properties文件
		BufferedReader bufferedReader = new BufferedReader(new FileReader("E:/config.properties"));
		properties.load(bufferedReader);
		// 获取key对应的value值
		//properties.getProperty(String key);
		return properties;
	}

	/**
	 * 用javaapi读取
	 * @return
	 */
	public ResourceBundle javaUtil() {
		//config为属性文件名，放在包com.test.config下，如果是放在src下，直接用config即可
		ResourceBundle resource = ResourceBundle.getBundle("com/test/config/config");
		String key = resource.getString("keyWord");
		return resource;
	}

}
