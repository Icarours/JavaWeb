package com.syl.basic.chapter3;

/**
 * @ClassName: AutoInc
 * @Description: 自动递增（increment）和递减（decrement）
 * @Description: int值转换为字符串
 * @author Bright
 * @date 2017年7月7日 上午9:42:03
 */
public class AutoInc {
	public static void main(String[] args) {
		int i = 1;
		System.out.println("i=" + i);
		System.out.println("i++=" + i++);
		System.out.println("++i=" + ++i);
		System.out.println("i--=" + i--);
		System.out.println("--i=" + --i);
		System.out.println("i=" + i);

		int x = 0, y = 1, z = 2;
		String sString = "x, y, z ";
		System.out.println(sString + x + y + z);// x, y, z直接被当做字符串处理
		System.out.println(sString + (x + y + z));// (x + y + z)先计算,在被当做字符串处理
		System.out.println(x + sString);
	}
}
