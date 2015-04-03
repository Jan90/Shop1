package com.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public class ShoppingCartTest {
	@Test
	@Transactional
	public void testPageInfo() {

		CD cd1 = new CD();
		cd1.setType("Video");
		cd1.setGenre("Film");
		cd1.setName("Matrix");
		cd1.setQuantity();
		CD cd2 = new CD();
		cd2.setType("Video");
		cd2.setGenre("Fil");
		cd2.setName("Mortal Kombat");
		cd2.setQuantity();
		ShoppingCart cart = new ShoppingCart();
		cart.addItem(cd1);
		assertTrue("Correct list of products", cart.getItems().get(0) == cd1);
		cart.addItem(cd2);
		assertTrue("Correct list of products", cart.getItems().get(1) == cd2);
	}
}
