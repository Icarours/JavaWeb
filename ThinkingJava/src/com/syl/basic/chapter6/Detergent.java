package com.syl.basic.chapter6;

/**
 * 
 * @ClassName: Detergent
 * @Description: 继承
 * @author Bright
 * @date 2017年7月9日 下午3:39:26
 */
public class Detergent extends Cleanser {

	public static void main(String[] args) {
		Detergent x = new Detergent();
		x.dilute();
		x.apply();
		x.scrub();
		x.foam();
		System.out.println(x);
		System.out.println("Testing base class:");
	}

	// Change a method:
	public void scrub() {
		append(" Detergent.scrub()");
		super.scrub(); // Call base-class version
	}

	// Add methods to the interface:
	public void foam() {
		append(" foam()");
	}
}

/**
 * 
 * @ClassName: Cleanser
 * @Description: 父类
 * @author Bright
 * @date 2017年7月9日 下午3:40:17
 */
class Cleanser {
	private String s = new String("Cleanser");

	public void append(String a) {
		s += a;
	}

	public void dilute() {
		append(" dilute()");
	}

	public void apply() {
		append(" apply()");
	}

	public void scrub() {
		append(" scrub()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cleanser [s=" + s + "]";
	}
}
