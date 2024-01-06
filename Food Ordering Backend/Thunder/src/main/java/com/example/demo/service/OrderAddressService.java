package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AddressDetails;
import com.example.demo.repository.AddressDetailRepo;

@Service
public class OrderAddressService {

	@Autowired
	private AddressDetailRepo addressrepo;
	
// to save all details which are coming from order_details;	
public AddressDetails addaddress(AddressDetails address) {
	return addressrepo.save(address);
	}

// to show all order_address
	public  List<AddressDetails> alladdressdata() {
		return addressrepo.findAll();
	}
	
}
