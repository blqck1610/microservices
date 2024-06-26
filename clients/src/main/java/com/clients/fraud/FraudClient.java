package com.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "FRAUD", path = "api/v1/fraud-check")
public interface FraudClient {
    @GetMapping(path = "{customerId}")
    FraudCheckResponse isFraud(@PathVariable("customerId") Integer customerId);
}

