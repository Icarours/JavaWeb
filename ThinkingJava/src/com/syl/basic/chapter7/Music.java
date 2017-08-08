package com.syl.basic.chapter7;


/**
 * @ClassName: Music
 * @Description:
 * @author Bright
 * @date 2017年7月23日 上午11:28:52
 */
public class Music {

	public static void main(String[] args) {
		tune(new Wind());
		Music music = new Music();
		System.out.println(music.add(3, 7));
	}

	/**
	 * 
	 * @Title: tune
	 * @Description:
	 * @param i
	 * @return void
	 * @throws
	 */
	public static void tune(Instrument i) {
		i.play(Note.C_SHARP);
	}

	private int add(int a, int b) {
		return a + b;
	}
}
/**
 * 
* @ClassName: Wind
* @Description: 
* @author Bright
* @date 2017年7月23日 下午1:45:45
 */
class Wind extends Instrument {

	public void play(Note n) {
		System.out.println("Wind.play()" + n);
	}
}
/**
 * @ClassName: Instrument
 * @Description:乐器的基类
 * @author Bright
 * @date 2017年7月21日 上午10:23:56
 */
class Instrument {
	public void play(Note n) {
		System.out.println(n + " is playing");
		System.out.println("Instrument.play() "+n);
	}
}

