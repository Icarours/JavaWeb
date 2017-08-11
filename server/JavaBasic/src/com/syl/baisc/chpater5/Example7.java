package com.syl.baisc.chpater5;

/**
 * @ClassName: Example7
 * @Description:
 * @author Bright
 * @date 2017年8月11日 下午11:14:25
 */
public class Example7 {
	public static void main(String[] args) {
		Storage storage = new Storage();
		
		Input input = new Input(storage);
		OutPut outPut = new OutPut(storage);
		
		new Thread(input).start();
		new Thread(outPut).start();
	}
}
