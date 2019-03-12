package com.example.rmmservices.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findByType", query = "select s from RMMService s where s.type = ?1")
public class RMMService {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String type;
	private BigDecimal monthlyCost;

	protected RMMService() {
	}

	public RMMService(String type, BigDecimal monthlyCost) {
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

	// Overrides

	@Override
	public String toString() {
		return String.format("Service[id=%d, type='%s', monthlyCost='%s']", this.id, this.type,
				new DecimalFormat("$#0.##").format(this.monthlyCost.setScale(2, BigDecimal.ROUND_HALF_UP)));
	}
}
