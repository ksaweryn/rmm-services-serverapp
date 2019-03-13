package com.example.rmmservices;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rmmservices.model.Device;
import com.example.rmmservices.model.RMMService;

@Service
public class CustomerBean {

	private final String WINDOWS = "windows";
	private final String MAC = "mac";

	public BigDecimal getDevicesBill(Iterable<Device> devices) {
		BigDecimal bill = BigDecimal.ZERO;
		Iterator<Device> it = devices.iterator();
		String typeOfOS = "";
		while (it.hasNext()) {
			Device device = it.next();
			typeOfOS = device.getType().toLowerCase();
			if (typeOfOS.contains(WINDOWS.toLowerCase())) {
				bill = bill.add(new BigDecimal(5));
			} else if (typeOfOS.contains(MAC.toLowerCase())) {
				bill = bill.add(new BigDecimal(7));
			}
			bill = bill.add(getDevicesMohtlyBill(device.getRmmServices()));
		}
		return bill;
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
