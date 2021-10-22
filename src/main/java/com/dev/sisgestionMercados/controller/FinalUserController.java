package com.dev.sisgestionMercados.controller;

import javax.annotation.security.PermitAll;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.sisgestionMercados.Input.FinalUserInput;
import com.dev.sisgestionMercados.entity.FinalUser;
import com.dev.sisgestionMercados.entity.Role;
import com.dev.sisgestionMercados.service.FinalUserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/finalUser")
public class FinalUserController {

	@Autowired
	private FinalUserService finalUserService;
	@Autowired
	private ModelMapper modelMapper;
	
	@PermitAll
	@PostMapping("/createFinalUser")
	public ResponseEntity<?> createUser(@RequestBody FinalUser finalUser){
		
		return ResponseEntity.ok(finalUserService.save(finalUser));
	}
	
	@PermitAll
	@PutMapping("/codeVerification")
	public ResponseEntity<?> verify(@RequestBody FinalUserInput finalUser){
		
		return ResponseEntity.ok(finalUserService.verify(finalUser));
	}
	
}
