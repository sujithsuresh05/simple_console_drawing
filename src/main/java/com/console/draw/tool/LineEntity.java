package com.console.draw.tool;

import com.console.draw.exceptions.InvalidEntityException;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class LineEntity implements Entity {

	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public LineEntity(int x1, int y1, int x2, int y2) {
		if (x1 != x2 && y1 != y2) {
			throw new IllegalArgumentException("Does not support diagonal line at the moment");
		}
		Entity.validateAllPositive(x1, y1, x2, y2);
		if ((x1 == x2 && y1 > y2) || (y1 == y2 && x1 > x2)) {
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

	/**
	 * The function fill values to the canvas array using the callback function {@code PointFiller}
	 * Implementation will trim part line outside the canvas
	 */
	@Override
	public void draw(int height, int width, PointFiller pointFiller) {
		if (Entity.isOutside(this.x1 - 1, this.y1 - 1, width, height) || Entity.isOutside(this.x2 - 1, this.y2 - 1, width, height))
			throw new InvalidEntityException("Point is outside of the canvas");

		for (int row = y1 - 1; row <= y2 - 1 && row < height; row++) {
			for (int col = x1 - 1; col <= x2 - 1 && col < width; col++) {
				pointFiller.fill(row, col, Entity.LINE_CHAR);
			}
		}
	}

}
