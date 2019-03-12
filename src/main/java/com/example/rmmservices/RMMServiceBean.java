package com.example.rmmservices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rmmservices.dao.RMMServiceDao;
import com.example.rmmservices.model.RMMService;

@Service
public class RMMServiceBean {

	@Autowired
	private RMMServiceDao rmmServiceDao;

	public RMMService add(RMMService RMMService) {
		return rmmServiceDao.save(RMMService);
	}

	public RMMService findById(Long id) {
		Optional<RMMService> rmmService = rmmServiceDao.findById(id);
		return rmmService.orElse(null);
	}
	
	public Iterable<RMMService> findAll(){
		return rmmServiceDao.findAll();
	}

	public void delete(RMMService rmmService) {
		rmmServiceDao.delete(rmmService);
	}
	
	public RMMService update(RMMService rmmService) {
		return rmmServiceDao.save(rmmService);
	}
	
	public Iterable<RMMService> findByType(String type) {
		return rmmServiceDao.findByType(type);
	}
}
