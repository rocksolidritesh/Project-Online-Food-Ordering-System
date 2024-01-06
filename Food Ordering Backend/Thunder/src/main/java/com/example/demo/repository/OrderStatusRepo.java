package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.OrderStatus;

public interface OrderStatusRepo extends JpaRepository<OrderStatus, Integer> {

}
