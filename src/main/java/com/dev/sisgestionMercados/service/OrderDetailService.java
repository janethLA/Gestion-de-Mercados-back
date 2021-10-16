package com.dev.sisgestionMercados.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.sisgestionMercados.entity.OrderDetail;
import com.dev.sisgestionMercados.entity.Role;
import com.dev.sisgestionMercados.repository.OrderDetailRepository;

@Service
public class OrderDetailService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	public OrderDetail findById(int idOrderDetail) {
		return orderDetailRepository.findById(idOrderDetail).get();
	}
	
	@Transactional
	public OrderDetail save(OrderDetail orderDetail) {
	     return orderDetailRepository.save(orderDetail);
	}
}
