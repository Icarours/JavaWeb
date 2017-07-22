package com.syl.basic.chapter6;

/**
 * @ClassName: Chess
 * @Description:多重继承,带参数的构造方法
 * @author Bright
 * @date 2017年7月9日 下午4:01:09
 */
public class Chess extends BoardGame {
	/**
	 * @param i
	 */
	public Chess() {
		super(11);
		System.out.println("Chess Construced");
	}

	public static void main(String[] args) {
		Chess chess = new Chess();
		System.out.println(chess);
	}
}

class Game {
	int i;

	public Game() {
		super();
		System.out.println("Game Construced ");
	}

	public Game(int i) {
		super();
		this.i = i;
		System.out.println("Game Construced " + i);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Game [i=" + i + "]";
	}
}

class BoardGame extends Game {

	public BoardGame(int i) {
		System.out.println("BoardGame Construced " + i);
	}
}
