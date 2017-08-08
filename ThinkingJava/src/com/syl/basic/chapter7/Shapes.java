package com.syl.basic.chapter7;

import java.util.Random;

/**
 * @ClassName: Shapes
 * @Description:
 * @author Bright
 * @date 2017年7月23日 下午12:47:40
 */
public class Shapes {
	public static void main(String[] args) {
//		RandomShapeGenarator gen = new RandomShapeGenarator();
//		Shape shape = gen.next();
//		shape.draw();
//		shape.erase();
		
		Shape shape = new Circle();
		shape.draw();
		shape.erase();
	}
}

/**
 * 图形生成器
 * 
 * @ClassName: RandomShapeGenarator
 * @Description:这个类不好使会出现异常
 * @author Bright
 * @date 2017年7月23日 下午1:25:11
 */
class RandomShapeGenarator {
	private Random random = new Random();

	public Shape next() {
		Shape shape = null;
		switch (random.nextInt(12)) {
		case 1:
			shape = new Circle();
			break;
		case 2:
			shape = new Square();
			break;
		case 3:
			shape = new Triangle();
			break;
		default:
			break;
		}
		return shape;
	}
}

/**
 * 基类
 * 
 * @ClassName: Shape
 * @Description:
 * @author Bright
 * @date 2017年7月23日 下午1:10:39
 */
class Shape {
	void draw() {
	}

	void erase() {
	}
}

/**
 * 导出类
 * 
 * @ClassName: Circle
 * @Description:
 * @author Bright
 * @date 2017年7月23日 下午1:10:52
 */
class Circle extends Shape {

	@Override
	void draw() {
		System.out.println("Circle.draw() ");
	}

	@Override
	void erase() {
		System.out.println("Circle.erase() ");
	}
}

/**
 * 导出类
 * 
 * @ClassName: Square
 * @Description:
 * @author Bright
 * @date 2017年7月23日 下午1:11:00
 */
class Square extends Shape {

	@Override
	void draw() {
		System.out.println("Square.draw() ");
	}

	@Override
	void erase() {
		System.out.println("Square.erase() ");
	}
}

/**
 * 导出类
 * 
 * @ClassName: Triangle
 * @Description:
 * @author Bright
 * @date 2017年7月23日 下午1:11:07
 */
class Triangle extends Shape {

	@Override
	void draw() {
		System.out.println("Triangle.draw() ");
	}

	@Override
	void erase() {
		System.out.println("Triangle.erase() ");
	}

}
