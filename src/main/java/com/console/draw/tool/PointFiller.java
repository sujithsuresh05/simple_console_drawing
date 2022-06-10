package com.console.draw.tool;

/**
 * This interface will serve as to provide callback facility to fill the canvas array
 * and so the actual canvas property will not exposed to outside world  
 * @author Sujith Suresh
 *
 */
@FunctionalInterface
public interface PointFiller {
	
	void fill(int x, int y, char ch);

}
