package com.example.demo.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FoodMenu;
import com.example.demo.model.Order;
import com.example.demo.model.PinDetails;
import com.example.demo.service.FoodService;
import com.example.demo.service.OrderService;
import com.example.demo.service.PinService;


@RestController
@RequestMapping("/users")
public class UsersOrderController {
	@Autowired
	private OrderService oservice;
	
	@Autowired
	private PinService pinservice;
	
	@Autowired
	private FoodService fservice;
	
	
// getting pincode from pindetails database to check whether service is provide in that area or not.	
	@GetMapping(value="pincode-details/{id}")
	public ResponseEntity<Optional<PinDetails>> getid(@PathVariable("id") int id){
		 return new ResponseEntity<Optional<PinDetails>>( pinservice.getid(id),HttpStatus.OK);
		
	}
	
// getting Foodmenu all value from food_menu database to order food with help of dishid. 
	@GetMapping(value="/food_menu_list",produces="application/json")
	public ResponseEntity< List<FoodMenu>>  getall()
	{
		return new ResponseEntity<List<FoodMenu>>( fservice.fetchalldata(),HttpStatus.ACCEPTED);
	}

//Ordering food (Creating order);	
	@PostMapping("create-order")
	public ResponseEntity<Order> createdata(@RequestBody Order order)
	{  
	
		
		return new ResponseEntity<Order>(oservice.adddata(order),HttpStatus.CREATED);
	}
	
	
// to check order status with the help of orderid
	@GetMapping(value="get-order/{orderid}",produces="application/json")
	public ResponseEntity<Optional<Order>> createdata(@PathVariable("orderid") int orderid)
	{
		return new ResponseEntity<Optional<Order>>(oservice.getData(orderid),HttpStatus.ACCEPTED);
	}
	
// to cancel food order with help orderid.
	@DeleteMapping("/user-cancelorder/{orderid}")
	public ResponseEntity<String> deleteId(@PathVariable("orderid") int orderid)
	{
		return new ResponseEntity<String>(oservice.deleteit(orderid),HttpStatus.OK);
	}
	
}
