package com.syl.basic.chapter6;

/**
 * @ClassName: PlaceSetting
 * @Description:继承,对象的初始化
 * @author Bright
 * @date 2017年7月10日 上午10:15:06
 */
public class PlaceSetting extends Custom{
	private Spoon mSpoon;
	private Fork mFork;
	private Knife mKnife;
	private DinnerPlate mDinnerPlate;

	/**
	 * @param i
	 */
	PlaceSetting(int i) {
		super(i+2);
		mSpoon = new Spoon(i+3);
		mFork = new Fork(i+3);
		mKnife = new Knife(i+4);
		mDinnerPlate = new DinnerPlate(i+5);
		
		System.out.println("PlaceSetting Constructor");
	}

	public static void main(String[] args) {
		PlaceSetting placeSetting = new PlaceSetting(9);
	}

}

class Plate {
	Plate(int i) {
		System.out.println("Plate Constructor");
	}
}

class DinnerPlate extends Plate {

	/**
	 * @param i
	 */
	DinnerPlate(int i) {
		super(i);
		System.out.println("DinnerPlate Constructor");
	}
}

class Utensil {
	Utensil(int i) {
		System.out.println("Utensil Constructor");
	}
}

class Spoon extends Utensil {

	/**
	 * @param i
	 */
	Spoon(int i) {
		super(i);
		System.out.println("Spoon Constructor");
	}
}

class Fork extends Utensil {

	/**
	 * @param i
	 */
	Fork(int i) {
		super(i);
		System.out.println("Fork Constructor");
	}
}

class Knife extends Utensil {

	/**
	 * @param i
	 */
	Knife(int i) {
		super(i);
		System.out.println("Knife Constructor");
	}
}

class Custom {
	Custom(int i) {
		System.out.println("Custom Constructor");
	}
}