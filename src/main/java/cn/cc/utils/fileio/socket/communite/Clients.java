package cn.cc.utils.fileio.socket.communite;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Clients {
	Socket socket;

	public static void main(String[] args) {
		new Clients();
	}

	public Clients() {
		try {
			socket = new Socket("localhost", 9999);
			if (socket.isConnected() == true) {
				System.out.println("连接成功");
				new Thread() {// 开启一个接受数据的线程
					@Override
					public void run() {
						InputStream in;
						try {
							in = socket.getInputStream();
							byte[] b = new byte[1024];
							int date = 0;
							while (true) {
								while ((date = in.read(b, 0, b.length)) != -1) {
									String result = new String(b, 0, date);
									System.out.println(result);
								}

								System.out.println("接收到服务端消息：" + b.toString());
							}
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}.start();
			}
			OutputStream out = null;
			while (true) {
				Scanner scanner = new Scanner(System.in);
				String str = scanner.nextLine();
				out = socket.getOutputStream();
				out.write(str.getBytes());
				out.flush();
				if (str.equals("fileio")) {
					System.exit(0);// 关闭客户端
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
