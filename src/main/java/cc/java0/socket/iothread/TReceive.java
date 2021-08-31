package cc.java0.socket.iothread;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 服务器多线程发消息
 *
 * @author cc
 *
 */
public class TReceive implements Runnable {
	Socket socket;

	public TReceive(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {

			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			Scanner sc = new Scanner(System.in);
			String s = null;
			System.out.println("服务器：");
			while ((s = sc.next()) != null) {
				pw.println(s);
				pw.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
