package com.syl.baisc.chpater5;

/**
 * @ClassName: DeadLock
 * @Description: 死锁
 * @author Bright
 * @date 2017年8月11日 下午9:24:15
 */
public class DeadLock implements Runnable {
	static Object knifeAndFork = new Object();
	static Object chopsticks = new Object();
	private boolean flag;

	/**
	 * @param b
	 */
	public DeadLock(boolean b) {
		this.flag = b;
	}

	@Override
	public void run() {
		if (flag) {
			while (true) {
				synchronized (chopsticks) {// chopsticks锁对象上的同步代码块
					System.out.println(Thread.currentThread().getName() + "---if chopsticks");
					synchronized (knifeAndFork) {// knifeAndFork锁对象上的同步代码块
						System.out.println(Thread.currentThread().getName() + "---if knifeAndFork");
					}
				}
			}
		} else {
			while (true) {
				synchronized (knifeAndFork) {// knifeAndFork锁对象上的同步代码块
					System.out.println(Thread.currentThread().getName() + "---else knifeAndFork");
					synchronized (chopsticks) {// chopsticks锁对象上的同步代码块
						System.out.println(Thread.currentThread().getName() + "---else chopsticks");
					}
				}
			}
		}
	}
}
