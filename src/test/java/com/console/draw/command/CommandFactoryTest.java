package com.console.draw.command;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.console.draw.exceptions.IllegalCommandException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommandFactoryTest {

	private CommandFactory commandFactory;

	@BeforeAll
	public void setUp() throws Exception {
		commandFactory = new CommandFactory();
	}

	@AfterAll	
	public void tearUp() throws Exception {
		commandFactory = null;
	}

	@Test
	@DisplayName("Create canvas command test")
	public void canvasCommandCreateTest() {
		Command command = commandFactory.getCommand("c 20 30");
		assertThat(command, CoreMatchers.instanceOf(CanvasCommand.class));
	}
	
	@Test
	@DisplayName("Create quit canvas command test")
	public void quitCommandCreateTest() {
		Command command = commandFactory.getCommand("q");
		assertThat(command, CoreMatchers.instanceOf(QuitCommand.class));
	}

	@Test
	@DisplayName("Create bucketfill canvas command test")
	public void bucketFillCommandCreateTest() {
		Command command = commandFactory.getCommand("b 1 1 c");
		assertThat(command, CoreMatchers.instanceOf(BucketFillCommand.class));
	}
	
	@Test
	@DisplayName("Create line canvas command test")
	public void lineCommandCreateTest() {
		Command command = commandFactory.getCommand("l 20 30 20 50");
		assertThat(command, CoreMatchers.instanceOf(LineCommand.class));
	}
	
	@Test
	@DisplayName("Create rectangle canvas command test")
	public void rectangleCommandCreateTest() {
		Command command = commandFactory.getCommand("r 20 30 40 50");
		assertThat(command, CoreMatchers.instanceOf(RectangleCommand.class));
	}
	
	@Test
	@DisplayName("Try to use build with invalid command")
	public void canvasCommandCreateFailTest() {
		IllegalCommandException thrown = assertThrows(IllegalCommandException.class,
				() -> commandFactory.getCommand("a 20 30"), "Expected illegal command exception");
		assertEquals("Given command not supported", thrown.getMessage());
		assertEquals("Use one of the valid command", thrown.getHelpMessage());

	}
}
