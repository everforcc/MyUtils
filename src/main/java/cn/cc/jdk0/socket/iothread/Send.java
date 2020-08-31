package cn.cc.jdk0.socket.iothread;

/**
 * 客户端发消息
 */
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Send {
	public static void main(String[] args) throws UnknownHostException,
			IOException {

		Socket socket = new Socket("localhost", 9999);

		Thread th = new Thread(new TSend(socket));
		th.start();
		try {

			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			Scanner sc = new Scanner(System.in);
			String s = null;
			System.out.println("客户端：");
			while ((s = sc.next()) != null) {
				pw.println(s);
				pw.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
