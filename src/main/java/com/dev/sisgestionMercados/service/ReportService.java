package com.dev.sisgestionMercados.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.sisgestionMercados.Input.ReportOrderInput;
import com.dev.sisgestionMercados.entity.FinalUser;
import com.dev.sisgestionMercados.entity.OrderP;
import com.dev.sisgestionMercados.entity.Report;
import com.dev.sisgestionMercados.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private FinalUserService finalUserService;
	@Autowired
	private ReportRepository reportRepository;
	
    public Iterable<ReportOrderInput> reportOfOrders(){
    	
    	Iterable<OrderP> allOrders=orderService.allOrders();
		List<ReportOrderInput> allOrdersByAtributes= new ArrayList<ReportOrderInput>();
		
		for(OrderP p: allOrders) {
			ReportOrderInput newOrder=new ReportOrderInput();
			newOrder.setIdOrder(p.getIdOrder());
			newOrder.setDateOfOrder(p.getOrderDate());
			newOrder.setHourOfOrder(p.getOrderTime());
			newOrder.setStatus(p.getStatus());
			newOrder.setShippingCost(p.getShippingCost());
			
			if(!p.getOrderAssigned().isEmpty()){
				newOrder.setDelivery(p.getOrderAssigned().get(p.getOrderAssigned().size()-1).getUserS().getName());
			}
			newOrder.setQuantityProducts(p.getQuantityProducts());
			newOrder.setTotalPrice(p.getTotalPrice());
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
    
    public Report saveReport(MultipartFile file) {
    	Report report=new Report();
    	try {
			report.setReport(file.getBytes());
			report.setReportName(file.getOriginalFilename());
			reportRepository.save(report);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return report;	
    }
    
}
