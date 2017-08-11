package com.syl.baisc.chpater5;

/**
 * @ClassName: OutPut
 * @Description:从Storage中取出数据
 * @author Bright
 * @date 2017年8月11日 下午11:11:32
 */
public class OutPut implements Runnable {
	private Storage storage;

	public OutPut(Storage storage) {
		super();
		this.storage = storage;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			storage.get();
		}
	}
}
