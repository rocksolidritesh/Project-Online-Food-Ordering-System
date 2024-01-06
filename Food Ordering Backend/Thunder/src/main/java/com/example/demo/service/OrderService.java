package com.example.demo.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.exception.CancelOrderException;
import com.example.demo.exception.OpeningClosingoneStopException;
import com.example.demo.model.AddressDetails;
import com.example.demo.model.FoodMenu;
import com.example.demo.model.MenuList;
import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.PinDetails;
import com.example.demo.repository.OrderRepo;

@Service
public class OrderService {
	@Autowired
	private OrderRepo orepo;
	@Autowired
	private FoodService fservice;
	@Autowired
	private PinService pservice;
	
	
	
	
	
	public Order adddata(Order order)
	
	{
		
		
//(new Date().getHours() >= 11 && new Date().getHours() < 21)==to check whether shop is open b/w that interval		
		if(new Date().getHours() >= 9 && new Date().getHours() < 23) 
		{
			
			
			Optional<PinDetails> pin=pservice.getid(order.getAddress().getPincode());
			PinDetails pindata=pin.get();
			System.out.println(pindata);
			
//set address while after finding pincode and setting the value with the help of pindetails.
			AddressDetails address=new AddressDetails();
					address.setFulladdress(order.getAddress().getFulladdress());
					address.setPincode(pindata.getPincode());
					address.setCountry(pindata.getCountry());
					address.setState(pindata.getState());
					address.setTown(pindata.getTown());
					order.setAddress(address);
		

				
				
		List<MenuList> list=new ArrayList<MenuList>();
		Date d=new Date();
		order.setBookingtime(d.toString());
		order.getList().forEach(e->{
//	to retrieve all data from food_details database with the help of dishid;
			Optional<FoodMenu> op=fservice.getfooddetails(e.getDishid());
			FoodMenu f=op.get();
//to set value in MenuList...
			MenuList m= new MenuList();
					m.setDishid(f.getDishid());
					m.setFood_name(f.getFoodname());
					m.setPrice(f.getPrice());
					m.setQuantity(e.getQuantity());
					m.setFood_type(f.getFoodtype());
					m.setOrderno(e.getOrderno());
					order.setTotalamount(Math.ceil(order.getTotalamount()+(m.getQuantity()*m.getPrice())));
					list.add(m);
					order.setList(list);});
		
		
//orderstatus full address setting 				
String orderstatusaddress=order.getAddress().getFulladdress()+" "+pindata.getTown()+" "
						+pindata.getDistrict()+" "+pindata.getPincode()+" "+pindata.getState()+" "+pindata.getCountry();
					
String [] paymentGateway= {"Cash On Delivery","UPI","Credit Card","Debit Card"};
Random randomGenerator=new Random();
int k=randomGenerator.nextInt(3) + 0;

Calendar date = Calendar.getInstance();
long timeInSecs = date.getTimeInMillis();
Date afterAdding30Mins = new Date(timeInSecs + (30 * 60 * 1000));

		
// for adding expected time
			OrderStatus ordstatus= new OrderStatus();
			ordstatus.setDetailaddress(orderstatusaddress);
			ordstatus.setExpectedtime(afterAdding30Mins.toString().substring(11,16));
			ordstatus.setName(order.getName());
			ordstatus.setOrderstatus("Order Confirmation Pending");
			ordstatus.setPaymentmode(paymentGateway[k]);
			ordstatus.setPrice(order.getTotalamount());

			order.setOrderstatus(ordstatus);
		}
		else
		{
			throw new OpeningClosingoneStopException("Sorry For Inconvenient But Order Will Be Accepting B/w 10 A.M to 9 P.M ");
		}
		return orepo.save(order);
		
	}
	
	
	public Optional<Order> getData(int id)
	{	
		if(orepo.findById(id).isPresent()) {
		return orepo.findById(id);
		}
		else {
			throw new OrderNotFoundException("Oops.. You haven't order food");
		}
	}

	public String deleteit(int id)
	{
		if(orepo.findById(id).isPresent())
		{
// for filtering time into hh:mm	
			System.out.println("------------------------here---------------------------");
		String filterhrs = orepo.findById(id).get().getBookingtime().substring(11,16);
			int ordertime= convert_into_integers(filterhrs);
			int currentTime=convert_into_integers(new Date().toString().substring(11,16));
//to check the condition whether it is less than 5 minutes or not.	
			if((currentTime-ordertime)< 3)
			{
				
				System.out.println(ordertime);
				System.out.println(currentTime);
				System.out.println(currentTime-ordertime);
				orepo.deleteById(id);
				 return "Order get Cancelled";
			}
			else {
				
				throw new CancelOrderException("Sorry Can't Cancel Order Acccording to Term and condition you can cancel order within 5 minutes");
			}	
		}
		else {
			
			throw new OrderNotFoundException("Sorry Can't Cancel because Id Doesn't Exist");
		}
		
	}
	
	
	public List<Order> getallorder(){
		return orepo.findAll();
	}
	

//converting string hh:mm to Integer hhmm	
	private int convert_into_integers(String hrs)
	{
	
		String filtermorehrs=hrs.substring(0,2)+hrs.substring(3);
		int k=Integer.valueOf(filtermorehrs);
		return k;
		
	}
}