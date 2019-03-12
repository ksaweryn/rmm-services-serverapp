package com.example.rmmservices.controller;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rmmservices.RMMServiceBean;
import com.example.rmmservices.model.RMMService;

@RestController
@RequestMapping(value = "/rmmService")
public class RMMServiceController {
	@Autowired
	private RMMServiceBean rmmServiceBean;

	@RequestMapping(value = "add")
	public @ResponseBody RMMService add(@RequestParam String type, @RequestParam BigDecimal monthlyCost) {
		RMMService rmmService = rmmServiceBean
				.add(new RMMService(type, monthlyCost.setScale(2, BigDecimal.ROUND_HALF_UP)));
		if (Objects.isNull(rmmService)) {
			new Exception("Error saving the RMM Service");
		}
		return rmmService;
	}

	@RequestMapping(value = "update")
	public @ResponseBody RMMService update(@RequestParam Long id, @RequestParam(required = false) String type,
			@RequestParam(required = false) BigDecimal monthlyCost) {
		RMMService rmmService = rmmServiceBean.findById(id);
		if (Objects.isNull(rmmService)) {
			new Exception("There is not a rmmService with id: " + id);
		}
		rmmService.setType(type);
		rmmService.setMonthlyCost(monthlyCost.setScale(2, BigDecimal.ROUND_HALF_UP));
		rmmService = rmmServiceBean.update(rmmService);
		return rmmService;
	}

	@RequestMapping(value = "delete")
	public @ResponseBody String delete(Long id) {
		RMMService rmmService = rmmServiceBean.findById(id);
		if (Objects.isNull(rmmService)) {
			new Exception("There is not a rmmService with id: " + id);
		}
		rmmServiceBean.delete(rmmService);
		return "Service deleted";
	}

	@RequestMapping(value = "findById")
	public @ResponseBody RMMService findById(Long id) {
		return rmmServiceBean.findById(id);
	}

	@RequestMapping(value = "getAll")
	public @ResponseBody Iterable<RMMService> getAll() {
		return rmmServiceBean.findAll();
	}
}
