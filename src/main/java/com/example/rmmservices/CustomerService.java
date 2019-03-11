package com.example.rmmservices;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rmmservices.dao.CustomerDao;
import com.example.rmmservices.model.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	public Customer add(Customer customer) {
		return customerDao.save(customer);
	}

	public Customer findById(Long id) {
		Optional<Customer> customer = customerDao.findById(id);
		return customer.orElse(null);
	}

	public Customer findCustomerByUsername(String username) {
		Customer customer = customerDao.findByUsername(username);
		if (Objects.isNull(customer)) {
			return null;
		} else {
			return customer;
		}
	}

	public Customer update(Customer customer) {
		return customerDao.save(customer);
	}

	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

}
