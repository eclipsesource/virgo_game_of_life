
package com.eclipsesource.examples.gol.jenova;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eclipsesource.examples.gol.api.GameOfLife;

public class JenovaTest {

	@Test
	public void shouldContainBeanNamedJenova() {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/gol.jenova-applicationContext.xml", getClass());
		assertTrue(Arrays.asList(ctx.getBeanNamesForType(GameOfLife.class)).contains("jenova"));
	}

	@Test
	public void shouldProperlyComputeNextGeneration() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/gol.jenova-applicationContext.xml", getClass());
		GameOfLife jenova = (GameOfLife) ctx.getBean("jenova");
		assertFalse(AopUtils.isAopProxy(jenova));

		int[][] initialBoard = new int[][] { //
			{ 1, 0, 1, 0 }, //
			{ 1, 0, 0, 0 }, //
			{ 1, 0, 0, 0 }, //
			{ 1, 0, 0, 0 } };

		int[][] actual = jenova.next(initialBoard);

		int[][] expected = new int[][] { //
			{ 1, 0, 0, 0 }, //
			{ 1, 0, 0, 0 }, //
			{ 1, 1, 0, 1 }, //
			{ 1, 0, 0, 0 } };

		assertArrayEquals(actual, expected);
	}
}
