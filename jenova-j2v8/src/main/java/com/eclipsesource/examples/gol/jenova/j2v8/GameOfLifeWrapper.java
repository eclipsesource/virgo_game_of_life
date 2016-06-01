
package com.eclipsesource.examples.gol.jenova.j2v8;

import org.springframework.core.convert.converter.Converter;

import com.eclipsesource.examples.gol.api.GameOfLife;

public class GameOfLifeWrapper implements GameOfLife {

	private FlatGameOfLife gameOfLife;

	private Converter<int[][], int[]> inputConverter = new Generation2FlatGenerationConverter();

	@Override
	public int[][] next(int[][] oldGeneration) {
		Converter<int[], int[][]> outputConverter = new FlatGeneration2GenerationConverter(oldGeneration[0].length, oldGeneration.length);
		return outputConverter.convert(this.gameOfLife.next(inputConverter.convert(oldGeneration), oldGeneration[0].length, oldGeneration.length));
	}

	public void setGameOfLife(FlatGameOfLife gameOfLife) {
		this.gameOfLife = gameOfLife;
	}
}
