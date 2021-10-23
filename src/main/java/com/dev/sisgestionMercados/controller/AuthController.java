package com.dev.sisgestionMercados.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.sisgestionMercados.Input.AuthenticationRequest;
import com.dev.sisgestionMercados.Output.AuthenticationResponse;
import com.dev.sisgestionMercados.Output.FinalUserOutput;
import com.dev.sisgestionMercados.config.JWTUtil;
import com.dev.sisgestionMercados.service.AuthUserService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private JWTUtil jwtUtil;

    
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
    		if(userType==2 && authUserService.getTelephone(request.getUsername()).equals(request.getPassword())) {
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
}
