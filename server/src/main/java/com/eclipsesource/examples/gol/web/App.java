
package com.eclipsesource.examples.gol.web;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The {@link App} handles the client to server part of the WebSocket communication.
 */
@Controller("app")
public class App {

	@Autowired
	private EventAdmin eventAdmin;

	/**
	 * Handle client request "Update a Cell"
	 */
	@MessageMapping("/updateCell")
	public void updateCell(Cell cell) throws Exception {
		Map<String, Object> updateCellProperties = new HashMap<>();
		updateCellProperties.put("x", cell.col);
		updateCellProperties.put("y", cell.row);
		eventAdmin.postEvent(new Event("topic_updateCell", updateCellProperties));
	}

	/**
	 * Serve the Game of Life board
	 */
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String home() {
		return "board";
	}

}
