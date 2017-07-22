package com.syl.basic.chapter6;

/**
 * @ClassName: SprinklerSystem
 * @Description:类的创建及调用
 * @author Bright
 * @date 2017年7月9日 下午2:53:15
 */
public class SprinklerSystem {
	private String valve1, valve2, valve3, valve4;
	private WaterSource source;
	private int i;
	private float f;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SprinklerSystem sprinklerSystem = new SprinklerSystem();
		System.out.println(sprinklerSystem);
		Bath bath = new Bath();
		System.out.println(bath);

		System.out.println(args);// args只是一个内存地址
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SprinklerSystem [valve1=" + valve1 + ", valve2=" + valve2
				+ ", valve3=" + valve3 + ", valve4=" + valve4 + ", source="
				+ source + ", i=" + i + ", f=" + f + "]";
	}
}

class WaterSource {
	private String s;

	public WaterSource() {
		System.out.println("WaterResource()");
		s = new String("Constructed");
	}

	public WaterSource(String s) {
		super();
		this.s = s;
	}

	@Override
	public String toString() {
		return "WaterResource [s=" + s + "]";
	}
}

class Soap {
	private String s;

	Soap() {
		s = new String("Constructed");
		System.out.println("Soup()" + "---" + s);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Soup [s=" + s + "]";
	}

}

class Bath {
	private String // Initializing at point of definition:在定义处初始化
			s1 = new String("Happy"),
			s2 = "Happy", s3, s4;
	private Soap castille;
	private int i;
	private float toy;

	Bath() {
		System.out.println("Inside Bath()");
		s3 = new String("Joy");
		i = 47;
		toy = 3.14f;
		castille = new Soap();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bath [s1=" + s1 + ", s2=" + s2 + ", s3=" + s3 + ", s4=" + s4
				+ ", castille=" + castille + ", i=" + i + ", toy=" + toy + "]";
	}

}
