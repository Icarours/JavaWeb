package com.syl.basic.chapter5;

/**
 * @ClassName: Test
 * @Description:
 * @author Bright
 * @date 2017年7月10日 下午2:35:28
 */

public class Test {
	public static void main(String[] args) {
		Number n1 = new Number();
		Number n2 = new Number();
		Number n3 = new Number();
		n1.n = 1;
		n2.n = 2;
		n3.n = 3;
		System.out.println("n1.n = " + n1.n + ";n2.n = " + n2.n + ";n3.n = "
				+ n3.n);
		n1.n = 11;
		n2.n = n1.n;
		n3.n = n1.n;
		System.out.println("n1.n = " + n1.n + ";n2.n = " + n2.n + ";n3.n = "
				+ n3.n);
		n1.n = 11;
		n2.n = 22;
		n3.n = n2.n;
		System.out.println("n1.n = " + n1.n + ";n2.n = " + n2.n + ";n3.n = "
				+ n3.n);
	}
}

class Number {
	int n;
}
