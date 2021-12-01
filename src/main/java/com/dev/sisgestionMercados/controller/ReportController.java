package com.dev.sisgestionMercados.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.sisgestionMercados.Input.ReportOrderInput;
import com.dev.sisgestionMercados.entity.Report;
import com.dev.sisgestionMercados.service.ReportService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ReportService reportService;
	
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
}
