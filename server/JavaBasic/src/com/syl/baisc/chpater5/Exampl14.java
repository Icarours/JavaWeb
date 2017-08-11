package com.syl.baisc.chpater5;

/**
 * @ClassName: Exampl14
 * @Description:死锁测试
 * @author Bright
 * @date 2017年8月11日 下午9:40:46
 */
public class Exampl14 {
	public static void main(String[] args) {
		DeadLock deadLock1 = new DeadLock(true);
		DeadLock deadLock2 = new DeadLock(false);

		new Thread(deadLock1, "Chinese").start();
		new Thread(deadLock2, "America").start();
	}
}
