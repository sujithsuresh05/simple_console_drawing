package com.console.draw.tool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class LineEntityTest {

	@Test
	@DisplayName("Create line entity test")
	public void bucketFillCommandCreateTest() {
		LineEntity lineEntity = new LineEntity(1, 2, 10, 2);
		assertNotNull(lineEntity);
	}

	@Test
	@DisplayName("Test creation of LineEntity with invalid params")
	public void lineEntityCreateFailWithDiaginalNumberTest() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new LineEntity(1, 2, 3, 4),
				"Expected invalid param exception");
		assertEquals("Does not support diagonal line at the moment", thrown.getMessage());
	}

	@Test
	@DisplayName("Test creation of line length with LineEntity")
	public void lineEntityCreateDrawFailWithInvalidPositionTest() {
		LineEntity lineEntity = new LineEntity(1, 2, 10, 2);
		List<Character> charList = new ArrayList<>();
		lineEntity.draw(20, 30, (x, y, ch) -> {
			charList.add(ch);
			assertEquals('x', ch);
		});
		assertEquals(10, charList.size());
	}

}
