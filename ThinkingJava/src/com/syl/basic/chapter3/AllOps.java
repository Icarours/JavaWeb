package com.syl.basic.chapter3;

/**
 * @ClassName: AllOps
 * @Description:
 * @author Bright
 * @date 2017年7月7日 下午3:45:43
 */
public class AllOps {
	public static void main(String[] args) {
		AllOps allOps = new AllOps();
		allOps.booleanTest(true, true);
	}

	void f(boolean b) {
		System.out.println(b);
	}

	/**
	 * 
	 * @Title: booleanTest
	 * @Description: 逻辑运算与位运算
	 * @param x
	 * @param y
	 * @return void
	 * @throws
	 */
	void booleanTest(boolean x, boolean y) {
		// Relational and logical:逻辑运算
		f(x == y);
		f(x != y);
		f(!y);

		x = x && y;
		System.out.println("x && y result: " + x);
		y = x || y;
		System.out.println("x || y result: " + x);
		// Bitwise operators:位运算
		x = x & y;
		System.out.println("x & y result: " + x);//true
		x = x | y;
		System.out.println("x | y result: " + x);//true
		x = x ^ y;
		System.out.println("x ^ y result: " + x);//false

		// Compound assignment:复合赋值
		x &= y;
		System.out.println("x &= y result: " + x);//false
		x |= y;
		System.out.println("x |= y result: " + x);//true
		x ^= y;
		System.out.println("x ^= y result: " + x);//false
	}

	void charTest(char x, char y) {
		// Arithmetic operator算术运算
		x = (char) (x + y);
		x = (char) (x - y);
		x = (char) (x / y);
		x = (char) (x * y);
		x = (char) (x % y);

		x++;
		x--;
		x = (char) +y;
		x = (char) -y;

		// Relational and logical:逻辑关系运算
		f(x > y);
		f(x >= y);
		f(x < y);
		f(x <= y);
		f(x == y);
		f(x != y);

		// Bitwise operators:位运算
		x = (char) ~y;
		x = (char) (x & y);
		x = (char) (x | y);
		x = (char) (x ^ y);
		x = (char) (x << 1);
		x = (char) (x >> 1);
		x = (char) (x >>> 1);

		// Compound assignment:混合运算
		x += y;
		x -= y;
		x *= y;
		x /= y;
		x %= y;
		x <<= 1;
		x >>= 1;
		x >>>= 1;
		x &= y;
		x ^= y;
		x |= y;
		// Casting:强转
		// ! boolean b = (boolean)x;
		byte B = (byte) x;
		short s = (short) x;
		int i = (int) x;
		long l = (long) x;
		float f = (float) x;
		double d = (double) x;
	}

	void byteTest(byte x, byte y) {
		// Arithmetic operators:
		x = (byte) (x * y);
		x = (byte) (x / y);
		x = (byte) (x % y);
		x = (byte) (x + y);
		x = (byte) (x - y);
		x++;
		x--;
		x = (byte) +y;
		x = (byte) -y;
		// Relational and logical:
		f(x > y);
		f(x >= y);
		f(x < y);
		f(x <= y);
		f(x == y);
		f(x != y);

		// Bitwise operators:
		x = (byte) ~y;
		x = (byte) (x & y);
		x = (byte) (x | y);
		x = (byte) (x ^ y);
		x = (byte) (x << 1);
		x = (byte) (x >> 1);
		x = (byte) (x >>> 1);
		// Compound assignment:
		x += y;
		x -= y;
		x *= y;
		x /= y;
		x %= y;
		x <<= 1;
		x >>= 1;
		x >>>= 1;
		x &= y;
		x ^= y;
		x |= y;
		// Casting:
		// ! boolean b = (boolean)x;
		char c = (char) x;
		short s = (short) x;
		int i = (int) x;
		long l = (long) x;
		float f = (float) x;
		double d = (double) x;
	}

	void shortTest(short x, short y) {
		// Arithmetic operators:
		x = (short) (x * y);
		x = (short) (x / y);
		x = (short) (x % y);
		x = (short) (x + y);
		x = (short) (x - y);
		x++;
		x--;
		x = (short) +y;
		x = (short) -y;
		// Relational and logical:
		f(x > y);
		f(x >= y);
		f(x < y);
		f(x <= y);
		f(x == y);
		f(x != y);
		// ! f(!x);
		// ! f(x && y);
		// ! f(x || y);
		// Bitwise operators:
		x = (short) ~y;
		x = (short) (x & y);
		x = (short) (x | y);
		x = (short) (x ^ y);
		x = (short) (x << 1);
		x = (short) (x >> 1);
		x = (short) (x >>> 1);
		// Compound assignment:
		x += y;
		x -= y;
		x *= y;
		x /= y;
		x %= y;
		x <<= 1;
		x >>= 1;
		x >>>= 1;
		x &= y;
		x ^= y;
		x |= y;
		// Casting:
		// ! boolean b = (boolean)x;
		char c = (char) x;
		byte B = (byte) x;
		int i = (int) x;
		long l = (long) x;
		float f = (float) x;
		double d = (double) x;
	}

