package com.syl.basic.chapter4;

/**
 * @ClassName: Mugs
 * @Description: 非静态变量初始化
 * @author Bright
 * @date 2017年7月8日 下午4:23:03
 */
public class Mugs {

	public static void main(String[] args) {
		System.out.println("Inside main()");
		new Mugs();
	}

	Mug c1;
	Mug c2;
	/**
	 * 和静态代码块相比,只少了一个static关键字
	 */
	{
		c1 = new Mug(1);
		c2 = new Mug(2);
		System.out.println("c1 & c2 initialized");
	}

	Mugs() {
		System.out.println("Mugs()");
	}
}

class Mug {
	Mug(int marker) {
		System.out.println("Mug(" + marker + ")");
	}

	void f(int marker) {
		System.out.println("f(" + marker + ")");
	}
}
