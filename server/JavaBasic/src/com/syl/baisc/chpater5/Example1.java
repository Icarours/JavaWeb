package com.syl.baisc.chpater5;

/**
 * @ClassName: Example1
 * @Description: 多线程的创建
 * @author Bright
 * @date 2017年8月8日 下午11:08:06
 */
public class Example1 {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println("Main()方法中运行");
		}
		/*
		 * 最好还是使用Thread类来创建线程,Thread类中有线程的常用方法,实现Runnable接口里面的方法还需要自己来实现
		 */
		Thread myThread = new Thread() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("myThread()方法中运行");
				}
			};
		};
		myThread.start();
		Thread thread = new Thread(new MyThread1());
		thread.start();
	}
}

class MyThread1 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("myThread1()方法中运行");
		}
	}
}