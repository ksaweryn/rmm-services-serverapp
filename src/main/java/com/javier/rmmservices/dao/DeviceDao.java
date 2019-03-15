package com.javier.rmmservices.dao;

import org.springframework.data.repository.CrudRepository;

import com.javier.rmmservices.model.Device;

public interface DeviceDao extends CrudRepository<Device, Long> {
	
	Device findDeviceBySystemName (String systemName);

}
