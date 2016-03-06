package com.eclipsesource.examples.gol.engine;


public interface GameEngine {

	void init(int horizontalSize, int verticalSize);

	String print();

	void run(long periodInMilliseconds);

	int[][] next();

	int toggle(int col, int row);

	void pause();

	void reset();

	void addObject(int[][] objectMatrix, int col, int row);

}
