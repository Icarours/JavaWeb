package com.syl.basic.chapter7;

/**
 * @ClassName: Music2
 * @Description:
 * @author Bright
 * @date 2017年7月23日 上午11:59:24
 */
public class Music2 {
	public static void main(String[] args) {
		Wind wind = new Wind();
		Stringed stringed = new Stringed();
		Brass brass = new Brass();

		tune(wind);
		tune(stringed);
		tune(brass);
	}

	public static void tune(Wind w) {
		w.play(Note.B_FLAT);
	}

	public static void tune(Stringed stringed) {
		stringed.play(Note.C_SHARP);
	}

	public static void tune(Brass brass) {
		brass.play(Note.MIDDEL_C);
	}
}

class Stringed extends Instrument {
	public void play(Note n) {
		System.out.println("Stringed.play() " + n);
	}
}

class Brass extends Instrument {
	public void play(Note n) {
		System.out.println("Brass.play() " + n);
	}
}

