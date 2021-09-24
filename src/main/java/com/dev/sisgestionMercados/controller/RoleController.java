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

import com.dev.sisgestionMercados.Output.RoleOutput;
import com.dev.sisgestionMercados.entity.Role;
import com.dev.sisgestionMercados.service.RoleService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/role")

public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ModelMapper modelMapper;
	
	public RoleController() {
		
	}
	
	@PostMapping("/createRole")
	public ResponseEntity<?> createUser(@RequestBody Role role){
		
		return ResponseEntity.ok(roleService.save(role));
	}
	
	@GetMapping("/allRoles")
	public Iterable<RoleOutput> getAllRoles(){
		
		return roleService.getAllRoles();
	}
}

