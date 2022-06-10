package com.console.draw.command;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.console.draw.exceptions.IllegalCommandException;
import com.console.draw.exceptions.InvalidParamsException;
import com.console.draw.tool.ConsoleCanvas;
import com.console.draw.utils.Utils;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class CanvasCommand implements Command {

	private static String message = "Command C w h  will create a new canvas of width w and height h. Here w, h should be > 0";

	private int height;

	private int width;

	private PropertyChangeSupport support;

	private ConsoleCanvas consoleCanvas;

	public CanvasCommand(CanvasBuilder builder) {
		if (builder == null) {
			throw new InvalidParamsException("Builder object is null", "Builder cannot be null");
		}
		String[] params = builder.params;
		if (params == null) {
			throw new InvalidParamsException("Params object is null", "Params cannot be null");
		}
		if (params.length < 2)
			throw new IllegalCommandException("Create command expects 2 params", message);
		try {
			width = Utils.parseToInt(params[0]);
			height = Utils.parseToInt(params[1]);
		} catch (NumberFormatException e) {
			throw new InvalidParamsException("Given value not a number", message);
		} catch (IllegalArgumentException e) {
			throw new InvalidParamsException("Numbers should be >= 0", message);
		}
		support = new PropertyChangeSupport(this);
		if (builder.pcl != null)
			support.addPropertyChangeListener(builder.pcl);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public static CanvasBuilder builder() {
		return new CanvasBuilder();
	}

	public static class CanvasBuilder {
		private String[] params;

		private PropertyChangeListener pcl;

		public CanvasBuilder() {
		}

		public CanvasBuilder withParams(String... params) {
			this.params = params;
			return this;
		}

		public CanvasBuilder withPropertyChangeListener(PropertyChangeListener pcl) {
			this.pcl = pcl;
			return this;
		}

		public CanvasCommand build() {
			return new CanvasCommand(this);
		}

	}

	@Override
	public void execute() {
		ConsoleCanvas newConsoleCanvas = new ConsoleCanvas(width, height);
		if (support != null)
			support.firePropertyChange("consoleCanvas", this.consoleCanvas, newConsoleCanvas);
		this.consoleCanvas = newConsoleCanvas;
		consoleCanvas.display();
	}
}
