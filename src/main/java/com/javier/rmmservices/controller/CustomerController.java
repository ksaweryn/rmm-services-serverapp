package com.javier.rmmservices.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javier.rmmservices.CustomerBean;
import com.javier.rmmservices.DeviceBean;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private CustomerBean customerBean;
	@Autowired
	private DeviceBean deviceBean;

	@RequestMapping("bill")
	public String getMonthyBill() {
		BigDecimal bill = customerBean.getDevicesBill(deviceBean.findAll());
		return "Your monthly bill is $" + bill.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
}
