package cn.cc.core;

public class Hashcode {
	public static void main(String[] args) {
		String s = "Hello";
		System.out.println(System.identityHashCode(s));
		s += "word";
		System.out.println(System.identityHashCode(s));
		StringBuffer sb = new StringBuffer();
		sb.append("Hello");
		System.out.println(System.identityHashCode(sb));
		sb.append("Hello");
		System.out.println(System.identityHashCode(sb));
		String s1 = "AB" + "C";
		String s2 = "A" + "BC";
		System.out.println(System.identityHashCode(s1));
		System.out.println(System.identityHashCode(s2));
		if (s1 == s2) {
			System.out.println("1");
		}

		int i = 1;
		for (int j = 1; j < 100; j++) {
			i = i++;
		}
		System.out.println(i);

	}
}
