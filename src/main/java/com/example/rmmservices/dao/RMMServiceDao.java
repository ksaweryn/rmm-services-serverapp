package com.example.rmmservices.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.rmmservices.model.RMMService;

public interface RMMServiceDao extends CrudRepository<RMMService, Long> {
	
	public Iterable<RMMService> findByType(String type);
	
}
