package com.syl.baisc.chpater5;

/**
 * @ClassName: Example5
 * @Description:调用卖票类
 * @author Bright
 * @date 2017年8月8日 下午11:40:20
 */
public class Example12 {
	public static void main(String[] args) {
		Ticket1 ticketWindow = new Ticket1();
		new Thread(ticketWindow,"窗口1").start();
		new Thread(ticketWindow,"窗口2").start();
		new Thread(ticketWindow,"窗口3").start();
		new Thread(ticketWindow,"窗口4").start();
	}
}


