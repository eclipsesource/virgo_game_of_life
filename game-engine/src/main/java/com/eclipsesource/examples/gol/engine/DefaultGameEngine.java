
package com.eclipsesource.examples.gol.engine;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

@Component("gameEngine")
public class DefaultGameEngine implements GameEngine {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultGameEngine.class);

	static final long DEFAULT_PERIOD = 1000;

	private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MILLISECONDS;

	// TODO task 03.1 autowire GameOfLife

	// TODO task 05.1 autowire EventAdmin

	private ThreadPoolTaskScheduler taskScheduler;

	private Runnable gameMaster = new Runnable() {

		@Override
		public void run() {
			LOG.info(" -- Calculating next generation --");
			next();
		}
	};

	private int[][] board = new int[10][15];

	DefaultGameEngine() {
	}

	public void init(int squareSize) {
		init(squareSize, squareSize);
	}

	public void init(int horizontalSize, int verticalSize) {
		board = new int[verticalSize][horizontalSize];
		Random random = new Random(System.currentTimeMillis());
		for (int[] horizontal : board) {
			for (int i = 0; i < horizontal.length; i++) {
				if (random.nextInt(100) > 70) {
					horizontal[i] = 1;
				}
			}
		}
		publishBoard();
	}

	public String print() {
		return printBoard(board);
	}

	public static String printBoard(int[][] board) {
		StringBuffer buffer = new StringBuffer();
		for (int[] rows : board) {
			for (int b : rows) {
				buffer.append(b + " ");
			}
			buffer.append("\n");
		}
		buffer.append("\n");
		return buffer.toString();
	}

	public int[][] next() {
		// TODO task 03.3 calculate and store next generation of the board
		publishBoard();
		return this.board;
	}

	private void publishBoard() {
		// TODO task 05.2 post event "topic_newBoard" with key="board" and payload board
	}

	// Think about concurrent access
	public int toggle(int x, int y) {
		if (this.board[y][x] == 0) {
			this.board[y][x] = 1;
		} else {
			this.board[y][x] = 0;
		}
		publishToggle(x, y);
		return board[y][x];
	}

	private void publishToggle(int x, int y) {
		// TODO task 05.3 post event "topic_userModifiedCell" and keys "x", "y"
	}

	// TODO task 03.2 start bean post construction
	public void start() {
		init(30, 20);
		run();
	}

	public void initialize() {
		this.taskScheduler = new ThreadPoolTaskScheduler();
		this.taskScheduler.setRemoveOnCancelPolicy(true);
		this.taskScheduler.initialize();
	}

	// TODO task 03.4 shutdown bean pre destruction
	public void shutdown() {
		taskScheduler.shutdown();
		taskScheduler = null;
	}

	public void run() {
		run(DEFAULT_PERIOD);
	}

	public void run(long periodInMilliseconds) {
		if (taskScheduler != null) {
			shutdown();
		}
		initialize();
		taskScheduler.schedule(gameMaster, new PeriodicTrigger(periodInMilliseconds, DEFAULT_TIME_UNIT));
	}

	public void pause() {
		if (taskScheduler != null) {
			shutdown();
		}
	}

	void setGameMaster(Runnable gameMaster) {
		this.gameMaster = gameMaster;
	}

	public int getHorizontalSize() {
		return board[0].length;
	}

	public int getVerticalSize() {
		return board.length;
	}

	public int[][] getCurrentBoard() {
		return board;
	}

	public void addObject(int[][] object, int colOffset, int rowOffset) {
		for (int i = 0; i < object.length; i++) {
			int[] row = object[i];
			for (int j = 0; j < row.length; j++) {
				int colPosition = colOffset + i;
				int rowPosition = rowOffset + j;
				board[rowPosition][colPosition] = object[i][j];
			}
		}
	}

	public void reset() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				this.board[i][j] = 0;
			}
		}
		publishBoard();
	}

}
