package com.syl.basic.chapter4;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * @ClassName: Overloading
 * @Description: 方法重载 构造方法的重载和普通方法的重载
 * @author Bright
 * @date 2017年7月8日 上午12:22:20
 */
public class Overloading {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			Tree tree = new Tree(i);
			tree.info();
			tree.info("overloading");
			
		}
		System.out.println("-----");
		// Overloaded constructor:
		new Tree();
	}
}

class Tree {
	int height;

	Tree() {
		this(0);
	}

	Tree(int height) {
		System.out.println("creating a new Tree that is " + height
				+ " feet tall.");
		this.height = height;
	}

	void info() {
		this.info("");
	}

	void info(String s) {
		System.out.println(s + " Tree is " + height + " feet tall.");
	}
}