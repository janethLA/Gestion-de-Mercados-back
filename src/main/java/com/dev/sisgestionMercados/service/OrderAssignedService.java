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
import com.dev.sisgestionMercados.Output.OrderToPayOutput;
import com.dev.sisgestionMercados.Output.OrdersCompletedByDeliveryOutput;
import com.dev.sisgestionMercados.entity.OrderAssigned;
import com.dev.sisgestionMercados.entity.OrderP;
import com.dev.sisgestionMercados.entity.Payment;
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
	@Autowired
	private PaymentService paymentService;

	public String save(OrderAssignedInput orderAssigned) {
		
		OrderP o=orderService.findById(orderAssigned.getIdOrder());
		o.setShippingCost(orderAssigned.getShippingCost());
		Payment payment = paymentService.findById(orderAssigned.getIdPayment());
		UserS delivery=userService.findById(orderAssigned.getIdUser());
		
		OrderAssigned newOrderAssigned=new OrderAssigned();
		newOrderAssigned.setStatus("Pendiente");
		newOrderAssigned.setDate(LocalDate.now());
		newOrderAssigned.setHour(LocalTime.now());
		newOrderAssigned.setOrderP(o);
		newOrderAssigned.setUserS(delivery);
		newOrderAssigned.setReassigned(false);
		newOrderAssigned.setIdUserCallCenter(orderAssigned.getIdUserCallCenter());
		newOrderAssigned.setPayment(payment);
		
		newOrderAssigned.setIdUserOfBuyer(orderAssigned.getIdUserOfBuyer());
		newOrderAssigned.setBuyerCost(orderAssigned.getBuyerCost());
		newOrderAssigned.setDeliveryCost(orderAssigned.getDeliveryCost());
		newOrderAssigned.setPaymentStatusToBuyer("Por pagar");
		
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
			
			o.setFinalUserName(orderA.getOrderP().getFinalUser().getFinalUserName());
			o.setFinalUserTelephone(orderA.getOrderP().getFinalUser().getTelephone());
			o.setFinalUserWhatsappLink(orderA.getOrderP().getFinalUser().getWhatsappLink());
			
			UserS user=userService.findById(orderA.getIdUserCallCenter());
			o.setAdminName(user.getName());
			o.setAdminTelephone(user.getTelephone());
			o.setAdminEmail(user.getEmail());
			o.setAdminWhatsappLink(user.getWhatsappLink());
			
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
		o.setStatus("Enviando");
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
	
	public  List<AllOrderAssignedOutput> getAllAssignedOrders(int id){
		
		List<AllOrderAssignedOutput> AllOrderAssigned=new ArrayList<AllOrderAssignedOutput>();
		List<OrderAssigned> orders= orderAssignedRepository.findAll();
		for(OrderAssigned o: orders) {
			if(o.getIdUserCallCenter()==id) {
				AllOrderAssignedOutput newOrderA=new AllOrderAssignedOutput();
				newOrderA.setIdOrderAssigned(o.getIdOrderAssigned());
				newOrderA.setDate(o.getDate());
				newOrderA.setHour(o.getHour());
				newOrderA.setStatus(o.getStatus());
				newOrderA.setIdOrder(o.getOrderP().getIdOrder());
				newOrderA.setCommentary(o.getCommentary());
				newOrderA.setReassigned(o.isReassigned());
				 AllOrderAssigned.add(newOrderA);
			}
			
		}
		return AllOrderAssigned;
	}
	
	public String reportEmergency(int id, String commentary) {
		OrderAssigned orderA=orderAssignedRepository.findById(id).get();
		orderA.setStatus("Rechazado");
		orderA.setCommentary(commentary);
		////OrderP o=orderA.getOrderP();
		//o.setStatus("Pendiente");
		orderAssignedRepository.save(orderA);
		//orderService.save2(o);
		return "Se ha registrado su percance";
	}
	
   public void save2(OrderAssigned order) {
    	orderAssignedRepository.save(order);
    }
   
	public String payDelivery(int id,long receiptNumber, List<Integer> ids) {
		UserS u = userService.findById(id);
		List<OrderAssigned> allAsignedOrders = u.getOrderAssigned();
	    
		for (int j =0;j< allAsignedOrders.size();j++) {
			
			OrderAssigned o =allAsignedOrders.get(j);
			
			if (o.getStatus().equals("Finalizado")) {
				for (int i = 0; i < ids.size(); i++) {
					if (o.getOrderP().getIdOrder() == ids.get(i)) {
                        o.setStatus("Pagado");
                        o.setPaymentDate(LocalDate.now());
                        o.setReceiptNumber(receiptNumber);
                        orderAssignedRepository.save(o);
                        
					}
				}

			}
		}
		return "Se realizo el pago";
	}
	
	public List<OrdersCompletedByDeliveryOutput> allOrdersCompletedForReport(int id) {
		UserS u = userService.findById(id);
		List<OrderAssigned> allAsignedOrders = u.getOrderAssigned();
    	List<OrdersCompletedByDeliveryOutput> allOrdersCompleted=new ArrayList<OrdersCompletedByDeliveryOutput>();
    	
    	for(OrderAssigned o:allAsignedOrders) {
    		if(o.getStatus().equals("Finalizado") ||o.getStatus().equals("Pagado") ) {
    			
        			OrdersCompletedByDeliveryOutput order=new OrdersCompletedByDeliveryOutput() ;
        			order.setIdOrder(o.getOrderP().getIdOrder());
        			order.setDelivery(o.getUserS().getName());
        			order.setDateOfOrderAssigned(o.getDate());
        			order.setPaymentDate(o.getPaymentDate());
        			order.setReceiptNumber(o.getReceiptNumber());
        			order.setShippingCost(o.getOrderP().getShippingCost());
        			order.setStatusOfOrder(o.getOrderP().getStatus());
        			order.setStatusOfOrderAssigned(o.getStatus());
        			allOrdersCompleted.add(order);
        		}
    		}

    	return allOrdersCompleted;
	}
	
	public String payBuyer(int id, long receiptNumber, List<Integer> ids) {
		//UserS u = userService.findById(id);
		Iterable<OrderP> allOrders = orderService.allOrders();
		List<OrderAssigned> allAsignedOrders = new ArrayList<OrderAssigned>();
		for (OrderP o : allOrders) {
			if (o.getStatus().equals("Finalizado") && !o.getOrderAssigned().isEmpty()) {
				OrderAssigned orderA = o.getOrderAssigned().get(o.getOrderAssigned().size() - 1);
				if (orderA.getIdUserOfBuyer() == id) {
					allAsignedOrders.add(orderA);
				}
			}
		}

        for (int j =0;j< allAsignedOrders.size();j++) {
			
			OrderAssigned o =allAsignedOrders.get(j);
			
			if (o.getStatus().equals("Finalizado")) {
				for (int i = 0; i < ids.size(); i++) {
					if (o.getOrderP().getIdOrder() == ids.get(i)) {
                        o.setPaymentStatusToBuyer("Pagado");
                        o.setPaymentDateOfBuyer(LocalDate.now());
                        o.setReceiptNumberOfBuyer(receiptNumber);
                        orderAssignedRepository.save(o);
                        
					}
				}

			}
		}
        
		return "Se realizó el pago al comprador";
	}
	
	public String collectDelivery(int id,long receiptNumber, List<Integer> ids) {
		UserS u = userService.findById(id);
		List<OrderAssigned> allAsignedOrders = u.getOrderAssigned();
	    
		for (int j =0;j< allAsignedOrders.size();j++) {
			
			OrderAssigned o =allAsignedOrders.get(j);
			
			if (o.getStatus().equals("Finalizado")) {
				for (int i = 0; i < ids.size(); i++) {
					if (o.getOrderP().getIdOrder() == ids.get(i)) {
                        o.getOrderP().setSubstate("Pagado");
                        o.setReceiptNumberOfCollect(receiptNumber);
                        orderAssignedRepository.save(o);
                        orderService.save2(o.getOrderP());
                        
					}
				}

			}
		}
		return "Se realizo el cobro al Delivery exitosamente";
	}
	
}



