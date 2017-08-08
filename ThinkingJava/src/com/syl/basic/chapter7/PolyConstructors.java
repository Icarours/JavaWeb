package com.syl.basic.chapter7;

/**
 * @ClassName: PolyConstructors
 * @Description:
 * @author Bright
 * @date 2017年7月23日 下午7:13:36
 */
public class PolyConstructors {
	public static void main(String[] args) {
		new RoundGlyph(5);//父类调用子类方法,子类初始化还没有完成,radius初始化时,在内存中会被置为二进制的0.
	}
}

abstract class Glyph {
	abstract void draw();

	Glyph() {
		System.out.println("Glyph() before draw()");
		draw();
		System.out.println("Glyph() after draw()");
	}
}

class RoundGlyph extends Glyph {
	private int radius = 1;

	RoundGlyph(int r) {
		radius = r;
		System.out.println("RoundGlyph.RoundGlyph(), radius = " + radius);
	}

	void draw() {
		System.out.println("RoundGlyph.draw(), radius = " + radius);
	}
}
