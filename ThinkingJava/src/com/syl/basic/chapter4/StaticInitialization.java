package com.syl.basic.chapter4;

/**
 * @ClassName: StaticInitialization
 * @Description:静态初始化的顺序:静态成员变量,构造方法
 * @author Bright
 * @date 2017年7月8日 下午3:38:15
 */
public class StaticInitialization {
	public static void main(String[] args) {
		System.out.println("creating new cupboard() in main");
		new Cupboard();
		t2.f2(2);
		c3.f3(3);
	}

	static Table t2 = new Table();
	static Cupboard c3 = new Cupboard();
}

class Bowl {
	Bowl(int marker) {
		System.out.println("(Bowl" + marker + ")");
	}

	void f(int marker) {
		System.out.println("f(" + marker + ")");
	}
}

class Table {
	static Bowl b1 = new Bowl(1);

	Table() {
		System.out.println("Table()");
		b2.f(1);
	}

	void f2(int marker) {
		System.out.println("f2(" + marker + ")");
	}

	static Bowl b2 = new Bowl(2);
}

class Cupboard {
	Bowl b3 = new Bowl(3);
	static Bowl b4 = new Bowl(4);

	Cupboard() {
		System.out.println("Cupboard()");
		b4.f(4);
	}

	void f3(int marker) {
		System.out.println("f3(" + marker + ")");
	}

	static Bowl b5 = new Bowl(5);
}
