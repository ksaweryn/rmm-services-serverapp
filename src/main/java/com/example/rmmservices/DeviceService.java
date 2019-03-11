package com.example.rmmservices;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rmmservices.dao.DeviceDao;
import com.example.rmmservices.model.Device;

@Service
public class DeviceService {

	@Autowired
	private DeviceDao deviceDao;

	public Device add(Device device) {
		return deviceDao.save(device);
	}

	public Device findById(Long id) {
		Optional<Device> device = deviceDao.findById(id);
		return device.orElse(null);
	}

	public Device findDeviceBySystemName(String systemName) {
		Device device = deviceDao.findDeviceBySystemName(systemName);
		if (Objects.isNull(device)) {
			return null;
		} else {
			return device;
		}
	}

	public Device update(Device device) {
		return deviceDao.save(device);
	}

	public void delete(Device device) {
		deviceDao.delete(device);
	}

}
