package com.console.draw.tool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.console.draw.exceptions.InvalidEntityException;
import com.console.draw.exceptions.InvalidParamsException;

public class BucketFillEntityTest {

	@Test
	@DisplayName("Create bucket entity test")
	public void bucketFillCommandCreateTest() {
		BucketFillEntity bucketFillEntity = new BucketFillEntity(1, 2, 'c', new ConsoleCanvas(40, 20));
		assertNotNull(bucketFillEntity);
	}

	@Test
	@DisplayName("Test creation of BucktFillEntity with invalid negative number")
	public void bucketFillEntityCreateFailWithNegativeNumberTest() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> new BucketFillEntity(1, -3, 'w', new ConsoleCanvas(40, 20)), "Expected invalid param exception");
		assertEquals("Number should be greater than zero", thrown.getMessage());
	}

	@Test
	@DisplayName("Test creation of BucktFillEntity draw method with point is outside of the canvas")
	public void bucketFillEntityCreateDrawFailWithInvalidPositionTest() {
		InvalidEntityException thrown = assertThrows(InvalidEntityException.class,
				() -> new BucketFillEntity(43, 2, 'c', new ConsoleCanvas(40, 20)).draw(40, 20, (x, y, ch) -> {
				}), "Expected illegal command exception");
		assertEquals("Point is outside of the canvas", thrown.getMessage());

	}

	@Test
	@DisplayName("Test creation of BucktFillEntity draw method with callback function null")
	public void bucketFillEntityCreateDrawFailWithNullCallbackTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class,
				() -> new BucketFillEntity(43, 2, 'c', new ConsoleCanvas(40, 20)).draw(40, 20, null),
				"Expected invalid param exception");
		assertEquals("Callback function cannot be null", thrown.getMessage());
		assertEquals("Pass valid callback function", thrown.getHelpMessage());
	}

	@Test
	@DisplayName("Test creation of BucktFillEntity draw method with canvas object null")
	public void bucketFillEntityCreateDrawFailWithNullCanvasTest() {
		InvalidParamsException thrown = assertThrows(InvalidParamsException.class,
				() -> new BucketFillEntity(38, 15, 'c', null).draw(20, 40, (x, y, ch) -> {
				}), "Expected invalid param exception");
		assertEquals("Canvas is null", thrown.getMessage());
		assertEquals("First create canvas!", thrown.getHelpMessage());
	}

}
