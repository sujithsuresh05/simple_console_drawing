package com.console.draw.command;

import com.console.draw.CommandReader;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class QuitCommand implements Command {
	public static String message = "Q To exit the program.";

	public QuitCommand(QuitCommandBuilder builder) {
	}

	@Override
	public void execute() {
		CommandReader.INPUT_READER.closeScanner();
		System.out.println("Exiting...");
		System.exit(0);
	}

	public static QuitCommandBuilder builder() {
		return new QuitCommandBuilder();
	}

	public static class QuitCommandBuilder {

		public QuitCommandBuilder() {
		}

		public QuitCommand build() {
			return new QuitCommand(this);
		}

	}
}
