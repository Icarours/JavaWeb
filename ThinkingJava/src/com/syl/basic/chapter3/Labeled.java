package com.syl.basic.chapter3;

/**
 * @ClassName: LabeledFor
 * @Description:多重循环,使用标签跳转
 * @author Bright
 * @date 2017年7月7日 下午10:16:29
 */
public class Labeled {
	public static void main(String[] args) {
		 labeledFor();
//		labeledWhile();
	}

	/**
	 * @Title: labeledWhile
	 * @Description: while循环标签
	 * @return void
	 * @throws
	 */
	private static void labeledWhile() {
		int i = 0;
		outer: while (true) {
			inner: while (true) {
				System.out.println("i = " + i++);
				if (i == 1) {
					System.out.println("continue");
					continue;
				}
				if (i == 3) {
					System.out.println("continue outer");
					continue outer;
				}
				if (i == 5) {
					System.out.println("break");
					break;
				}
				if (i == 7) {
					System.out.println("continue inner");
					continue inner;
				}
				if (i == 9) {
					System.out.println("break outer");
					break outer;
				}
			}
		}
	}

	/**
	 * @Title: labeledFor
	 * @Description: for 标签循环
	 * @return void
	 * @throws
	 */
	private static void labeledFor() {
		int i = 0;
		outer: for (;;) {
			inner: for (; i < 10; i++) {
				System.out.println("i = " + i);
				if (i == 2) {
					System.out.println(i + "--continue");
					continue;
				}
				if (i == 3) {
					System.out.println(i + "break");
					i++;
					break;
				}
				if (i == 7) {
					System.out.println(i + "--continue outer");
					i++;
					continue outer;
				}
				if (i == 8) {
					System.out.println(i + "--break outer");
					break outer;
				}
				System.out.println("-----------------");
				for (int j = 0; j < 5; j++) {
					if (j == 3) {
						System.out.println("j = " + j + "--continue outer");
						continue inner;
					}
				}
			}
		}
	}
}
