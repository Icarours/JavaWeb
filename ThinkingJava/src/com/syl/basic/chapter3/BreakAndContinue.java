package com.syl.basic.chapter3;

/**
 * @ClassName: BreakAndContinue
 * @Description:BreakAndContinue,终止循环和跳出当前循环
 * @author Bright
 * @date 2017年7月7日 下午9:39:19
 */
public class BreakAndContinue {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			if (i == 90) {
				break;
			}
			if (i % 9 != 0) {
				continue;
			}
			System.out.println(i);
		}
		System.out.println("----------------");
		int i = 0;
		// 无限循环,等同于for(;;){}
		while (true) {
			i++;
			int j = i * 27;
			if (j == 1269) {
				break;
			}
			if (i % 10 != 0) {
				continue;
			}
			System.out.println(i);
		}
		System.out.println("----------------+++");
		// 无限循环,等同于while (true){}
		i = 0;
		for (;;) {
			i++;
			int j = i * 27;
			if (j >= 1269) {
				break;
			}
			if (i % 10 != 0) {
				continue;
			}
			System.out.println(i);
		}
	}
}
