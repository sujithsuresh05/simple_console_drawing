package com.console.draw.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class UtilsTest {

	@Test
	@DisplayName("Test to check parsing number")
	public void utilsParsingTest() {
		assertEquals(2, Utils.parseToInt("2"));
	}
	
	@Test
	@DisplayName("Test to check parsing invalid number")
	public void utilsParsingInvalidNumTest() {
		assertThrows(NumberFormatException.class,() -> Utils.parseToInt("fg"));
	}
}
