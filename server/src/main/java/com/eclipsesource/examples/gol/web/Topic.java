
package com.eclipsesource.examples.gol.web;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.eclipsesource.examples.gol.MoveListener;
import com.eclipsesource.examples.gol.server.MoveListenerDelegate;

/**
 * The {@link Topic} handles the server to client part of the WebSocket communication.
 */
@Component(value = "topic")
public class Topic implements MoveListener {

	@Autowired
	private SimpMessagingTemplate template;

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
		template.convertAndSend("/topic/newBoard", board);
	}

	@Override
	public void toggle(Cell cell) {
		template.convertAndSend("/topic/userModifiedCell", cell);
	}

}
