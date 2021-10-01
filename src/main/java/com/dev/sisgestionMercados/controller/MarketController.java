package com.dev.sisgestionMercados.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.sisgestionMercados.Input.MarketInput;
import com.dev.sisgestionMercados.Output.UserOutput;
import com.dev.sisgestionMercados.Output.WarehouseOutput;
import com.dev.sisgestionMercados.entity.Warehouse;
import com.dev.sisgestionMercados.entity.Role;
import com.dev.sisgestionMercados.service.MarketService;
import com.dev.sisgestionMercados.service.RoleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/market")
public class MarketController {
	
	@Autowired
	private MarketService marketService;
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/createMarket")
	public ResponseEntity<?> save(@RequestBody MarketInput market){
		
		return ResponseEntity.ok(marketService.save(market));
	}
	
	@GetMapping("/allWarehouse")
	public Iterable<WarehouseOutput> getAllWarehouse(){
		
		return marketService.getAllWarehouse();
	}

}
