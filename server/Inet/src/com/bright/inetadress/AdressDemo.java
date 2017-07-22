package com.bright.inetadress;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AdressDemo {
	public static void main(String[] args) {
		try {
			String host = "www.baidu.com";
			InetAddress remoteAddress = InetAddress.getByName(host);
			String hostAddress = remoteAddress.getHostAddress();
			String hostName = remoteAddress.getHostName();
			boolean isReach = remoteAddress.isReachable(3000);
			InetAddress localHost = InetAddress.getLocalHost();
			System.out.println("remoteAddress===" + remoteAddress);
			System.out.println("localHost===" + localHost);
			System.out.println("hostAddress=="+hostAddress);
			System.out.println("hostName=="+hostName);
			System.out.println("3秒内是否可达isReach=="+isReach);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
