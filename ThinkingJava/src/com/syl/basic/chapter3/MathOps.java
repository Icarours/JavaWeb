package com.syl.basic.chapter3;

import java.util.Random;

/**
 * @ClassName: MathOps
 * @Description: 常用的算术运算符
 * @author Bright
 * @date 2017年7月7日 上午12:06:43
 */
public class MathOps {
	public static void main(String[] args) {
		int i, j, k;
		Random random = new Random();
		j = random.nextInt(100) + 1;
		k = random.nextInt(100) + 1;
		printInt("j", j);
		printInt("k", k);
		i = j + k;
		printInt("j+k", i);
		i = j - k;
		printInt("j-k", i);
		i = j / k;
		printInt("j/k", i);
		i = j * k;
		printInt("j*k", i);
		i = j % k;
		printInt("j+k", i);
		j %= k;
		printInt("j %=k", i);

		float u, v, w; // applies to doubles, too
		v = random.nextFloat();
		w = random.nextFloat();
		printFloat("v", v);
		printFloat("w", w);
		u = v + w;
		printFloat("v + w", u);
		u = v - w;
		printFloat("v - w", u);
		u = v * w;
		printFloat("v * w", u);
		u = v / w;
		printFloat("v / w", u);
		u += v;
		printFloat("u += v", u);
		u -= v;
		printFloat("u -= v", u);
		u *= v;
		printFloat("u *= v", u);
		u /= v;
		printFloat("u /= v", u);
	}

	/**
	 * 
	 * @Title: printInt
	 * @Description: 打印int值
	 * @param s
	 * @param i
	 * @return void
	 * @throws
	 */
	static void printInt(String s, int i) {
		System.out.println(s + "=" + i);
	}

	/**
	 * 
	 * @Title: printFloat
	 * @Description: 打印float值
	 * @param s
	 * @param f
	 * @return void
	 * @throws
	 */
	static void printFloat(String s, float f) {
		System.out.println(s + "=" + f);
	}
}
