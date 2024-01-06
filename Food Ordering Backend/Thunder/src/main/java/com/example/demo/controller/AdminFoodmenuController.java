package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.FoodMenu;
import com.example.demo.service.FoodService;

@RestController
@RequestMapping("admin")
public class AdminFoodmenuController {
	@Autowired
	private FoodService fservice;

	@PostMapping("/food_menu/create/")
	public ResponseEntity<FoodMenu> createMenu(@Valid @RequestBody  FoodMenu foodmenu)
	{
		
		return new ResponseEntity<>(fservice.savemenu(foodmenu),HttpStatus.CREATED);
	}
	@PutMapping("/food_menu/{id}")
	public ResponseEntity<FoodMenu> getupdate(@PathVariable("id") int id,@RequestBody FoodMenu foodmenu) {
		
		return new ResponseEntity<FoodMenu>(fservice.updatemenu(id, foodmenu),HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="/food_menu_list",produces="application/json")
	public ResponseEntity< List<FoodMenu>>  getall()
	{
		return new ResponseEntity<List<FoodMenu>>( fservice.fetchalldata(),HttpStatus.OK);
	}
	@GetMapping(value="/food_menu_list/sorted_asc",produces="application/json")
	public ResponseEntity< List<FoodMenu>>  getallsortedAsc()
	{
		return new ResponseEntity<List<FoodMenu>>( fservice.fetchalldatasortedAsc(),HttpStatus.OK);
	}
	
	@DeleteMapping("/food_menu/{id}")
	public ResponseEntity<String> deleteby(@PathVariable("id") int id)
	{
		return new ResponseEntity<String> (fservice.deletemenu(id),HttpStatus.NO_CONTENT);
	}
}
