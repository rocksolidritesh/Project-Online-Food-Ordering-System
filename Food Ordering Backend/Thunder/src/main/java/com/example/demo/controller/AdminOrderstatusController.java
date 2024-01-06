package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;
import com.example.demo.service.OrderService;
import com.example.demo.service.OrderStatusService;

@RestController
@RequestMapping("admin")
public class AdminOrderstatusController {
	
	@Autowired
	private OrderService oservice;
	@Autowired
	private OrderStatusService orderservice;
	
	@GetMapping(value="/getallcustomer/orderdetails" ,produces="application/json")
	public ResponseEntity<List<Order>> getall(){
		
		return new ResponseEntity<List<Order>>(oservice.getallorder(),HttpStatus.OK);
	}
		
	@GetMapping(value="get-order/{orderid}",produces="application/json")
	public ResponseEntity<Optional<Order>> createdata(@PathVariable("orderid") int orderid)
	{
		return new ResponseEntity<Optional<Order>>(oservice.getData(orderid),HttpStatus.ACCEPTED);
	}
	@PutMapping("/update-order-status/{id}")
	public ResponseEntity<OrderStatus> updateorderStatus(@PathVariable("id") int id,@RequestBody OrderStatus orderstatus)
	{
		return new ResponseEntity<OrderStatus>(orderservice.updatedata(id, orderstatus),HttpStatus.ACCEPTED);
	}
	@GetMapping(value="get-orderstatus/{orderid}",produces="application/json")
	public ResponseEntity<Optional<OrderStatus>> getorderstatusid(@PathVariable("orderid") int orderid)
	{
		return new ResponseEntity<Optional<OrderStatus>>(orderservice.findorderStatus(orderid),HttpStatus.ACCEPTED);
	}
}
