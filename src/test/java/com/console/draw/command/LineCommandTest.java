package com.console.draw.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.console.draw.exceptions.IllegalCommandException;
import com.console.draw.exceptions.InvalidParamsException;

public class LineCommandTest {

	private static final String message = "L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2).\n"
			+ "horizontal or vertical lines are supported. Horizontal and vertical lines \n"
			+ "will be drawn by character 'x'.";

	@Test
	@DisplayName("Create line tool command test")
	public void lineCommandCreateTest() {
		LineCommand.builder().withParams("1", "2", "1", "40").build();
	}

	@Test
	@DisplayName("Test failing of line tool command with wrong number of params")
	public void canvasCommandCreateFailWithInvalidCommandTest() {
		IllegalCommandException thrown = assertThrows(IllegalCommandException.class,
				() -> LineCommand.builder().withParams("1").build(), "Expected illegal command exception");
		assertEquals("Draw line command expects 4 params", thrown.getMessage());
		assertEquals(message, thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test failing of line tool command with wrong parameter")
	public void linToolCommandCreateFailWithInvalidParamsTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class,
				() -> LineCommand.builder().withParams("1", "e", "w", "5").build(), "Expected invalid param exception");
		assertEquals("Not a number", thrown.getMessage());
		assertEquals(message, thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test failing of line tool command with negative")
	public void linToolCommandCreateFailWithNegativeNumberTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class,
				() -> LineCommand.builder().withParams("1", "-3", "4", "5").build(),
				"Expected invalid param exception");
		assertEquals("Numbers should be greater than 0", thrown.getMessage());
		assertEquals(message, thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test failing of line tool command with diagonal line params")
	public void linToolCommandCreateFailWithDigonalLineTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class,
				() -> LineCommand.builder().withParams("1", "6", "4", "5").build(), "Expected invalid param exception");
		assertEquals("Diagonal lines are not supporting now!", thrown.getMessage());
		assertEquals(message, thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test creation of LineCommand without params")
	public void lineCommandCreateFailWithoutParamsTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class, () -> LineCommand.builder().build(),
				"Expected invalid param exception");
		assertEquals("Params object is null", thrown.getMessage());
		assertEquals("Params cannot be null", thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test creation of LineCommand without builder")
	public void lineCommandCreateFailWithoutBuilderTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class, () -> new LineCommand(null),
				"Expected invalid param exception");
		assertEquals("Builder object is null", thrown.getMessage());
		assertEquals("Builder cannot be null", thrown.getHelpMessage());
	}

}
