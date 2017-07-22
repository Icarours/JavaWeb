package com.syl.basic.chapter3;

import java.util.Random;

/**
 * @ClassName: BitManipulation
 * @Description:位运算
 * @author Bright
 * @date 2017年7月7日 下午1:02:52
 */
public class BitManipulation {
	public static void main(String[] args) {
		Random random = new Random();
		int i = random.nextInt(100) + 1;
		int j = random.nextInt(100) + 1;
		int intMax = Integer.MAX_VALUE;
		int intMin = Integer.MIN_VALUE;
		printBinaryInt("-1", -1);
		printBinaryInt("1", 1);
		printBinaryInt("intMax", intMax);
		printBinaryInt("intMin", intMin);
		printBinaryInt("i", i);
		printBinaryInt("-i", -i);// 负数 -i = i +(~i+1)
		printBinaryInt("~i", ~i);
		printBinaryInt("j", j);
		printBinaryInt("i&j", i & j);
		printBinaryInt("i|j", i | j);
		printBinaryInt("i^j", i ^ j);
		printBinaryInt("i<<5", i << 5);
		printBinaryInt("i>>5", i >> 5);
		printBinaryInt("i>>>5", i >>> 5);
		printBinaryInt("(~i)>>5", (~i) >> 5);
		printBinaryInt("(~i)>>>5", (~i) >>> 5);

		long l = random.nextLong();
		long m = random.nextLong();
		printBinaryLong("-1L", -1L);
		printBinaryLong("+1L", +1L);
		long longMax = Long.MAX_VALUE;
		printBinaryLong("longMax", longMax);
		long longMin = Long.MIN_VALUE;
		printBinaryLong("longMin", longMin);
		printBinaryLong("l", l);
		printBinaryLong("~l", ~l);
		printBinaryLong("-l", -l);
		printBinaryLong("m", m);
		printBinaryLong("l & m", l & m);
		printBinaryLong("l | m", l | m);
		printBinaryLong("l ^ m", l ^ m);
		printBinaryLong("l << 5", l << 5);
		printBinaryLong("l >> 5", l >> 5);
		printBinaryLong("(~l) >> 5", (~l) >> 5);
		printBinaryLong("l >>> 5", l >>> 5);
		printBinaryLong("(~l) >>> 5", (~l) >>> 5);
	}

	static void printBinaryInt(String s, int i) {
		System.out.println(s + ",int is " + i + " binary is ");
		System.out.print("	");
		for (int j = 31; j >= 0; j--) {
			if (((1 << j) & i) != 0) {// i的每个二进制位都和1进行&运算,1 << j,的二进制位上都是1
				System.out.print("1");
			} else {
				System.out.print("0");
			}
		}
		System.out.println();
	}

	static void printBinaryLong(String s, long i) {
		System.out.println(s + ",long is " + i + " binary is ");
		System.out.print("	");
		for (int j = 63; j >= 0; j--) {
			if (((1 << j) & i) != 0) {
				System.out.print("1");
			} else {
				System.out.print("0");
			}
		}
		System.out.println();
	}
}
