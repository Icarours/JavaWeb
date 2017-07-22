package com.syl.basic.chapter3;

/**
 * @ClassName: VowelsAndConsonants
 * @Description: 开关switch语句
 * @Description: double,char转换为int值
 * @author Bright
 * @date 2017年7月7日 下午10:58:00
 */
public class VowelsAndConsonants {
	
	public static void main(String[] args) {
		// vowelsAndConsonants();
//		castingNumbers();
		randomBounds(args);
	}

	/**
	* @Title: randomBounds
	* @Description: 
	* @param args
	* @return void
	* @throws
	*/ 
	private static void randomBounds(String[] args) {
		if (args.length != 1)
			usage();
		if (args[0].equals("lower")) {
			while (Math.random() != 0.0)
				; // Keep trying
			System.out.println("Produced 0.0!");
		} else if (args[0].equals("upper")) {
			while (Math.random() != 1.0)
				; // Keep trying
			System.out.println("Produced 1.0!");
		} else
			usage();
	}

	static void usage() {
		System.out.println("Usage: \n\t"
				+ "RandomBounds lower\n\tRandomBounds upper");
		System.exit(1);
	}

	/**
	 * @Title: castingNumbers
	 * @Description: double,char转换为int值
	 * @return void
	 * @throws
	 */
	private static void castingNumbers() {
		double above = 0.7, below = 0.4;
		System.out.println("above: " + above);
		System.out.println("below: " + below);
		System.out.println("(int)above: " + (int) above);
		System.out.println("(int)below: " + (int) below);
		System.out.println("(char)('a' + above): " + (char) ('a' + above));
		System.out.println("(char)('a' + below): " + (char) ('a' + below));
	}

	/**
	 * @Title: vowelsAndConsonants
	 * @Description:开关switch语句
	 * @return void
	 * @throws
	 */
	private static void vowelsAndConsonants() {
		for (int i = 0; i < 26; i++) {
			char ch = (char) (i + 'a');
			switch (ch) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				System.out.println(ch + "--is vowel");
				break;
			case 'y':
			case 'w':
				System.out.println(ch + "--sometimes is a vowel");
				break;
			default:
				System.out.println(ch + "--is consonants");
				break;
			}
		}
	}
}
