package com.example.rmmservices.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findDeviceBySystemName", query = "select d from Device d where d.systemName = ?1")
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String systemName;
	private String type;
	private BigDecimal monthlyCost;
	@ManyToMany
	private List<Customer> customers;

	protected Device() {
	}

	public Device(String systemName, String type, BigDecimal monthlyCost) {
		this.systemName = systemName;
		this.type = type;
		this.monthlyCost = monthlyCost;
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getMonthlyCost() {
		return monthlyCost;
	}

	public void setMonthlyCost(BigDecimal monthlyCost) {
		this.monthlyCost = monthlyCost;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	// Overrides

	@Override
	public String toString() {
		return String.format("Device[id=%d, systemName='%s', type='%s', monthlyCost='%s']", this.id, this.systemName,
				this.type, new DecimalFormat("$#0.##").format(this.monthlyCost.setScale(2, BigDecimal.ROUND_HALF_UP)));
	}
}
