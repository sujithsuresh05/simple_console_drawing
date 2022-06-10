package com.console.draw.command;

import com.console.draw.exceptions.IllegalCommandException;
import com.console.draw.exceptions.InvalidEntityException;
import com.console.draw.exceptions.InvalidParamsException;
import com.console.draw.tool.BucketFillEntity;
import com.console.draw.tool.Canvas;
import com.console.draw.tool.Entity;
import com.console.draw.utils.Utils;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class BucketFillCommand implements Command {

	private static final String message = "B x y c Should fill the entire area connected to (x,y) with \"colour\" c. The\n"
			+ " behaviour of this is the same as that of the \"bucket fill\" tool in paint programs.";
	private int x;
	private int y;
	private char character;
	private Canvas canvas;
	
	public BucketFillCommand(BucketFillBuilder builder) {
		if (builder == null) {
			throw new InvalidParamsException("Builder object is null", "Builder cannot be null");
		}
		String[] params = builder.params;
		if (params == null) {
			throw new InvalidParamsException("Params object is null", "Params cannot be null");
		}
		if (params.length < 3)
			throw new IllegalCommandException("Bucket fill expects 3 params", message);
		if (params[2].length() != 1)
			throw new IllegalCommandException("Only one charater is allowed as color character", message);
		try {
			x = Utils.parseToInt(params[0]);
			y = Utils.parseToInt(params[1]);
			character = params[2].charAt(0);
		} catch (NumberFormatException e) {
			throw new InvalidParamsException("Invalid number", message);
		} catch (IllegalArgumentException e) {
			throw new InvalidParamsException("x and y should be greater than 0", message);
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
			Entity drawEntity = new BucketFillEntity(x, y, character, this.canvas);
			canvas.addEntity(drawEntity);
		} catch (IllegalArgumentException | InvalidEntityException | InvalidParamsException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
	
	public static BucketFillBuilder builder() {
		return new BucketFillBuilder();
	}

	public static class BucketFillBuilder {
		private String[] params;

		private Canvas canvas;

		public BucketFillBuilder() {
		}

		public BucketFillBuilder withParams(String... params) {
			this.params = params;
			return this;
		}

		public BucketFillBuilder withCanvas(Canvas canvas) {
			this.canvas = canvas;
			return this;
		}

		public BucketFillCommand build() {
			return new BucketFillCommand(this);
		}

	}

}
