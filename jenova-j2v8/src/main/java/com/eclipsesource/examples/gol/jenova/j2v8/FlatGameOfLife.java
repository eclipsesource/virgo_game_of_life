
package com.eclipsesource.examples.gol.jenova.j2v8;

public interface FlatGameOfLife {

	/**
	 * Compute next generation based on
	 * @param oldGeneration
	 * @param rowLength
	 * @param colLength
	 * @return newGeneration
	 */
	int[] next(int[] oldGeneration, int rowLength, int colLength);
}
