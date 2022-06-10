package com.console.draw.command;

import com.console.draw.exceptions.IllegalCommandException;
import com.console.draw.exceptions.InvalidEntityException;
import com.console.draw.exceptions.InvalidParamsException;
import com.console.draw.tool.Canvas;
import com.console.draw.tool.Entity;
import com.console.draw.tool.LineEntity;
import com.console.draw.utils.Utils;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class LineCommand implements Command {

	private static final String message = "L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2).\n"
			+ "horizontal or vertical lines are supported. Horizontal and vertical lines \n"
			+ "will be drawn by character 'x'.";

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	private Canvas canvas;

	public LineCommand(LineBuilder builder) {
		if (builder == null) {
			throw new InvalidParamsException("Builder object is null", "Builder cannot be null");
		}
		String[] params = builder.params;
		if (params == null) {
			throw new InvalidParamsException("Params object is null", "Params cannot be null");
		}
		if (params.length < 4)
			throw new IllegalCommandException("Draw line command expects 4 params", message);
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
		if (x1 != x2 && y1 != y2) {
			throw new InvalidParamsException("Diagonal lines are not supporting now!", message);
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
			Entity drawEntity = new LineEntity(x1, y1, x2, y2); 
			canvas.addEntity(drawEntity);
		} catch (IllegalArgumentException | InvalidEntityException | InvalidParamsException e) {
			System.out.println(e.getMessage());
			return;
		}
	}

	public static LineBuilder builder() {
		return new LineBuilder();
	}

	public static class LineBuilder {
		private String[] params;

		private Canvas canvas;

		public LineBuilder() {
		}

		public LineBuilder withParams(String... params) {
			this.params = params;
			return this;
		}

		public LineBuilder withCanvas(Canvas canvas) {
			this.canvas = canvas;
			return this;
		}

		public LineCommand build() {
			return new LineCommand(this);
		}

	}

}
