package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="orderaddress_details")
public class AddressDetails {
	
//	work as primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderid;
	
	private String fulladdress;
	private int pincode;
	private String town;
	private String state;
	private String country;
	    

}
