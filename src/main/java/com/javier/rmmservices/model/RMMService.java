package com.javier.rmmservices.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "type" }))
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((monthlyCost == null) ? 0 : monthlyCost.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RMMService other = (RMMService) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (monthlyCost == null) {
			if (other.monthlyCost != null)
				return false;
		} else if (!monthlyCost.equals(other.monthlyCost))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
