package com.dev.sisgestionMercados.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.sisgestionMercados.Input.MarketInput;
import com.dev.sisgestionMercados.Output.WarehouseOutput;
import com.dev.sisgestionMercados.entity.Category;
import com.dev.sisgestionMercados.service.CategoryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/createCategory/{id}")
	public ResponseEntity<?> save(@RequestBody Category category, @PathVariable Integer id){
		
		return ResponseEntity.ok(categoryService.save(category,id));
	}
	
	@GetMapping("/allCategories")
	public Iterable<Category> getAllCategories(){
		
		return categoryService.getAllCategories();
	}
}
