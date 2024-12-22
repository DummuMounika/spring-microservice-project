package com.learnings.microservices.limitservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnings.microservices.limitservice.bean.Limit;
import com.learnings.microservices.limitservice.configuration.Configuration;

@RestController
public class LimitController {
	
	private Configuration configuration;
	
	@Autowired
	public LimitController(Configuration configuration) {
		this.configuration = configuration;
	}

	@GetMapping("/limits")
	public Limit retrieveLimits() {
		return new Limit(configuration.getMinimum(),configuration.getMaximum());
	}

}
