package cn.cc.java0.system;

import java.net.InetAddress;

public class ip {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// InetAddress.getLocalHost();
			// InetAddress ia = null;
			InetAddress ia = InetAddress.getLocalHost();
			// byte[] a = ia.getAddress();
			String b = ia.getHostAddress();
			String c = ia.getHostName();
			// System.out.println(a);
			System.out.println(b);
			System.out.println(c);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
