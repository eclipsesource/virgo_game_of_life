
package com.eclipsesource.examples.gol.jenova.j2v8;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eclipsesource.examples.gol.api.GameOfLife;

@SuppressWarnings("resource")
public class JenovaJ2V8Test {

	@Test
	public void shouldContainBeanNamedJenovaJ2V8() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/gol.jenova.j2v8-applicationContext.xml", getClass());
		assertTrue(Arrays.asList(ctx.getBeanNamesForType(FlatGameOfLife.class)).contains("jenova-j2v8"));
	}

	@Test
	public void shouldProperlyComputeOneGeneration() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/gol.jenova.j2v8-applicationContext.xml", getClass());
		FlatGameOfLife jenova = (FlatGameOfLife) ctx.getBean("jenova-j2v8");

		assertArrayEquals(new int[] { 0 }, jenova.next(new int[] { 1 }, 1, 1));
	}

	@Test
	public void shouldContainBeanNamedJenova() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/gol.jenova.j2v8-applicationContext.xml", getClass());
		assertTrue(Arrays.asList(ctx.getBeanNamesForType(GameOfLife.class)).contains("jenova"));
	}

	@Test
	public void shouldProperlyComputeNextGeneration() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/gol.jenova.j2v8-applicationContext.xml", getClass());
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

		assertArrayEquals(expected, actual);
	}

    @Test
    public void calculateNextGeneration() throws Exception {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/gol.jenova.j2v8-applicationContext.xml", getClass());
		GameOfLife jenova = (GameOfLife) ctx.getBean("jenova");

		int[][] initialBoard = new int[][] { //
			 { 1, 0, 1, 0 }, //
			 { 1, 0, 0, 0 }, //
			 { 1, 0, 0, 0 } };

		int[][] actual = jenova.next(initialBoard);

		int[][] expected = new int[][] { //
			 { 1, 0, 0, 0 }, //
			 { 1, 0, 0, 0 }, //
			 { 1, 0, 0, 0 } };

		assertArrayEquals(expected, actual);
    }

}
