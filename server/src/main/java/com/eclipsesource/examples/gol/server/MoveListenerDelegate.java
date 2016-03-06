
package com.eclipsesource.examples.gol.server;

import java.util.Arrays;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import com.eclipsesource.examples.gol.MoveListener;
import com.eclipsesource.examples.gol.web.Cell;

public class MoveListenerDelegate implements MoveListener, EventHandler {

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

	@Override
	public void handleEvent(Event event) {
		System.out.println("Event arrived: " + event);
		System.out.println("Available properties: " + Arrays.toString(event.getPropertyNames()));
	}
}
