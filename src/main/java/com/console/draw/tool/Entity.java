package com.console.draw.tool;

import java.util.Arrays;

/**
 * 
 * @author Sujith Suresh
 *
 */
public interface Entity {
	
	static final char LINE_CHAR = 'x';

	void draw(int height, int width, PointFiller pointFiller);

	static void validateAllPositive(int... nums) {
		Arrays.stream(nums).forEach(num -> {
			if (num < 1) {
				throw new IllegalArgumentException("Number should be greater than zero");
			}
		});
	}
	
	static boolean isOutside(int x, int y, int width, int height) {
		return x < 0 || x >= width || y < 0 || y >= height;
	}
	
	static void validateAllNonNegative(int... nums) {
		Arrays.stream(nums).forEach(num -> {
			if (num < 0) {
				throw new IllegalArgumentException("Number should be positive");
			}
		});
	}

}
