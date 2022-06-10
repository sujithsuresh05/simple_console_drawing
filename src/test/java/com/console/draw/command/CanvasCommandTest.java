package com.console.draw.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.console.draw.exceptions.IllegalCommandException;
import com.console.draw.exceptions.InvalidParamsException;

public class CanvasCommandTest {

	private static String message = "Command C w h  will create a new canvas of width w and height h. Here w, h should be > 0";

	@Test
	public void canvasCommandCreateTest() {
		CanvasCommand.builder().withParams("1", "2").build();
	}

	@Test
	public void canvasCommandCreateFailWithInvalidCommandTest() {
		IllegalCommandException thrown = assertThrows(IllegalCommandException.class,
				() -> CanvasCommand.builder().withParams("1").build(), "Expected illegal command exception");
		assertEquals("Create command expects 2 params", thrown.getMessage());
		assertEquals(message, thrown.getHelpMessage());
	}

	@Test
	public void bucketFillCommandCreateFailWithInvalidParamsTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class,
				() -> CanvasCommand.builder().withParams("1", "e", "w").build(), "Expected invalid param exception");
		assertEquals("Given value not a number", thrown.getMessage());
		assertEquals(message, thrown.getHelpMessage());
	}

	@Test
	public void bucketFillCommandCreateFailWithNegativeNumberTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class,
				() -> CanvasCommand.builder().withParams("1", "-3", "w").build(), "Expected invalid param exception");
		assertEquals("Numbers should be >= 0", thrown.getMessage());
		assertEquals(message, thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test creation of CanvasCommand without params")
	public void canvasCommandCreateFailWithoutParamsTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class,
				() -> CanvasCommand.builder().build(), "Expected invalid param exception");
		assertEquals("Params object is null", thrown.getMessage());
		assertEquals("Params cannot be null", thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test creation of CanvasCommand without builder")
	public void canvasCommandCreateFailWithoutBuilderTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class, () -> new CanvasCommand(null),
				"Expected invalid param exception");
		assertEquals("Builder object is null", thrown.getMessage());
		assertEquals("Builder cannot be null", thrown.getHelpMessage());
	}
}
