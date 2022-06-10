package com.console.draw.tool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.withSettings;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class ConsoleCanvasTest {

	@Test
	@DisplayName("Create console canvas test")
	public void consolCanvasCreateTest() {
		
		ConsoleCanvas createConsoleCanvas = new ConsoleCanvas(40, 20);
		assertNotNull(createConsoleCanvas);
	}

	@Test
	@DisplayName("Test whether the right argument is passing to draw method on while adding entity")
	public void consolCanvasAddEntityCallArugumentTest() {
		ConsoleCanvas consolCanvas = new ConsoleCanvas(40, 20);
		Entity entity = Mockito.mock(BucketFillEntity.class,
				withSettings().useConstructor(10, 20, 'c', consolCanvas));
		consolCanvas.addEntity(entity);
		ArgumentCaptor<Integer> heightCapture = ArgumentCaptor.forClass(Integer.class);
		ArgumentCaptor<Integer> widthCapture = ArgumentCaptor.forClass(Integer.class);
		verify(entity, times(1)).draw(heightCapture.capture(), widthCapture.capture(), any());

		assertEquals(20, heightCapture.getValue());
		assertEquals(40, widthCapture.getValue());
	}

}
