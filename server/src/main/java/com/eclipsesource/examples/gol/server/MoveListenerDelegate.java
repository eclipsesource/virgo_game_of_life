
package com.eclipsesource.examples.gol.server;

import com.eclipsesource.examples.gol.MoveListener;
import com.eclipsesource.examples.gol.web.Cell;

// TODO task 05.6 implement "NOP" org.osgi.service.event.EventHandler
public class MoveListenerDelegate implements MoveListener {

	private MoveListener moveListener;

	public void setMoveListener(MoveListener moveListener) {
		this.moveListener = moveListener;
	}

	@Override
	public void next(int[][] board) {
		if (moveListener != null) {
			moveListener.next(board);
		}
	}

	@Override
	public void toggle(Cell cell) {
		if (moveListener != null) {
			moveListener.toggle(cell);
		}
	}
}
