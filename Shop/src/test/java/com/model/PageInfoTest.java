package com.model;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public class PageInfoTest {

	@Test
	@Transactional
	public void testPageInfo() {
		ArrayList<CD> cdList = new ArrayList<CD>();
		CD cd1 = new CD();
		cd1.setType("Video");
		cd1.setGenre("Film");
		cd1.setName("Matrix");
		CD cd2 = new CD();
		cd2.setType("Video");
		cd2.setGenre("Film");
		cd2.setName("Mortal Kombat");
		cdList.add(cd1);
		cdList.add(cd2);
		Store.addProduct(cd1);
		Store.addProduct(cd2);
		PageInfo pageInfo = new PageInfo();
		assertTrue("Correct number of pages", pageInfo.getPageCount() == 1);
		assertTrue("Correct page list contents",
				pageInfo.getPageList().get(0) == cd1);
		pageInfo.setPageSize();
		assertTrue("Correct number of pages", pageInfo.getPageCount() == 1);
		assertTrue("Correct page list contents",
				pageInfo.getPageList().get(0) == cd1);
	}
}
