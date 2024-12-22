package com.learnings.microservices.currency_exchange_service.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learnings.microservices.currency_exchange_service.bean.CurrencyExchange;
import com.learnings.microservices.currency_exchange_service.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	public CurrencyExchangeController(CurrencyExchangeRepository repository, Environment environment) {
		super();
		this.repository = repository;
		this.environment = environment;
	}

	private CurrencyExchangeRepository repository;
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(
			@PathVariable String from ,
			@PathVariable String to) {
		CurrencyExchange currencyExchange= repository.findByFromAndTo(from, to);
		if(currencyExchange == null) {
			throw new RuntimeException("Unable to find data for "+ from+ " to "+to);
		}
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}

}
