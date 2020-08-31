package cn.cc.jdk0.socket.iothread;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 接收消息
 *
 * @author cc
 *
 */
public class TSend implements Runnable {
	Socket socket;

	public TSend(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// socket.isConnected()
			while (true) {
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String s = null;
				if ((s = br.readLine()) != null) {
					System.out.println(s);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
