package com.dev.sisgestionMercados.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.sisgestionMercados.Input.ProductOutput;
import com.dev.sisgestionMercados.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductService productService;
	
	@PreAuthorize("hasRole('ADMINISTRAR_ALMACENES')")	
	@PostMapping("/createProduct/{id}")
	public ResponseEntity<?> save(@ModelAttribute ProductOutput product, @PathVariable Integer id, @RequestParam("file") MultipartFile image){
		//productService.save(product);
		//productService.saveImage( image)
		return ResponseEntity.ok(productService.save(product,id, image));
	}
	
	/*
	 * @PostMapping("/createProduct/{id}")
	public ResponseEntity<?> save(@RequestBody ProductOutput category, @PathVariable Integer id, @RequestParam("file") MultipartFile image){
		
		return ResponseEntity.ok(productService.save(category,id, image));
	}*/
}
