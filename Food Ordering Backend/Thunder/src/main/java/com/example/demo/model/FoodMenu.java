package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


import lombok.Data;
@Data
@Entity
@Table(name="food_menu")
public class FoodMenu {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dishid;
	@NotBlank(message="Enter Food Name Can't be empty")
	private String foodname;
	@NotBlank(message="Veg 0r Non-Veg")
	private String foodtype;
	@NotBlank(message="should be in summary for the dish")
	private String description;
	private float price;
}
