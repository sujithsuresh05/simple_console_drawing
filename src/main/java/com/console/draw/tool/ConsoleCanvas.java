package com.console.draw.tool;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.console.draw.exceptions.InvalidEntityException;
import com.console.draw.exceptions.InvalidParamsException;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class ConsoleCanvas implements Canvas{

	private static final char HORIZONTAL_EDGE_CHAR = '-';
	private static final char VERTICAL_EDGE_CHAR = '|';

	private final String horizontalEdge;
	
	private final char[][] canvasArray;
	private final int width;
	private final int height;

	public ConsoleCanvas(int width, int height) {
		this.width = width;
		this.height = height;
		canvasArray = new char[this.height][this.width];
		Arrays.stream(canvasArray).forEach(chars -> Arrays.fill(chars, ' '));

		horizontalEdge = Stream.generate(() -> String.valueOf(HORIZONTAL_EDGE_CHAR)).limit(width + 2)
				.collect(Collectors.joining());
	}

	@Override
	public void addEntity(Entity tool) throws InvalidEntityException, InvalidParamsException {
		tool.draw(height, width, (x, y, ch) -> {
			if (x >= height || y >= width)
				throw new InvalidEntityException("Position is out of boundary!!");
			canvasArray[x][y] = ch;
		});
		this.display();
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	@Override
	public void display() {
		int height = getHeight();
		int width = getWidth();
		StringBuilder builder = new StringBuilder();
        builder.append(horizontalEdge).append("\n");
        for (int i = 0; i < height; i++) {
            builder.append(VERTICAL_EDGE_CHAR);
            for (int j = 0; j < width; j++) {
                builder.append(canvasArray[i][j]);
            }
            builder.append(VERTICAL_EDGE_CHAR);
            builder.append("\n");
        }
        builder.append(horizontalEdge);
        System.out.println(builder.toString());
	}
	
	@Override
	public Character getCharacterAtPoint(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height)
			throw new InvalidEntityException("Point goes out of the canvas");
		return canvasArray[y][x];
	}

}
