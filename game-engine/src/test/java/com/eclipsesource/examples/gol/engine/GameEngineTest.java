
package com.eclipsesource.examples.gol.engine;

import static com.eclipsesource.examples.gol.engine.DefaultGameEngine.DEFAULT_PERIOD;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.atLeast;
import static org.mockito.internal.verification.VerificationModeFactory.atMost;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Matchers;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eclipsesource.examples.gol.api.GameOfLife;

@Ignore
public class GameEngineTest {

	@Test
	public void defaultConstructorShouldInitTheGameWithDefaultSize() {
		DefaultGameEngine uut = new DefaultGameEngine();

		assertThat(uut.getHorizontalSize(), is(15));
		assertThat(uut.getVerticalSize(), is(10));
	}

	@Test
	public void shouldInitSquareSizedGame() throws Exception {
		DefaultGameEngine uut = new DefaultGameEngine();
		uut.init(7);

		assertThat(uut.getHorizontalSize(), is(7));
		assertThat(uut.getVerticalSize(), is(7));
	}

	@Test
	public void shouldInitSizedGame() throws Exception {
		DefaultGameEngine uut = new DefaultGameEngine();
		uut.init(4, 5);

		assertThat(uut.getHorizontalSize(), is(4));
		assertThat(uut.getVerticalSize(), is(5));
	}

	@Test
	public void shouldPrintTheBoard() throws Exception {
		DefaultGameEngine uut = new DefaultGameEngine();

		String actual = uut.print();

		assertThat(actual, notNullValue());
	}

	@Test
	public void shouldToggleField() throws Exception {
		DefaultGameEngine uut = new DefaultGameEngine();

		int actual = uut.toggle(0, 0);

		assertThat(actual, is(1));
	}

	@Test
	public void toggleShouldBeRevesibleField() throws Exception {
		DefaultGameEngine uut = new DefaultGameEngine();

		uut.toggle(0, 0);
		int actual = uut.toggle(0, 0);

		assertThat(actual, is(0));
	}

	@Test
	// maybe we can check the board instead of using our knowledge about the internals of the game engine
	public void shouldUpdateTheGameViaJenova() throws Exception {
		GameOfLife jenova = mock(GameOfLife.class);
		DefaultGameEngine uut = new DefaultGameEngine();

		when(jenova.next((int[][]) Matchers.notNull())).thenReturn(new int[10][10]);
		uut.next();

		verify(jenova, times(1)).next((int[][]) Matchers.notNull());
	}

	@Test
	public void shouldContainBeanGameOfLife() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
			new String[] { "/META-INF/spring/gol.engine-applicationContext.xml", "/META-INF/spring/gol.jenova-applicationContext.xml" }, getClass());
		assertTrue(Arrays.asList(ctx.getBeanNamesForType(GameEngine.class)).contains("gameEngine"));
	}

	@Test
	public void shouldTriggerUpdatesWhenRunning() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/gol.engine-applicationContext.xml", getClass());
		DefaultGameEngine uut = ctx.getBean(DefaultGameEngine.class, "gameOfLife");

		Runnable gameMasterMock = mock(Runnable.class);
		uut.setGameMaster(gameMasterMock);
		uut.run();

		Thread.sleep(DEFAULT_PERIOD * 3);

		verify(gameMasterMock, atLeast(2)).run();
	}

	@Test
	public void shouldReplaceUpdateTrigger() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/gol.engine-applicationContext.xml", getClass());
		DefaultGameEngine uut = ctx.getBean(DefaultGameEngine.class, "gameOfLife");

		Runnable gameMasterMock = mock(Runnable.class);
		uut.setGameMaster(gameMasterMock);

		uut.run();
		uut.run();
		uut.run();
		uut.run(DEFAULT_PERIOD * 10); // very long

		Thread.sleep(DEFAULT_PERIOD * 3);

		verify(gameMasterMock, atMost(4)).run();
	}

	@Test
	public void shouldPauseTheGame() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/gol.engine-applicationContext.xml", getClass());
		DefaultGameEngine uut = ctx.getBean(DefaultGameEngine.class, "gameOfLife");

		Runnable gameMasterMock = mock(Runnable.class);
		uut.setGameMaster(gameMasterMock);

		uut.run();
		uut.pause();

		Thread.sleep(DEFAULT_PERIOD * 3);

		verify(gameMasterMock, atMost(1)).run();
	}

	@Test
	public void shouldAddPointAtOrigin() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/gol.engine-applicationContext.xml", getClass());
		DefaultGameEngine uut = ctx.getBean(DefaultGameEngine.class, "gameOfLife");
		uut.addObject(new int[][] { { 1, 1 }, { 1, 1 } }, 0, 0);

		int[][] actual = uut.getCurrentBoard();
		assertThat(actual[0][0], is(1));
		assertThat(actual[1][0], is(1));
		assertThat(actual[0][1], is(1));
		assertThat(actual[1][1], is(1));
	}

	@Test
	public void shouldAddBlinkerAtOrigin() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/gol.engine-applicationContext.xml", getClass());
		DefaultGameEngine uut = ctx.getBean(DefaultGameEngine.class, "gameOfLife");
		uut.addObject(new int[][] { { 1, 1, 1 } }, 0, 0);

		int[][] actual = uut.getCurrentBoard();
		assertThat(actual[0][0], is(1));
		assertThat(actual[1][0], is(1));
		assertThat(actual[2][0], is(1));
	}

	@Test
	public void shouldAddFlowerAtOrigin() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/gol.engine-applicationContext.xml", getClass());
		DefaultGameEngine uut = ctx.getBean(DefaultGameEngine.class, "gameOfLife");
		uut.addObject(new int[][] { { 0, 1, 0 }, { 1, 0, 1 }, { 0, 1, 0 } }, 0, 0);

		int[][] actual = uut.getCurrentBoard();
		assertThat(actual[0][0], is(0));
		assertThat(actual[1][1], is(0));
		assertThat(actual[2][2], is(0));
	}
}
