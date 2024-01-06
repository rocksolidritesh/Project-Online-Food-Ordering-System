package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.exception.productNotFoundException;
import com.example.demo.model.FoodMenu;
import com.example.demo.model.Order;
import com.example.demo.repository.FoodRepo;

@Service
public class FoodService {

	@Autowired
	private FoodRepo foodrepo;
	
	
	
// Create a new Food Item;	
	public FoodMenu savemenu(FoodMenu foodmenu)
	{
		return foodrepo.save(foodmenu);
	}
	
	
//Updating 	the food item;
	public FoodMenu updatemenu(int id,FoodMenu foodmenu)
	{
		 if(foodrepo.findById(id).isPresent()) {
		Optional<FoodMenu> opt=foodrepo.findById(id);
		FoodMenu fupdate=opt.get();
		fupdate.setFoodname(foodmenu.getFoodname());
		fupdate.setDescription(foodmenu.getDescription());
		fupdate.setFoodtype(foodmenu.getFoodtype());
		fupdate.setPrice(foodmenu.getPrice());
		return foodrepo.save(fupdate);
		 }else
		 {
			 throw new productNotFoundException("Food id doesn't exist");
		 }
	}
//to delete the food item if the food item is not popular or not in demanding...
	public String  deletemenu(int id)
	{
		 if(foodrepo.findById(id).isPresent())
		 {
			 foodrepo.deleteById(id);
			 return "Deleted";
		 }
		 else
		 {
			 throw new productNotFoundException("Food id doesn't exist");

		 }
	}
	
// to get food item by id if it is present........... 	
	public Optional<FoodMenu> getfooddetails(int id)
	{      
		Optional<FoodMenu> f=foodrepo.findById(id);
		if(f.isPresent())
		{
			return f;
		}
		else {
			 throw new productNotFoundException("Food Id "+id+" is not present in the Food Menu");
		}
	}
// to get all food item especially design for customer to view all food items......	
	public List<FoodMenu> fetchalldata(){
		
		return foodrepo.findAll();
	}
	
	public List<FoodMenu> fetchalldatasortedAsc(){
		List<Sort.Order> colList = new ArrayList<>();
		Sort.Order order = new Sort.Order(Direction.ASC,"price");
		colList.add(order);
		return foodrepo.findAll(Sort.by(colList));
	}
}
