package com.syl.baisc.chpater5;

/**
 * @ClassName: Example5
 * @Description: 线程休眠之后,有可能多卖出票
 * @author Bright
 * @date 2017年8月8日 下午11:40:20
 */
public class Example5 {
	public static void main(String[] args) {
		TicketWindow ticketWindow = new TicketWindow();
		new Thread(ticketWindow,"窗口1").start();
		new Thread(ticketWindow,"窗口2").start();
		new Thread(ticketWindow,"窗口3").start();
		new Thread(ticketWindow,"窗口4").start();
	}
}


