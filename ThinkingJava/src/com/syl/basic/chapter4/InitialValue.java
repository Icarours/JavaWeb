package com.syl.basic.chapter4;

/**
 * @ClassName: InitialValue
 * @Description:基本数据类型的初始化
 * @author Bright
 * @date 2017年7月8日 下午1:40:50
 */
public class InitialValue {
	byte b;
	boolean bool;
	char c;
	short s;
	int i;
	long l;
	float f;
	double d;

	public static void main(String[] args) {
		InitialValue initial = new InitialValue();
		initial.initialValue();
		initial.initialValueInMethod();
	}

	/**
	 * 
	 * @Title: initialValueInMethod
	 * @Description:在方法内声明的基本数据类型变量,必须初始化
	 * @return void
	 * @throws
	 */
	void initialValueInMethod() {
		byte b = 4;
		boolean bool = true;
		char c = 'g';
		short s = 34;
		int i = 65535;
		long l = 999888;
		float f = 7899;
		double d = 79905;
		System.out.println("基本数据类型必须初始化(在方法内):");
		System.out.println("byte b = " + b);
		System.out.println("boolean bool = " + bool);
		System.out.println("char c = " + c);
		System.out.println("short s = " + s);
		System.out.println("int i = " + i);
		System.out.println("long l = " + l);
		System.out.println("float f = " + f);
		System.out.println("double d = " + d);
	}

	/**
	 * 
	 * @Title: initialValue
	 * @Description:在成员变量位置声明的基本数据类型,有默认值,可以不初始化
	 * @return void
	 * @throws
	 */
	void initialValue() {
		System.out.println("基本数据类型的默认值(在成员变量位置声明):");
		System.out.println("byte b = " + b);
		System.out.println("boolean bool = " + bool);
		System.out.println("char c = " + c);
		System.out.println("short s = " + s);
		System.out.println("int i = " + i);
		System.out.println("long l = " + l);
		System.out.println("float f = " + f);
		System.out.println("double d = " + d);
	}
}
