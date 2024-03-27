package com.demo.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    CustomerService customerService;

    public void createCustomer(@RequestBody  CustomerRegistrationRequest customerRegister){
        log.info("new customer created {}", customerRegister);
    }


}
