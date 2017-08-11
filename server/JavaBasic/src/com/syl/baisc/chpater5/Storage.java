package com.syl.baisc.chpater5;

/**
 * @ClassName: Storage
 * @Description:存储int值的类
 * @author Bright
 * @date 2017年8月11日 下午10:49:15
 */
public class Storage {
	private int[] cells = new int[10];
	private int inPos, outPos;
	private int count;//记住cells中元素的个数

	public synchronized void put(int num) {
		try {
			while (count == cells.length) {//数据存储完毕之后,线程等待
				this.wait();
			}
			cells[inPos] = num;
			System.out.println("在cells中加入数据cells[" + inPos + "],加入数据---" + cells[inPos]);
			inPos++;
			if (inPos == cells.length) {
				inPos = 0;
			}
			count++;
			this.notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void get() {
		try {
			while (count == 0) {//数据全部取出后,线程等待
				this.wait();
			}
			int data = cells[outPos];
			System.out.println("从cells中取出数据cells[" + outPos + "],取出数据---" + data);
//			cells[outPos] = 0;// 好像没有必要特意将数组中的元素置为0
			outPos++;
			if (outPos == cells.length) {
				outPos = 0;
			}
			count--;
			this.notify();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
