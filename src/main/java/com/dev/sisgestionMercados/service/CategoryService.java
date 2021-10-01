package com.dev.sisgestionMercados.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.Input.MarketInput;
import com.dev.sisgestionMercados.entity.Category;
import com.dev.sisgestionMercados.entity.Warehouse;
import com.dev.sisgestionMercados.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MarketService marketService;
	
	public Category save(Category category,int idWarehouse) {
		Warehouse warehouse=marketService.getById(idWarehouse);
		Category newCategory =new Category();
		newCategory.setWarehouse(warehouse);
		categoryRepository.save(category);
	    return newCategory;
	}
	
	public Category getById(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId).orElse(null);
	    return category;
	}
}
