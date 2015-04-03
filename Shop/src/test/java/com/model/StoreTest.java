package com.model;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public class StoreTest {

	@Test
	@Transactional
	public void testStore() {
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
		Store.addProduct(cd1);
		assertTrue("Correct list of products",
				Store.getProductList().get(0) == cd1);
		Store.addProduct(cd2);
		assertTrue("Correct list of products",
				Store.getProductList().get(1) == cd2);
	}
}
