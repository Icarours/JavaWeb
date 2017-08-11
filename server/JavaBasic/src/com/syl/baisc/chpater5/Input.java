package com.syl.baisc.chpater5;

/**
 * @ClassName: Input
 * @Description:向Storage中存入数据(存的太快,瞬间就到了数千)
 * @author Bright
 * @date 2017年8月11日 下午11:07:48
 */
public class Input implements Runnable {
	private Storage storage;
	private int num ;

	public Input(Storage storage) {
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
			System.out.println("num==" + num);
			storage.put(num++);
		}
	}
}
