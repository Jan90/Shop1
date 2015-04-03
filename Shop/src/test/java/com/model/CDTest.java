package com.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public class CDTest {
	@Test
	@Transactional
	public void testHasPet() {
		CD cd = new CD();
		assertEquals(null, cd.getName());
		cd.setName("Alladin");
		assertEquals("Alladin", cd.getName());
		assertEquals(null, cd.getGenre());
		cd.setGenre("Film");
		assertEquals("Film", cd.getGenre());
		assertEquals(null, cd.getType());
		cd.setType("Audio");
		assertEquals("Audio", cd.getType());
		assertEquals(0, cd.getQuantity());
		cd.setQuantity();
		assertEquals(1, cd.getQuantity());
	}
}
