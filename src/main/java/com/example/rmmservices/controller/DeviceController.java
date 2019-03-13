package com.example.rmmservices.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	private DeviceBean deviceBean;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody Device add(@RequestParam String systemName, @RequestParam String type,
			@RequestBody(required = false) List<RMMService> rmmServices) {
		Device device = deviceBean.add(new Device(systemName, type, rmmServices));
		if (Objects.isNull(device.getId())) {
			new Exception("Error while saving the device");
			return null;
		}
		return device;
	}

	@RequestMapping(value = "addRMMServices", method = RequestMethod.PUT)
	public @ResponseBody Device addRMMServices(@RequestParam Long id, @RequestParam(required = false) String systemName,
			@RequestParam(required = false) String type, @RequestBody(required = false) List<RMMService> rmmServices) {
		Device device = deviceBean.findById(id);
		if (Objects.isNull(device)) {
			new Exception("There is not a device with id: " + id);
			return null;
		}
		device = deviceBean.updateDevice(systemName, type, rmmServices, device);
		return deviceBean.update(device);
	}

	@RequestMapping(value = "deleteRMMService", method = RequestMethod.PUT)
	public @ResponseBody Device deleteRMMService(@RequestParam Long id, @RequestBody List<RMMService> rmmServices) {
		Device device = deviceBean.findById(id);
		if (Objects.isNull(device)) {
			new Exception("There is not a device with id: " + id);
			return null;
		}
		device = deviceBean.updateRMMServices(rmmServices, device);
		return deviceBean.update(device);
	}

	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public @ResponseBody String delete(@RequestParam(required = true) Long id) {
		Device device = deviceBean.findById(id);
		if (Objects.isNull(device)) {
			new Exception("There is not a device with id: " + id);
		}

		deviceBean.delete(device);
		return "Device deleted";
	}

	@RequestMapping(value = "findBySystemName")
	public @ResponseBody Device findBySystemName(@RequestParam String systemName) {
		Device device = deviceBean.findDeviceBySystemName(systemName);
		if (Objects.isNull(device)) {
			new Exception("There is not a device with system name: " + systemName);
		}
		return device;
	}

	@RequestMapping(value = "findById")
	public @ResponseBody Device findById(@RequestParam Long id) throws Exception {
		Device device = deviceBean.findById(id);
		if (Objects.isNull(device)) {
			new Exception("There is not a device with id: " + id);
		}
		return device;
	}

	@RequestMapping(value = "findAll")
	public @ResponseBody Iterable<Device> findAll() {
		Iterable<Device> devices = deviceBean.findAll();
		if (Objects.isNull(devices)) {
			new Exception("There are not any devices");
		}
		return devices;
	}
}
