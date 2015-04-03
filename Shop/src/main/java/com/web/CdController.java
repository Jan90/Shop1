package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.model.PageInfo;
import com.model.Store;
import com.service.StoreServiceImpl;

@Controller
public class CdController {
	public static final Integer FIRST_PAGE = 1;
	PageInfo pageInfo = new PageInfo();

	@Autowired
	private final StoreServiceImpl storeServiceImpl;

	@Autowired
	public CdController(StoreServiceImpl storeServiceImpl) {
		this.storeServiceImpl = storeServiceImpl;
	}

	@RequestMapping(value = "/getAllProductList", method = {
			RequestMethod.POST, RequestMethod.GET })
	public String getAllProductList(Model model) {
		Store.getProductList().clear();
		storeServiceImpl.getCDsProductList();
		return showProductList(model, FIRST_PAGE);
	}

	@RequestMapping(value = "/getProductList/name/{name}", method = RequestMethod.GET)
	public String getProductList(Model model, @PathVariable("name") String name) {
		Store.getProductList().clear();
		storeServiceImpl.getCDsProductList(name);
		return showProductList(model, FIRST_PAGE);
	}

	@RequestMapping(value = "/getProductList/type/{type}/genre/{genre}", method = RequestMethod.GET)
	public String getProductList(Model model,
			@PathVariable("type") String type,
			@PathVariable("genre") String genre) {
		Store.getProductList().clear();
		storeServiceImpl.getCDsProductList(type, genre);
		return showProductList(model, FIRST_PAGE);
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String showNewItems(Model model) {
		if (Store.getProductList() != null) {
			Store.getProductList().clear();
		}
		model.addAttribute("newProducts", storeServiceImpl.getCDsNewItems());
		return "main";
	}

	@RequestMapping(value = "/getProductList/page/{page}", method = RequestMethod.GET)
	public String showProductList(Model model,
			@PathVariable("page") Integer page) {
		if (Store.getProductList().size() == 0) {
			return "errorPage";
		} else {
			pageInfo.setPageSize();
			pageInfo.setPage(page - 1);
			model.addAttribute("pageCount", pageInfo.getPageCount());
			model.addAttribute("page", page);
			model.addAttribute("productList", pageInfo.getPageList());
			return "productList";
		}
	}

}
