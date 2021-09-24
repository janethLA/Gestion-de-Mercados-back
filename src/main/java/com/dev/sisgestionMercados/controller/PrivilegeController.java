package com.dev.sisgestionMercados.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.sisgestionMercados.Output.PrivilegeOutput;
import com.dev.sisgestionMercados.service.PrivilegeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/privilege")
public class PrivilegeController {

	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private ModelMapper modelMapper;
		
	@GetMapping("/allPrivileges")
	public Iterable<PrivilegeOutput> getAllPrivileges(){
		
		return privilegeService.getAllPrivileges();
	}
}
