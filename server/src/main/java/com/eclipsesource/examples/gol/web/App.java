
package com.eclipsesource.examples.gol.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The {@link App} handles the client to server part of the WebSocket communication.
 */
@Controller("app")
public class App {

	/**
	 * Handle client request "Update a Cell"
	 */
	// TODO task 06.1 add message mapping for "/updateCell"
	public void updateCell(Cell cell) throws Exception {
		// TODO task 06.2 post event "topic_updateCell with "x" and "y" coords
	}

	/**
	 * Serve the Game of Life board
	 */
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String home() {
		return "board";
	}

}
