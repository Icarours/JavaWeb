package com.syl.basic.chapter7;

/**
 * @ClassName: PrivateOverride
 * @Description:如果子类的同名方法用public修饰,父类的同名方法用private修饰,则调用返回的是父类的方法父类方法的修饰符必须是非private才能被子类继承.
 * @author Bright
 * @date 2017年7月23日 下午3:08:49
 */
public class PrivateOverride {
	public static void main(String[] args) {
		PrivateOverride privateOverride = new Derived();
		privateOverride.f();
	}

	private void f() {
		System.out.println("private f()");
	}
}

class Derived extends PrivateOverride {
	public void f() {
		System.out.println("public f()");
	}
}