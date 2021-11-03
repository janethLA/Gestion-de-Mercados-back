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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.sisgestionMercados.Input.OrderAssignedInput;
import com.dev.sisgestionMercados.Input.OrderInput;
import com.dev.sisgestionMercados.Output.AllOrderAssignedOutput;
import com.dev.sisgestionMercados.Output.OrderAssignedOutput;
import com.dev.sisgestionMercados.entity.OrderAssigned;
import com.dev.sisgestionMercados.entity.OrderP;
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
	
	@PreAuthorize("hasRole('VER_PEDIDOS')")
	@GetMapping("/allAssignedOrders/{id}")
	public Iterable<OrderAssignedOutput> allAssignedOrders(@PathVariable Integer id){
		
		return orderAssignedService.allAssignedOrders(id);
	}
	
	@PreAuthorize("hasRole('VER_PEDIDOS')")
	@PutMapping("/assignedOrderAccepted/{id}")
	public ResponseEntity<?> assignedOrderAccepted(@PathVariable Integer id){
		
		return ResponseEntity.ok(orderAssignedService.assignedOrderAccepted(id));
	}
	
	@PreAuthorize("hasRole('VER_PEDIDOS')")
	@PutMapping("/assignedOrderRejected/{id}")
	public ResponseEntity<?> assignedOrderRejected(@PathVariable Integer id){
		
		return ResponseEntity.ok(orderAssignedService.assignedOrderRejected(id));
	}
	
	@PreAuthorize("hasRole('VER_PEDIDOS')")
	@PutMapping("/orderCompleted/{id}")
	public ResponseEntity<?> orderCompleted(@PathVariable Integer id){
		
		return ResponseEntity.ok(orderAssignedService.orderCompleted(id));
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")
	@GetMapping("/allAssignedOrders")
	public Iterable<AllOrderAssignedOutput> getAllAssignedOrders(){
		
		return orderAssignedService.getAllAssignedOrders();
	}
	
	@PreAuthorize("hasRole('VER_PEDIDOS')")
	@PutMapping("/reportEmergency/{id}")
	public ResponseEntity<?> reportEmergency(@PathVariable Integer id, @RequestBody String commentary){
		
		return ResponseEntity.ok(orderAssignedService.reportEmergency(id, commentary));
	}
}
