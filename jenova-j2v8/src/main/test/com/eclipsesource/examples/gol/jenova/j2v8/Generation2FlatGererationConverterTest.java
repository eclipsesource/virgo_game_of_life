
package com.eclipsesource.examples.gol.jenova.j2v8;

import static org.junit.Assert.*;

import org.junit.Test;

public class Generation2FlatGererationConverterTest {

	private Generation2FlatGenerationConverter uut = new Generation2FlatGenerationConverter();

	@Test
	public void shouldConvertGenerationZeroCorrectly() {
		assertEquals(1, uut.convert(new int[][] { { 0 } }).length);
		assertArrayEquals(new int[] { 0 }, uut.convert(new int[][] { { 0 } }));
	}

	@Test
	public void shouldConvertGenerationOneCorrectly() {
		assertEquals(1, uut.convert(new int[][] { { 1 } }).length);
		assertArrayEquals(new int[] { 1 }, uut.convert(new int[][] { { 1 } }));
	}

	@Test
	public void shouldConvertOneRowCorrectly() {
		assertEquals(3, uut.convert(new int[][] { { 0, 1, 0 } }).length);
		assertArrayEquals(new int[] { 0, 1, 0 }, uut.convert(new int[][] { { 0, 1, 0 } }));
	}

	@Test
	public void shouldConvertOneColumnCorrectly() {
		assertEquals(3, uut.convert(new int[][] { { 0 }, { 1 }, { 0 } }).length);

		assertArrayEquals(new int[] { 0, 1, 0 }, uut.convert(new int[][] { { 0 }, { 1 }, { 0 } }));
	}

	@Test
	public void shouldConvertSquareMatrixCorrectly() {
		assertEquals(9, uut.convert(new int[][] { { 0, 0, 0 }, { 1, 1, 1 }, { 0, 0, 0 } }).length);
		assertArrayEquals(new int[] { 0, 0, 0, 1, 1, 1, 0, 0, 0 }, uut.convert(new int[][] { { 0, 0, 0 }, { 1, 1, 1 }, { 0, 0, 0 } }));
	}
}
