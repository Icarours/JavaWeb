package com.syl.basic.chapter7;

/**
 * @ClassName: Music3
 * @Description:乐队,音符,乐器
 * @author Bright
 * @date 2017年7月23日 下午2:39:42
 */
public class Music3 {
	public static void main(String[] args) {
		Instrument3[] orchestra = { new Wind3(), new Percussion3(),
				new Stringed3(), new Brass3(), new WoodWind3() };
		tuneAll(orchestra);
	}

	public static void tune(Instrument3 i) {
		i.play(Note.MIDDEL_C);
	}

	public static void tuneAll(Instrument3[] instrument3s) {
		for (int j = 0; j < instrument3s.length; j++) {
			tune(instrument3s[j]);
		}
	}
}

/**
 * 基类
 * 
 * @ClassName: Instrument3
 * @Description:
 * @author Bright
 * @date 2017年7月23日 下午2:42:29
 */
class Instrument3 {
	String what() {
		return "Instrument3";
	}

	void ajust() {
	}

	void play(Note n) {
		System.out.println(this.getClass().getSimpleName());
		System.out.println(this.getClass().getSimpleName() + "--play()" + n);
	}
}

class Wind3 extends Instrument3 {

	@Override
	String what() {
		return "Wind3";
	}

	@Override
	void ajust() {
		System.out.println("Wind3.ajust()");
	}

	@Override
	void play(Note n) {
		// TODO Auto-generated method stub
		super.play(n);
	}
}

class Percussion3 extends Instrument3 {

	@Override
	String what() {
		return "Percussion3";
	}

	@Override
	void ajust() {
		System.out.println("Percussion3.ajust()");
	}

	@Override
	void play(Note n) {
		super.play(n);
	}
}

class Stringed3 extends Instrument3 {

	@Override
	String what() {
		return "Stringed3";
	}

	@Override
	void ajust() {
		System.out.println("Stringed3.adjust()");
	}

	@Override
	void play(Note n) {
		System.out.println("Stringed3.play() " + n);
	}
}

class Brass3 extends Instrument3 {

	@Override
	String what() {
		return "Brass3";
	}

	@Override
	void ajust() {
		System.out.println("Brass3.adjust()");
	}

	@Override
	void play(Note n) {
		System.out.println("Brass3.play() " + n);
	}

}

class WoodWind3 extends Instrument3 {

	@Override
	String what() {
		return "WoodWind3";
	}

	@Override
	void ajust() {
		System.out.println("WoodWind3.ajust()");
	}

	@Override
	void play(Note n) {
		System.out.println("WoodWind3.play() " + n);
	}
}
