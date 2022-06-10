package com.console.draw;

import java.util.Scanner;

public enum CommandReader {
	
	INPUT_READER;
	
	private Scanner scanner;
	private CommandReader() {
		scanner = new Scanner(System.in);
	}
	
	public Scanner getScanner() {
		return scanner;
	}
	
	public void closeScanner() {	
		scanner.close();
	}

}
