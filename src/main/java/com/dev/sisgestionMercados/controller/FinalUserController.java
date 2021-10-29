package com.dev.sisgestionMercados.controller;

import javax.annotation.security.PermitAll;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.dev.sisgestionMercados.Input.AuthUserFinal;
import com.dev.sisgestionMercados.Input.FinalUserInput;
import com.dev.sisgestionMercados.Output.FinalUserAtributesOutput;
import com.dev.sisgestionMercados.Output.FinalUserOutput;
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
	
	@PermitAll
	@PostMapping("/loginUserFinal")
	public ResponseEntity<?> authUser(@RequestBody AuthUserFinal authUserFinal){
		FinalUserOutput authUser=finalUserService.authUser(authUserFinal);
		if(authUser!=null) {
		    return ResponseEntity.ok(authUser);
		}else{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@PreAuthorize("hasRole('FINAL_USER')")
	@GetMapping("/userFinalData/{id}")
	public ResponseEntity<?> getFinalUser(@PathVariable Integer id){
		
		return ResponseEntity.ok(finalUserService.getFinalUser(id));
	}
	
	@PreAuthorize("hasRole('FINAL_USER')")
	@PutMapping("/updateDataUser/{id}")
	public ResponseEntity<?> updateDataUser(@RequestBody AuthUserFinal finalUser,@PathVariable long id){
		
		return ResponseEntity.ok(finalUserService.updateDataUser(finalUser,id));
	}
	
	@PreAuthorize("hasRole('FINAL_USER')")
	@GetMapping("/uniqueUserName/{userName}")
	public ResponseEntity<?> uniqueUserName(@PathVariable String userName){
		
		return ResponseEntity.ok(finalUserService.noExistsUserName(userName));
	}
	
	@PreAuthorize("hasRole('FINAL_USER')")
	@PutMapping("/updateTelephone")
	public ResponseEntity<?> verifyTelephone(@RequestBody FinalUserAtributesOutput finalUser){
		
		return ResponseEntity.ok(finalUserService.verifyTelephone(finalUser));
	}
	
	@PermitAll
	@GetMapping("/uniqueEmail/{email}")
	public ResponseEntity<?> uniqueEmail(@PathVariable String email){
		
		return ResponseEntity.ok(finalUserService.noExistsEmail(email));
	}
}
