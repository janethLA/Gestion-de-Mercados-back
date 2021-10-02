package com.dev.sisgestionMercados.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.Input.MarketInput;
import com.dev.sisgestionMercados.Output.WarehouseOutput;
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
		System.out.println("alllllllllllllllmacen es: "+warehouse.getIdMarket());
		//Category newCategory =new Category();
		category.setWarehouse(warehouse);
		categoryRepository.save(category);
	    return category;
	}
	
	public Category getById(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId).orElse(null);
	    return category;
	}
	
	public Iterable<Category> getAllCategories(int id){
		List <Category> allCategories= categoryRepository.findAll();
		List <Category> allCategoriesByWarehouse= new ArrayList<Category>();
		for(Category newCategory: allCategories) {
			if(newCategory.getWarehouse().getIdMarket() == id) {
				allCategoriesByWarehouse.add(newCategory);
			}
		}
		
		return allCategoriesByWarehouse;
	}
}
