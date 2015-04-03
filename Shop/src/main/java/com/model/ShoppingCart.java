package com.model;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ShoppingCart {

	protected CopyOnWriteArrayList<CD> items;

	public ShoppingCart() {
		items = new CopyOnWriteArrayList<CD>();
	}

	@SuppressWarnings("unchecked")
	public CopyOnWriteArrayList<CD> getItems() {
		return (CopyOnWriteArrayList<CD>) items.clone();
	}

	public void addItem(CD newItem) {
		Boolean flag = false;

		if (items.size() == 0) {
			items.add(newItem);
			return;
		}
		for (int i = 0; i < items.size(); i++) {
			CD dvd = (CD) items.get(i);
			if (dvd.getName().equals(newItem.getName())) {
				dvd.setQuantity();
				items.set(i, dvd);
				flag = true;
				break;
			}
		}
		if (newItem.getQuantity() > 0 && (flag == false)) {
			items.add(newItem);
		}
	}

	public void removeProduct(int productIndex) {
		items.remove(productIndex);
	}

}
