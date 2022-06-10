package com.console.draw.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.console.draw.exceptions.IllegalCommandException;
import com.console.draw.exceptions.InvalidParamsException;

public class RectangleCommandTest {

	private static final String message = "R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and\n"
			+ "                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn\n"
			+ "                using the 'x' character.";

	@Test
	@DisplayName("Create rectangle tool command test")
	public void rectangleCommandCreateTest() {
		RectangleCommand.builder().withParams("1", "2", "1", "40").build();
	}

	@Test
	@DisplayName("Test failing of rectangle tool command with wrong number of params")
	public void rectangleCommandCreateFailWithInvalidCommandTest() {
		IllegalCommandException thrown = assertThrows(IllegalCommandException.class,
				() -> RectangleCommand.builder().withParams("1", "3").build(), "Expected illegal command exception");
		assertEquals("Rectangle tool command expects 4 params", thrown.getMessage());
		assertEquals(message, thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test failing of rectangle tool command with wrong parameter")
	public void rectangleCommandCreateFailWithInvalidParamsTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class,
				() -> RectangleCommand.builder().withParams("1", "e", "w", "5").build(),
				"Expected invalid param exception");
		assertEquals("Not a number", thrown.getMessage());
		assertEquals(message, thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test failing of rectangle tool command with negative")
	public void rectangleCommandCreateFailWithNegativeNumberTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class,
				() -> RectangleCommand.builder().withParams("1", "-3", "4", "5").build(),
				"Expected invalid param exception");
		assertEquals("Numbers should be greater than 0", thrown.getMessage());
		assertEquals(message, thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test creation of RectangleCommand without params")
	public void rectangleCommandCreateFailWithoutParamsTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class,
				() -> RectangleCommand.builder().build(), "Expected invalid param exception");
		assertEquals("Params object is null", thrown.getMessage());
		assertEquals("Params cannot be null", thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test creation of RectangleCommand without builder")
	public void rectangleCommandCreateFailWithoutBuilderTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class, () -> new RectangleCommand(null),
				"Expected invalid param exception");
		assertEquals("Builder object is null", thrown.getMessage());
		assertEquals("Builder cannot be null", thrown.getHelpMessage());
	}
}
