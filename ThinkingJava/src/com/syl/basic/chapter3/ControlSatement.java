package com.syl.basic.chapter3;

import java.util.Random;

/**
 * @ClassName: ControlSatement
 * @Description:控制语句举例:while
 * @author Bright
 * @date 2017年7月7日 下午4:50:07
 */
public class ControlSatement {
	public static void main(String[] args) {
		// whileTest();
		// doWhileTest();
		// listLowCharacters();
		 listCharacters();
//		commaTest();
	}

	/**
	 * @Title: forTest
	 * @Description: 逗号操作符
	 * @return void
	 * @throws
	 */
	private static void commaTest() {
		for (int i = 0, j = i + 10; i < 4; i++, j = i * 2) {
			System.out.println("i = " + i + ";j = " + j);
		}
	}

	/**
	 * @Title: listCharacters
	 * @Description: forTest
	 * @Description: 列出字母(包括大写字母和小写字母)
	 * @return void
	 * @throws
	 */
	private static void listCharacters() {
		for (int i = 0; i < 128; i++) {
			if (Character.isLowerCase((char) i)
					|| Character.isUpperCase((char) i)) {
				System.out
						.println("character value is " + (char) i + "---" + i);
			}
		}
	}

	/**
	 * @Title: listLowCharacters
	 * @Description:forTest
	 * @Description:列出小写字母
	 * @return void
	 * @throws
	 */
	private static void listLowCharacters() {
		for (int i = 0; i < 128; i++) {
			if (Character.isLowerCase((char) i)) {
				System.out.println("character value is " + (char) i);
			}
		}
	}

	/**
	 * @Title: doWhileTest
	 * @Description:控制语句doWhile
	 * @return void
	 * @throws
	 */
	private static void doWhileTest() {
		int i = 1;
		do {
			Random random = new Random();
			i = random.nextInt(100) + 1;
			System.out.println(i);
		} while (i < 90);
	}

	/**
	 * @Title: whileTest
	 * @Description: 控制语句while
	 * @return void
	 * @throws
	 */
	private static void whileTest() {
		int i = 1;
		while (i < 90) {
			Random random = new Random();
			i = random.nextInt(100) + 1;
			System.out.println(i);
		}
	}

}
