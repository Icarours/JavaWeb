package com.syl.baisc.chpater2;

/**
 * @ClassName: Example24
 * @Description: 递归,计算1到100之和
 * @author Bright
 * @date 2017年8月8日 下午10:04:40
 */
public class Example24 {
	public static void main(String[] args) {
		System.out.println(getSum(100));
	}

	public static int getSum(int n) {
		if (n == 1) {
			return 1;
		} else {
			return n + getSum(n - 1);
		}
	}
}
