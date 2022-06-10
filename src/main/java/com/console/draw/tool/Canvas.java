package com.console.draw.tool;

/**
 * 
 * @author Sujith Suresh
 *
 */
public interface Canvas {
	
	void display();
	
	void addEntity(Entity tool);
	
	Character getCharacterAtPoint(int x, int y);
	
}
