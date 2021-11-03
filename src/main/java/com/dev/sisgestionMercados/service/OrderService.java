package com.dev.sisgestionMercados.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.Input.OrderDetailInput;
import com.dev.sisgestionMercados.Input.OrderInput;
import com.dev.sisgestionMercados.Output.DeliveryUserOutput;
import com.dev.sisgestionMercados.Output.OrderOutput;
import com.dev.sisgestionMercados.Output.PrivilegeOutput;
import com.dev.sisgestionMercados.entity.FinalUser;
import com.dev.sisgestionMercados.entity.OrderAssigned;
import com.dev.sisgestionMercados.entity.OrderDetail;
import com.dev.sisgestionMercados.entity.OrderP;
import com.dev.sisgestionMercados.entity.Product;
import com.dev.sisgestionMercados.entity.UserS;
import com.dev.sisgestionMercados.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private FinalUserService finalUserService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderAssignedService orderAssignedService;
	
	
	public void save2(OrderP order) {
		orderRepository.save(order);
	}
	public OrderInput save(OrderInput order, int id) {
		
		OrderP newOrder=new OrderP();
		newOrder.setQuantityProducts(order.getQuantityProducts());
		newOrder.setTotalPrice(order.getTotalPrice());
		newOrder.setOrderDate(LocalDate.now());
		newOrder.setOrderTime(LocalTime.now());
		newOrder.setStatus("Pendiente");
		OrderP savedOrder=orderRepository.save(newOrder);
		FinalUser finalUser=finalUserService.findById(id);
		savedOrder.setFinalUser(finalUser);
		
		List<OrderDetailInput> orderDetails= order.getOrderDetails();
		List<OrderDetail> savedOrderDetails=new ArrayList<OrderDetail>();
		for(OrderDetailInput orderDetail: orderDetails) {
			OrderDetail newOrderDetail=new OrderDetail();
			Product foundProduct=productService.findById(orderDetail.getIdProduct());
			newOrderDetail.setUnits(orderDetail.getUnits());
			newOrderDetail.setSubtotal(orderDetail.getSubtotal());
			newOrderDetail.setOrder(savedOrder);
			newOrderDetail.setProduct(foundProduct);
			OrderDetail savedOrderDetail=orderDetailService.save(newOrderDetail);
			savedOrderDetails.add(savedOrderDetail);
		}
		savedOrder.setOrderDetail(savedOrderDetails);
		
		return order;
	}
	
	public Iterable<OrderP> allOrderByUser(long id) {
		
		FinalUser finalUser=finalUserService.findById(id);
		List<OrderP> allOrderByUser=finalUser.getOrders();
		
		return allOrderByUser;
	}
	
    public Iterable<OrderP> allOrders(){
		
		return orderRepository.findAll();
	}
    public Iterable<OrderOutput> allOrders2(){
    	List<OrderP> orders=orderRepository.findAll();
		List<OrderOutput> allOrders=new ArrayList<OrderOutput>();
		
		for(OrderP o: orders) {
			OrderOutput order=new OrderOutput();
			order.setIdOrder(o.getIdOrder());
			order.setOrderDate(o.getOrderDate());
			order.setOrderTime(o.getOrderTime());
			order.setTotalPrice(o.getTotalPrice());
			order.setQuantityProducts(o.getQuantityProducts());
			order.setStatus(o.getStatus());
			order.setOrderDetail(o.getOrderDetail());
			order.setUserName(o.getFinalUser().getFinalUserName());
			order.setTelephone(o.getFinalUser().getTelephone());
			order.setEmail(o.getFinalUser().getEmail());
			order.setWarehouseName(o.getOrderDetail().get(0).getProduct().getCategory().getWarehouse().getWarehouseName());
			order.setSectorName(o.getOrderDetail().get(0).getProduct().getCategory().getWarehouse().getSector().getSectorName());
			allOrders.add(order);
		}
		
		return allOrders;
	}
    
    public List<DeliveryUserOutput> getAllDeliveries(){
    	
    	List<UserS> allUsers=userService.findAll();
    	List<DeliveryUserOutput> allDeliveries=new ArrayList<DeliveryUserOutput>();
    	for(UserS s: allUsers) {
    		if(s.getUserRole().get(0).getRole().getPrivileges().get(0).getPrivilege().equalsIgnoreCase("ROLE_VER_PEDIDOS")) {
    			DeliveryUserOutput delivery=new DeliveryUserOutput();
    			delivery.setIdUser(s.getIdUser());
    			delivery.setName(s.getName());
    			delivery.setTelephone(s.getTelephone());
    			delivery.setEmail(s.getEmail());
    			delivery.setSector(s.getUserRole().get(0).getSector().getSectorName());
    			allDeliveries.add(delivery);
    		}
    	}
    	return allDeliveries;
    }
    
    public OrderP findById(int id) {
    	return orderRepository.findById(id).get();
    }
    
    public String reassignOrder(int id) {
		OrderP order=orderRepository.findById(id).get();
		OrderAssigned orderA=order.getOrderAssigned().get(order.getOrderAssigned().size()-1);
		orderA.setReassigned(true);
		order.setStatus("Pendiente");
		orderRepository.save(order);
		orderAssignedService.save2(orderA);
		return "Vuelve a reasignar el pedido";
	}
    
    
}

