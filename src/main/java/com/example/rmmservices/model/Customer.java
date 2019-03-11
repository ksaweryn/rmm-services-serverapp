package com.example.rmmservices.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Customer.findByUsername", query = "select c from Customer c where c.username= ?1")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String username;
	private String pass;
	@ManyToMany
	private List<Device> devices;

	protected Customer() {
	}

	public Customer(String name, String username, String pass, List<Device> devices) {
		this.name = name;
		this.username = username;
		this.pass = pass;
		this.devices = devices;
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	// Overrides

	@Override
	public String toString() {
		return String.format("Customer[id=%d, name='%s', username='%s', devices='%s']", this.id, this.name,
				this.username, this.devices);
	}
}
