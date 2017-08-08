package com.syl.basic.chapter7;

/**
 * @ClassName: Transmogrify
 * @Description:
 * @author Bright
 * @date 2017年7月23日 下午7:55:54
 */
public class Transmogrify {
	public static void main(String[] args) {
		Stage stage = new Stage();
		stage.performPlay();
		stage.change();
		stage.performPlay();
	}
}

class Stage {
	private Actor actor = new HappyActor();

	public void change() {
		actor = new SaddyActor();
	}

	public void performPlay() {
		actor.act();
	}
}

abstract class Actor {
	public abstract void act();
}

class HappyActor extends Actor {

	@Override
	public void act() {
		System.out.println("HappyActor .act()");
	}
}

class SaddyActor extends Actor {

	@Override
	public void act() {
		System.out.println("SaddyActor .act()");
	}
}
