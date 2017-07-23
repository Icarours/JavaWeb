package com.syl.basic.chapter7;

/**
 * @ClassName: Note
 * @Description:
 * @author Bright
 * @date 2017年7月21日 上午9:58:33
 */
public class Note {
	private String noteName;

	/**
	 * 私有化构造方法,通过枚举值对外提供乐器类型
	 * 
	 * @param noteName
	 */
	private Note(String noteName) {
		super();
		this.noteName = noteName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return noteName;
	}

	/**
	 * 枚举类型
	 */
	public static final Note MIDDEL_C = new Note("MIDDEL_C"),
			C_SHARP = new Note("C_SHARP"), B_FLAT = new Note("B_FLAT");
}
