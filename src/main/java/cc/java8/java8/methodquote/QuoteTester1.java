package cc.java8.java8.methodquote;

import java.util.ArrayList;
import java.util.List;

public class QuoteTester1 {
	public static void main(String args[]){
		List<String> names = new ArrayList<String>();

		names.add("Google");
		names.add("Runoob");
		names.add("Taobao");
		names.add("Baidu");
		names.add("Sina");

		// 可以去看源码forEach最上面有调用
		names.forEach(System.out::println);
	}
}
