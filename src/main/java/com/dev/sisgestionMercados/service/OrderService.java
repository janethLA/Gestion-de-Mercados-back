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
import com.dev.sisgestionMercados.Output.OrderToCollectDelivery;
import com.dev.sisgestionMercados.Output.OrderToPayBuyerOutput;
import com.dev.sisgestionMercados.Output.OrderToPayOutput;
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
		newOrder.setSubstate("por pagar");
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
			
			if(o.getStatus().equalsIgnoreCase("Pendiente")|| o.getStatus().equalsIgnoreCase("Cancelado")) {
				OrderByUserOutput order=new OrderByUserOutput();
				order.setIdOrder(o.getIdOrder());
				order.setOrderTime(o.getOrderTime());
				order.setOrderDate(o.getOrderDate());
				order.setShippingCost(o.getShippingCost());
				order.setStatus(o.getStatus());
				order.setTotalPrice(o.getTotalPrice());
				order.setQuantityProducts(o.getQuantityProducts());
				order.setOrderDetail(o.getOrderDetail());
				order.setWarehouseName(o.getOrderDetail().get(0).getProduct().getCategory().getWarehouse().getWarehouseName());	
				order.setSectorName(o.getOrderDetail().get(0).getProduct().getCategory().getWarehouse().getSector().getSectorName());			
				
				//PARA QUE EL USUARIO SE PONGA EN CONTACTO CON EL ADMIN , TAL VEZ PONER UN CONTACTO POR DEFECTO
				
				UserS userAdmin=getUserAdmin();
			    order.setAdminName(userAdmin.getName());
				order.setAdminTelephone(userAdmin.getTelephone());
				order.setAdminEmail(userAdmin.getEmail());
				order.setAdminWhatsappLink(userAdmin.getWhatsappLink());
				 
				
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
				order.setWarehouseName(o.getOrderDetail().get(0).getProduct().getCategory().getWarehouse().getWarehouseName());	
				order.setSectorName(o.getOrderDetail().get(0).getProduct().getCategory().getWarehouse().getSector().getSectorName());			
				
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
	
	private UserS getUserAdmin() {
		List<UserS> allUsers=userService.findAll();
    	UserS userAdmin=new UserS();
    	int cont=0;
    	for(int i=0;i< allUsers.size();i++) {
    		UserS s=allUsers.get(i);
    		System.out.println("Nombre ADMIN ANTES"+ s.getName());
    		List<Privilege> privilege=s.getUserRole().get(0).getRole().getPrivileges();
    		for(Privilege p :privilege) {
    			if(p.getPrivilege().equalsIgnoreCase("ROLE_ADMINISTRAR_PEDIDOS") ) {
    				cont++;
    				if(cont==2 && s.isActive()) {
    					userAdmin=s;
    				}
    				if(cont==2 && !s.isActive()) {
    					cont--;
    				}				
        		}
    		}	
    	}
    	return userAdmin;
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
				order.setSubstate(o.getSubstate());
				if(!o.getOrderAssigned().isEmpty()) {
					order.setReassigned(o.getOrderAssigned().get(0).isReassigned());
					order.setIdPayment(o.getOrderAssigned().get(0).getPayment().getIdPayment());
					order.setBuyerCost(o.getOrderAssigned().get(0).getBuyerCost());
					order.setDeliveryCost(o.getOrderAssigned().get(0).getDeliveryCost());
					order.setShippingCost(o.getShippingCost());
					order.setIdUserOfBuyer(o.getOrderAssigned().get(0).getIdUserOfBuyer());
					// TAL VEZ SE PODRIA MOSTRAR EN LOS ESTADOS PENDIENTES que hayan sido reasignados por lo menos una vez el contacto con el comprador:
					int idBuyer=o.getOrderAssigned().get(0).getIdUserOfBuyer();
					UserS buyer=userService.findById(idBuyer);
					order.setBuyerName(buyer.getName());
					order.setBuyerEmail(buyer.getEmail());
					order.setBuyerTelephone(buyer.getTelephone());
					order.setBuyerWhatsappLink(buyer.getWhatsappLink());
				}
			
				order.setFinalUserName(o.getFinalUser().getFinalUserName());
				order.setFinalUserTelephone(o.getFinalUser().getTelephone());
				order.setFinalUserWhatsappLink(o.getFinalUser().getWhatsappLink());
		
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
					order.setReassigned(o.getOrderAssigned().get(0).isReassigned());
					order.setSubstate(o.getSubstate());
					
					order.setFinalUserName(o.getFinalUser().getFinalUserName());
					order.setFinalUserTelephone(o.getFinalUser().getTelephone());
					order.setFinalUserWhatsappLink(o.getFinalUser().getWhatsappLink());
					
					UserS delivery=o.getOrderAssigned().get(o.getOrderAssigned().size()-1).getUserS();
					order.setDeliveryName(delivery.getName());
					order.setDeliveryTelephone(delivery.getTelephone());
					order.setDeliveryWhatsappLink(delivery.getWhatsappLink());
					order.setDeliveryEmail(delivery.getEmail());
					
					int idBuyer=o.getOrderAssigned().get(0).getIdUserOfBuyer();
					UserS buyer=userService.findById(idBuyer);
					order.setBuyerName(buyer.getName());
					order.setBuyerEmail(buyer.getEmail());
					order.setBuyerTelephone(buyer.getTelephone());
					order.setBuyerWhatsappLink(buyer.getWhatsappLink());
					
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
		order.setStatus("Enviando");
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
		//System.out.println("Order size: "+order.getOrderAssigned().size() +" y el id ultimo del pedido asignado: "+orderA.getIdOrderAssigned());
		orderA.setReassigned(true);
		order.setStatus("Pendiente");
		orderA.setStatus("Rechazado");
		orderRepository.save(order);
		orderAssignedService.save2(orderA);
		return "Vuelve a reasignar el pedido";
	}
    
    public List<OrderToPayOutput>  allOrdersCompleted() {
    	List<OrderP> allOrders=orderRepository.findAll();
    	List<OrderToPayOutput> allOrdersCompleted=new ArrayList<OrderToPayOutput>();
    	
    	for(OrderP o:allOrders) {
    		if(o.getStatus().equals("Finalizado")) {
    			OrderAssigned orderA=o.getOrderAssigned().get(o.getOrderAssigned().size()-1);
        		if(  orderA.getStatus().equals("Finalizado")|| orderA.getStatus().equals("Pagado")) {
        			OrderToPayOutput order=new OrderToPayOutput() ;
        			order.setIdOrder(o.getIdOrder());
        			order.setIdDelivery(orderA.getUserS().getIdUser());
        			order.setDelivery(orderA.getUserS().getName());
        			order.setDateOfOrderAssigned(orderA.getDate());
        			order.setDeliveryCost(orderA.getDeliveryCost());
        			order.setStatus(o.getStatus());
        			if(orderA.getStatus().equals("Finalizado")) {
        				order.setPaymentStatusToDelivery("Por pagar");
        			}else {
        				order.setPaymentStatusToDelivery(orderA.getStatus());
        			}
        			
        			allOrdersCompleted.add(order);
        		}
    		}
    	
    	}
    	return allOrdersCompleted;
    }
    
    public List<DeliveryUserOutput> getAllBuyers(){
    	
    	List<UserS> allUsers=userService.findAll();
    	List<DeliveryUserOutput> allDeliveries=new ArrayList<DeliveryUserOutput>();
    	for(UserS s: allUsers) {
    		if(!s.getUserRole().get(0).getRole().getRoleName().equalsIgnoreCase("ADMIN")) {
    			List<Privilege> privilege=s.getUserRole().get(0).getRole().getPrivileges();
        		for(Privilege p :privilege) {
        			if(p.getPrivilege().equalsIgnoreCase("ROLE_ACTUALIZAR_PRECIOS") ) {
            			DeliveryUserOutput buyer=new DeliveryUserOutput();
            			buyer.setIdUser(s.getIdUser());
            			buyer.setName(s.getName());
            			buyer.setTelephone(s.getTelephone());
            			buyer.setEmail(s.getEmail());
            			buyer.setSector(s.getUserRole().get(0).getSector().getSectorName());
            			System.out.println("Nombre comprador: "+s.getName());
            			allDeliveries.add(buyer);
            		}
        		}	
    		}
    		
    	}
    	return allDeliveries;
    }
    
    public String changeSubstate(int id, String subestate) {
    	OrderP order=orderRepository.findById(id).get();
		order.setSubstate(subestate);
		orderRepository.save(order);
		return "Se cambio el sub-estado a "+subestate;
    }
    
    public List<OrderToPayBuyerOutput>  allOrdersCompletedForPayToBuyer() {
    	List<OrderP> allOrders=orderRepository.findAll();
    	List<OrderToPayBuyerOutput> allOrdersCompleted=new ArrayList<OrderToPayBuyerOutput>();
    	
    	for(OrderP o:allOrders) {
    		if(o.getStatus().equals("Finalizado")) {
    			OrderAssigned orderA=o.getOrderAssigned().get(o.getOrderAssigned().size()-1);
    			UserS buyer=userService.findById(orderA.getIdUserOfBuyer());
        		if(  orderA.getStatus().equals("Finalizado")|| orderA.getStatus().equalsIgnoreCase("pagado")) {
        			OrderToPayBuyerOutput order=new OrderToPayBuyerOutput() ;
        			order.setIdOrder(o.getIdOrder());
        			order.setIdBuyer(buyer.getIdUser());
        			order.setBuyerName(buyer.getName());
        			order.setBuyerCost(orderA.getBuyerCost());
        			order.setDateOfOrderAssigned(orderA.getDate());
        			order.setStatus(o.getStatus());
        			order.setPaymentStatusToBuyer(orderA.getPaymentStatusToBuyer());
        			allOrdersCompleted.add(order);
        		}
    		}
    	
    	}
    	return allOrdersCompleted;
    }
    
    public List<OrderToCollectDelivery>  allOrdersToCollectDelivery() {
    	List<OrderP> allOrders=orderRepository.findAll();
    	List<OrderToCollectDelivery> allOrdersCompleted=new ArrayList<OrderToCollectDelivery>();
    	
    	for(OrderP o:allOrders) {
    		//System.out.println("estado del pedido: "+o.getStatus());
    		if(o.getStatus().equals("Finalizado")) {
    			OrderAssigned orderA=o.getOrderAssigned().get(o.getOrderAssigned().size()-1);
        		if( o.getSubstate().equalsIgnoreCase("pagado al delivery") || (o.getSubstate().equalsIgnoreCase("pagado") && orderA.getReceiptNumberOfCollect()!=0 )) {
        			OrderToCollectDelivery order=new OrderToCollectDelivery() ;
        			order.setIdOrder(o.getIdOrder());
        			order.setIdDelivery(orderA.getUserS().getIdUser());
        			order.setDeliveryName(orderA.getUserS().getName());
        			order.setShippingCost(o.getShippingCost());
        			order.setDateOfOrderAssigned(orderA.getDate());
        			order.setTotalPrice(o.getTotalPrice());
        			order.setStatusOfOrder(o.getStatus());
        			if(o.getSubstate().equalsIgnoreCase("pagado")) {
        				order.setSubstateOfOrder("cobrado");
        			}else {
        				order.setSubstateOfOrder(o.getSubstate());
        			}
        			
        			allOrdersCompleted.add(order);
        		}
    		}
    	
    	}
    	return allOrdersCompleted;
    }
}

