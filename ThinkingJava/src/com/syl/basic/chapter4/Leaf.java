package com.syl.basic.chapter4;

/**
 * @ClassName: Leaf
 * @Description:
 * @author Bright
 * @date 2017年7月8日 上午1:14:16
 */
public class Leaf {
	int i = 0;

	public static void main(String[] args) {
		Leaf leaf = new Leaf();
		leaf.increment().increment().increment().print();
	}

	Leaf increment() {
		i++;
		return this;
	}

	void print() {
		System.out.println("i = " + i);
	}
}
