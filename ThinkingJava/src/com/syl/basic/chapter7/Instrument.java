package com.syl.basic.chapter7;

/**
 * @ClassName: Instrument
 * @Description:乐器的基类
 * @author Bright
 * @date 2017年7月21日 上午10:23:56
 */
public class Instrument {
	public void play() {
	}

	static void tune(Instrument i) {
		i.play();
	}
}
