package com.example.rmmservices.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rmmservices.DeviceBean;
import com.example.rmmservices.model.Device;
import com.example.rmmservices.model.RMMService;

@RestController
@RequestMapping(value = "/device")
public class DeviceController {

	@Autowired
	private DeviceBean deviceService;

	@RequestMapping(value = "add")
	public @ResponseBody Device add(@RequestParam String systemName, @RequestParam String type,
			@RequestParam(required = false) List<RMMService> rmmServices) {
		Device device = deviceService.add(new Device(systemName, type, rmmServices));
		if (Objects.isNull(device.getId())) {
			new Exception("Error while saving the device");
		}
		return device;
	}

	@RequestMapping(value = "update")
	public @ResponseBody Device update(@RequestParam Long id, @RequestParam(required = false) String systemName,
			@RequestParam(required = false) String type, @RequestParam(required = false) List<RMMService> rmmServices) {
		Device device = deviceService.findById(id);
		if (Objects.isNull(device)) {
			new Exception("There is not a device with id: " + id);
		}

		device.setSystemName(systemName);
		device.setType(type);
		device.setRmmServices(rmmServices);
		device = deviceService.update(device);
		return device;
	}

	@RequestMapping(value = "delete")
	public @ResponseBody String delete(@RequestParam(required = true) Long id) {
		Device device = deviceService.findById(id);
		if (Objects.isNull(device)) {
			new Exception("There is not a device with id: " + id);
		}

		deviceService.delete(device);
		return "Device deleted";
	}

	@RequestMapping(value = "findBySystemName")
	public @ResponseBody Device findBySystemName(@RequestParam String systemName) {
		Device device = deviceService.findDeviceBySystemName(systemName);
		if (Objects.isNull(device)) {
			new Exception("There is not a device with system name: " + systemName);
		}
		return device;
	}

	@RequestMapping(value = "findById")
	public @ResponseBody Device findById(@RequestParam Long id) throws Exception {
		Device device = deviceService.findById(id);
		if (Objects.isNull(device)) {
			new Exception("There is not a device with id: " + id);
		}
		return device;
	}

	@RequestMapping(value = "findAll")
	public @ResponseBody Iterable<Device> findAll() {
		Iterable<Device> devices = deviceService.findAll();
		if (Objects.isNull(devices)) {
			new Exception("There are not any devices");
		}
		return devices;
	}
}
