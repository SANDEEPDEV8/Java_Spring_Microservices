package com.skan.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;

//    @Autowired
//    private ExchangeValueRepository repository;

//    http://localhost:8000/currency-exchange/from/USD/to/INR

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue
            (@PathVariable String from, @PathVariable String to){

        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
        String port = environment.getProperty("local.server.port");

        currencyExchange.setEnvironment(port);
        return currencyExchange;
//        ExchangeValue exchangeValue =
//                repository.findByFromAndTo(from, to);
//
//        exchangeValue.setPort(
//                Integer.parseInt(environment.getProperty("local.server.port")));
//
//        logger.info("{}", exchangeValue);
//
//        return exchangeValue;
      }
}