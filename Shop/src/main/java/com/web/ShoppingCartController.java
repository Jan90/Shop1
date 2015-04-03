package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.CD;
import com.model.ShoppingCart;

@Controller
public class ShoppingCartController {
	ShoppingCart shoppingCart = new ShoppingCart();

	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public @ResponseBody
	void addToCart(@RequestBody String[] products) {
		for (int i = 0, j = 1, k = 2; k < products.length; i += 3, j += 3, k += 3) {
			String type = products[i];
			String genre = products[j];
			String name = products[k];
			CD CDProduct = new CD(name, type, genre);
			shoppingCart.addItem(CDProduct);
		}
	}

	@RequestMapping(value = "/getShoppingCart", method = RequestMethod.GET)
	public String showShoppingCart(Model model) {
		model.addAttribute("cartProducts", shoppingCart.getItems());
		return "shoppingCart";
	}

	@RequestMapping(value = "/removeProduct/{index}", method = RequestMethod.GET)
	public String removeProduct(Model model,
			@PathVariable("index") Integer index) {
		shoppingCart.removeProduct(index);
		return showShoppingCart(model);
	}
}
