
package com.eclipsesource.examples.gol.api;

public interface GameOfLife {

	/**
	 * Compute next generation based on
	 * @param oldGeneration
	 * @return newGeneration
	 */
	int[][] next(int[][] oldGeneration);
}
