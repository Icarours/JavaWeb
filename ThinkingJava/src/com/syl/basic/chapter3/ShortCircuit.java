package com.syl.basic.chapter3;

/**
 * @ClassName: ShortCircuit
 * @Description:短路&&,如果多个boolean值中前面有一个是false,后面的就不用判断了,直接返回false
 * @author Bright
 * @date 2017年7月7日 上午10:51:22
 */
public class ShortCircuit {
	public static void main(String[] args) {
		if (test1(0) && test2(2) && test3(3)) {
			System.out.println("expression is true");
		} else {
			System.out.println("expression is false");
		}
	}

	static boolean test1(int i) {
		System.out.println("test1 i=" + i);
		System.out.println("test1 result is " + (i < 1));
		return i < 1;
	}

	static boolean test2(int i) {
		System.out.println("test2 i=" + i);
		System.out.println("test2 result is " + (i < 2));
		return i < 2;
	}

	static boolean test3(int i) {
		System.out.println("test3 i=" + i);
		System.out.println("test3 result is " + (i < 3));
		return i < 3;
	}
}
