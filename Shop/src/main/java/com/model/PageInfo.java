package com.model;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.support.PagedListHolder;

public class PageInfo<T> implements Serializable {
	public static final int MAX_ITEMS_PER_PAGE = 5;
	private static final long serialVersionUID = 1L;
	private final PagedListHolder<CD> itemList = new PagedListHolder<CD>(
			Store.getProductList());

	public void setPageSize() {
		itemList.setPageSize(MAX_ITEMS_PER_PAGE);
	}

	public void setPage(int page) {
		itemList.setPage(page);
	}

	public int getPageCount() {
		return itemList.getPageCount();
	}

	public List<CD> getPageList() {
		return itemList.getPageList();
	}
}
