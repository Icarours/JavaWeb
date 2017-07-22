package com.syl.basic.chapter6;

/**
 * @ClassName: Cartoon
 * @Description: 多重继承,依次调用父类的构造方法
 * @author Bright
 * @date 2017年7月9日 下午3:49:53
 */
public class Cartoon extends Drawing {

	public Cartoon() {
		System.out.println("Cartoon Constructed");
	}

	public static void main(String[] args) {
		Cartoon cartoon = new Cartoon();
		System.out.println(cartoon);// 返回的是cartoon的内存地址
	}

}

class Art {
	public Art() {
		System.out.println("Art Constructed");
	}
}

class Drawing extends Art {

	public Drawing() {
		// super();//即使没有调用super(),子类也一定会调用父类的默认构造方法
		System.out.println("Drawing Constructed");
	}
}
