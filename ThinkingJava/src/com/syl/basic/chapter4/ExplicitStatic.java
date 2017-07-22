package com.syl.basic.chapter4;

/**
 * @ClassName: ExplicitStatic
 * @Description:静态初始化动作只进 行一次。
 * @author Bright
 * @date 2017年7月8日 下午4:06:36
 */
public class ExplicitStatic {
	public static void main(String[] args) {
		Cups.c1.f(99);// (1)
	}
	// static Cups x = new Cups(); // (2)
	// static Cups y = new Cups(); // (2)
}

class Cup {
	Cup(int marker) {
		System.out.println("Cup(" + marker + ")");
	}

	void f(int marker) {
		System.out.println("f(" + marker + ")");
	}
}

class Cups {
	static Cup c1;
	static Cup c2;
	static {
		c1 = new Cup(1);
		c2 = new Cup(2);
	}

	Cups() {
		System.out.println("Cups()");
	}
}
