package com.console.draw;

import java.util.Scanner;

import com.console.draw.command.Command;
import com.console.draw.command.CommandFactory;
import com.console.draw.exceptions.IllegalCommandException;
import com.console.draw.exceptions.InvalidParamsException;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class App {
	private static CommandFactory commandFactory;
	private static final String initialMsg = "Command 		Description\n"
			+ "C w h           Create a new canvas of width w and height h.\n"
			+ "L x1 y1 x2 y2   Create a new line of 'x' from (x1,y1) to (x2,y2). Only support \n"
			+ "                horizontal or vertical lines.\n"
			+ "R x1 y1 x2 y2   Create a new rectangle, (x1,y1) is upper left corner & (x2,y2) is \n"
			+ "                lower right corner.\n"
			+ "B x y c         Fill the entire area around (x,y) with \"colour\" c.\n"
			+ "                Same as that of the \"bucket fill\" tool in paint programs.\n" + "Q               Quit.";

	public static void main(String[] args) {
		commandFactory = new CommandFactory();
		Scanner scanner = CommandReader.INPUT_READER.getScanner();
		System.out.println(initialMsg);
		while (true) {
			process(scanner.nextLine());
			System.out.println("Enter command:");
		}
	}

	private static void process(String commandLine) {
		Command command = null;
		try {
			command = commandFactory.getCommand(commandLine);
			command.execute();
		} catch (InvalidParamsException e) {
			System.out.println("Command syntax is not correct: " + e.getMessage());
			System.out.println("Syntax to follow: \n" + e.getHelpMessage());
			return;
		} catch (IllegalCommandException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getHelpMessage());
			return;
		}
	}
}
