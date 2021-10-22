package com.dev.sisgestionMercados.controller;

import javax.annotation.security.PermitAll;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PreAuthorize("hasRole('ADMINISTRAR_ALMACENES')")	
	@PostMapping("/createCategory/{id}")
	public ResponseEntity<?> save(@RequestBody Category category, @PathVariable Integer id){
		
		return ResponseEntity.ok(categoryService.save(category,id));
	}
	
	@PermitAll	
	@GetMapping("/allCategories/{id}")
	public Iterable<Category> getAllCategories(@PathVariable Integer id){
		
		return categoryService.getAllCategories(id);
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_USUARIOS')")	
	@GetMapping("/uniqueCategoryName/{categoryName}")
	public ResponseEntity<?> uniqueCategoryName(@PathVariable String categoryName, @RequestParam("idWarehouse") int idWarehouse){
		
		return ResponseEntity.ok(categoryService.noExistsCategoryName(categoryName,idWarehouse));//Devuelve true en caso de que el nombre es unico, devuelva false si ya existe ese nombre
	}
}
