package com.syl.basic.chapter8;

/**
 * @ClassName: Adventure
 * @Description:继承和实现
 * @author Bright
 * @date 2017年7月24日 下午11:52:54
 */
public class Adventure {
	public static void main(String[] args) {
		Hero hero = new Hero();
		t(hero);
		u(hero);
		v(hero);
		w(hero);
	}

	public static void t(CanFight h) {
		h.fight();
	}

	public static void u(CanFly h) {
		h.fly();
	}

	public static void v(CanSwing h) {
		h.swing();
	}

	public static void w(ActionCharacter h) {
		h.fight();
	}
}

class Hero extends ActionCharacter implements CanFight, CanFly, CanSwing {

	@Override
	public void swing() {
		System.out.println("Hero.swing()");
	}

	@Override
	public void fly() {
		System.out.println("Hero.fly()");
	}

}

class ActionCharacter {
	public void fight() {
		System.out.println(this.getClass().getSimpleName()+".fight()");
	}
}

interface CanFight {
	void fight();
}

interface CanFly {
	void fly();
}

interface CanSwing {
	void swing();
}