package com.syl.baisc.chpater5;

/**
 * @ClassName: Example6
 * @Description: 后台线程,前台线程死亡后jvm会通知后台线程,这个过程有一点时间的之后后台线程在前台结束之后结束,
 * @author Bright
 * @date 2017年8月9日 上午12:10:52
 */
public class Example6 {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		System.out.println("main是后台线程吗?"+Thread.currentThread().isDaemon());
		Thread thread = new Thread(new DamonThread(),"后台线程");
		System.out.println("main是后台线程吗?"+thread.isDaemon());
		thread.setDaemon(true);//thread设置成后台线程之后才成了后台线程
		thread.start();
		System.out.println("main是后台线程吗?"+thread.isDaemon());
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}
	}
}

class DamonThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getName() + "is running..");
		}
	}
}