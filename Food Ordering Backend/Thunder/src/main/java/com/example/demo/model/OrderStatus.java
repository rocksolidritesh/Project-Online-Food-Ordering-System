package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="order_status")
public class OrderStatus {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int orderid;
	private String name;
	private String paymentmode;
//real time+30 min;
	private String expectedtime;
//order placed	
	private String orderstatus;
// full+town+pincode+state+country	
	private String detailaddress;
	private double price;
}
