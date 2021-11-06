package com.dev.sisgestionMercados.controller;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.sisgestionMercados.entity.Payment;
import com.dev.sisgestionMercados.service.PaymentService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private ModelMapper modelMapper;
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")	
	@PostMapping("/createPayment")
	public ResponseEntity<?> savePayment( @RequestParam("qr") MultipartFile image,@ModelAttribute Payment payment) {
		//System.out.println("NOMBRE: " +image.getOriginalFilename());
		//System.out.println("bytes: " +image.getBytes());
		Payment p=paymentService.savePayment(image,payment);
		if(p!=null) {
			return ResponseEntity.ok(p);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	 
	}
	
	@PreAuthorize("hasRole('ADMINISTRAR_PEDIDOS')")	
	@GetMapping("/allPayments")
	public ResponseEntity<?> getAllPayment(){
		
		return ResponseEntity.ok(paymentService.getAllPayments());
	}
}

