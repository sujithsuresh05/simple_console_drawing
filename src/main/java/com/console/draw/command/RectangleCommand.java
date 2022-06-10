package com.console.draw.command;

import com.console.draw.exceptions.IllegalCommandException;
import com.console.draw.exceptions.InvalidEntityException;
import com.console.draw.exceptions.InvalidParamsException;
import com.console.draw.tool.Canvas;
import com.console.draw.tool.Entity;
import com.console.draw.tool.RectangleEntity;
import com.console.draw.utils.Utils;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class RectangleCommand implements Command {

	private static final String message = "R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and\n"
			+ "                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn\n"
			+ "                using the 'x' character.";
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Canvas canvas;
	
	public RectangleCommand(RectangleBuilder builder) {
		if (builder == null) {
			throw new InvalidParamsException("Builder object is null", "Builder cannot be null");
		}
		String[] params = builder.params;
		if (params == null) {
			throw new InvalidParamsException("Params object is null", "Params cannot be null");
		}
		if (params.length < 4)
			throw new IllegalCommandException("Rectangle tool command expects 4 params", message);
		try {
			x1 = Utils.parseToInt(params[0]);
			y1 = Utils.parseToInt(params[1]);
			x2 = Utils.parseToInt(params[2]);
			y2 = Utils.parseToInt(params[3]);
		} catch (NumberFormatException e) {
			throw new InvalidParamsException("Not a number", message);
		} catch (IllegalArgumentException e) {
			throw new InvalidParamsException("Numbers should be greater than 0", message);
		}
		this.canvas = builder.canvas;
	}
	
	@Override
	public void execute() {
		if (canvas == null) {
			System.out.println("Create a canvas first");
			return;
		}
		try {
			Entity drawEntity = new RectangleEntity(x1, y1, x2, y2); 
			canvas.addEntity(drawEntity);
		} catch (IllegalArgumentException | InvalidEntityException | InvalidParamsException e) {
			System.out.println(e.getMessage());
			return;
		}
	}

	public static RectangleBuilder builder() {
		return new RectangleBuilder();
	}

	public static class RectangleBuilder {
		private String[] params;

		private Canvas canvas;

		public RectangleBuilder() {
		}

		public RectangleBuilder withParams(String... params) {
			this.params = params;
			return this;
		}

		public RectangleBuilder withCanvas(Canvas canvas) {
			this.canvas = canvas;
			return this;
		}

		public RectangleCommand build() {
			return new RectangleCommand(this);
		}

	}

}
