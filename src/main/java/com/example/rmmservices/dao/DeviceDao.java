package com.example.rmmservices.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.rmmservices.model.Device;

public interface DeviceDao extends CrudRepository<Device, Long> {
	
	Device findDeviceBySystemName (String systemName);

}
