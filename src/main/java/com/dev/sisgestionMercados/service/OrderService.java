package com.dev.sisgestionMercados.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.Input.OrderDetailInput;
import com.dev.sisgestionMercados.Input.OrderInput;
import com.dev.sisgestionMercados.entity.FinalUser;
import com.dev.sisgestionMercados.entity.OrderDetail;
import com.dev.sisgestionMercados.entity.OrderP;
import com.dev.sisgestionMercados.entity.Product;
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
	
	
	public OrderInput save(OrderInput order, int id) {
		
		OrderP newOrder=new OrderP();
		newOrder.setQuantityProducts(order.getQuantityProducts());
		newOrder.setTotalPrice(order.getTotalPrice());
		newOrder.setOrderDate(LocalDate.now());
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
}

