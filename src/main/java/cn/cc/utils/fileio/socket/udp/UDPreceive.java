package cn.cc.utils.fileio.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 接收端
 *
 * @author cc
 *
 */
public class UDPreceive {
	public static void main(String[] args) {
		try {
			byte[] b = new byte[1024];
			DatagramPacket dp = new DatagramPacket(b, 1024);
			DatagramSocket ds = new DatagramSocket(8888);
			ds.receive(dp);
			String message = new String(dp.getData(), 0, dp.getLength());
			System.out.println(dp.getAddress().getHostName() + message);
		} catch (Exception e) {

		}
	}
}
