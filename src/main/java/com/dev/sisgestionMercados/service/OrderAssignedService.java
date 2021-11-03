package com.dev.sisgestionMercados.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.Input.OrderAssignedInput;
import com.dev.sisgestionMercados.Output.AllOrderAssignedOutput;
import com.dev.sisgestionMercados.Output.OrderAssignedOutput;
import com.dev.sisgestionMercados.Output.OrderOutput;
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
	
	public  List<OrderAssignedOutput> allAssignedOrders(int id){
		UserS u=userService.findById(id);
		List<OrderAssigned> allAsignedOrders=u.getOrderAssigned();
		List<OrderAssignedOutput> allAsignedOrdersFound=new ArrayList<OrderAssignedOutput>();
		for (OrderAssigned orderA:allAsignedOrders) {
			OrderAssignedOutput o=new OrderAssignedOutput();
			o.setIdOrderAssigned(orderA.getIdOrderAssigned());
			o.setStatus(orderA.getStatus());
			o.setDate(orderA.getDate());
			o.setHour(orderA.getHour());
			o.setOrder(orderA.getOrderP());
			
			o.setUserName(orderA.getOrderP().getFinalUser().getFinalUserName());
			o.setUserEmail(orderA.getOrderP().getFinalUser().getEmail());
			o.setUserTelephone(orderA.getOrderP().getFinalUser().getTelephone());
			
			o.setWarehouseNameOfOrder(orderA.getOrderP().getOrderDetail().get(0).getProduct().getCategory().getWarehouse().getWarehouseName());
			o.setWarehouseAddressOfOrder(orderA.getOrderP().getOrderDetail().get(0).getProduct().getCategory().getWarehouse().getAddress());
			o.setWarehouseSectorOfOrder(orderA.getOrderP().getOrderDetail().get(0).getProduct().getCategory().getWarehouse().getSector().getSectorName());
			allAsignedOrdersFound.add(o);
		}
		return allAsignedOrdersFound;
	}
	
	public String assignedOrderAccepted(int id) {
		OrderAssigned orderA=orderAssignedRepository.findById(id).get();
		orderA.setStatus("Aceptado");
		OrderP o=orderA.getOrderP();
		o.setStatus("Enviado");
		orderAssignedRepository.save(orderA);
		orderService.save2(o);
		return "Se ha aceptado el pedido asignado";
	}
	
	public String assignedOrderRejected(int id) {
		OrderAssigned orderA=orderAssignedRepository.findById(id).get();
		orderA.setStatus("Rechazado");
		orderAssignedRepository.save(orderA);

		return "Se ha rechazado el pedido asignado";
	}
	
	public String orderCompleted(int id) {
		OrderAssigned orderA=orderAssignedRepository.findById(id).get();
		orderA.setStatus("Finalizado");
		OrderP o=orderA.getOrderP();
		o.setStatus("Finalizado");
		orderAssignedRepository.save(orderA);
		orderService.save2(o);
		return "Se ha finalizado el pedido asignado";
	}
	
	public  List<AllOrderAssignedOutput> getAllAssignedOrders(){
		
		List<AllOrderAssignedOutput> AllOrderAssigned=new ArrayList<AllOrderAssignedOutput>();
		List<OrderAssigned> orders= orderAssignedRepository.findAll();
		for(OrderAssigned o: orders) {
			AllOrderAssignedOutput newOrderA=new AllOrderAssignedOutput();
			newOrderA.setIdOrderAssigned(o.getIdOrderAssigned());
			newOrderA.setDate(o.getDate());
			newOrderA.setHour(o.getHour());
			newOrderA.setStatus(o.getStatus());
			newOrderA.setIdOrder(o.getOrderP().getIdOrder());
			 AllOrderAssigned.add(newOrderA);
		}
		return  AllOrderAssigned;
	}
}

