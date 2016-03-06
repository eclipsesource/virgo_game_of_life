
package com.eclipsesource.examples.gol.web;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eclipsesource.examples.gol.MoveListener;
import com.eclipsesource.examples.gol.server.MoveListenerDelegate;

/**
 * The {@link Topic} handles the server to client part of the WebSocket communication.
 */
@Component(value = "topic")
public class Topic implements MoveListener {

	// TODO task 06.3 auto wire SimpMessagingTemplate

	@Autowired
	private MoveListener moveListener;

	@PostConstruct
	public void init() {
		if (moveListener != null) {
			((MoveListenerDelegate) moveListener).setMoveListener(this);
		}
	}

	@Override
	public void next(int[][] board) {
		// TODO task 06.4 convert and send board to "topic/newBoard"
	}

	@Override
	public void toggle(Cell cell) {
		// TODO task 06.5 convert and send cell to "topic/userModifiedCell"
	}

}
