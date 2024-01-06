package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name="order_details")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int orderid;
	@NotBlank(message="Required name should not be blank  ")
	private String name;
	@NotBlank(message="Required Contact Number ")
	@Pattern(regexp="[6-9][0-9]{9}",message="Enter Valid Mobile Number")
	private String contactno;
	private String bookingtime;
	private double totalamount;
	
// for Adding all address.........................	
//cascade = CascadeType.ALL ( PERSIST, REMOVE, REFRESH, MERGE, DETACH )
	@OneToOne(cascade = CascadeType.ALL)
//order_id work as foreign key in this table and orderid work as primary key in AddressDetails
	@JoinColumn(name="order_id",referencedColumnName = "orderid")
	private AddressDetails address;

	//for adding multiple Food item....	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "orderid",referencedColumnName ="orderid")
	private List<MenuList> list;

	
	@OneToOne(cascade = CascadeType.ALL)
//  ordestatus_id work as foreign key in this table and orderid work as primary key in AddressDetails
	@JoinColumn(name="orderstatus_id",referencedColumnName ="orderid")
	private OrderStatus orderstatus;
	
	
	

}
