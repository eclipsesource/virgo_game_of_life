
package com.eclipsesource.examples.gol.server;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BoardMapperTest {

	@Test
	public void testBoardToJsonMapping() throws JsonProcessingException {
		int[][] board = new int[][] { { 1, 0, 1 }, { 0, 1, 0 } };

		String actual = new ObjectMapper().writeValueAsString(board);

		Assert.assertEquals("[[1,0,1],[0,1,0]]", actual);
	}

}
