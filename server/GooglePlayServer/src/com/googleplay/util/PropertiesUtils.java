package com.googleplay.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class PropertiesUtils {
	private static Properties properties;

	static {
		InputStream is = null;

		properties = new Properties();
		try {
			is = PropertiesUtils.class
					.getResourceAsStream("/system.properties");
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				is = null;
			}
		}
	}

	public static String getDir() {
		String property = properties.getProperty("dir");
		try {
			property = new String(property.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return property;

		// return "F:/课程/备课/google应用市场/day01/服务器";
	}
}
