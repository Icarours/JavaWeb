package com.syl.basic.chapter3;

/**
 * @ClassName: OverFlow
 * @Description:int取值范围溢出
 * @author Bright
 * @date 2017年7月7日 下午4:27:21
 */
public class OverFlow {
	public static void main(String[] args) {
		int intMax = Integer.MAX_VALUE / 2;
		System.out.println("intMax=" + intMax);
		int bigger = intMax * 3;
		System.out.println("bigger=" + bigger);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(0x7fffffff);
	}
}
