package com.wycody.utils;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 26, 2015
 */
public class StringUtils {


	public static boolean isNumeric(String s) {
		try {
			Long.parseLong(s);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
}
