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

import com.dev.sisgestionMercados.Input.OrderInput;
import com.dev.sisgestionMercados.Output.DeliveryUserOutput;
import com.dev.sisgestionMercados.Output.OrderByUserOutput;
import com.dev.sisgestionMercados.Output.OrderOutput;
import com.dev.sisgestionMercados.Output.OrderToCollectDelivery;
import com.dev.sisgestionMercados.Output.OrderToPayBuyerOutput;
import com.dev.sisgestionMercados.Output.OrderToPayOutput;
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
	@GetMapping("/ordersByUser/{id}")
	public Iterable<OrderByUserOutput> allOrderByUser(@PathVariable long id){
		
		return orderService.allOrderByUser(id);
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")	
	@GetMapping("/allOrdersManaged/{id}")
	public Iterable<OrderOutput> allOrdersByAdmin(@PathVariable Integer id){
		
		return orderService.allOrders2(id);
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")	
	@GetMapping("/allDeliveries")
	public Iterable<DeliveryUserOutput> getAllDeliveries(){
		
		return orderService.getAllDeliveries();
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")
	@PutMapping("/reassignOrder/{id}")
	public ResponseEntity<?> reassignOrder(@PathVariable Integer id){
		
		return ResponseEntity.ok(orderService.reassignOrder(id));
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")
	@PutMapping("/orderCanceled/{id}")
	public ResponseEntity<?> orderCanceled(@PathVariable Integer id){
		
		return ResponseEntity.ok(orderService.orderCanceled(id));
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")
	@PutMapping("/orderSent/{id}")
	public ResponseEntity<?> orderSent(@PathVariable Integer id){
		
		return ResponseEntity.ok(orderService.orderSent(id));
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")
	@PutMapping("/cancelOrderInProgressAndSent/{id}")
	public ResponseEntity<?> cancelOrderInProgressAndSent(@PathVariable Integer id){
		
		return ResponseEntity.ok(orderService.cancelOrderInProgressAndSent(id));
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")
	@PutMapping("/finalizeOrder/{id}")
	public ResponseEntity<?> finalizeOrder(@PathVariable Integer id){
		
		return ResponseEntity.ok(orderService.finalizeOrder(id));
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")
	@PutMapping("/reassignOrderInProgress/{id}")
	public ResponseEntity<?> reassignOrderInProgress(@PathVariable Integer id){
		
		return ResponseEntity.ok(orderService.reassignOrderInProgress(id));
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")	
	@GetMapping("/allOrdersCompletedForPay")
	public Iterable<OrderToPayOutput> allOrdersCompleted(){
		
		return orderService.allOrdersCompleted();
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")	
	@GetMapping("/allBuyers")
	public Iterable<DeliveryUserOutput> getAllBuyers(){
		
		return orderService.getAllBuyers();
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")
	@PutMapping("/changeSubstate/{id}")
	public ResponseEntity<?> changeSubstate(@PathVariable Integer id, @RequestBody String substate){
		
		return ResponseEntity.ok(orderService.changeSubstate(id,substate));
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")	
	@GetMapping("/allOrdersCompletedForPayToBuyer")
	public Iterable<OrderToPayBuyerOutput> allOrdersCompletedForPayToBuyer(){
		
		return orderService.allOrdersCompletedForPayToBuyer();
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")	
	@GetMapping("/allOrdersToCollectDelivery")
	public Iterable<OrderToCollectDelivery> allOrdersToCollectDelivery(){
		
		return orderService.allOrdersToCollectDelivery();
	}
}
