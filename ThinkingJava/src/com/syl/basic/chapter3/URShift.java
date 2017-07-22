package com.syl.basic.chapter3;

/**
 * @ClassName: URShift
 * @Description:无符号右移和有符号右移
 * @author Bright
 * @date 2017年7月7日 下午12:50:48
 */
public class URShift {
	public static void main(String[] args) {
		byte b = 1;
		System.out.println("b= " + b);
		System.out.println("b>>>10 = " + (b >>> 10));
		System.out.println("b>>10 = " + (b >> 10));
		short s = 1;
		System.out.println("s= " + s);
		System.out.println("s>>>10 = " + (s >>> 10));
		System.out.println("s>>10 = " + (s >> 10));
		int i = 1;
		System.out.println("i = " + i);
		System.out.println("i>>>10 = " + (i >>> 10));
		System.out.println("i>>10 = " + (i >> 10));
		long l = 1;
		System.out.println("l = " + l);
		System.out.println("l>>>10 = " + (l >>> 10));
		System.out.println("l>>10 = " + (l >> 10));

		b = -1;
		System.out.println("b= " + b);
		System.out.println("b>>>10 = " + (b >>> 10));
		System.out.println("b>>10 = " + (b >> 10));

		System.out.println(1 << 1);
		System.out.println(1 << 2);
	}
}
