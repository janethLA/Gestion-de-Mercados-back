package com.dev.sisgestionMercados.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.Input.ReportOrderInput;
import com.dev.sisgestionMercados.entity.FinalUser;
import com.dev.sisgestionMercados.entity.OrderP;

@Service
public class ReportService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private FinalUserService finalUserService;
	
    public Iterable<ReportOrderInput> reportOfOrders(){
    	
    	Iterable<OrderP> allOrders=orderService.allOrders();
		List<ReportOrderInput> allOrdersByAtributes= new ArrayList<ReportOrderInput>();
		
		for(OrderP p: allOrders) {
			ReportOrderInput newOrder=new ReportOrderInput();
			newOrder.setIdOrder(p.getIdOrder());
			newOrder.setDateOfOrder(p.getOrderDate());
			newOrder.setHourOfOrder(p.getOrderTime());
			newOrder.setStatus(p.getStatus());
			newOrder.setTelephone(p.getFinalUser().getTelephone());
			newOrder.setUserName(p.getFinalUser().getFinalUserName());
			allOrdersByAtributes.add(newOrder);
		}
		
		return allOrdersByAtributes;
	}
	
    public List<Integer> getAllTelephones(){
    	List<Integer> allTelephones=new ArrayList<Integer>();
    	List<FinalUser> allUsers=finalUserService.findAll();
    	
    	for(FinalUser u:allUsers) {
    		if(!u.getOrders().isEmpty()) {
    			allTelephones.add(u.getTelephone());
    		}
    	}
    	return allTelephones;
    }
    
    public List<String> getAllUsers(){
    	List<String> allUser=new ArrayList<String>();
    	List<FinalUser> allUsers=finalUserService.findAll();
    	
    	for(FinalUser u: allUsers) {
    		if(!u.getOrders().isEmpty()) {
    			allUser.add(u.getFinalUserName());
    		}
    	}
    	return allUser;
    }
}
