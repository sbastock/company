package com.stocksba.company.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stocksba-stock")
public interface StockClient {
	
	@RequestMapping(value = "/stock/api/v1/query", method = RequestMethod.GET)
	ResponseEntity<Boolean> getCompany(@RequestParam("code") String code);

}
