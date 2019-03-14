package com.example.rmmservices.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Users {

	@Id
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private Boolean enabled;

	@ManyToMany
	private List<Device> devices;

	public Users(String username, String password, List<Device> devices) {
		this.username = username;
		this.password = password;
		this.enabled = Boolean.TRUE;
		this.devices = devices;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	
}
