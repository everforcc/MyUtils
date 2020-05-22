package cn.cc.utils.fileio.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 发送端
 *
 * @author cc
 *
 */
public class UDPsend {
	public static void main(String[] args) {
		try {
			InetAddress ia = null;
			String message = "test";
			ia = InetAddress.getByName("YUKINO");
			DatagramPacket dp = new DatagramPacket(message.getBytes(),
					message.getBytes().length, ia, 8888);
			DatagramSocket ds = new DatagramSocket();
			ds.send(dp);
			ds.close();
		} catch (Exception e) {

		}
	}
}
