package com.dev.sisgestionMercados.controller;

import java.util.List;

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
	
	@PreAuthorize("hasRole('ADMINISTRAR_USUARIOS')")	
	@PostMapping("/createRole")
	public ResponseEntity<?> createUser(@RequestBody Role role){
		
		return ResponseEntity.ok(roleService.save(role));
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_USUARIOS')")	
	@GetMapping("/allRoles")
	public Iterable<RoleOutput> getAllRoles(){
		
		return roleService.getAllRoles();
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_USUARIOS')")	
	@GetMapping("/uniqueRoleName/{roleName}")
	public ResponseEntity<?> uniqueSectorName(@PathVariable String roleName){
		
		return ResponseEntity.ok(roleService.noExistsRoleName(roleName));
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_USUARIOS')")	
	@PutMapping("/addPrivileges/{id}")
	public ResponseEntity<?> addPrivileges(@PathVariable Integer id, @RequestBody List<String> privileges){
		
		return ResponseEntity.ok(roleService.addPrivileges(id, privileges));
		
	}
}

