package com.syl.basic.chapter3;

import java.util.Random;

/**
 * @ClassName: Bool
 * @Description: 关系运算符
 * @author Bright
 * @date 2017年7月7日 上午10:34:04
 */
public class Bool {
	public static void main(String[] args) {
		Random random = new Random();
		int i = random.nextInt(100) + 1;
		int j = random.nextInt(100) + 1;
		System.out.println("i=" + i);
		System.out.println("j=" + j);
		System.out.println("i>j is " + (i > j));
		System.out.println("i<j is " + (i < j));
		System.out.println("i>=j is " + (i >= j));
		System.out.println("i<=j is " + (i <= j));
		System.out.println("i==j is " + (i == j));
		System.out.println("1!=j is " + (i != j));
	}
}
