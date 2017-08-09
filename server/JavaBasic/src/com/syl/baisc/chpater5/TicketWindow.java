package com.syl.baisc.chpater5;

/**
 * @ClassName: TicketWindow
 * @Description: 
 * @author Bright
 * @date 2017年8月9日 上午11:35:05
 */
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