	void intTest(int x, int y) {
		// Arithmetic operators:
		x = x * y;
		x = x / y;
		x = x % y;
		x = x + y;
		x = x - y;
		x++;
		x--;
		x = +y;
		x = -y;
		// Relational and logical:
		f(x > y);
		f(x >= y);
		f(x < y);
		f(x <= y);
		f(x == y);
		f(x != y);
		// ! f(!x);
		// ! f(x && y);
		// ! f(x || y);
		// Bitwise operators:
		x = ~y;
		x = x & y;
		x = x | y;
		x = x ^ y;
		x = x << 1;
		x = x >> 1;
		x = x >>> 1;
		// Compound assignment:
		x += y;
		x -= y;
		x *= y;
		x /= y;
		x %= y;
		x <<= 1;
		x >>= 1;
		x >>>= 1;
		x &= y;
		x ^= y;
		x |= y;
		// Casting:
		// ! boolean b = (boolean)x;
		char c = (char) x;
		byte B = (byte) x;
		short s = (short) x;
		long l = (long) x;
		float f = (float) x;
		double d = (double) x;
	}

	void longTest(long x, long y) {
		// Arithmetic operators:
		x = x * y;
		x = x / y;
		x = x % y;
		x = x + y;
		x = x - y;
		x++;
		x--;
		x = +y;
		x = -y;
		// Relational and logical:
		f(x > y);
		f(x >= y);
		f(x < y);
		f(x <= y);
		f(x == y);
		f(x != y);
		// ! f(!x);
		// ! f(x && y);
		// ! f(x || y);
		// Bitwise operators:
		x = ~y;
		x = x & y;
		x = x | y;
		x = x ^ y;
		x = x << 1;
		x = x >> 1;
		x = x >>> 1;
		// Compound assignment:
		x += y;
		x -= y;
		x *= y;
		x /= y;
		x %= y;
		x <<= 1;
		x >>= 1;
		x >>>= 1;
		x &= y;
		x ^= y;
		x |= y;
		// Casting:
		// ! boolean b = (boolean)x;
		char c = (char) x;
		byte B = (byte) x;
		short s = (short) x;
		int i = (int) x;
		float f = (float) x;
		double d = (double) x;
	}

	void floatTest(float x, float y) {
		// Arithmetic operators:
		x = x * y;
		x = x / y;
		x = x % y;
		x = x + y;
		x = x - y;
		x++;
		x--;
		x = +y;
		x = -y;
		// Relational and logical:
		f(x > y);
		f(x >= y);
		f(x < y);
		f(x <= y);
		f(x == y);
		f(x != y);
		// ! f(!x);
		// ! f(x && y);
		// ! f(x || y);
		// Bitwise operators:
		// ! x = ~y;
		// ! x = x & y;
		// ! x = x | y;
		// ! x = x ^ y;
		// ! x = x << 1;
		// ! x = x >> 1;
		// ! x = x >>> 1;
		// Compound assignment:
		x += y;
		x -= y;
		x *= y;
		x /= y;
		x %= y;
		// ! x <<= 1;
		// ! x >>= 1;
		// ! x >>>= 1;
		// ! x &= y;
		// ! x ^= y;
		// ! x |= y;
		// Casting:
		// ! boolean b = (boolean)x;
		char c = (char) x;
		byte B = (byte) x;
		short s = (short) x;
		int i = (int) x;
		long l = (long) x;
		double d = (double) x;
	}

	void doubleTest(double x, double y) {
		// Arithmetic operators:
		x = x * y;
		x = x / y;
		x = x % y;
		x = x + y;
		x = x - y;
		x++;
		x--;
		x = +y;
		x = -y;
		// Relational and logical:
		f(x > y);
		f(x >= y);
		f(x < y);
		f(x <= y);
		f(x == y);
		f(x != y);
		// ! f(!x);
		// ! f(x && y);
		// ! f(x || y);
		// Bitwise operators:
		// ! x = ~y;
		// ! x = x & y;
		// ! x = x | y;
		// ! x = x ^ y;
		// ! x = x << 1;
		// ! x = x >> 1;
		// ! x = x >>> 1;
		// Compound assignment:
		x += y;
		x -= y;
		x *= y;
		x /= y;
		x %= y;
		// ! x <<= 1;
		// ! x >>= 1;
		// ! x >>>= 1;
		// ! x &= y;
		// ! x ^= y;
		// ! x |= y;
		// Casting:
		// ! boolean b = (boolean)x;
		char c = (char) x;
		byte B = (byte) x;
		short s = (short) x;
		int i = (int) x;
		long l = (long) x;
		float f = (float) x;
	}
}
