
package com.eclipsesource.examples.gol.commands;

import static java.lang.Integer.parseInt;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import com.eclipsesource.examples.gol.engine.GameEngine;

public class OsgiCommandProvider implements CommandProvider {

	private final GameEngine gameEngine;

	private Map<String, int[][]> golPatterns = new HashMap<>();

	public OsgiCommandProvider(GameEngine gameEngine) {
		this.gameEngine = gameEngine;

		// static golPatterns
		this.golPatterns.put("point", new int[][] { { 1, 1 }, { 1, 1 } });

		// oscillating golPatterns
		this.golPatterns.put("blinker", new int[][] { { 1, 1, 1 } });

		// fancy golPatterns
		this.golPatterns.put("glider", new int[][] { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 } });
	}

	public Object _init(CommandInterpreter commandInterpreter) {
		int horizontalSize = 10;
		int verticalSize = 10;

		String horizontalSizeAsString = commandInterpreter.nextArgument();
		if (horizontalSizeAsString != null) {
			horizontalSize = Integer.valueOf(horizontalSizeAsString);
			verticalSize = horizontalSize;
		}
		String verticalSizeAsString = commandInterpreter.nextArgument();
		if (verticalSizeAsString != null) {
			verticalSize = Integer.valueOf(verticalSizeAsString);
		}
		gameEngine.init(horizontalSize, verticalSize);
		return null;
	}

	public Object _print(CommandInterpreter commandInterpreter) {
		System.out.println(gameEngine.print());
		return null;
	}

	public Object _run(CommandInterpreter commandInterpreter) {
		long periodInMilliseconds = 250;
		String periodInMillisecondsAsString = commandInterpreter.nextArgument();
		if (periodInMillisecondsAsString != null) {
			periodInMilliseconds = Long.valueOf(periodInMillisecondsAsString);
		}
		gameEngine.run(periodInMilliseconds);
		return null;
	}

	public Object _next(CommandInterpreter commandInterpreter) {
		gameEngine.next();
		return null;
	}

	public Object _toggle(CommandInterpreter commandInterpreter) {
		int x = parseInt(commandInterpreter.nextArgument());
		int y = parseInt(commandInterpreter.nextArgument());
		gameEngine.toggle(x, y);
		return null;
	}

	public Object _pause(CommandInterpreter commandInterpreter) {
		gameEngine.pause();
		return null;
	}

	public Object _reset(CommandInterpreter commandInterpreter) {
		gameEngine.reset();
		return null;
	}

	// TODO task 04.2 implement OSGi command add [object_name] [x[,y]] - add object to the game

	public String getHelp() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("---game of life commands---\n\t");
		buffer.append("init [x[,y]] - create a new game with size (10,10), square size (x,x) or specific size (x,y)\n\t");
		buffer.append("print - print the current game state to the console\n\t");
		buffer.append("run [ms] - run the game with the given update interval (milliseconds)\n\t");
		buffer.append("next - run a single step\n\t");
		buffer.append("toggle x,y - toggle a single field\n\t");
		buffer.append("pause - pause the game\n\t");
		buffer.append("reset - reset the game\n\t");
		return buffer.toString();
	}
}
