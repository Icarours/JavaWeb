package com.syl.baisc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @ClassName: Example2
 * @Description: 
 * @author Bright
 * @date 2017年8月10日 上午12:34:54
 */
public class Example2 {
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
		String format2 = format.format(date);
		System.out.println(format.format(date));
	}
}
