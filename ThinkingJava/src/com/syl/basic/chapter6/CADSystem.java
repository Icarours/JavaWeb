package com.syl.basic.chapter6;

/**
 * @ClassName: CADSystem
 * @Description:确保清楚,释放内存
 * @author Bright
 * @date 2017年7月10日 上午10:53:02
 */
public class CADSystem extends Shape {
	private Circle c;
	private Triangle t;
	private Line[] lines = new Line[5];

	/**
	 * @param i
	 */
	CADSystem(int i) {
		super(i);
		c = new Circle(1);
		t = new Triangle(2);
		for (int j = 0; j < lines.length; j++) {
			lines[j] = new Line(j, j * j);
		}
		System.out.println("Combined Constructor");
	}

	public static void main(String[] args) {
		CADSystem cadSystem = new CADSystem(55);
		try {
			//需要执行的代码
		} catch (Exception e) {
			cadSystem.dispose();
		}
	}

	/* (non-Javadoc)
	 * @see com.syl.basic.chapter6.Shape#dispose()
	 */
	@Override
	void dispose() {
		System.out.println("CADSystem dispose");
		c.dispose();
		t.dispose();
		for (int i = 0; i < lines.length; i++) {
			lines[i].dispose();
		}
		super.dispose();
	}
}

class Shape {
	Shape(int i) {
		System.out.println("Shape Constructor");
	}

	void dispose() {
		System.out.println("Shape dispose");
	}
}

class Circle extends Shape {

	/**
	 * @param i
	 */
	Circle(int i) {
		super(i);
		System.out.println("Drawing Cirlce");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syl.basic.chapter6.Shape#dispose()
	 */
	@Override
	void dispose() {
		System.out.println("Circle erasing");
		super.dispose();
	}
}

class Triangle extends Shape {

	/**
	 * @param i
	 */
	Triangle(int i) {
		super(i);
		System.out.println("Drawing Triangle");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syl.basic.chapter6.Shape#dispose()
	 */
	@Override
	void dispose() {
		System.out.println("Triangle erasing");
		super.dispose();
	}
}

class Line extends Shape {
	private int start, end;

	/**
	 * @param start
	 */
	Line(int start, int end) {
		super(start);
		this.start = start;
		this.end = end;
		System.out.println("Drawing line " + start + "--" + end);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syl.basic.chapter6.Shape#dispose()
	 */
	@Override
	void dispose() {
		System.out.println("line erasing" + start + "--" + end);
		super.dispose();
	}

}
