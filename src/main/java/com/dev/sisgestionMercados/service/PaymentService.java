package com.dev.sisgestionMercados.service;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.sisgestionMercados.entity.Payment;
import com.dev.sisgestionMercados.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PaymentRepository paymentRepository; 
	
	public Payment savePayment (MultipartFile image, Payment payment) {
		Payment p=new Payment();
		
		try {
			p.setImage(image.getBytes());
			p.setBankName(payment.getBankName());
			p.setNameAccount(payment.getNameAccount());
			p.setNroAccount(payment.getNroAccount());
			paymentRepository.save(p);
		} catch (IOException e) {
			
		}
		return p;
	}
	
	public List<Payment> getAllPayments(){
		return paymentRepository.findAll();
	}
	
	public Payment findById(int id) {
		return paymentRepository.findById(id).get();
	}
}
