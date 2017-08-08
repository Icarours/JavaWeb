package com.syl.baisc.chpater5;

/**
 * @ClassName: Example5
 * @Description:
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

class TicketWindow implements Runnable {
	private int tickets = 100;

	@Override
	public void run() {
		while (true) {
			if (tickets > 0) {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName + "正在发售,第" + tickets-- + "张票");
			}
		}
	}
}
