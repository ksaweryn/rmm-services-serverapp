package com.example.rmmservices;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rmmservices.dao.DeviceDao;
import com.example.rmmservices.model.Device;
import com.example.rmmservices.model.RMMService;

@Service
public class DeviceBean {

	@Autowired
	private DeviceDao deviceDao;

	public Device add(Device device) {
		return deviceDao.save(device);
	}

	public Device findById(Long id) {
		Optional<Device> device = deviceDao.findById(id);
		return device.orElse(null);
	}

	public Iterable<Device> findAll() {
		return deviceDao.findAll();
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

	public Device updateRMMServices(List<RMMService> rmmServices, Device device) {
		Device updatedDevice = device;
		if (!Objects.isNull(rmmServices)) {
			List<RMMService> originalRMMServices = updatedDevice.getRmmServices();
			originalRMMServices
					.removeIf(item -> rmmServices.stream().anyMatch(rmm -> rmm.getId().equals(item.getId())));
			updatedDevice.setRmmServices(originalRMMServices);
		}
		return updatedDevice;
	}

	public Device updateDevice(String systemName, String type, List<RMMService> rmmServices, Device device) {
		Device updatedDevice = device;

		if (!Objects.isNull(systemName)) {
			updatedDevice.setSystemName(systemName);
		}

		if (!Objects.isNull(type)) {
			updatedDevice.setType(type);
		}

		if (!Objects.isNull(rmmServices)) {
			List<RMMService> originalRMMServices = updatedDevice.getRmmServices();
			originalRMMServices.addAll(rmmServices);
			updatedDevice.setRmmServices(rmmServices.stream().distinct().collect(Collectors.toList()));
		}
		return updatedDevice;
	}

}
