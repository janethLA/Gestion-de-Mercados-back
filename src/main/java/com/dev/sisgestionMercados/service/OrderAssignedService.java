package com.dev.sisgestionMercados.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.Input.OrderAssignedInput;
import com.dev.sisgestionMercados.entity.OrderAssigned;
import com.dev.sisgestionMercados.entity.OrderP;
import com.dev.sisgestionMercados.entity.UserS;
import com.dev.sisgestionMercados.repository.OrderAssignedRepository;
import com.dev.sisgestionMercados.repository.OrderRepository;

@Service
public class OrderAssignedService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OrderAssignedRepository orderAssignedRepository;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderRepository orderRepository;

	public String save(OrderAssignedInput orderAssigned) {
		OrderP o=orderService.findById(orderAssigned.getIdOrder());
		UserS u=userService.findById(orderAssigned.getIdUser());
		OrderAssigned newOrderAssigned=new OrderAssigned();
		newOrderAssigned.setStatus("Pendiente");
		newOrderAssigned.setDate(LocalDate.now());
		newOrderAssigned.setHour(LocalTime.now());
		newOrderAssigned.setOrderP(o);
		newOrderAssigned.setUserS(u);
		o.setStatus("En curso");
		orderAssignedRepository.save(newOrderAssigned);
		orderRepository.save(o);
		return "Delivery asignado correctamente";
	}
}
