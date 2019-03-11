package com.example.rmmservices.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rmmservices.CustomerService;
import com.example.rmmservices.model.Customer;
import com.example.rmmservices.model.Device;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "add")
	public @ResponseBody String addCustomer(String name, String username, String pass,
			@RequestParam(required = false) List<Device> devices) {
		Customer customer = customerService.add(new Customer(name, username, pass, devices)); 
		if (Objects.isNull(customer)) {
			return "Error while saving the customer";
		}
		
		return customer.toString();
	}

	@RequestMapping(value = "update")
	public @ResponseBody String updateCustomer(@RequestParam Long id, @RequestParam(required = false) String name,
			@RequestParam(required = false) String username, @RequestParam(required = false) String pass,
			@RequestParam(required = false) List<Device> devices) {
		Customer customer = customerService.findById(id);
		if (Objects.isNull(customer)) {
			return "There is not a customer with id: " + id;
		}

		customer.setName(name);
		customer.setUsername(username);
		customer.setPass(pass);
		customer.setDevices(devices);
		customer = customerService.update(customer);
		return customer.toString();
	}

	@RequestMapping(value = "delete")
	public @ResponseBody String deleteCustomer(Long id) {
		Customer customer = customerService.findById(id);
		if (Objects.isNull(customer)) {
			return "There is not a customer with id: " + id;
		}

		customerService.delete(customer);
		return "Customer deleted";
	}

	@RequestMapping(value = "findCustomerByUsername")
	public @ResponseBody String findCustomerByUsername(String username) {
		Customer customer = customerService.findCustomerByUsername(username);
		return customer.toString();
	}

	@RequestMapping(value = "findCustomerById")
	public @ResponseBody String findCustomerById(Long id) {
		return customerService.findById(id).toString();
	}
}
