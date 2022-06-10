package com.console.draw.tool;

import com.console.draw.exceptions.InvalidEntityException;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class RectangleEntity implements Entity {

	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public RectangleEntity(int x1, int y1, int x2, int y2) {
		Entity.validateAllPositive(x1, y1, x2, y2);
		if (x1 == x2 || y1 == y2) {
			throw new IllegalArgumentException("Points are not valid to draw a rectangle");
		}
		if (y1 > y2 || x1 > x2) {
			this.x1 = x2;
			this.y1 = y2;
			this.x2 = x1;
			this.y2 = y1;
		} else {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	@Override
	public void draw(int height, int width, PointFiller pointFiller) {
		if (Entity.isOutside(x1 - 1, y1 -1, width, height) || Entity.isOutside(x2 - 1, y2 - 1, width, height))
			throw new InvalidEntityException("Edge is outside of the canvas");
		LineEntity topHorizontalLine = new LineEntity(x1, y1, x2, y1);
		LineEntity bottomHorizontalLine = new LineEntity(x1, y2, x2, y2);
		LineEntity rightVerticalLine = new LineEntity(x1, y1, x1, y2);
		LineEntity leftVericalLine = new LineEntity(x2, y1, x2, y2);
		rightVerticalLine.draw(height, width, pointFiller);
		topHorizontalLine.draw(height, width, pointFiller);
		bottomHorizontalLine.draw(height, width, pointFiller);
		leftVericalLine.draw(height, width, pointFiller);
	}

}
