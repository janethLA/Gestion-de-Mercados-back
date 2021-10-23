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
import org.springframework.web.bind.annotation.RestController;

import com.dev.sisgestionMercados.Input.OrderInput;
import com.dev.sisgestionMercados.entity.FinalUser;
import com.dev.sisgestionMercados.entity.OrderP;
import com.dev.sisgestionMercados.service.OrderService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private ModelMapper modelMapper;
	
	@PermitAll
	@PostMapping("/createOrder/{id}")
	public ResponseEntity<?> createorder(@RequestBody OrderInput order, @PathVariable Integer id){
		
		return ResponseEntity.ok(orderService.save(order,id));
	}
	
	@PreAuthorize("hasRole('FINAL_USER')")
	@GetMapping("/allOrder/{id}")
	public Iterable<OrderP> allOrderByUser(@PathVariable long id){
		
		return orderService.allOrderByUser(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMINISTRAR_PEDIDOS')")	
	@GetMapping("/allOrders")
	public Iterable<OrderP> allOrders(){
		
		return orderService.allOrders();
	}
}
