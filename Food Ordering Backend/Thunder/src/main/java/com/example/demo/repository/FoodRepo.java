package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FoodMenu;

public interface FoodRepo extends JpaRepository<FoodMenu, Integer> {
	
	

}
