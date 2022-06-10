package com.console.draw.tool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.console.draw.exceptions.InvalidEntityException;

public class RectangleEntityTest {

	@Test
	@DisplayName("Create rectangle entity test")
	public void bucketFillCommandCreateTest() {
		RectangleEntity rectangleEntity = new RectangleEntity(1, 2, 10, 20);
		assertNotNull(rectangleEntity);
	}

	@Test
	@DisplayName("Test creation of rectangle with invalid params")
	public void rectangleEntityCreateFailWithNegativeNumberTest() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> new RectangleEntity(1, 2, -3, 4), "Expected invalid param exception");
		assertEquals("Number should be greater than zero", thrown.getMessage());
	}

	@Test
	@DisplayName("Test creation to rectangle coordinates")
	public void rectangleEntityCreateDrawTovalidateCordinateTest() {
		List<Integer> expectedCoordinates = Arrays.asList(0, 0, 1, 0, 2, 0, 3, 0, 0, 0, 0, 1, 3, 0, 3, 1, 0, 1, 1, 1, 2,
				1, 3, 1);
		RectangleEntity rectangleEntity = new RectangleEntity(1, 1, 2, 4);
		List<Integer> actalCoordinates = new ArrayList<>();
		rectangleEntity.draw(20, 30, (x, y, ch) -> {
			actalCoordinates.add(x);
			actalCoordinates.add(y);
		});
		assertEquals(expectedCoordinates, actalCoordinates);
	}

	@Test
	@DisplayName("Test creation to rectangle with coordinates outside canvas")
	public void rectangleEntityCreateDrawFailWithOutsidePositionTest() {
		RectangleEntity rectangleEntity = new RectangleEntity(1, 1, 2, 45);
		InvalidEntityException thrown = assertThrows(InvalidEntityException.class,
				() -> rectangleEntity.draw(20, 30, (x, y, ch) -> {
				}), "Expected invalid param exception");
		assertEquals("Edge is outside of the canvas", thrown.getMessage());

	}
	
	@Test
	@DisplayName("Test creation to rectangle with invalid coordinates")
	public void rectangleEntityCreateDrawFailWithInvalidCoordinateTest() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> new RectangleEntity(1, 1, 1, 45), "Expected invalid param exception");
		assertEquals("Points are not valid to draw a rectangle", thrown.getMessage());

	}

}
