package com.eclipsesource.examples.gol.jenova.j2v8;

import org.springframework.core.convert.converter.Converter;

public class Generation2FlatGenerationConverter implements Converter<int[][], int[]>{

	@Override
	public int[] convert(int[][] source) {
		int[] result = new int[source.length * source[0].length];
		for (int i = 0; i < source.length; i++) {
			for (int j = 0; j < source[0].length; j++) {
				result[i * source[0].length + j] = source[i][j];
			}
		}
		return result;
	}

}
