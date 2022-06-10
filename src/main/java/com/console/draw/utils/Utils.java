package com.console.draw.utils;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class Utils {

	public static int parseToInt(String number) throws IllegalArgumentException, NumberFormatException {
		int num = Integer.parseInt(number);
		if (num <= 0) {
			throw new IllegalArgumentException();
		}
		return num;
	}
	
}
