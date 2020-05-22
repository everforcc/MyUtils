package cn.cc.utils.fileio.socket.iothread;
/**
 * 服务器端接收端
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Receive {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(9999);
		Socket socket = ss.accept();

		/**
		 * 接收
		 */
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
		Thread th = new Thread(new TReceive(socket));
		th.start();
	}
}
