package com.console.draw.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.console.draw.exceptions.IllegalCommandException;
import com.console.draw.exceptions.InvalidParamsException;

public class BucketFillCommandTest {

	private static final String message = "B x y c Should fill the entire area connected to (x,y) with \"colour\" c. The\n"
			+ " behaviour of this is the same as that of the \"bucket fill\" tool in paint programs.";

	@Test
	@DisplayName("Create bucket command test")
	public void bucketFillCommandCreateTest() {
		new BucketFillCommand(BucketFillCommand.builder().withParams("1", "2", "c"));
	}

	@Test
	@DisplayName("Test creation of BucktFillCommand with invalid params count")
	public void bucketFillCommandCreateFailWithInvalidCommandTest() {
		IllegalCommandException thrown = assertThrows(IllegalCommandException.class,
				() -> BucketFillCommand.builder().withParams("1", "2").build(), "Expected illegal command exception");
		assertEquals("Bucket fill expects 3 params", thrown.getMessage());
		assertEquals(message, thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test creation of BucktFillCommand with invalid params value length")
	public void bucketFillCommandCreateFailWithInvalidCommand2Test() {
		IllegalCommandException thrown = assertThrows(IllegalCommandException.class,
				() -> BucketFillCommand.builder().withParams("1", "2", "er").build(),
				"Expected illegal command exception");
		assertEquals("Only one charater is allowed as color character", thrown.getMessage());
		assertEquals(message, thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test creation of BucktFillCommand with invalid params value")
	public void bucketFillCommandCreateFailWithInvalidParamsTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class,
				() -> BucketFillCommand.builder().withParams("1", "e", "w").build(),
				"Expected invalid param exception");
		assertEquals("Invalid number", thrown.getMessage());
		assertEquals(message, thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test creation of BucktFillCommand with invalid negative number")
	public void bucketFillCommandCreateFailWithNegativeNumberTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class,
				() -> BucketFillCommand.builder().withParams("1", "-3", "w").build(),
				"Expected invalid param exception");
		assertEquals("x and y should be greater than 0", thrown.getMessage());
		assertEquals(message, thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test creation of BucktFillCommand without params")
	public void bucketFillCommandCreateFailWithoutParamsTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class,
				() -> BucketFillCommand.builder().build(), "Expected invalid param exception");
		assertEquals("Params object is null", thrown.getMessage());
		assertEquals("Params cannot be null", thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test creation of BucktFillCommand without builder")
	public void bucketFillCommandCreateFailWithoutBuilderTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class, () -> new BucketFillCommand(null),
				"Expected invalid param exception");
		assertEquals("Builder object is null", thrown.getMessage());
		assertEquals("Builder cannot be null", thrown.getHelpMessage());
	}
}
