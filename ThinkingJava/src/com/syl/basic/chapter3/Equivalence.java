package com.syl.basic.chapter3;

/**
 * @ClassName: Equivalence
 * @Description: 关系运算符
 * @author Bright
 * @date 2017年7月7日 上午9:56:06
 */
public class Equivalence {
	public static void main(String[] args) {
		/**
		 * 引用数据类型中==和!=比较的是引用 基本数据类型中==和!=比较的是数值 引用数据类型中比较两个数据是否相等应该用equals()方法
		 */
		Integer integer1 = new Integer(23);
		Integer integer2 = new Integer(23);
		System.out.println(integer1 == integer2);
		System.out.println(integer1 != integer2);
		System.out.println(integer1.equals(integer2));
		System.out.println("-------------");
		Value v1 = new Value();
		Value v2 = new Value();
		v1.i = v2.i = 100;
		System.out.println("v1=" + v1);
		System.out.println("v2=" + v2);
		System.out.println(v1.equals(v2));// equals()的默认行为是比较引用
		System.out.println(v1.i == v2.i);
	}

}

class Value {
	int i;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
		// return i==(int)obj;//复写equals()父类方法中的方法体
	}
}
