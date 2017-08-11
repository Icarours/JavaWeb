package com.syl.baisc.chpater5;

/**
 * @ClassName: Ticket1
 * @Description: 卖票
 * @author Bright
 * @date 2017年8月9日 上午11:37:01
 */
public class Ticket1 implements Runnable {
	private int tickets = 100;

	@Override
	public void run() {
		while (true) {
			synchronized (Ticket1.class) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (tickets > 0) {
					String threadName = Thread.currentThread().getName();
					System.out.println(threadName + "正在发售,第" + tickets-- + "张票");
				} else {
					break;
				}
			}
		}
	}
}
