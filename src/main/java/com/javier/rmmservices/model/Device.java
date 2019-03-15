package com.javier.rmmservices.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
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
	@Column(unique = true)
	private String systemName;
	private String type;

	@ManyToMany
	private List<RMMService> rmmServices;

	protected Device() {
	}

	public Device(String systemName, String type, List<RMMService> rmmServices) {
		this.systemName = systemName;
		this.type = type;
		if (!Objects.isNull(rmmServices)) {
		/*	for (RMMService rmm : rmmServices) {
				if ((rmm.getType().toLowerCase().contains("antivirus")
						&& ((rmm.getType().toLowerCase().contains("windows")
								&& systemName.toLowerCase().contains("mac"))
								|| (rmm.getType().toLowerCase().contains("mac"))
										&& systemName.toLowerCase().contains("windows")))) {

				}
			}*/

			rmmServices.removeIf(item -> (item.getType().toLowerCase().contains("antivirus"))
					&& ((item.getType().toLowerCase().contains("windows") && systemName.toLowerCase().contains("mac"))
							|| ((item.getType().toLowerCase().contains("mac"))
									&& systemName.toLowerCase().contains("windows"))));
			this.rmmServices = rmmServices;

		} else {
			this.rmmServices = null;
		}
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

	public List<RMMService> getRmmServices() {
		return rmmServices;
	}

	public void setRmmServices(List<RMMService> rmmServices) {
		this.rmmServices = rmmServices;
	}

	// Overrides
	@Override
	public String toString() {
		return String.format("Device[id=%d, systemName='%s', type='%s']", this.id, this.systemName, this.type);
	}
}
