package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.MenuList;
import com.example.demo.repository.MenuListRepo;

@Service
public class MenuService {
	@Autowired
	private MenuListRepo menuadd;
	
	
	public MenuList addindetails(MenuList menu)
	{	
		return menuadd.save(menu);
	}
	public List<MenuList> getall(MenuList menu)
	{
		
		return menuadd.findAll();
	}
}
