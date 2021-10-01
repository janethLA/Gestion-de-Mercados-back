package com.dev.sisgestionMercados.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.sisgestionMercados.entity.Product;
import com.dev.sisgestionMercados.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductService productService;
	
	/*@PostMapping("/createProduct/{id}")
	public ResponseEntity<?> save(@RequestBody Product category, @PathVariable Integer id, MultipartFile image){
		
		return ResponseEntity.ok(productService.save(category,id));
	}*/
}
