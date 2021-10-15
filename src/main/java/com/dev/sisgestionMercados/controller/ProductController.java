package com.dev.sisgestionMercados.controller;

import javax.annotation.security.PermitAll;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.sisgestionMercados.Input.ProductInput;
import com.dev.sisgestionMercados.Output.ProductOutput;
import com.dev.sisgestionMercados.entity.Category;
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
	public ResponseEntity<?> save(@ModelAttribute ProductInput product, @PathVariable Integer id, @RequestParam("file") MultipartFile image){
		
		return ResponseEntity.ok(productService.save(product,id, image));
	}
	
	@PermitAll
	@GetMapping("/allProducts")
	public Iterable<ProductOutput> getAllProduct(){
		
		return productService.getAllProducts();
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_ALMACENES')")	
	@GetMapping("/allMeasurement")
	public Iterable<String> getAllMeasurement(){
		
		return productService.getAllMeasurement();
	}
}
