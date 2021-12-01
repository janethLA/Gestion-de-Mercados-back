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

import com.dev.sisgestionMercados.Input.LocationInput;
import com.dev.sisgestionMercados.Input.MarketInput;
import com.dev.sisgestionMercados.Output.ProductSearch;
import com.dev.sisgestionMercados.Output.UserOutput;
import com.dev.sisgestionMercados.Output.WarehouseOutput;
import com.dev.sisgestionMercados.Output.WarehouseSearch;
import com.dev.sisgestionMercados.Output.WarehouseSearchByAtributes;
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
	
	@PreAuthorize("hasRole('ADMINISTRAR_ALMACENES')")	
	@PostMapping("/createMarket")
	public ResponseEntity<?> save(@ModelAttribute  MarketInput market, @RequestParam("image") MultipartFile image){
		
		return ResponseEntity.ok(marketService.save(market,image));
	}
	
	@PermitAll
	@GetMapping("/allWarehouse")
	public Iterable<WarehouseOutput> getAllWarehouse(){
		
		return marketService.getAllWarehouse();
	}
	
	@PermitAll
	@GetMapping("/warehouseSearch/{sectorName}")
	public Iterable<WarehouseSearchByAtributes> getAllWarehouseSearchBySearch(@PathVariable String sectorName ){
		
		return marketService.getAllWarehouseSearchBySector(sectorName);
	}
	
	@PermitAll
	@PostMapping("/warehouseSearch")
	public ResponseEntity<?> getAllWarehouseSearchByLocation(@RequestBody LocationInput location){
		
		return  ResponseEntity.ok(marketService.getAllWarehouseSearchByLocation(location));
	}
    
	@PermitAll
	@GetMapping("/searchOfAllWarehouses")
	public Iterable<WarehouseSearchByAtributes> getSearchOfAllWarehouses(){
		
		return marketService.getSearchOfAllWarehouses();
	}
}
