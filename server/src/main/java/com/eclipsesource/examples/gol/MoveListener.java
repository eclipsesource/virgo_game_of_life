package com.eclipsesource.examples.gol;

import com.eclipsesource.examples.gol.web.Cell;

public interface MoveListener {

	void next(int[][] board);

	void toggle(Cell cell);

}
