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

import com.example.demo.model.PinDetails;
import com.example.demo.service.PinService;

@RestController
@RequestMapping("admin")
public class AdminPincodeController {
	@Autowired
	private PinService pinservice;
	
	
	@PostMapping("pincode-details")
	public ResponseEntity<PinDetails> saveall(@Valid @RequestBody  PinDetails pin)
	{	
		return new ResponseEntity<PinDetails>(pinservice.createDetails(pin),HttpStatus.ACCEPTED);
	}
	
	
	
	@PutMapping("pincode-details/update/{pincode}")
	public ResponseEntity<PinDetails> upd(@PathVariable("pincode") int pincode,@RequestBody PinDetails pin)
	{
		//System.out.println(pin);
		 return new ResponseEntity<PinDetails>(pinservice.getupdate(pincode, pin),HttpStatus.OK);
	}
	@DeleteMapping("pincode-details/delete/{pincode}")
	public ResponseEntity<String> del(@PathVariable("pincode") int pincode)
	{
		return new ResponseEntity<String>(pinservice.delete(pincode),HttpStatus.OK);
	}
	@GetMapping(value="pincode-details/all_Values", produces="application/json")
	public ResponseEntity<List<PinDetails>> getall()
	{
		return new ResponseEntity<List<PinDetails>>(pinservice.getall(),HttpStatus.OK);
	}

}
