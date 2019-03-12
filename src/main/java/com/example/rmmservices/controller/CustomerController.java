package com.example.rmmservices.controller;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rmmservices.DeviceBean;
import com.example.rmmservices.model.Device;
import com.example.rmmservices.model.RMMService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
	
	@Autowired
	private DeviceBean deviceBean;
	
	public String getMonthyBill() {
		BigDecimal bill = BigDecimal.ZERO;
		Iterable<Device> devices = deviceBean.findAll();
		Iterator<Device> it = devices.iterator();
		while(it.hasNext()) {
			Device device = it.next();
			bill = bill.add(getDevicesMohtlyBill(device.getRmmServices()));
		}
		
		return "Your monthly bill is $" + bill.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	// Private methods section
	private BigDecimal getDevicesMohtlyBill(List<RMMService> rmmServices) {
		BigDecimal monthlyBill = BigDecimal.ZERO;
		for (RMMService rmmService : rmmServices) {
			monthlyBill = monthlyBill.add(rmmService.getMonthlyCost());
		}
		return monthlyBill;
	}

}
