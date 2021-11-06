package com.dev.sisgestionMercados.controller;

import javax.annotation.security.PermitAll;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.sisgestionMercados.Input.ProductInput;
import com.dev.sisgestionMercados.Input.ReportOrderInput;
import com.dev.sisgestionMercados.Output.CategorySearchOutput;
import com.dev.sisgestionMercados.Output.FinalUserAtributesOutput;
import com.dev.sisgestionMercados.entity.Product;
import com.dev.sisgestionMercados.entity.Report;
import com.dev.sisgestionMercados.service.ProductService;
import com.dev.sisgestionMercados.service.ReportService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ReportService reportService;
	
	/*@PreAuthorize("hasRole('ADMINISTRAR_ALMACENES')")	
	@PostMapping("/createProduct/{id}")
	public ResponseEntity<?> save(@ModelAttribute ProductInput product, @PathVariable Integer id, @RequestParam("file") MultipartFile image){
		
		return ResponseEntity.ok(productService.save(product,id, image));
	}*/
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")	
	@PostMapping("/saveReport")
	public ResponseEntity<?> saveReport(@RequestParam("report")MultipartFile file) {
		Report report=reportService.saveReport(file);
		if(report!=null) {
			return ResponseEntity.ok( report);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")	
	@GetMapping("/reportOfOrders")
	public Iterable<ReportOrderInput> reportOfOrders(){
		
		return reportService.reportOfOrders();
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")	
	@GetMapping("/allTelephones")
	public Iterable<Integer> getAllTelephones(){
		
		return reportService.getAllTelephones();
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")	
	@GetMapping("/allUsers")
	public Iterable<String> getAllUsers(){
		
		return reportService.getAllUsers();
	}
	/*@PreAuthorize("hasRole('ADMINISTRAR_ALMACENES')")	
	@GetMapping("/allMeasurement")
	public Iterable<String> getAllMeasurement(){
		
		return productService.getAllMeasurement();
	}
	
	@PermitAll
	@GetMapping("/productSearch/{id}")
	public Iterable<CategorySearchOutput> getAllByproductName(@PathVariable Integer id, @RequestParam("productName") String productName){
		
		return productService.getAllByproductName(id, productName);
	}*/
}
