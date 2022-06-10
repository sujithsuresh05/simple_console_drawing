package com.console.draw.tool;

import java.util.Stack;

import com.console.draw.exceptions.InvalidEntityException;
import com.console.draw.exceptions.InvalidParamsException;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class BucketFillEntity implements Entity {

	private int x;
	private int y;
	private Character fillingCharacter;
	private Canvas canvas;

	public BucketFillEntity(int x, int y, char character, Canvas canvas) {
		Entity.validateAllPositive(x, y);
		this.x = x;
		this.y = y;
		this.fillingCharacter = character;
		this.canvas = canvas;
	}

	@Override
	public void draw(int height, int width, PointFiller pointFiller)
			throws InvalidEntityException, InvalidParamsException {

		if (pointFiller == null)
			throw new InvalidParamsException("Callback function cannot be null", "Pass valid callback function");

		if (Entity.isOutside(this.x - 1, this.y - 1, width, height))
			throw new InvalidEntityException("Point is outside of the canvas");
		if (canvas == null)
			throw new InvalidParamsException("Canvas is null", "First create canvas!");
		char actualChar = canvas.getCharacterAtPoint(x - 1, y - 1);
		Stack<BucketFillEntity.Coordinate> queue = new Stack<>();
		queue.add(new Coordinate(x - 1, y - 1));
		while (!queue.isEmpty()) {
			Coordinate crd = queue.pop();
			int posX = crd.getPosX();
			int posY = crd.getPosY();
			if (canvas.getCharacterAtPoint(posX, posY).equals(actualChar)) {
				pointFiller.fill(posY, posX, fillingCharacter);
			}
			if (posX - 1 >= 0 && canvas.getCharacterAtPoint(posX - 1, posY).equals(actualChar)) {
				queue.push(new Coordinate(posX - 1, posY));
			}
			if (posX + 1 < width && canvas.getCharacterAtPoint(posX + 1, posY).equals(actualChar)) {
				queue.push(new Coordinate(posX + 1, posY));
			}
			if (posY - 1 >= 0 && canvas.getCharacterAtPoint(posX, posY - 1).equals(actualChar)) {
				queue.push(new Coordinate(posX, posY - 1));
			}
			if (posY + 1 < height && canvas.getCharacterAtPoint(posX, posY + 1).equals(actualChar)) {
				queue.push(new Coordinate(posX, posY + 1));
			}
		}

	}

	class Coordinate {
		private int posX;
		private int posY;

		public Coordinate(int x, int y) {
			Entity.validateAllNonNegative(x, y);
			this.posX = x;
			this.posY = y;
		}

		public int getPosX() {
			return posX;
		}

		public int getPosY() {
			return posY;
		}
	}

}
