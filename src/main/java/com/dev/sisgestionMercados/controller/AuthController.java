package com.dev.sisgestionMercados.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.sisgestionMercados.Input.AuthenticationRequest;
import com.dev.sisgestionMercados.Input.UserPasswordInput;
import com.dev.sisgestionMercados.Output.AuthenticationResponse;
import com.dev.sisgestionMercados.Output.FinalUserAtributesOutput;
import com.dev.sisgestionMercados.Output.FinalUserOutput;
import com.dev.sisgestionMercados.Output.UserPasswordOutput;
import com.dev.sisgestionMercados.config.JWTUtil;
import com.dev.sisgestionMercados.service.AuthUserService;
import com.dev.sisgestionMercados.service.FinalUserService;
import com.dev.sisgestionMercados.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUserService authUserService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private FinalUserService finalUserService;
    
    @PostMapping("/authenticate")
    public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest request) {
    	int userType=authUserService.getUserType(request.getUsername());
    	if(userType==1) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
                UserDetails userDetails = authUserService.loadUserByUsername(request.getUsername());
                String jwt = jwtUtil.generateToken(userDetails);
            	Collection<? extends GrantedAuthority> roles=userDetails.getAuthorities();
            		return new ResponseEntity<>(new AuthenticationResponse(jwt,roles,authUserService.getIdUser(request.getUsername()),
    						authUserService.getNameUser(request.getUsername()) , authUserService.getName(request.getUsername())), HttpStatus.OK);
    	}else {
    		if(userType==2 && authUserService.getPassword(request.getUsername()).equals(request.getPassword())) {
                   // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
                    UserDetails userDetails = authUserService.loadUserByUsername(request.getUsername());
                    String jwt = jwtUtil.generateToken(userDetails);
                	Collection<? extends GrantedAuthority> roles=userDetails.getAuthorities();
                	return new ResponseEntity<>(new FinalUserOutput(jwt,roles,authUserService.getIdFinalUser(request.getUsername()),
    						authUserService.getFinalNameUser(request.getUsername()) , authUserService.getFinalName(request.getUsername())), HttpStatus.OK);
        	}else {
        		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        	}
    	}
    	
    }
    
    @PermitAll
	@GetMapping("/loginAccount/{userName}")
	public ResponseEntity<?> getloginAccount(@PathVariable String userName){
    	FinalUserAtributesOutput u=userService.getloginAccount(userName);
		if(u!=null) {
			return ResponseEntity.ok(userService.getloginAccount(userName));
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
    
    @PermitAll
	@GetMapping("/uniqueEmailAll/{email}")
	public ResponseEntity<?> uniqueEmail(@PathVariable String email){
		
		return ResponseEntity.ok(finalUserService.noExistsEmailAll(email));
	}
    
    @PermitAll
	@GetMapping("/uniqueTelephoneAll/{telephone}")
	public ResponseEntity<?> uniqueTelephone(@PathVariable Integer telephone){
		
		return ResponseEntity.ok(finalUserService.noExistsTelephone(telephone));
	}
    
    @PermitAll
   	@GetMapping("/recoverByPhone/{telephone}")
   	public ResponseEntity<?> recoverByPhone(@PathVariable Integer telephone){
    	UserPasswordOutput u=userService.recoverByPhone(telephone);
		if(u!=null) {
			return ResponseEntity.ok(u);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
 
   	}
    
    @PermitAll
   	@PutMapping("/changePassword")
   	public ResponseEntity<?> changePassword(@RequestBody UserPasswordInput user){
   		
   		return ResponseEntity.ok(userService.changePassword(user));
   	}
}
