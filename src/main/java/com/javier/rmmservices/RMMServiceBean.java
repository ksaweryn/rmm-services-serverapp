package com.javier.rmmservices;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.javier.rmmservices.dao.RMMServiceDao;
import com.javier.rmmservices.model.RMMService;

@Service
public class RMMServiceBean {

	@Autowired
	private RMMServiceDao rmmServiceDao;

	public RMMService add(RMMService RMMService) {
		try {
			return rmmServiceDao.save(RMMService);
		} catch (ConstraintViolationException | DataIntegrityViolationException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while saving the RMM Service");
		}
	}

	public RMMService findById(Long id) {
		Optional<RMMService> rmmService = rmmServiceDao.findById(id);
		return rmmService.orElse(null);
	}

	public Iterable<RMMService> findAll() {
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
