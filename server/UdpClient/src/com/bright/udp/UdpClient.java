package com.bright.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpClient {
	public static void main(String[] args) {
		byte[] buf = new byte[1024];
		try {
			// 创建端口号
			DatagramSocket ds = new DatagramSocket(8954);
			// 创建数据包,用于接收数据
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			System.out.println("等待接收数据.......");
			ds.receive(dp);
			// 将接受到的数据打印出来
			String str = new String(dp.getData(), 0, dp.getLength()) + "---from---"
					+ dp.getAddress().getHostAddress() + ":" + dp.getPort();
			System.out.println(str);
			ds.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
