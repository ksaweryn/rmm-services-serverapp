package com.example.rmmservices.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.rmmservices.model.Customer;

public interface CustomerDao extends CrudRepository<Customer, Long> {
	
	Customer findByUsername (String username);
}
