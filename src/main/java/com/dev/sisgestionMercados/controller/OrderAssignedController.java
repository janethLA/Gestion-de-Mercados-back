package com.dev.sisgestionMercados.controller;

import javax.annotation.security.PermitAll;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.sisgestionMercados.Input.OrderAssignedInput;
import com.dev.sisgestionMercados.Input.OrderInput;
import com.dev.sisgestionMercados.service.OrderAssignedService;
import com.dev.sisgestionMercados.service.OrderService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/orderAssigned")
public class OrderAssignedController {

	@Autowired
	private OrderAssignedService orderAssignedService;
	@Autowired
	private ModelMapper modelMapper;
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")	
	@PostMapping("/assignOrder")
	public ResponseEntity<?> createorder(@RequestBody OrderAssignedInput orderAssigned){
		
		return ResponseEntity.ok(orderAssignedService.save(orderAssigned));
	}
}
