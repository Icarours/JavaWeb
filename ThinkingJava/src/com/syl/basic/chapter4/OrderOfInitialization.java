package com.syl.basic.chapter4;

/**
 * @ClassName: OrderOfInitialization
 * @Description: 变量定义的先后顺序决定其初始化顺序
 * @Description: 1.初始化类的成员变量2.调用类的构造方法3.调用类的方法
 * @author Bright
 * @date 2017年7月8日 下午2:15:55
 */
public class OrderOfInitialization {
	public static void main(String[] args) {
		Card card = new Card();
		card.f();
	}
}

class Tag {
	Tag(int marker) {
		System.out.println("Tag(" + marker + ")");
	}
}

class Card {
	Tag t1 = new Tag(1);

	Card() {
		System.out.println("Card()");
		t3 = new Tag(33);
	}

	Tag t2 = new Tag(2);

	void f() {
		System.out.println("f()");
	}

	Tag t3 = new Tag(3);
}
