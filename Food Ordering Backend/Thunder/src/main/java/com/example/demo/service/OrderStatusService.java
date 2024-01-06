package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.model.OrderStatus;
import com.example.demo.repository.OrderStatusRepo;

@Service
public class OrderStatusService {
	
	
	@Autowired
	private OrderStatusRepo statusRepo;
	
	
	public OrderStatus saveOrderStatus(OrderStatus orderstatus){
		return statusRepo.save(orderstatus);
		
	}
	public List<OrderStatus> orderstatusfindall(){
		return statusRepo.findAll();
	}

	
//used by admin to update order Status................	
	public OrderStatus updatedata(int id,OrderStatus orderstatus)
	{
		if(statusRepo.findById(id).isPresent())
		{
			Optional<OrderStatus> ord=statusRepo.findById(id);
			OrderStatus upord=ord.get();
			upord.setDetailaddress(orderstatus.getDetailaddress());
			upord.setExpectedtime(orderstatus.getExpectedtime());
			upord.setName(orderstatus.getName());
			upord.setOrderstatus(orderstatus.getOrderstatus());
			upord.setPaymentmode(orderstatus.getPaymentmode());
			upord.setPrice(orderstatus.getPrice());
			return statusRepo.save(upord);
		}
		else {
			throw new OrderNotFoundException("Customer doesn't have any order of this id");
		}
	}

// used by Admin To view order_status with the help of id.
	public Optional<OrderStatus> findorderStatus(int id)
	{
		return Optional.of(statusRepo.findById(id).get());
	}
	

}
