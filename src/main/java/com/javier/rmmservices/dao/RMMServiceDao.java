package com.javier.rmmservices.dao;

import org.springframework.data.repository.CrudRepository;

import com.javier.rmmservices.model.RMMService;

public interface RMMServiceDao extends CrudRepository<RMMService, Long> {
	
	public Iterable<RMMService> findByType(String type);
	
}
