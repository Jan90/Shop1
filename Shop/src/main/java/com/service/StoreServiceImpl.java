package com.service;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import com.model.CD;
import com.repository.CategoriesRepository;
import com.repository.CdRepository;

public class StoreServiceImpl implements StoreService {

	private CdRepository cdRepository;

	private CategoriesRepository categoriesRepository;

	public StoreServiceImpl() {}
	
	@Autowired
	public StoreServiceImpl(CdRepository cdRepository,
			CategoriesRepository categoriesRepository) {
		this.cdRepository = cdRepository;
		this.categoriesRepository = categoriesRepository;
	}

	@Override
	public List<String> populateGenreDropDownList(String type) {
		// TODO Auto-generated method stub
		return categoriesRepository.populateDropDownList(type);
	}

	@Override
	public Collection<CD> getCDsProductList() throws DataAccessException {
		// TODO Auto-generated method stub
		return cdRepository.getProductList();
	}

	@Override
	public void getCDsProductList(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		cdRepository.getProductList(name);

	}

	@Override
	public void getCDsProductList(String type, String genre)
			throws DataAccessException {
		cdRepository.getProductList(type, genre);
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<CD> getCDsNewItems() throws DataAccessException {
		// TODO Auto-generated method stub
		return cdRepository.getNewItems();
	}

}
