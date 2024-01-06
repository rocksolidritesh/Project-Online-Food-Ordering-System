package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MenuList;

public interface MenuListRepo extends JpaRepository<MenuList, Integer> {
	
	

}
