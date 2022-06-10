package com.console.draw.command;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import com.console.draw.exceptions.IllegalCommandException;
import com.console.draw.exceptions.InvalidParamsException;
import com.console.draw.tool.Canvas;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class CommandFactory implements PropertyChangeListener {

	private Canvas canvas;

	public Command getCommand(String commandLine) throws InvalidParamsException, IllegalCommandException {
		commandLine = commandLine.trim().replaceAll("  +", " ");
		String[] split = commandLine.split(" ");
		String actualCommand = split[0].toUpperCase();
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Command command = null;
		switch (actualCommand) {
		case "C":
			command = CanvasCommand.builder().withParams(params).withPropertyChangeListener(this).build();
			break;
		case "Q":
			command = QuitCommand.builder().build();
			break;
		case "L":
			command = LineCommand.builder().withParams(params).withCanvas(canvas).build();
			break;
		case "R":
			command = RectangleCommand.builder().withParams(params).withCanvas(canvas).build();
			break;
		case "B":
			command = BucketFillCommand.builder().withParams(params).withCanvas(canvas).build();
			break;
		default:
			throw new IllegalCommandException("Given command not supported", "Use one of the valid command");
		}

		return command;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		canvas = (Canvas) evt.getNewValue();
	}

}
