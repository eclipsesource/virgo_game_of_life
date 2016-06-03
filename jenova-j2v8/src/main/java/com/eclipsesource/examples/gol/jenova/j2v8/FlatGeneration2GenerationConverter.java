
package com.eclipsesource.examples.gol.jenova.j2v8;

import org.springframework.core.convert.converter.Converter;

public class FlatGeneration2GenerationConverter implements Converter<int[], int[][]> {

	private final int rowLength;

	private final int colLength;

	public FlatGeneration2GenerationConverter(int rowLength, int colLength) {
		super();
		this.rowLength = rowLength;
		this.colLength = colLength;
	}

	@Override
	public int[][] convert(int[] source) {
		int[][] result = new int[colLength][rowLength];
		for (int i = 0; i < colLength; i++) {
			for (int j = 0; j < rowLength; j++) {
				result[i][j] = source[i * rowLength + j];
			}
		}
		return result;
	}

}
