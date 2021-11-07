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
import com.dev.sisgestionMercados.Output.OrderByUserOutput;
import com.dev.sisgestionMercados.Output.OrderOutput;
import com.dev.sisgestionMercados.Output.PrivilegeOutput;
import com.dev.sisgestionMercados.entity.FinalUser;
import com.dev.sisgestionMercados.entity.OrderAssigned;
import com.dev.sisgestionMercados.entity.OrderDetail;
import com.dev.sisgestionMercados.entity.OrderP;
import com.dev.sisgestionMercados.entity.Payment;
import com.dev.sisgestionMercados.entity.Privilege;
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
	
	public Iterable<OrderByUserOutput> allOrderByUser(long id) {
		
		FinalUser finalUser=finalUserService.findById(id);
		List<OrderP> allOrderByUser=finalUser.getOrders();
        List<OrderByUserOutput> orderByUserOutput=new ArrayList<OrderByUserOutput>();
		for(OrderP o:allOrderByUser) {
			
			if(o.getStatus().equalsIgnoreCase("Pendiente")) {
				OrderByUserOutput order=new OrderByUserOutput();
				order.setIdOrder(o.getIdOrder());
				order.setOrderTime(o.getOrderTime());
				order.setOrderDate(o.getOrderDate());
				order.setShippingCost(o.getShippingCost());
				order.setStatus(o.getStatus());
				order.setTotalPrice(o.getTotalPrice());
				order.setQuantityProducts(o.getQuantityProducts());
				order.setOrderDetail(o.getOrderDetail());
				
				/*PARA QUE EL USUARIO SE PONGA EN CONTACTO CON EL ADMIN , TAL VEZ PONER UN CONTACTO POR DEFECTO
			    order.setAdminName(admin.getName());
				order.setAdminTelephone(admin.getTelephone());
				order.setAdminEmail(admin.getEmail());
				order.setAdminWhatsappLink(admin.getWhatsappLink());
				 */
				
				orderByUserOutput.add(order);
				
			}else {
				OrderByUserOutput order=new OrderByUserOutput();
				order.setIdOrder(o.getIdOrder());
				order.setOrderTime(o.getOrderTime());
				order.setOrderDate(o.getOrderDate());
				order.setShippingCost(o.getShippingCost());
				order.setStatus(o.getStatus());
				order.setTotalPrice(o.getTotalPrice());
				order.setQuantityProducts(o.getQuantityProducts());
				order.setOrderDetail(o.getOrderDetail());
				
				int idAdmin=o.getOrderAssigned().get(o.getOrderAssigned().size()-1).getIdUserCallCenter();
				UserS admin=userService.findById(idAdmin);
				order.setAdminName(admin.getName());
				order.setAdminTelephone(admin.getTelephone());
				order.setAdminEmail(admin.getEmail());
				order.setAdminWhatsappLink(admin.getWhatsappLink());
				
				if(o.getOrderAssigned().get(o.getOrderAssigned().size()-1).getPayment()!=null) {
					Payment payment=o.getOrderAssigned().get(o.getOrderAssigned().size()-1).getPayment();
					order.setNroAccount(payment.getNroAccount());
					order.setBankName(payment.getBankName());
					order.setNameAccount(payment.getNameAccount());
					order.setQr(payment.getImage());
				}
				
				orderByUserOutput.add(order);
			}
			
			
		}
		return orderByUserOutput;
	}
	
    public Iterable<OrderP> allOrders(){
		
		return orderRepository.findAll();
	}
    public Iterable<OrderOutput> allOrders2(int id){
    
    	List<OrderP> orders=orderRepository.findAll();
		List<OrderOutput> allOrders=new ArrayList<OrderOutput>();
		for(OrderP o: orders) {
		
			if(o.getStatus().equalsIgnoreCase("Pendiente") || o.getStatus().equalsIgnoreCase("Cancelado") ) {
				
				OrderOutput order=new OrderOutput();
				order.setIdOrder(o.getIdOrder());
				order.setOrderDate(o.getOrderDate());
				order.setOrderTime(o.getOrderTime());
				order.setTotalPrice(o.getTotalPrice());
				order.setQuantityProducts(o.getQuantityProducts());
				order.setStatus(o.getStatus());
				order.setOrderDetail(o.getOrderDetail());
			
				order.setFinalUserName(o.getFinalUser().getFinalUserName());
				order.setFinalUserTelephone(o.getFinalUser().getTelephone());
				order.setFinalUserWhatsappLink(o.getFinalUser().getWhatsappLink());
				order.setFinalUserEmail(o.getFinalUser().getEmail());
				
				try {
					order.setShippingCost(o.getShippingCost());
				} catch (Exception e) {
					// TODO: handle exception
				}
				order.setWarehouseName(o.getOrderDetail().get(0).getProduct().getCategory().getWarehouse().getWarehouseName());
				order.setSectorName(o.getOrderDetail().get(0).getProduct().getCategory().getWarehouse().getSector().getSectorName());
				allOrders.add(order);
			}else {
				if(o.getOrderAssigned().get(o.getOrderAssigned().size()-1).getIdUserCallCenter() == id) {
					OrderOutput order=new OrderOutput();
					order.setIdOrder(o.getIdOrder());
					order.setOrderDate(o.getOrderDate());
					order.setOrderTime(o.getOrderTime());
					order.setTotalPrice(o.getTotalPrice());
					order.setQuantityProducts(o.getQuantityProducts());
					order.setStatus(o.getStatus());
					order.setOrderDetail(o.getOrderDetail());
					
					order.setFinalUserName(o.getFinalUser().getFinalUserName());
					order.setFinalUserTelephone(o.getFinalUser().getTelephone());
					order.setFinalUserWhatsappLink(o.getFinalUser().getWhatsappLink());
					order.setFinalUserEmail(o.getFinalUser().getEmail());
					
					UserS delivery=o.getOrderAssigned().get(o.getOrderAssigned().size()-1).getUserS();
					order.setDeliveryName(delivery.getName());
					order.setDeliveryTelephone(delivery.getTelephone());
					order.setDeliveryWhatsappLink(delivery.getWhatsappLink());
					order.setDeliveryEmail(delivery.getEmail());
					
					try {
						order.setShippingCost(o.getShippingCost());
					} catch (Exception e) {
						// TODO: handle exception
					}
					order.setWarehouseName(o.getOrderDetail().get(0).getProduct().getCategory().getWarehouse().getWarehouseName());
					order.setSectorName(o.getOrderDetail().get(0).getProduct().getCategory().getWarehouse().getSector().getSectorName());
					allOrders.add(order);
				}
			}
			
		}
		
		return allOrders;
	}
    
    public List<DeliveryUserOutput> getAllDeliveries(){
    	
    	List<UserS> allUsers=userService.findAll();
    	List<DeliveryUserOutput> allDeliveries=new ArrayList<DeliveryUserOutput>();
    	for(UserS s: allUsers) {
    		List<Privilege> privilege=s.getUserRole().get(0).getRole().getPrivileges();
    		for(Privilege p :privilege) {
    			if(p.getPrivilege().equalsIgnoreCase("ROLE_VER_PEDIDOS")) {
        			DeliveryUserOutput delivery=new DeliveryUserOutput();
        			delivery.setIdUser(s.getIdUser());
        			delivery.setName(s.getName());
        			delivery.setTelephone(s.getTelephone());
        			delivery.setEmail(s.getEmail());
        			delivery.setSector(s.getUserRole().get(0).getSector().getSectorName());
        			System.out.println("Nombre delivery: "+s.getName());
        			allDeliveries.add(delivery);
        		}
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
    
    public String orderCanceled(int id) {
		OrderP o=orderRepository.findById(id).get();
		o.setStatus("Cancelado");
		o.setTotalPrice(0);
		o.setShippingCost(0);
		List<OrderDetail> orderDetails=o.getOrderDetail();
		for(OrderDetail od: orderDetails) {
			od.setSubtotal(0);
		}
		o.setOrderDetail(orderDetails); 
		orderRepository.save(o);
		return "Se ha cancelado el pedido";
	}
    
    public String orderSent(int id) {
    	OrderP order=orderRepository.findById(id).get();
		OrderAssigned orderA=order.getOrderAssigned().get(order.getOrderAssigned().size()-1);
		order.setStatus("Enviado");
		orderA.setStatus("Aceptado");
		orderRepository.save(order);
		orderAssignedService.save2(orderA);
		return "Se cambio el estado a Enviado";
    }
    
    public String cancelOrderInProgressAndSent(int id) {
  		OrderP o=orderRepository.findById(id).get();
  		o.setStatus("Cancelado");
  		o.setTotalPrice(0);o.setShippingCost(0);
		List<OrderDetail> orderDetails=o.getOrderDetail();
		for(OrderDetail od: orderDetails) {
			od.setSubtotal(0);
		}
		o.setOrderDetail(orderDetails); 
  		OrderAssigned orderA=o.getOrderAssigned().get(o.getOrderAssigned().size()-1);
  		orderA.setStatus("Cancelado");
  		orderRepository.save(o);
  		orderAssignedService.save2(orderA);
  		return "Se ha cancelado el pedido que estaba en curso";
  	}
    
    public String finalizeOrder(int id) {
    	OrderP order=orderRepository.findById(id).get();
		OrderAssigned orderA=order.getOrderAssigned().get(order.getOrderAssigned().size()-1);
		order.setStatus("Finalizado");
		orderA.setStatus("Finalizado");
		orderRepository.save(order);
		orderAssignedService.save2(orderA);
		return "Se cambio el estado a Finalizado";
    }
    
    public String reassignOrderInProgress(int id) {
		OrderP order=orderRepository.findById(id).get();
		OrderAssigned orderA=order.getOrderAssigned().get(order.getOrderAssigned().size()-1);
		System.out.println("Order size: "+order.getOrderAssigned().size() +" y el id ultimo del pedido asignado: "+orderA.getIdOrderAssigned());
		//System.out.println("Order ultimo: "+order.getOrderAssigned().get(4).getIdOrderAssigned());
		orderA.setReassigned(true);
		order.setStatus("Pendiente");
		orderA.setStatus("Rechazado");
		orderRepository.save(order);
		orderAssignedService.save2(orderA);
		return "Vuelve a reasignar el pedido";
	}
    
}

