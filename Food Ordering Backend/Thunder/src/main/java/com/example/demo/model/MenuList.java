package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name="customer_order_menu")
public class MenuList {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderno;
	private int dishid;
	private String food_name;
	private String food_type;
	private float price;
	private int quantity;
	
	

}
