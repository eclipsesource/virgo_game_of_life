
package com.eclipsesource.examples.gol.jenova.j2v8;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FlatGeneration2GererationConverterTest {

	@Test
	public void shouldConvertGenerationZeroCorrectly() {
		FlatGeneration2GenerationConverter uut = new FlatGeneration2GenerationConverter(1, 1);
		int[] input = new int[] { 0 };

		assertEquals(1, uut.convert(input).length);
		assertEquals(1, uut.convert(input)[0].length);
		assertArrayEquals(new int[][] { { 0 } }, uut.convert(input));
	}

	@Test
	public void shouldConvertGenerationOneCorrectly() {
		FlatGeneration2GenerationConverter uut = new FlatGeneration2GenerationConverter(1, 1);
		int[] input = new int[] { 1 };

		assertEquals(1, uut.convert(input).length);
		assertEquals(1, uut.convert(input)[0].length);
		assertArrayEquals(new int[][] { { 1 } }, uut.convert(input));
	}

	@Test
	public void shouldConvertOneRowCorrectly() {
		FlatGeneration2GenerationConverter uut = new FlatGeneration2GenerationConverter(3, 1);
		int[] input = new int[] { 0, 1, 0 };

		// verification only
		assertEquals(3, new int[][] { { 0, 1, 0 } }[0].length);
		assertEquals(1, new int[][] { { 0, 1, 0 } }.length);

		assertEquals(3, uut.convert(input)[0].length);
		assertEquals(1, uut.convert(input).length);
		assertArrayEquals(new int[][] { { 0, 1, 0 } }, uut.convert(input));
	}

	@Test
	public void shouldConvertOneColumnCorrectly() {
		FlatGeneration2GenerationConverter uut = new FlatGeneration2GenerationConverter(1, 3);
		int[] input = new int[] { 0, 1, 0 };

		assertEquals(1, uut.convert(input)[0].length);
		assertEquals(3, uut.convert(input).length);

		assertArrayEquals(new int[][] { { 0 }, { 1 }, { 0 } }, uut.convert(input));

	}

	@Test
	public void shouldConvertSquareMatrixCorrectly() {
		FlatGeneration2GenerationConverter uut = new FlatGeneration2GenerationConverter(3, 3);
		int[] input = new int[] { 0, 0, 0, 1, 1, 1, 0, 0, 0 };

		assertEquals(3, uut.convert(input)[0].length);
		assertEquals(3, uut.convert(input).length);

		assertArrayEquals(new int[][] { { 0, 0, 0 }, { 1, 1, 1 }, { 0, 0, 0 } }, uut.convert(input));
	}
}
