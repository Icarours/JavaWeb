package com.syl.basic.chapter4;

/**
 * @ClassName: TerminationCondition
 * @Description:
 * @author Bright
 * @date 2017年7月8日 上午10:56:19
 */
public class TerminationCondition {

	/**
	 * @Title: main
	 * @Description:
	 * @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) {
		Book book = new Book(true);
		book.checkedIn();
		System.out.println("book==" + book);
		book.finalize();
		Book book2 = new Book(true);
		System.out.println("book2 == " + book2);
		book2.finalize();
		System.gc();// 没有调用finalize()方法
	}

}

class Book {
	boolean chekedOut = false;

	Book(boolean checkOut) {
		chekedOut = checkOut;
	}

	void checkedIn() {
		chekedOut = false;
	}

	public void finalize() {
		if (!chekedOut) {
			System.out.println("Error: checked out .--" + this);
		}
	}
}
