
package com.eclipsesource.examples.gol.web;

public class Cell {

	public int col;

	public int row;

	public Cell() {
	}

	public Cell(int col, int row) {
		this.col = col;
		this.row = row;
	}

	@Override
	public String toString() {
		return "Cell [col=" + col + ", row=" + row + "]";
	}

}
