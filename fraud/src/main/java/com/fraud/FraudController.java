package com.fraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraud-check")
@Slf4j
public class FraudController {
    @Autowired
    FraudCheckService fraudCheckService;

    @GetMapping(path= "{customerId}" )
    public FraudCheckResponse isFraud(@PathVariable("customerId") Integer customerId) {
        Boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("fraud check request for customer " + customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("ok");
    }
}
