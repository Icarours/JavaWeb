package com.syl.basic.chapter7;

/**
 * @ClassName: Wind
 * @Description:向上转型
 * @author Bright
 * @date 2017年7月21日 上午10:27:42
 */
public class Wind extends Instrument {
	public static void main(String[] args) {
		Wind flute = new Wind();
		flute.play();
		Instrument.tune(flute);// upCasting
	}

	public void play(Note n) {
		super.play();
		System.out.println("Wind.play()" + n);
	}
}
