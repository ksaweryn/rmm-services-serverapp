package com.example.rmmservices.controller;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rmmservices.DeviceService;
import com.example.rmmservices.model.Device;

@RestController
@RequestMapping(value = "/device")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@RequestMapping(value = "add")
	public @ResponseBody String addDevice(@RequestParam(required = true) String systemName,
			@RequestParam(required = true) String type, @RequestParam(required = true) BigDecimal monthlyCost) {
		Device device = deviceService
				.add(new Device(systemName, type, monthlyCost.setScale(2, BigDecimal.ROUND_HALF_UP)));
		if (Objects.isNull(device)) {
			return "Error while saving the customer";
		}
		return device.toString();
	}

	@RequestMapping(value = "update")
	public @ResponseBody String updateDevice(@RequestParam(required = true) Long id, @RequestParam String systemName,
			@RequestParam String type, @RequestParam BigDecimal monthlyCost) {
		Device device = deviceService.findById(id);
		if (Objects.isNull(device)) {
			return "There is not a device with id: " + id;
		}

		device.setSystemName(systemName);
		device.setType(type);
		device.setMonthlyCost(monthlyCost);
		device = deviceService.update(device);
		return device.toString();
	}

	@RequestMapping(value = "delete")
	public @ResponseBody String deleteDevice(@RequestParam(required = true) Long id) {
		Device device = deviceService.findById(id);
		if (Objects.isNull(device)) {
			return "There is not a device with id: " + id;
		}

		deviceService.delete(device);
		return "Device deleted";
	}

	@RequestMapping(value = "findDeviceBySystemName")
	public @ResponseBody String findDeviceBySystemName(@RequestParam(required = true) String systemName) {
		Device device = deviceService.findDeviceBySystemName(systemName);
		if (Objects.isNull(device)) {
			return "There is no device with system name: " + systemName;
		} else {
			return device.toString();
		}
	}

	@RequestMapping(value = "findDeviceById")
	public @ResponseBody String findDeviceById(@RequestParam(required = true) Long id) {
		return deviceService.findById(id).toString();
	}
}
