package com.model;

import java.util.ArrayList;
import java.util.List;

import com.model.CD;

public class Store {

	public static List<CD> productList;

	public static List<CD> getProductList() {
		if (productList == null) {
			productList = new ArrayList<CD>();
		}
		return productList;
	}

	public static void addProduct(CD cd) {
		getProductList().add(cd);
	}
}