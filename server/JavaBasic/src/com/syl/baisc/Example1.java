package com.syl.baisc;

/**
 * @ClassName: Example1
 * @Description: Math常用函数
 * @author Bright
 * @date 2017年8月9日 下午7:39:30
 */
public class Example1 {
	public static void main(String[] args) {
		System.out.println("Math.round(19.6)=" + Math.round(19.6));// 四舍五入
		System.out.println("Math.round(-19.6)=" + Math.round(-19.6));

		System.out.println("Math.abs(19.6)=" + Math.abs(19.6));
		System.out.println("Math.E=" + Math.E);// 自然对数的底数
		System.out.println("Math.PI=" + Math.PI);// 圆周率
		System.out.println("Math.floor(19.6)=" + Math.floor(19.6));// 取整,带0
		System.out.println("Math.floor(-19.6)=" + Math.floor(-19.6));// 取整,带0
		System.out.println("Math.max(10.5, 20.1)=" + Math.max(10.5, 20.1));// 最大值

		System.out.println("Math.ceil(19.6)=" + Math.ceil(19.6));// 取整
		System.out.println("Math.ceil(-19.6)=" + Math.ceil(-19.6));// 取整

		System.out.println("Math.pow(3, 4)=" + Math.pow(3, 4));// 指数运算
	}
}
