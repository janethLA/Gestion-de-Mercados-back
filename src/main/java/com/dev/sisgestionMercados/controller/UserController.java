package com.dev.sisgestionMercados.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.sisgestionMercados.Input.UserInput;
import com.dev.sisgestionMercados.Output.SectorOutput;
import com.dev.sisgestionMercados.Output.UserOutput;
import com.dev.sisgestionMercados.repository.UserRepository;
import com.dev.sisgestionMercados.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	
	public UserController() {	
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody UserInput user){
		
		return ResponseEntity.ok(userService.save(user));
	}
	
	@GetMapping("/allUser")
	public Iterable<UserOutput> getAllUser(){
		
		return userService.getAllUser();
	}
	
}

